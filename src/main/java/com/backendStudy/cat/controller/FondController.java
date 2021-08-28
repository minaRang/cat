package com.backendStudy.cat.controller;

import com.backendStudy.cat.domain.CustomUserDetails;
import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.domain.DTOFond;
import com.backendStudy.cat.domain.DTOUser;
import com.backendStudy.cat.mapper.UserMapper;
import com.backendStudy.cat.service.BoardService;
import com.backendStudy.cat.service.FondService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class FondController {
    @Autowired
    FondService fondService;

    @Autowired
    BoardService boardService;

    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "/board/qusetion_fond", method = {RequestMethod.PUT})
    @ResponseBody
    public List<String> updateFondScore(@RequestParam(value = "fond") Long fondScore,
                                        @RequestParam(value="question-boardIdx") Long boardIdx,
                                        Model model) {
        DTOUser user = new DTOUser();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")) {
            log.info(authentication.toString());
            return new ArrayList<>();
        }

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        user = userMapper.selectUserByEmail(customUserDetails.getUsername());
        log.info("login user {} {} {}", customUserDetails.getUsername(), user.getUserName(), user.getUserIdx());
        DTOFond fond = new DTOFond();
        fond.setBoardIdx(boardIdx);
        fond.setUserIdx(user.getUserIdx());
        fond = fondService.findFondBoard(fond);
        List<String> data = new ArrayList<>();
        if (fond.getFondScore() * fondScore!=0){
            data.add("hc-icons-thumb-up");
            data.add("hc-icons-thumb-down");
            fondScore=0L;
        }
        else if(fond.getFondScore()==0){
            fondScore += fond.getFondScore();
            if (fondScore == 1) {
                data.add("hc-icons-thumb-up active");
                data.add("hc-icons-thumb-down");
            }
            else if (fondScore==-1) {
                data.add("hc-icons-thumb-up");
                data.add("hc-icons-thumb-down active");
            }
        }
        fondService.updateFondBoard(boardIdx, user.getUserIdx(), fondScore);
        data.add(fondService.cntFondBoard(boardIdx).toString());
        return data;
    }
    //TODO:answer 구현
    @RequestMapping(value = "/board/answer_fond", method = {RequestMethod.PUT})
    @ResponseBody
    public List<String> updateFondScoreAnswer(@RequestParam(value = "fond") Long fondScore,
                                        @RequestParam(value="question-answerIdx") Long answerIdx,
                                        Model model) {
        DTOUser user = new DTOUser();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")) {
            log.info(authentication.toString());
            return new ArrayList<>();
        }

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        user = userMapper.selectUserByEmail(customUserDetails.getUsername());
        log.info("login user {} {} {}", customUserDetails.getUsername(), user.getUserName(), user.getUserIdx());
        log.info("answerIdx {}", answerIdx);
        DTOFond fond = new DTOFond();
        fond.setAnswerIdx(answerIdx);
        fond.setUserIdx(user.getUserIdx());
        fond = fondService.findFondAnswer(fond);
        List<String> data = new ArrayList<>();
        if (fond.getFondScore() * fondScore!=0){
            data.add("hc-icons-thumb-up");
            data.add("hc-icons-thumb-down");
            fondScore=0L;
        }
        else if(fond.getFondScore()==0){
            fondScore += fond.getFondScore();
            if (fondScore == 1) {
                data.add("hc-icons-thumb-up active");
                data.add("hc-icons-thumb-down");
            }
            else if (fondScore==-1) {
                data.add("hc-icons-thumb-up");
                data.add("hc-icons-thumb-down active");
            }
        }
        fondService.updateFondAnswer(answerIdx, user.getUserIdx(), fondScore);
        data.add(fondService.cntFondAnswer(answerIdx).toString());
        data.add("#answer" + answerIdx);
        return data;
    }
}
