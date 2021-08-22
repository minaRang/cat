package com.backendStudy.cat.mapper;

import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.domain.DTOTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SearchMapper {
    public List<DTOBoard> searchBoard(DTOBoard board); //게시판 검색
    public Optional<Integer> totalSearchBoard(DTOBoard board); // 검색 결과 게시물의 수
    public List<DTOTag> searchTag(DTOTag tag); //태그 검색
    public Optional<Integer> totalSearchTag(DTOTag tag); //검색 결과 태그의 수
}
