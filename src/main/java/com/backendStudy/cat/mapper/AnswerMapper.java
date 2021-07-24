package com.backendStudy.cat.mapper;

import com.backendStudy.cat.domain.DTOAnswer;
import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.domain.DTOComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AnswerMapper {
    public int insertAnswer(DTOAnswer answer);
    public Optional<DTOAnswer> findByAnswerIdx(Long answerIdx);
    public List<DTOAnswer> findByBoardIdx(Long boardIdx);
    public List<DTOAnswer> getAllAnswer();
    public int updateAnswer(DTOAnswer answer);
    public int deleteAnswer(Long index);
    public Long selectCountAnswer();

}
