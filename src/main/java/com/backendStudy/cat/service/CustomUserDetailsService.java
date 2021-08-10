package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.CustomUserDetails;
import com.backendStudy.cat.domain.DTOUser;
import com.backendStudy.cat.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //사용자의 정보를 CustomUserDetails 형으로 가져온다.
        log.info("============================");
        log.info("실행");

        //사용자 정보를 가져옴
        DTOUser user= userMapper.selectUserByEmail(username);

        //만약 해당 username의 사용자 정보가 없다면 예외처리
        if(user==null){
            log.info("사용자의 정보가 없습니다.");
            throw new UsernameNotFoundException(username);
        }

        //성공이면 사용자의 정보가 담긴 user 리턴
        log.info("사용자의 정보가 있습니다.");
        log.info("사용자 : " +user.getUserEmail()+"   "+user.getUserPassword()+"   "+user.getUserAuthority());
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if(user.getUserAuthority().equals("admin")){
            log.info("관리자입니다.");
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
        }else if (user.getUserAuthority().equals("member")){
            log.info("회원입니다.");
            authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
        }else{
            log.info("권한이 없습니다.");
            return null;}
        return new CustomUserDetails(user.getUserEmail(), user.getUserPassword(),user.getIsAccountNonExpired(),user.getIsEnabled(),authorities);
    }
}
