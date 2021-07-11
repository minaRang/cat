package com.backendStudy.cat.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOAnswer {

    private long answerIdx;        // 인덱스
    private long userIdx;        // 유저인덱스
    private long boardIdx;       // 게시글인덱스
    private String answerContent;     // 내용
    private long answerIsAdopted;      // 채택여부
    private Date date;      // 등록일
    private Date update;        // 수정일

}
