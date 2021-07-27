package com.backendStudy.cat.mapper;

import com.backendStudy.cat.domain.DTOEdit;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EditMapper {

    public Long insertEditContent(DTOEdit edit);
    public Long selectCountEdit();
}
