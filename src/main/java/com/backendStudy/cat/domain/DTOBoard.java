package com.backendStudy.cat.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOBoard {
    private long boardIdx;        // 인덱스
    private long userIdx;        // 유저인덱스
    private String boardTitle;          // 제목
    private String boardContent;     // 내용
    private long boardView;       // 조회수
    private Date date;     // 등록일
    private Date update;       // 수정일

}
