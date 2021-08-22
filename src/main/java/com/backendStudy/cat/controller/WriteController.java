package com.backendStudy.cat.controller;

import com.backendStudy.cat.domain.CustomUserDetails;
import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.domain.DTOTag;
import com.backendStudy.cat.domain.DTOUser;
import com.backendStudy.cat.mapper.BoardMapper;
import com.backendStudy.cat.mapper.UserMapper;
import com.backendStudy.cat.service.BoardService;
import com.backendStudy.cat.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    //TODO: service로 수정
    @Autowired
    UserMapper userMapper;

    @GetMapping("/write")
    public String mainForm(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")) {
            log.info(authentication.getPrincipal().toString());
            model.addAttribute("url", "/");
            model.addAttribute("msg", "로그인이 필요합니다");
            return "/redirect";
        }
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")) {
            log.info(authentication.getPrincipal().toString());
            model.addAttribute("url", "/");
            model.addAttribute("msg", "로그인이 필요합니다");
            return "/redirect";
        }
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        DTOUser user = userMapper.selectUserByEmail(customUserDetails.getUsername());
        log.info("User Name {}",customUserDetails.getUsername());

        board.setUserIdx(user.getUserIdx());
        boardService.registerBoard(board,tagNameList);

        return "/";
    }
}