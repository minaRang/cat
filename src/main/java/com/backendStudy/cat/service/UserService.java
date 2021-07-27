package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.DTOUser;

public interface UserService {
    public int singUpUser();//회원가입
    public int loginUser(String userEmail, String userPassword); //로그인
    public int updateUser(String type, DTOUser user); //회원 정보 수정
}
