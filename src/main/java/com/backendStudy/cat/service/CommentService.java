package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.DTOComment;

import java.util.List;

public interface CommentService {

    public Long registerComment(DTOComment comment); //댓글 정보 등록
    public Long editComment(DTOComment comment); //댓글 정보 수정
    public void deleteComment(long index);//댓글 삭제
    public List<DTOComment> getCommentList();//댓글 리스트 조회
}
