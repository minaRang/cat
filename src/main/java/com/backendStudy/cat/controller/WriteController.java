package com.backendStudy.cat.controller;

import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.domain.DTOTag;
import com.backendStudy.cat.mapper.BoardMapper;
import com.backendStudy.cat.service.BoardService;
import com.backendStudy.cat.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@Slf4j
public class WriteController {
    @Autowired
    BoardService boardService;

    @Autowired
    TagService tagService;

    @GetMapping("/write")
    public String MainForm(Model model){
        model.addAttribute("board",new DTOBoard());
        model.addAttribute("tag", new DTOTag());
        return "board/write";
    }

    @PostMapping("/form")
    @ResponseBody
    public String RegisterTag (@RequestParam(value = "tagNameList[]") List<String> tagNameList,
                               @RequestParam(value="boardTitle") String boardTitle,
                               @RequestParam(value = "boardContent") String boardContent,
                               HttpServletResponse response,
                               Model model){

        DTOBoard board = new DTOBoard();
        board.setBoardTitle(boardTitle);
        board.setBoardContent(boardContent);

        //test용
        //TODO : 로그인한 계정의 idx 값 저장으로 수정
        board.setUserIdx(2L);
        boardService.registerBoard(board,tagNameList);

        return "/";
    }
}