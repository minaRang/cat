package com.backendStudy.cat.domain;

import com.backendStudy.cat.domain.paging.Criteria;
import com.backendStudy.cat.domain.paging.PageInfo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DTOCommon extends Criteria {
    private PageInfo PageInfo;
    private LocalDateTime insertTime;
    private LocalDateTime updateTime;
}
