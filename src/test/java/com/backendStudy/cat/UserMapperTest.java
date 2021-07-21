package com.backendStudy.cat;


import com.backendStudy.cat.domain.DTOUser;
import com.backendStudy.cat.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    //유저 정보 생성
    @Test
    public void insertTest(){
        DTOUser user = new DTOUser();
        user.setUserName("김씨2");
        user.setUserEmail("123@123");
        user.setUserPassword("1234");

        userMapper.insertUser(user);

        log.info("결과는"+userMapper.insertUser(user));
    }

    //유저 정보 조회
    @Test
    public void selectTest(){
        DTOUser user=userMapper.selectUser(1);
        log.info("결과는"+user.getUserIdx()+" "+user.getUserName());
    }

    //유저 정보 수정
    @Test
    public void updateTest(){
        DTOUser user=userMapper.selectUser(1);
        user.setUserPassword("1111");
        log.info("결과는"+userMapper.updateUser(user));
    }
}
