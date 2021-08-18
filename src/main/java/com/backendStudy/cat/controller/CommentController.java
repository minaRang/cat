package com.backendStudy.cat.controller;

import com.backendStudy.cat.adapter.GsonLocalDateTimeAdapter;
import com.backendStudy.cat.domain.DTOComment;
import com.backendStudy.cat.service.CommentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController //화면이 아닌 데이터 자체를 리턴
public class CommentController {

    @Autowired
    private CommentService commentService;

    /* 댓글 입력
    @RequestMapping(value = { "/comments", "/comments/{idx}" }, method = { RequestMethod.POST, RequestMethod.PATCH })
    public JsonObject registerComment(@PathVariable(value = "idx", required = false) Long idx, @RequestBody final CommentDTO params) {

        JsonObject jsonObj = new JsonObject();

        try {
            if (idx != null) {
                params.setIdx(idx);
            }

            boolean isRegistered = commentService.registerComment(params);
            jsonObj.addProperty("result", isRegistered);

        } catch (DataAccessException e) {
            jsonObj.addProperty("message", "DB 처리 과정에 오류가 발생하였습니다.");

        } catch (Exception e) {
            jsonObj.addProperty("message", "시스템 오류가 발생하였습니다.");
        }

        return jsonObj;
    }

*/

    //질문글 댓글 출력
    @GetMapping("/board/{boardIdx}/comments") //TODO: URI를 어떻게 정해야할지 모르겠다..ㅜ.ㅜ
    public JsonObject getCommentList(@PathVariable("boardIdx") Long boardIdx, //REST 방식에서 리소스 표현, URI에 파라미터로 전달받을 변수를 지정
                                     @ModelAttribute("comment") DTOComment comment) { //매개변수로 전달받은 객체를 뷰로 전달 (페이징 처리 시에도 사용)
        JsonObject jsonObj = new JsonObject(); //json 객체

        List<DTOComment> commentList = commentService.getCommentList(comment); //댓글 서비스에서 댓글 리스트 가져옴

        if (CollectionUtils.isEmpty(commentList) == false) { //댓글 리스트가 존재하면

            //Gson: java 객체와 json 간 변환을 처리해주는 라이브러리
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
            JsonArray jsonArr = gson.toJsonTree(commentList).getAsJsonArray(); //댓글 데이터를 JsonArray 타입으로 변환
            jsonObj.add("commentList", jsonArr); //json 객체에 담아서 리턴하면 여러 타입 데이터를 추가할 수 있어 효율적

        }
        return jsonObj;

    }

    //답변글 댓글
    //@GetMapping("/{boardIdx}/answers/{answerIdx}/comments")



}
