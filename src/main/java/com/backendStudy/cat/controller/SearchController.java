package com.backendStudy.cat.controller;

import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class SearchController {
    @Autowired
    BoardService boardService;

    @GetMapping("/questions/search")
    public String SearchBoard(@ModelAttribute("board")DTOBoard board,
                              @RequestParam(value = "text", required = false)String searchKeyword,
                              Model model){
        board.setSearchKeyword(searchKeyword);
        List<DTOBoard> boardList= boardService.searchBoardList(board);
        model.addAttribute("boardList",boardList);
        model.addAttribute("tab","home");
        return "board/search";
    }
}
