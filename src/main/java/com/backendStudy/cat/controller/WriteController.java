package com.backendStudy.cat.controller;

import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.mapper.BoardMapper;
import com.backendStudy.cat.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WriteController {
    @Autowired
    BoardService boardService;
    @GetMapping("/write")
    public String MainForm(Model model){
        model.addAttribute("board",new DTOBoard());
        return "board/write";
    }

    @PostMapping("/form")
    public String NewQuestion(@RequestParam String boardTitle,
                              @RequestParam String boardContent,
                              Model model){
        DTOBoard board = new DTOBoard();
        board.setBoardTitle(boardTitle);
        board.setBoardContent(boardContent);

        //test용
        //TODO : 로그인한 계정의 idx 값 저장으로 수정
        board.setUserIdx(Long.valueOf(2));

        //TODO: tag와 file 구현
        //TODO: 게시글 작성시 작성자를 편집 히스토리에 추가

        boardService.registerBoard(board);
        return "redirect:/";
    }
}