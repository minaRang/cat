package com.backendStudy.cat;

import com.backendStudy.cat.domain.DTOComment;
import com.backendStudy.cat.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class) //Autowired NPE 해결
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Test
    public void registerComments() {
        int number = 5;

        //테스트 댓글 5개 추가
        for (int i = 1; i <= number; i++) {
            DTOComment comment = new DTOComment();
            comment.setBoardIdx((long) 1); // 댓글을 추가할 게시글 번호
            comment.setCommentContent("test 댓글 입니다.");
            comment.setUserIdx(i);
            commentService.registerComment(comment);
        }

        log.info("댓글 등록 테스트: " + number + "개가 등록되었습니다.");
    }

    @Test
    public void editComment(){
        DTOComment comment = new DTOComment();
        comment.setCommentContent("댓글 수정 test");
        comment.setCommentIdx(1); //수정할 댓글을 1번 댓글로 지정

        if(commentService.editComment(comment) == true){
            log.info("댓글 수정 테스트 성공");
        } else {
            log.info("댓글 수정 테스트 실패");
        }

    }

    @Test
    public void deleteComment() {
        if(commentService.deleteComment((long) 1) == true){ //삭제할 댓글을 1번 댓글로 지정
            log.info("댓글 삭제 테스트 성공");
        } else {
            log.info("댓글 삭제 테스트 실패");
        }
    }

    @Test
    public void getCommentList() {
        DTOComment comment = new DTOComment();
        comment.setBoardIdx((long) 1); // 댓글을 추가할 게시글 번호
        commentService.getCommentList(comment);
        log.info("댓글 리스트 테스트 실행");
    }

}
