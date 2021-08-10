package com.backendStudy.cat.service.security;

import com.backendStudy.cat.service.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider { //화면에서 입력한 로그인 정보, DB에서 가져온 사용자 정보를 비교하는 인터페이스를 구현함.
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("authenticate 실행");
        String userId = (String)authentication.getPrincipal();
        String userPassword = (String)authentication.getCredentials();
        //로그인 로직 구현
        UserDetails user = customUserDetailsService.loadUserByUsername(userId);
        if(!userPassword.equals(user.getPassword())){
            log.info("비번다름");
            throw new BadCredentialsException("BadCredentialsException");
        }
        log.info("추가할 토큰 Username:"+user.getUsername()+"/userPassword:"+userPassword+" /userAuthorities:"+user.getAuthorities());
        return new UsernamePasswordAuthenticationToken(user.getUsername(), userPassword, user.getAuthorities());
    }

    // 토큰 타입과 일치할 때 인증
    @Override
    public boolean supports(Class<?> authentication) {
        log.info("supports 실행");
        return true;
    }
}
