package com.backendStudy.cat.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOEdit {
    private long editIdx;        // 인덱스
    private long boardIdx;       // 게시글인덱스
    private long userIdx;        // 유저인덱스
    private String editTitle;       // 제목
    private String editContent;     // 내용
    private Date date;     // 등록일
}
