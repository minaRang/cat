package com.backendStudy.cat.controller;

import com.backendStudy.cat.domain.DTOTag;
import com.backendStudy.cat.mapper.TagMapper;
import com.backendStudy.cat.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("searchTag")
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping
    public List<DTOTag> autoTagName(@RequestParam String tagName){
        List<DTOTag> dtoTags=tagService.autoMatchingTag(tagName);
        return dtoTags;
    }
}
