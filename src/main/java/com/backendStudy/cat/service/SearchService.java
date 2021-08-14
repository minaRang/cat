package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.domain.DTOTag;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SearchService {

    public List<DTOBoard> searchBoardList(DTOBoard board);
    List<DTOTag> searchTagList(DTOTag board);
}
