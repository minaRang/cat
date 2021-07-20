package com.backendStudy.cat.controller;

import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.Date;
import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private BoardService boardService;
    @GetMapping("/")
    public String Home(Model model){
        List<DTOBoard> boardList= boardService.getBoardList();
        boardList.sort((a,b)->b.getDate().compareTo(a.getDate()));
        boardList.forEach(b->b.setTimeInterval(CalDate(b.getDate())));
        model.addAttribute("boardList",boardList);
        model.addAttribute("tap","home");
        return "board/home";
    }
    @GetMapping("/popular")
    public String PopularList(Model model){
        List<DTOBoard> boardList= boardService.getBoardList();
        Collections.sort(boardList,(list1, list2)-> {
            return Long.valueOf(list2.getBoardView() - list1.getBoardView()).intValue();
        });
        boardList.forEach(b->b.setTimeInterval(CalDate(b.getDate())));
        model.addAttribute("boardList",boardList);
        model.addAttribute("tap","popular");
        return "board/home";
    }
    @GetMapping("/needAnswer")
    public String NeedAnswerList(Model model){
        List<DTOBoard> boardList= boardService.getBoardList();
        boardList.removeIf(m->m.getCntBoardAnswer()>0);
        boardList.sort((a,b)->b.getDate().compareTo(a.getDate()));
        boardList.forEach(b->b.setTimeInterval(CalDate(b.getDate())));
        model.addAttribute("boardList",boardList);
        model.addAttribute("tap","needAnswer");
        return "board/home";
    }

    //날짜 계산 함수
    public String CalDate(Date date) {
        Date now = new Date();
        Long calDate = now.getTime() - date.getTime();

        Long calTime = calDate / (60 * 1000);

        if (calTime <= 60) return calTime + "분 전";
        else if (calTime <= 60*24) return calTime/60+ "시간 전";
        else if(calTime<=60*24*30) return calTime/(60*24)+"일 전";
        else if (calTime<=60*24*30*12) return calTime/(60*24*30) +"달 전";
        else return calTime/(60*24*30*12) +"년 전";
    }

}
