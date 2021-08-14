package com.backendStudy.cat.mapper;

import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.domain.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    public int insertBoard(DTOBoard board);
    public Optional<DTOBoard> findByBoardIdx(Long index);
    public int updateBoard(DTOBoard board);
    public int deleteBoard(Long index);
    public List<DTOBoard> findAllBoardOrderByDate(DTOBoard board);
    public List<DTOBoard> findAllBoardOrderByPopular(DTOBoard board);
    public List<DTOBoard> findAllNeedAnswer(DTOBoard board);
    public Optional<Integer> selectBoardTotalCount(DTOBoard board);
    public int updateBoardView(Long index);
    public int selectAnswerCount(DTOBoard board);
    public Optional<Integer> selectAnswerIsAdopted(DTOBoard board);
}
