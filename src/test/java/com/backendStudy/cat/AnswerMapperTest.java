package com.backendStudy.cat;

import com.backendStudy.cat.domain.DTOAnswer;
import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.mapper.AnswerMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class AnswerMapperTest {
    @Autowired
    private AnswerMapper answerMapper;

    @Test
    public void testInsert(){
        DTOAnswer answer = new DTOAnswer();
        answer.setAnswerContent("반가워요");
        answer.setBoardIdx(1);
        answer.setUserIdx(Long.valueOf(1));

        answerMapper.insertAnswer(answer);
        DTOAnswer result= answerMapper.findByAnswerIdx(answer.getAnswerIdx()).get();
        assertThat(result.getAnswerIdx()).isEqualTo(answer.getAnswerIdx());
    }

    @Test
    public void testUpdate(){

        DTOAnswer answer = new DTOAnswer();
        answer.setAnswerContent("반가워요");
        answer.setUserIdx(Long.valueOf(1));
        answer.setBoardIdx(1);
        answerMapper.insertAnswer(answer);
        log.info("before update answer index {}",answer.getAnswerIdx());
        log.info("board Idx : {}",answer.getBoardIdx());
        log.info("content : {}",answer.getAnswerContent());
        log.info("user : index={}",answer.getUserIdx());

        DTOAnswer answer2 = new DTOAnswer();
        answer2.setAnswerContent("안녕하세요");
        answer2.setUserIdx(Long.valueOf(2));
        answer2.setAnswerIdx((long) answer.getAnswerIdx());

        int cnt =answerMapper.updateAnswer(answer2);

        DTOAnswer result=answerMapper.findByAnswerIdx(answer.getAnswerIdx()).get();
        log.info("after update answer index : {}",result.getAnswerIdx());
        log.info("board Idx : {}",result.getBoardIdx());
        log.info("content : {}",result.getAnswerContent());
        log.info("user : index={}",result.getUserIdx());

        assertThat(result.getAnswerContent()).isEqualTo(answer2.getAnswerContent());
        assertThat(cnt).isEqualTo(1);
        assertThat(result.getBoardIdx()).isEqualTo(answer.getBoardIdx());
    }

    @Test
    public void testDelete(){
        DTOAnswer answer = new DTOAnswer();
        answer.setAnswerContent("hi");
        answer.setUserIdx(Long.valueOf(1));
        answer.setBoardIdx(2);
        answerMapper.insertAnswer(answer);

        int result = answerMapper.deleteAnswer(answer.getAnswerIdx());
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void testFindByBoardIdx(){
        List<DTOAnswer> answerList = answerMapper.findByBoardIdx(Long.valueOf(1));
        log.info("total={}", answerList.size());
        for (DTOAnswer answer :answerList) {
            log.info("boardIdx : {}", answer.getBoardIdx());
            log.info("content: {}", answer.getAnswerContent());
        }

    }
}
