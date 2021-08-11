package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.domain.DTOTag;
import com.backendStudy.cat.domain.paging.PageInfo;
import com.backendStudy.cat.domain.tagInfo.TagInfo;
import com.backendStudy.cat.mapper.BoardMapper;
import com.backendStudy.cat.mapper.TagMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.backendStudy.cat.service.BoardServiceImpl.CalDate;

@Service
@Transactional
@Slf4j
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;
    @Autowired
    BoardMapper boardMapper;

    @Override
    public List<DTOTag> autoMatchingTag(String tagName) {
        List<DTOTag> dtoTags =tagMapper.findAllMatchingTag(tagName);
        DTOTag tag = new DTOTag();
        tag.setTagName(tagName);
        if (dtoTags.isEmpty()) {
            dtoTags.add(tag);
            return dtoTags;
        }
        for (DTOTag t : dtoTags) {
            if (!t.getTagName().equalsIgnoreCase(tagName)) {
                dtoTags.add(0, tag);
                break;
            }
        }
        return dtoTags;
    }

    @Override
    public List<DTOBoard> findBoardByTagName(DTOBoard board, String tagName, String tab) {
        DTOTag tag = new DTOTag();
        tag.setTagName(tagName);
        int totalBoard = tagMapper.findByTagNameTotalCount(tag).orElseGet(()->{return 0;});

        PageInfo pageInfo = new PageInfo(board);
        pageInfo.SetTotalData(totalBoard);
        board.setPageInfo(pageInfo);
        if (totalBoard==0)return new ArrayList<>();
        List<DTOBoard> boardList = new ArrayList<>();
        if (tab.equalsIgnoreCase("home")|| tab.equalsIgnoreCase("needAnswer"))
            boardList = tagMapper.findBoardByTagName(board, tagName);
        else if (tab.equalsIgnoreCase("popular"))
            boardList = tagMapper.findBoardByTagNamePopular(board, tagName);
        boardList.forEach(b -> b.setTimeInterval(CalDate(b.getDate())));
        boardList.forEach(b -> b.setTagList(tagMapper.findByBoardIdx(b.getBoardIdx())));
        boardList.forEach(b -> b.setCntBoardAnswer(boardMapper.selectAnswerCount(b)));
        boardList.forEach(b -> b.setAnswerIsAdopted(boardMapper.selectAnswerIsAdopted(b).orElseGet(() -> {
            return 0;
        })));

        if(tab.equalsIgnoreCase("needAnswer"))
            boardList.removeIf(b->b.getCntBoardAnswer()>0);
        return boardList;
    }
    @Override
    public List<DTOTag> findAllTagList(DTOTag tag){
        int totalTag = tagMapper.findAllTagCount(tag).orElseGet(()-> {
            return 0;
        });

        tag.setRecordsPerPage(12); // 일단 페이징 테스트를 위해 추후 수정 될 수 있음
        PageInfo pageInfo = new PageInfo(tag);
        pageInfo.SetTotalData(totalTag);
        tag.setPageInfo(pageInfo);
        if (totalTag==0) return new ArrayList<>();
        List<DTOTag> tagList = tagMapper.findAllTagName(tag);
        return tagList;
    }

    @Override
    public int findAllTagCount(DTOTag tag){
        return tagMapper.findAllTagCount(tag).orElseGet(()->{return 0;});
    }

    @Override
    public Long registerTag(DTOTag tag) {
        tagMapper.insertTag(tag);
        return tag.getTagIdx();
    }

    @Override
    public TagInfo findTagInfo(DTOTag tag){
        TagInfo tagInfo = new TagInfo();
        tagInfo.setTotalRegister(tagMapper.findTotalRegister(tag).orElseGet(()->{return 0;}));
        tagInfo.setTotalQuestions(tagMapper.findByTagNameTotalCount(tag).orElseGet(()->{return 0;}));
        return tagInfo;
    }
}
