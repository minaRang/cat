package com.backendStudy.cat.controller;

import com.backendStudy.cat.domain.*;
import com.backendStudy.cat.mapper.UserMapper;
import com.backendStudy.cat.service.BoardService;
import com.backendStudy.cat.service.FondService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

//게시글 상세 페이지
@Slf4j
@Controller
public class ViewController {
    @Autowired
    BoardService boardService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    FondService fondService;

    @GetMapping("/board/{boardIdx}")
    public String boardDetail(@PathVariable long boardIdx, Model model){
        boardService.setBoardView(boardIdx);
        DTOBoard board = boardService.getBoardDetail(boardIdx);

        DTOUser user = new DTOUser();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (! authentication.getPrincipal().equals("anonymousUser")) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            user = userMapper.selectUserByEmail(customUserDetails.getUsername());
            DTOFond fond = new DTOFond();
            fond.setUserIdx(user.getUserIdx());
            fond.setBoardIdx(boardIdx);

            fond=fondService.findFondBoard(fond);
            if (fond.getFondScore()>0)
                board.setUserFond("up");
            else if (fond.getFondScore()<0)
                board.setUserFond("down");

        }
        //TODO: 답변 내용 및 편집 히스토리(작성자) 내용 표시
        model.addAttribute("boardDetail",board);
        return "questions";
    }
}
