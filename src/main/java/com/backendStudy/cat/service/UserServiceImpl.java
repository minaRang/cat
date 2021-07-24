package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.DTOUser;
import com.backendStudy.cat.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DTOUser user;

    //회원가입
    @Override
    public int singUpUser() {
        //웹페이지에 입력한 값들 가져와서 user에 넣는 과정
        if(userMapper.overlapEmailUser(user.getUserEmail())>0); //중복 이메일이 있을 경우 처리 X
        return userMapper.insertUser(user);
    }

    //로그인
    @Override
    public int loginUser(String userEmail, String userPassword) {
        //웹페이지에 입력한 값들 가져온뒤
        if(userMapper.loginUser(userEmail, userPassword)>0); //일치하는 이메일, 비밀번호가 있을 경우 처리
        return 1;
    }

    //회원 정보 변경
    @Override
    public int updateUser(String type, DTOUser user) {
        if (type==null) {
            userMapper.updateUser(user);
            return 1;
        }
        else if (type=="detail"){
            userMapper.updateUserDetail(user);
            return 1;
        }
        else if (type =="score"){
            userMapper.updateUserScore(user.getUserScore(),user.getUserIdx());
            return 1;
        }

        return 0;
    }
}
