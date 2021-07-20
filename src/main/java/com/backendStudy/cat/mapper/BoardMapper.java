package com.backendStudy.cat.mapper;

import com.backendStudy.cat.domain.DTOBoard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    public int insertBoard(DTOBoard board);
    public Optional<DTOBoard> findByBoardIdx(Long index);
    public int updateBoard(DTOBoard board);
    public int deleteBoard(Long index);
    public List<DTOBoard> findAllBoard();
    public Long selectBoardTotalCount();
    public int updateBoardView(Long index);
}
