package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.DTOComment;
import com.backendStudy.cat.mapper.CommentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public boolean registerComment(DTOComment comment) { //댓글 등록
        int queryResult = 0;

        queryResult = commentMapper.insertComment(comment);
        return (queryResult == 1) ? true : false;
    }

    @Override
    public boolean editComment(DTOComment comment) { //댓글 수정
        int queryResult = 0;

        queryResult = commentMapper.updateComment(comment);
        return (queryResult == 1) ? true : false;
    }

    @Override
    public boolean deleteComment(long index) { //댓글 삭제
        int queryResult = 0;
        DTOComment comment = commentMapper.selectCommentIdx(index);

        if(comment != null && comment.getCommentExist() == 1){ //댓글이 존재하면
            queryResult = commentMapper.deleteComment(index);
        }
        return (queryResult == 1) ? true : false;
    }

    @Override
    public List<DTOComment> getCommentList(DTOComment comment) { //댓글 리스트 반환
        List<DTOComment> commentList = Collections.emptyList();
        long totalComment = commentMapper.selectCountComment(comment); //댓글 개수

        if (totalComment > 0){ //댓글이 존재하면
            commentList = commentMapper.selectCommentList(comment);
        }
        return commentList;
    }

}
