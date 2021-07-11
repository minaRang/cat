package com.backendStudy.cat.domain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOFond {
    private long fondIdx;        // 인덱스
    private long userIdx;         // 유저인덱스
    private long boardIdx;       // 게시글인덱스
    private long answerIdx;      // 답변인덱스
    private long fondScore;       // 좋아요싫어요

}
