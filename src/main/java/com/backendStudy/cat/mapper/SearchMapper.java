package com.backendStudy.cat.mapper;

import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.domain.DTOTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SearchMapper {
    public List<DTOBoard> searchBoard(DTOBoard board);
    public Optional<Integer> totalSearchBoard(DTOBoard board);
    public List<DTOTag> searchTag(DTOTag tag);
    public Optional<Integer> totalSearchTag(DTOTag tag);
}
