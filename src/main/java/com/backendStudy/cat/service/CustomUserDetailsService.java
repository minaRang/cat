package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.CustomUserDetails;
import com.backendStudy.cat.domain.DTOUser;
import com.backendStudy.cat.mapper.UserMapper;

import lombok.AllArgsConstructor;
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

@AllArgsConstructor
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
        log.info("사용자 : " +user.toString());
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if(user.getUserAuthority().equals("ROLE_ADMIN"))
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));

        CustomUserDetails c= new CustomUserDetails(user.getUserEmail(), user.getUserPassword(),user.getIsAccountNonExpired(),user.getIsEnabled(),authorities);
        log.info(c.toString());
        return c;
    }
}
