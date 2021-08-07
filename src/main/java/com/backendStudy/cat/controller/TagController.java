package com.backendStudy.cat.controller;

import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.domain.DTOTag;
import com.backendStudy.cat.domain.tagInfo.TagInfo;
import com.backendStudy.cat.mapper.TagMapper;
import com.backendStudy.cat.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping("/searchTag")
    @ResponseBody
    public List<DTOTag> autoTagName(@RequestParam String tagName){
        List<DTOTag> dtoTags=tagService.autoMatchingTag(tagName);
        return dtoTags;
    }

    @GetMapping("/tagItem/{tagName}")
    public String TagItemView(@PathVariable String tagName,@ModelAttribute("board")DTOBoard board, Model model){
        DTOTag tag = new DTOTag();
        tag.setTagName(tagName);
        TagInfo tagInfo = tagService.findTagInfo(tag);
        List<DTOBoard> boardList= tagService.findBoardByTagName(board,tagName,"home");
        if(boardList != null)
            model.addAttribute("boardList",boardList);
        model.addAttribute("tab","home");
        model.addAttribute("tagItem",tag);
        model.addAttribute("tagInfo",tagInfo);
        return "tag/tagItem";
    }

    @GetMapping("/tagItem/popular/{tagName}")
    public String TagItemPopularView(@PathVariable String tagName,@ModelAttribute("board")DTOBoard board, Model model){
        DTOTag tag = new DTOTag();
        tag.setTagName(tagName);
        TagInfo tagInfo = tagService.findTagInfo(tag);
        List<DTOBoard> boardList= tagService.findBoardByTagName(board,tagName,"popular");
        if(boardList != null)
            model.addAttribute("boardList",boardList);
        model.addAttribute("tab","popular");
        model.addAttribute("tagItem",tag);
        model.addAttribute("tagInfo",tagInfo);
        return "tag/tagItem";
    }

    @GetMapping("/tagItem/needAnswer/{tagName}")
    public String TagItemNeedAnswerView(@PathVariable String tagName,@ModelAttribute("board")DTOBoard board, Model model){
        DTOTag tag = new DTOTag();
        tag.setTagName(tagName);
        TagInfo tagInfo = tagService.findTagInfo(tag);
        List<DTOBoard> boardList= tagService.findBoardByTagName(board,tagName,"needAnswer");
        if(boardList != null)
            model.addAttribute("boardList",boardList);
        model.addAttribute("tab","needAnswer");
        model.addAttribute("tagItem",tag);
        model.addAttribute("tagInfo",tagInfo);
        return "tag/tagItem";
    }

    @GetMapping("/selectTag")
    public String SelectTagView(@ModelAttribute("tag")DTOTag tag,
                                Model model){
        int totalTagNum = tagService.findAllTagCount(tag);
        List<DTOTag> tagList = tagService.findAllTagList(tag);
        model.addAttribute("totalTagNum",totalTagNum);
        model.addAttribute("tagList",tagList);
        return "tag/selectTag";
    }
}
