package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.DTOBoard;

import java.util.List;

public interface BoardService {
    public Long registerBoard(DTOBoard board, List<String> tagNameList);
    public Long editBoard(DTOBoard board);
    public DTOBoard getBoardDetail(Long index);
    public boolean deleteBoard(long index);
    public List<DTOBoard> getBoardListOrderByDate(DTOBoard board);
    public List<DTOBoard> getBoardListOrderByNeedAnswer(DTOBoard board);
    List<DTOBoard> getBoardListOrderByPopular(DTOBoard board);
    public Integer setBoardView(Long index);

}
