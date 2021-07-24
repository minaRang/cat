package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.DTOComment;
import com.backendStudy.cat.mapper.CommentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    private CommentMapper commentMapper;

    @Override
    public Long registerComment(DTOComment comment) { //댓글 등록
        commentMapper.insertComment(comment);
        return comment.getCommentIdx();
    }

    @Override
    public Long editComment(DTOComment comment) { //댓글 수정
        commentMapper.updateComment(comment);
        return comment.getCommentIdx();
    }

    @Override
    public void deleteComment(long index) { //댓글 삭제
        commentMapper.deleteComment(index);
    }

    @Override
    public List<DTOComment> getCommentList() { //댓글 리스트 반환
        Long totalComment = commentMapper.selectCountComment();
        if (totalComment > 0){ //댓글이 존재하면
            List<DTOComment> commentList = commentMapper.getAllComment();
            return commentList;
        }
        else return null; //댓글 존재하지 않으면
    }

}
