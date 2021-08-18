package com.backendStudy.cat.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DTOComment {

    private long commentIdx;    // 인덱스
    private long userIdx;   // 유저인덱스
    private long boardIdx;  // 게시글인덱스
    private long answerIdx; // 답변인덱스
    private String commentContent;  // 내용
    private Date date;  // 등록일
    private Date update; // 수정일
    private long commentExist;  //댓글 존재여부

}
