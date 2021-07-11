package com.backendStudy.cat.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOFile {

    private long fileIdx;        // 인덱스
    private long boardIdx;       // 게시글인덱스
    private long userIdx;      //유저인덱스
    private String fileName;        // 파일명
    private String filePath;        // 파일경로
    private long fileSize;       // 파일크기
    private Date date;     // 등록일

}
