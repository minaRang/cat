package com.backendStudy.cat.mapper;

import com.backendStudy.cat.domain.DTOEdit;

public interface EditMapper {

    public Long insertEditContent(DTOEdit edit);
    public Long selectCountEdit();
}
