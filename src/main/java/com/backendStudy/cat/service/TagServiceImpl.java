package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.DTOTag;
import com.backendStudy.cat.mapper.TagMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;

    @Override
    public List<DTOTag> autoMatchingTag(String term) {
        return tagMapper.findAllMatchingTag(term);
    }

    @Override
    public Boolean findTagName(String tagName) {
        if(tagMapper.findTagName(tagName) > 0)
            return true;
        else return false;
    }

    @Override
    public Long registerTag(DTOTag tag) {
        log.info(tag.getTagName());
        log.info("{}",tag.getTagIdx());
        tagMapper.insertTag(tag);
        return tag.getTagIdx();
    }
}
