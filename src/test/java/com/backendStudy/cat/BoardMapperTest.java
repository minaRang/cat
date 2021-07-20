package com.backendStudy.cat;


import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.mapper.BoardMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import java.util.List;

//user table이 존재해야합니다!
@Slf4j
@SpringBootTest
public class BoardMapperTest {
    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void testInsert(){
        DTOBoard board = new DTOBoard();
        board.setBoardTitle("안녕하세요");
        board.setBoardContent("반가워요");
        board.setUserIdx(Long.valueOf(1));

        boardMapper.insertBoard(board);
        DTOBoard result= boardMapper.findByBoardIdx(board.getBoardIdx()).get();
        assertThat(result.getBoardIdx()).isEqualTo(board.getBoardIdx());
    }

    @Test
    public void testUpdate(){

        DTOBoard board = new DTOBoard();
        board.setBoardTitle("안녕하세요");
        board.setBoardContent("반가워요");
        board.setUserIdx(Long.valueOf(1));
        boardMapper.insertBoard(board);
        log.info("before update board index {}",board.getBoardIdx());
        log.info("title : {}",board.getBoardTitle());
        log.info("content : {}",board.getBoardContent());
        log.info("user : index={}",board.getUserIdx());

        DTOBoard board2 = new DTOBoard();
        board2.setBoardTitle("다시 만나요");
        board2.setBoardContent("안녕하세요");
        board2.setUserIdx(Long.valueOf(2));
        board2.setBoardIdx((long) board.getBoardIdx());

        int cnt =boardMapper.updateBoard(board2);

        DTOBoard result=boardMapper.findByBoardIdx(board.getBoardIdx()).get();
        log.info("after update index : {}",result.getBoardIdx());
        log.info("title : {}",result.getBoardTitle());
        log.info("content : {}",result.getBoardContent());
        log.info("user : index={}",result.getUserIdx());

        assertThat(result.getBoardTitle()).isEqualTo(board2.getBoardTitle());

        assertThat(cnt).isEqualTo(1);
    }

    @Test
    public void testDelete(){
        DTOBoard board = new DTOBoard();
        board.setBoardTitle("1");
        board.setBoardContent("hi");
        board.setUserIdx(Long.valueOf(1));
        boardMapper.insertBoard(board);

        int result = boardMapper.deleteBoard(board.getBoardIdx());
        assertThat(result).isEqualTo(1);
    }
    @Test
    public void testSelectALL(){
        Long total = boardMapper.selectBoardTotalCount();
        if(total>0){
            List<DTOBoard> boardList=boardMapper.findAllBoard();
            log.info("total={}",boardList.size());
            for (DTOBoard board : boardList){
                log.info("title : {}",board.getBoardTitle());
                log.info("content: {}",board.getBoardContent());
                if (board.getUserName() != null)
                    log.info("user: {}",board.getUserName());
                log.info("fond : {}",board.getFondScore());
            }
        }
    }
    @Test
    public void testUpdateBoardView(){
        DTOBoard board = new DTOBoard();
        board.setBoardTitle("안녕하세요");
        board.setBoardContent("반가워요");
        board.setUserIdx(Long.valueOf(1));

        int cnt=boardMapper.insertBoard(board);
        log.info("before update View: {}", board.getBoardView());


        boardMapper.updateBoardView((long) board.getBoardIdx());
        assertThat(cnt).isEqualTo(1);

    }

}
