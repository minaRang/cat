package com.backendStudy.cat.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;


@ToString
@Component
@Getter
@Setter
public class DTOUser {

    private long userIdx;// 인덱스
    private String userName; // 이름
    private String userEmail; // 이메일
    private String userPassword;// 비밀번호
    private long userNtcAnswer; // 이메일 설정(답변알림)
    private long userNtcNews; // 이메일 설정(뉴스레터수신)
    private long userNtcComment;// 이메일설정(댓글알림)
    private long userScore;// 점수
    private String userBio;// 자기소개
    private String userHomepage; // 홈페이지
    private String userAddress;// 주소
    private String userCompany;// 소속
    private long isEnabled;//계정사용가능
    private long isAccountNonExpired;// 계정만료
    private String userAuthority;// 권한
    private Date date;// 등록일
    private Date update_time;// 수정일

}
