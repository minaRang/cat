package com.backendStudy.cat.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOBoard extends DTOCommon {
    private Long boardIdx;        // 인덱스
    private Long userIdx;        // 유저인덱스
    private String boardTitle;          // 제목
    private String boardContent;     // 내용
    private Long boardView;       // 조회수
    private Date date;     // 등록일
    private Date update;       // 수정일

    //join을 위한 변수
    private String userName;
    private int cntBoardAnswer;
    private int answerIsAdopted;
    private int fondScore;

    //지난 시간 계산(시간 간격)
    private String timeInterval;
}
