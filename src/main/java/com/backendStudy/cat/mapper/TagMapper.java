package com.backendStudy.cat.mapper;

import com.backendStudy.cat.domain.DTOTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TagMapper {
    public int insertTag(DTOTag tag);
    public Optional<DTOTag> findByTagIdx(Long index);
    public List<DTOTag> findByTagName(String name);
    public List<DTOTag> findByBoardIdx(Long boardIdx);
    public int updateTag(DTOTag tag);
    public int deleteTag(Long index);
    public List<DTOTag> findAllMatchingTag(String tag);
    public int findTagName(String tag);
}
