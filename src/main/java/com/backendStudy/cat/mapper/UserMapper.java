package com.backendStudy.cat.mapper;

import com.backendStudy.cat.domain.DTOUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface UserMapper {

    public int insertUser(DTOUser user);    //유저 정보 생성

    public DTOUser selectUser(long userIdx); //유저 정보 조회

    public int updateUser(DTOUser user);  //유저 정보 수정
    public int updateUserDetail(DTOUser user); //유저 정보 수정 디테일
    public int updateUserScore(long userIdx, long userScore); //유저 정보 수정 점수
    public int enableChangeUser(long userIdx); //유저 계정 사용 여부 변경

    public int deleteUser(long userIdx); //유저 정보 삭제

    public int loginUser(String userEmail, String userPassword);//로그인

    public int overlapEmailUser(String userEmail);  // 이메일 중복 확인

    public String getAuthority(long userIdx); //권한 확인

    public ArrayList<DTOUser> getAllUser(); //모든 회원 데이터


}
