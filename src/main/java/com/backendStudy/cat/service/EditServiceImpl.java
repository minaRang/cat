package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.DTOEdit;
import com.backendStudy.cat.mapper.EditMapper;

public class EditServiceImpl implements EditService {
    private EditMapper editMapper;

    //편집 요청 등록
    @Override
    public Long registerEdit(DTOEdit edit) {
        editMapper.insertEditContent(edit);
        return edit.getEditIdx();
    }

    //편집 요청 개수
    @Override
    public Long getCountEdit() {
        return editMapper.selectCountEdit();
    }


}
