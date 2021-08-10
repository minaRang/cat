package com.backendStudy.cat;


import com.backendStudy.cat.domain.DTOUser;
import com.backendStudy.cat.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@Slf4j
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    //유저 정보 생성
    @Test
    public void insertTest(){
        DTOUser user = new DTOUser();
        user.setUserName("김씨3");
        user.setUserEmail("123@123");
        user.setUserPassword("1234");

        log.info("결과는"+userMapper.insertUser(user));
    }

    //유저 정보 조회
    @Test
    public void selectTest(){
        DTOUser user=userMapper.selectUserByIdx(1);
        log.info("결과는"+user.getUserIdx()+" "+user.getUserName());
    }

    //유저 정보 수정 (비밀번호, 이메일 알림 여부)
    @Test
    public void updateTest(){
        DTOUser user=userMapper.selectUserByIdx(5);
        user.setUserPassword("1111");
        user.setUserNtcAnswer(0);
        user.setUserNtcComment(0);
        user.setUserNtcNews(0);
        log.info("결과는"+userMapper.updateUser(user));
    }
    //유저 정보 수정 상세 정보
    @Test
    public void updateDetailTest(){
        DTOUser user=userMapper.selectUserByIdx(5);
        user.setUserName("홍씨");
        user.setUserBio("소개");
        user.setUserHomepage(".com");
        user.setUserAddress("대한민국");
        user.setUserCompany("cat");
        log.info("결과는"+userMapper.updateUserDetail(user));
    }
    //유저 정보 수정 점수
    @Test
    public void updateScoreTest(){
        log.info("결과는"+userMapper.updateUserScore(5,300));
    }
    //유저 계정 사용 변경
    @Test
    public void enableChangeTest(){
        log.info("결과는"+userMapper.enableChangeUser(5));
    }
    //유저 삭제
    @Test
    public void deleteTest(){
        log.info("결과는"+userMapper.deleteUser(5));
    }

    //로그인
    @Test
    public void loginTest(){
        log.info("결과는"+userMapper.loginUser("123@naver.com","124"));
        log.info("결과는"+userMapper.loginUser("123@naver.com","1234"));
    }

    //이메일 중복 확인
    @Test
    public void overlapEmailTest(){
        log.info("결과는"+userMapper.overlapEmailUser("123@123"));
    }

    //권한 확인
    @Test
    public void getAuthorityTest(){
        log.info("결과는"+userMapper.getAuthority(5));
    }

    //모든 회원 데이터
    @Test
    public void getAllDataTest(){
        ArrayList<DTOUser> users = userMapper.getAllUser();
        for (DTOUser user : users){
            log.info(user.toString());
        }

    }
}
