package com.backendStudy.cat.mapper;

import com.backendStudy.cat.domain.DTOFond;
import org.apache.ibatis.annotations.Mapper;

import javax.swing.text.html.Option;
import java.util.Optional;

@Mapper
public interface FondMapper {
    public int insertFondBoard(DTOFond fond); //게시판 좋아요 삽입
    public int updateFond(DTOFond foundIdx); //좋아요 수정
    public Optional<DTOFond> findBoardFond(DTOFond found); //유저가 설정한 게시판의 좋아요 값 반환
    public Optional<Integer> findByBoardIdx(Long boardIdx); // 게시판의 총 좋아요 수

    public int insertFondAnswer(DTOFond fond); //답변 좋아요 삽입
    public Optional<DTOFond> findAnswerFond(DTOFond found); //유저가 설정한 답변의 좋아요 값 반환
    public Optional<Integer> findByAnswerIdx(Long answerIdx); //게시판의 총 좋아요 수 반환
}
