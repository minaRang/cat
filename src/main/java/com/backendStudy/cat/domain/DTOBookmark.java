package com.backendStudy.cat.domain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOBookmark {
    private long bookmarkIdx;        // 인덱스
    private String userIdx;     // 유저인덱스
    private String boardIdx;        // 게시글인덱스

}
