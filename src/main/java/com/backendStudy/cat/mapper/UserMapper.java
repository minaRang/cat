package com.backendStudy.cat.mapper;

import com.backendStudy.cat.domain.DTOUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public int insertUser(DTOUser user);    //유저 정보 생성

    public DTOUser selectUser(long userIdx); //유저 정보 조회

    public int updateUser(DTOUser user);  //유저 정보 수정
    public int updateUserDetail(DTOUser user); //유저 정보 수정 디테일
    public int updateUserScore(long userIdx, long userScore); //유저 정보 수정 점수

    public int deleteUser(int userIdx); //유저 정보 삭제

    public int loginUser(String userEmail, String userPasswd);//로그인


}
