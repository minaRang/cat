package com.backendStudy.cat.mapper;

import com.backendStudy.cat.domain.DTOEdit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EditMapper {

    public int insertEditContent(DTOEdit edit); //편집요청 내용 등록
    public Long selectCountEdit(); //편집요청 개수
    public List<DTOEdit> selectEditList(); //편집요청 리스트
    public int updateEdit(DTOEdit edit); //편집요청 수정
    public int deleteEdit(Long index);//편집 요청 삭제
    public int approvalEdit(Long index); //편집 요청 승인
    public int rejectEdit(Long index); //편집 요청 거절
}
