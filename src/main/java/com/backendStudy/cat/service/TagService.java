package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.domain.DTOTag;
import com.backendStudy.cat.domain.tagInfo.TagInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface TagService {
    public List<DTOTag> autoMatchingTag(String term); //자동완성
    public List<DTOBoard> findBoardByTagName(DTOBoard board,String tagName, String tab); //태그 이름과 일치하는 게시판 찾기
    List<DTOTag> findAllTagList(DTOTag tag); // 모든 태그의 목록 반환
    int findAllTagCount(DTOTag tag); //모든 태그의 수 반환
    public Long registerTag(DTOTag tag); //태그 등록
    TagInfo findTagInfo(DTOTag tag); // 태그의 정보 반환
}
