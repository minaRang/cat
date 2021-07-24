package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.DTOEdit;

public interface EditService {

    public Long registerEdit(DTOEdit edit);
    public Long getCountEdit();

}
