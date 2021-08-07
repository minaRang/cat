package com.backendStudy.cat;

import com.backendStudy.cat.domain.DTOComment;
import com.backendStudy.cat.mapper.CommentMapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.CollectionUtils;
import java.util.List;

@Slf4j
@SpringBootTest
@ActiveProfiles("dev")
public class CommentMapperTest {

    @Autowired
    private CommentMapper commentMapper;

    @Test
    public void insertTest(){ //댓글 정보
        DTOComment comment = new DTOComment();
        comment.setUserIdx(1);
        comment.setBoardIdx(1);
        comment.setAnswerIdx(1);
        comment.setCommentContent("댓글 test");

        log.info("댓글 test: "+ commentMapper.insertComment(comment));

    }

    @Test
    public void selectCountTest(){ //댓글 개수
        DTOComment comment = new DTOComment();
        comment.setCommentContent("댓글 test");
        commentMapper.insertComment(comment);
        Long totalComment = commentMapper.selectCountComment();

        log.info("댓글 개수: "+ totalComment);
    }

    @Test
    public void updateTest(){ //댓글 수정
        DTOComment comment = new DTOComment();
        comment.setCommentContent("댓글 수정 test");
        comment.setUserIdx(1);
        comment.setCommentIdx(1); //수정할 내용을 1번 댓글로 지정
        int result = commentMapper.updateComment(comment);

        if(result == 1){
            log.info("댓글 수정 test 성공");
        } else {
            log.info("댓글 수정 test 실패");
        }

    }

    @Test
    public void deleteTest(){ //댓글 삭제 (data 1개 이상 필요)
        int result = commentMapper.deleteComment((long)1); //1번 인덱스 존재여부를 0으로 변경

        if(result == 1){
            log.info("댓글 삭제 test 성공");
        } else {
            log.info("댓글 삭제 test 실패");
        }

    }

    @Test
    public void getAllCommentTest(){ //모든 댓글 리스트
        long totalComment = commentMapper.selectCountComment();
        if (totalComment > 0){
            List<DTOComment> commentList = commentMapper.selectCommentList();
            if(CollectionUtils.isEmpty(commentList) == false){
                for(DTOComment comment : commentList){
                    log.info("댓글 내용: "+ comment.getCommentContent());
                    log.info("댓글 작성자 인덱스: "+ comment.getUserIdx());
                    log.info("========================");
                }
            }
        }
    }

    /*
    @Test
    public void selectIdxTest(){ //댓글 인덱스

    }*/

}
