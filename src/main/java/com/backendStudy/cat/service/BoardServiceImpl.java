package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.mapper.BoardMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{
    @Autowired
    private BoardMapper boardMapper;

    @Override
    public Long registerBoard(DTOBoard board) {
            boardMapper.insertBoard(board);
            return board.getBoardIdx();
    }
    @Override
    public Long editBoard(DTOBoard board) {
        boardMapper.updateBoard(board);
        return board.getBoardIdx();
    }

    @Override
    public DTOBoard getBoardDetail(Long index) {
        return boardMapper.findByBoardIdx(index).orElseGet(
                ()->{throw new IllegalStateException("존재하지 않는 게시글 입니다");
        });
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
    public List<DTOBoard> getBoardList() {
        Long totalBoard = boardMapper.selectBoardTotalCount();
        if (totalBoard>0){
            List<DTOBoard> boardList = boardMapper.findAllBoard();
            return boardList;
        }
        else return null;
    }

    @Override
    public Integer setBoardView(Long index) {
        return boardMapper.updateBoardView(index);
    }
}
