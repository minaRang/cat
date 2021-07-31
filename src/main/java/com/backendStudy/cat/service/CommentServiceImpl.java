package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.DTOComment;
import com.backendStudy.cat.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Long registerComment(DTOComment comment) { //댓글 등록
        long queryResult = 0;

        queryResult = commentMapper.insertComment(comment);
        return queryResult;
    }

    @Override
    public Long editComment(DTOComment comment) { //댓글 수정
        long queryResult = 0;

        queryResult = commentMapper.updateComment(comment);
        return queryResult;
    }

    @Override
    public boolean deleteComment(long index) { //댓글 삭제
        DTOComment comment = commentMapper.selectCommentIdx(index);

        if(comment != null && comment.getCommentExist() == 1){ //댓글이 존재하면
            commentMapper.deleteComment(index);
            return true;
        }
        else return false;
    }

    @Override
    public List<DTOComment> getCommentList() { //댓글 리스트 반환
        Long totalComment = commentMapper.selectCountComment(); //댓글 개수

        if (totalComment > 0){ //댓글이 존재하면
            List<DTOComment> commentList = commentMapper.selectCommentList();
            return commentList;
        }
        else return null; //댓글이 존재하지 않으면
    }

}
