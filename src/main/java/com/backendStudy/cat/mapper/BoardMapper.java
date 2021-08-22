package com.backendStudy.cat.mapper;

import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.domain.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    public int insertBoard(DTOBoard board); //게시판 작성
    public Optional<DTOBoard> findByBoardIdx(Long index);//게시판 index로 찾기
    public int updateBoard(DTOBoard board); //게시판 업데이트
    public int deleteBoard(Long index); //게시판 삭제
    public List<DTOBoard> findAllBoardOrderByDate(DTOBoard board); //모든 게시판 반환(날짜순)
    public List<DTOBoard> findAllBoardOrderByPopular(DTOBoard board); //모든 게시판 반환(인기순)
    public List<DTOBoard> findAllNeedAnswer(DTOBoard board);//모든 게시판 반환(답변수가 없는 게시판)
    public Optional<Integer> selectBoardTotalCount(DTOBoard board); // 모든 게시판의 수
    public int updateBoardView(Long index); //게시판 조회수 갱신
    public int selectAnswerCount(DTOBoard board); //게시판의 답변수 반환
    public Optional<Integer> selectAnswerIsAdopted(DTOBoard board); // 게시판의 채택된 답변이 있는지 반환
}
