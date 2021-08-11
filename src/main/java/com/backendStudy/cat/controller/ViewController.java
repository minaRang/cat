package com.backendStudy.cat.controller;

import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//게시글 상세 페이지
@Controller
public class ViewController {
    @Autowired
    BoardService boardService;

    @GetMapping("/board/{boardIdx}")
    public String BoardDetail(@PathVariable long boardIdx, Model model){
        boardService.setBoardView(boardIdx);
        DTOBoard board = boardService.getBoardDetail(boardIdx);

        //TODO: 답변 내용 및 편집 히스토리(작성자) 내용 표시
        model.addAttribute("boardDetail",board);
        return "questions";
    }
}
