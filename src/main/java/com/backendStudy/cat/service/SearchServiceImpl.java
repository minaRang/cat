package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.domain.DTOTag;
import com.backendStudy.cat.domain.paging.PageInfo;
import com.backendStudy.cat.mapper.BoardMapper;
import com.backendStudy.cat.mapper.SearchMapper;
import com.backendStudy.cat.mapper.TagMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.backendStudy.cat.service.BoardServiceImpl.CalDate;

@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private SearchMapper searchMapper;
    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<DTOBoard> searchBoardList(DTOBoard board) {
        int totalBoard = searchMapper.totalSearchBoard(board).orElseGet(()->{
            return 0;
        });

        PageInfo pageInfo = new PageInfo(board);
        pageInfo.SetTotalData(totalBoard);
        board.setPageInfo(pageInfo);
        if (totalBoard==0) return new ArrayList<>();

        List<DTOBoard> boardList = searchMapper.searchBoard(board);
        boardList.forEach(b->b.setTimeInterval(CalDate(b.getDate())));
        boardList.forEach(b->b.setTagList(tagMapper.findByBoardIdx(b.getBoardIdx())));
        boardList.forEach(b -> b.setCntBoardAnswer(boardMapper.selectAnswerCount(b)));
        boardList.forEach(b -> b.setAnswerIsAdopted(boardMapper.selectAnswerIsAdopted(b).orElseGet(() -> {
            return 0;
        })));
        return boardList;
    }

    @Override
    public List<DTOTag> searchTagList(DTOTag tag) {
        int totalTag = searchMapper.totalSearchTag(tag).orElseGet(()-> {
            return 0;
        });
        tag.setRecordsPerPage(12); // 일단 페이징 테스트를 위해 추후 수정 될 수 있음
        PageInfo pageInfo = new PageInfo(tag);
        pageInfo.SetTotalData(totalTag);
        tag.setPageInfo(pageInfo);
        if (totalTag==0) return new ArrayList<>();
        List<DTOTag> tagList = searchMapper.searchTag(tag);
        for (DTOTag t : tagList)
            log.info(t.getTagName());
        return tagList;
    }

}
