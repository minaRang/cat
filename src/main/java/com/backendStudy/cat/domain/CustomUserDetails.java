package com.backendStudy.cat.domain;

import java.util.Collection;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@ToString
@Getter
@Setter
public class CustomUserDetails implements UserDetails {

    private String userEmail;
    private String password;
    private long isAccountNonExpired;
    private long isEnabled;
    private List<GrantedAuthority> auth;

    public CustomUserDetails(String userEmail, String password, long isAccountNonExpired, long isEnabled, List<GrantedAuthority> auth) {
        this.userEmail = userEmail;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.isAccountNonExpired = isAccountNonExpired;
        this.isEnabled = isEnabled;
        this.auth = auth;
        log.info("CustomUserDetails= "+toString());
    }

    //계정이 갖고 있는 권한을 목록으로 리턴하기 위한 설정
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return auth;
    }

    //계정 비밀번호 리턴
    @Override
    public String getPassword() {
        return password;
    }

    //계정 이름 리턴
    @Override
    public String getUsername() {
        return userEmail;
    }

    //계정 만료되었는지 리턴 (true: 만료x)
    @Override
    public boolean isAccountNonExpired() {
        if(isAccountNonExpired==1)
            return false;
        else return true;
    }
    //계정 사용가능한지 (활성화 되었는지) 리턴 (true: 활성화)
    @Override
    public boolean isEnabled() {
        if (isEnabled==1)
            return true;
        else return false;
    }

    //아래는 우리 프로젝트에서는 쓰지 않는 것
    //계정 잠겨있지 않은지 리턴 (true: 안잠김)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호 만료되었는지 리턴 (true: 만료x)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
