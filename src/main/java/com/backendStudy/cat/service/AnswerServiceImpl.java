package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.DTOAnswer;
import com.backendStudy.cat.mapper.AnswerMapper;

import java.util.List;

public class AnswerServiceImpl implements AnswerService {
    private AnswerMapper answerMapper;

    @Override
    public Long registerAnswer(DTOAnswer answer) { //답변 등록
        answerMapper.insertAnswer(answer);
        return answer.getAnswerIdx();
    }

    @Override
    public Long editAnswer(DTOAnswer answer) { //답변 수정
        answerMapper.updateAnswer(answer);
        return answer.getAnswerIdx();
    }

    @Override
    public void deleteAnswer(long index) { //답변 삭제
        answerMapper.deleteAnswer(index);
    }

    @Override
    public List<DTOAnswer> getAnswerList() { //답변 리스트 반환
        Long totalAnswer = answerMapper.selectCountAnswer(); //답변 개수
        if(totalAnswer > 0){ //답변이 존재하면
            List<DTOAnswer> answerList = answerMapper.getAllAnswer();
            return answerList;
        }
        else return null;
    }
}
