package com.backendStudy.cat;

import com.backendStudy.cat.domain.DTOFond;
import com.backendStudy.cat.mapper.FondMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
class FondMapperTest {
    @Autowired
    FondMapper fondMapper;
    @Test
    void testInsertFondBoard() {
        DTOFond fond = new DTOFond();
        fond.setBoardIdx(1L);
        fond.setUserIdx(3L);
        fond.setFondScore(0);
        DTOFond result= fondMapper.findBoardFond(fond).orElseGet(() -> {
            fondMapper.insertFondBoard(fond);
            return fond;
        });
        assertThat(fond.getFondIdx()).isEqualTo(result.getFondIdx());

    }

    @Test
    void updateFondBoard() {
        DTOFond fond = new DTOFond();
        fond.setBoardIdx(1L);
        fond.setUserIdx(3L);
        fond.setFondScore(-1);
        fond = fondMapper.findBoardFond(fond).orElseGet(()-> {
            return new DTOFond();
        });
        fondMapper.updateFond(fond);
        DTOFond result = fondMapper.findBoardFond(fond).get();
        assertThat(-1).isEqualTo(fond.getFondScore());
    }

    @Test
    void testFindByBoardIdx() {
        DTOFond fond = new DTOFond();
        fond.setBoardIdx(1L);
        fond.setUserIdx(4L);
        fond.setFondScore(3);
        if(fondMapper.findBoardFond(fond).isPresent()){
            fond = fondMapper.findBoardFond(fond).orElseGet(()-> {
                return new DTOFond();
            });
            fondMapper.updateFond(fond);
        }
        else{
            fondMapper.insertFondBoard(fond);
        }
        Integer result = fondMapper.findByBoardIdx(fond.getBoardIdx()).get();
        assertThat(2).isEqualTo(result);
    }

    @Test
    void insertFondAnswer() {
        DTOFond fond = new DTOFond();
        fond.setAnswerIdx(1L);
        fond.setUserIdx(3L);
        fond.setFondScore(0);
        DTOFond result= fondMapper.findAnswerFond(fond).orElseGet(() -> {
            fondMapper.insertFondAnswer(fond);
            return fond;
        });
        assertThat(fond.getFondIdx()).isEqualTo(result.getFondIdx());
    }

    @Test
    void updateFondAnswer() {
        DTOFond fond = new DTOFond();
        fond.setAnswerIdx(1L);
        fond.setUserIdx(3L);
        fond.setFondScore(-1);
        fond = fondMapper.findAnswerFond(fond).orElseGet(()-> {
            return new DTOFond();
        });
        fondMapper.updateFond(fond);
        DTOFond result = fondMapper.findAnswerFond(fond).get();
        assertThat(-1).isEqualTo(fond.getFondScore());
    }

    @Test
    void findByAnswerIdx() {
        DTOFond fond = new DTOFond();
        fond.setAnswerIdx(1L);
        fond.setUserIdx(4L);
        fond.setFondScore(3);
        if(fondMapper.findAnswerFond(fond).isPresent()){
            fond = fondMapper.findAnswerFond(fond).orElseGet(()-> {
                return new DTOFond();
            });
            fondMapper.updateFond(fond);
        }
        else{
            fondMapper.insertFondAnswer(fond);
        }
        Integer result = fondMapper.findByAnswerIdx(fond.getAnswerIdx()).get();
        assertThat(2).isEqualTo(result);
    }
}