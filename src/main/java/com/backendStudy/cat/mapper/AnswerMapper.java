package com.backendStudy.cat.mapper;

import com.backendStudy.cat.domain.DTOAnswer;
import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.domain.DTOComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AnswerMapper {
    public int insertAnswer(DTOAnswer answer); //답변 추가 쿼리
    public Optional<DTOAnswer> findByAnswerIdx(Long answerIdx); //답변글 인덱스 쿼리
    public List<DTOAnswer> findByBoardIdx(Long boardIdx); //질문글 인덱스 쿼리
    public List<DTOAnswer> getAllAnswer(); //답변 리스트 쿼리
    public int updateAnswer(DTOAnswer answer); //답변 수정 쿼리
    public int deleteAnswer(Long index); //답변 삭제 쿼리
    public Long selectCountAnswer(); //답변 개수 쿼리

}
