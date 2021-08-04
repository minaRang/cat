package com.backendStudy.cat.mapper;

import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.domain.DTOTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TagMapper {
    public int insertTag(DTOTag tag);
    public Optional<DTOTag> findByTagIdx(Long index);
    public List<DTOBoard> findBoardByTagName(@Param("board") DTOBoard board, @Param("tagName") String tagName);
    public List<DTOBoard> findBoardByTagNamePopular(@Param("board") DTOBoard board, @Param("tagName") String tagName);
    public List<DTOTag> findByBoardIdx(Long boardIdx);
    public List<DTOTag> findAllMatchingTag(String tag); //태그 자동 완성 기능 구현시 사용
    public List<DTOTag> findAllTagName(DTOTag tag); //모든 태그 이름을 찾아 반환
    public Optional<Integer> findTotalRegister(DTOTag tag); //태그 이름을 등록한 user 수 반환
    public Optional<Integer> findByTagNameTotalCount(DTOTag board); //해당 태그를 사용한 게시물의 수 반환
    public Optional<Integer> findAllTagCount(DTOTag board); // 등록된 태그의 수 반환
    public int updateTag(DTOTag tag);
    public int deleteTag(Long index);
}
