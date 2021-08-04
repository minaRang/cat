package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.domain.DTOTag;
import com.backendStudy.cat.domain.paging.PageInfo;
import com.backendStudy.cat.mapper.BoardMapper;
import com.backendStudy.cat.mapper.TagMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional
public class BoardServiceImpl implements BoardService{
    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Long registerBoard(DTOBoard board, List<String> tagNameList) {
        boardMapper.insertBoard(board);
        DTOTag dtoTag = new DTOTag();

        //TODO : 로그인한 계정의 idx 값 저장으로 수정
        for (String tagName:tagNameList) {
            dtoTag.setUserIdx(Long.valueOf(2));
            dtoTag.setBoardIdx(board.getBoardIdx());
            dtoTag.setTagName(tagName);
            tagMapper.insertTag(dtoTag);
        }

        //TODO: file 구현
        //TODO: 게시글 작성시 작성자를 편집 히스토리에 추가
            return board.getBoardIdx();
    }
    @Override
    public Long editBoard(DTOBoard board) {
        boardMapper.updateBoard(board);
        return board.getBoardIdx();
    }

    @Override
    public DTOBoard getBoardDetail(Long index) {
        DTOBoard board= boardMapper.findByBoardIdx(index).orElseGet(
                ()->{throw new IllegalStateException("존재하지 않는 게시글 입니다");
        });
        board.setTimeInterval(CalDate(board.getDate()));
        board.setTagList(tagMapper.findByBoardIdx(board.getBoardIdx()));
        return board;
    }

    @Override
    public boolean deleteBoard(long index) {
        if(boardMapper.findByBoardIdx(index).isPresent()){
            boardMapper.deleteBoard(index);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<DTOBoard> getBoardList(DTOBoard board, String tab) {
        int totalBoard = boardMapper.selectBoardTotalCount(board);
        PageInfo pageInfo = new PageInfo(board);
        pageInfo.SetTotalData(totalBoard);
        board.setPageInfo(pageInfo);
        if (totalBoard>0){
            List<DTOBoard> boardList = new ArrayList<>();
            if (tab.equalsIgnoreCase("home"))
                boardList = boardMapper.findAllBoardOrderByDate(board);
            else if (tab.equalsIgnoreCase("popular"))
                boardList = boardMapper.findAllBoardOrderByPopular(board);
            else if (tab.equalsIgnoreCase("needAnswer"))
                boardList=boardMapper.findAllNeedAnswer(board);
            boardList.forEach(b->b.setTimeInterval(CalDate(b.getDate())));
            boardList.forEach(b->b.setTagList(tagMapper.findByBoardIdx(b.getBoardIdx())));
            return boardList;
        }
        else return null;
    }

    @Override
    public Integer setBoardView(Long index) {
        return boardMapper.updateBoardView(index);
    }

    //날짜 계산 함수
    public static String CalDate(Date date) {
        Date now = new Date();
        Long calDate = now.getTime() - date.getTime();

        Long calTime = calDate / (60 * 1000);

        if (calTime <= 60) return calTime + "분 전";
        else if (calTime <= 60*24) return calTime/60+ "시간 전";
        else if(calTime<=60*24*30) return calTime/(60*24)+"일 전";
        else if (calTime<=60*24*30*12) return calTime/(60*24*30) +"달 전";
        else return calTime/(60*24*30*12) +"년 전";
    }
}
