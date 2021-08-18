package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.DTOComment;

import java.util.List;

public interface CommentService {

    public boolean registerComment(DTOComment comment); //댓글 등록
    public boolean editComment(DTOComment comment); //댓글 수정
    public boolean deleteComment(long index); //댓글 삭제
    public List<DTOComment> getCommentList(DTOComment comment); //댓글 리스트 조회
}
