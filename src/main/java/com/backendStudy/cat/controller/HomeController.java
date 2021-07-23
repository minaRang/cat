package com.backendStudy.cat.controller;

import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    private BoardService boardService;


    @GetMapping("/")
    public String Home(@ModelAttribute("board")DTOBoard board,
                       Model model){
        List<DTOBoard> boardList= boardService.getBoardListOrderByDate(board);
        model.addAttribute("boardList",boardList);
        model.addAttribute("tap","home");
        return "board/home";
    }
    @GetMapping("/popular")
    public String PopularList(@ModelAttribute("board")DTOBoard board,
                              Model model){
        List<DTOBoard> boardList= boardService.getBoardListOrderByPopular(board);
        model.addAttribute("boardList",boardList);
        model.addAttribute("tap","popular");
        return "board/home";
    }
    @GetMapping("/needAnswer")
    public String NeedAnswerList(@ModelAttribute("board")DTOBoard board,
                                 Model model){
        List<DTOBoard> boardList= boardService.getBoardListOrderByNeedAnswer(board);
        model.addAttribute("boardList",boardList);
        model.addAttribute("tap","needAnswer");
        return "board/home";
    }


}
