package com.backendStudy.cat.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("DTOTag")
public class DTOTag extends DTOCommon{
    private Long tagIdx;        // 인덱스
    private Long boardIdx;       // 게시글인덱스
    private Long userIdx;        // 유저인덱스
    private String tagName;     // 태그명
    private Date date;     // 등록일
    private Date update;        // 수정일

    private int cntBoard; // 등록된 board의 수
}
