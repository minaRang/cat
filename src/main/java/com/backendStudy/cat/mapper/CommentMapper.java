package com.backendStudy.cat.mapper;

import com.backendStudy.cat.domain.DTOComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    public Long selectCountComment();
    public Long insertComment(DTOComment comment);
    public List<DTOComment> selectCommentIdx(Long index);
    public List<DTOComment> getAllComment();
    public Long updateComment(DTOComment comment);
    public Long deleteComment(Long index);


}
