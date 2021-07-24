package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.DTOAnswer;

import java.util.List;

public interface AnswerService {
    public Long registerAnswer(DTOAnswer answer); //답변 등록 메소드
    public Long editAnswer(DTOAnswer answer); //답변 수정 메소드
    public void deleteAnswer(long index); //답변 삭제 메소드
    public List<DTOAnswer> getAnswerList();//답변 리스트 메소드

}
