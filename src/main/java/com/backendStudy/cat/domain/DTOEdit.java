package com.backendStudy.cat.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DTOEdit {

    private long editIdx;        // 인덱스
    private long boardIdx;       // 게시글인덱스
    private long answerIdx;      // 답변인덱스
    private long userIdx;        // 유저인덱스
    private String editTitle;       // 제목
    private String editContent;     // 내용
    private Date date;     // 등록일
    private Date updateTime; //수정일
    private long editExist; //편집 요청 존재 여부
    private long editApproval; //편집 요청 승인 여부
    private String editMessage; //편집 요청 메시지
    private String editTagName; //태그 편집
}
