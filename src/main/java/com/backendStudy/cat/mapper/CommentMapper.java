package com.backendStudy.cat.mapper;

import com.backendStudy.cat.domain.DTOComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    public int insertComment(DTOComment comment); //댓글 생성 쿼리
    public DTOComment selectCommentIdx(Long index); //댓글 인덱스 쿼리
    public Long selectCountComment(); //댓글 개수 쿼리
    public List<DTOComment> selectCommentList(); //댓글 리스트 쿼리
    public int updateComment(DTOComment comment); //댓글 수정 쿼리
    public int deleteComment(Long index); //댓글 삭제 쿼리


}
