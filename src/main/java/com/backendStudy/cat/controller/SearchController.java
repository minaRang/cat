package com.backendStudy.cat.controller;

import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.domain.DTOTag;
import com.backendStudy.cat.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class SearchController {
    @Autowired
    SearchService searchService;

    @GetMapping("/questions/search")
    public String SearchBoard(@ModelAttribute("board")DTOBoard board,
                              @RequestParam(value = "text", required = false)String searchKeyword,
                              Model model){
        board.setSearchKeyword(searchKeyword);
        List<DTOBoard> boardList= searchService.searchBoardList(board);
        model.addAttribute("boardList",boardList);
        model.addAttribute("tab","home");
        return "board/search";
    }

    @GetMapping("/selectTag/search")
    public String SearchTag(@ModelAttribute("tag") DTOTag tag,
                              @RequestParam(value = "text", required = false)String searchKeyword,
                              Model model){
        tag.setSearchKeyword(searchKeyword);
        List<DTOTag> tagList= searchService.searchTagList(tag);
        model.addAttribute("totalTagNum",tag.getPageInfo().getTotalData());
        model.addAttribute("tagList",tagList);
        model.addAttribute("search","yes");
        return "tag/selectTag";
    }
}
