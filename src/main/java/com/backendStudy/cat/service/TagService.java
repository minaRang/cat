package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.DTOTag;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface TagService {
    public List<DTOTag> autoMatchingTag(String term);
    public Boolean findTagName (String tagName);
    public Long registerTag(DTOTag tag);
}
