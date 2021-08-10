package com.backendStudy.cat.config;
import com.backendStudy.cat.service.CustomUserDetailsService;
import com.backendStudy.cat.service.security.CustomAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity      //Spring Security를 설정할 클래스예요~
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{ //일반적으로 이걸 상속받음. WebSecurityConfigurer 인스턴스를 편리하게 생성하기 위한 클래스이다.
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Override protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() { //Service에서 비밀번호를 암호화할 수 있도록 Bean으로 등록
        return new BCryptPasswordEncoder(); //Spring Security에서 제공하는 비밀번호 암호화 객체
    }

    //스프링 시큐리티 룰을 무시하게 하는 URL 규칙 (이곳에 등록하면 규칙이 적용하지 않는다.)
    @Override
    public void configure(WebSecurity web)throws Exception //WebSecurity는 FilterChainProxy를 생성하는 필터
    {
        web.ignoring().antMatchers("/CSS/**","/JS/**","/image/**"); // 해당 경로의 파일들은 Spring Security가 무시할 수 있도록 설정. 파일 기준은 resources/static 디렉터리
    }

    //스프링 시큐리티 규칙
    @Override
    protected void configure(HttpSecurity http) throws Exception { //HTTP 요청에 대한 웹 기반 보안을 구성
        http.authorizeRequests()  // 요청에 의한 보안검사 시작
                //페이지 권한 설정
                .antMatchers("/admin/**").hasRole("ADMIN") //ADMIN 권한을 가지는 사용자만 접근 가능
                .antMatchers("/users/myPage").hasRole("MEMBER") //MEMBER 권한을 가지는 사용자만 접근 가능
                .antMatchers("/**").permitAll() //접근을 전부 허용

                //로그인 설정
                .and()
                .formLogin() //보안 검증은 formLogin 방식으로 하겠다.
                .loginPage("/users/login") //사용자 정의 로그인 페이지. 기본 제공되는 form 말고 커스텀 폼 쓰고 싶으면 사용함.
                .defaultSuccessUrl("/users/myPage") //로그인 성공 후 이동 페이지
                .failureUrl("/users/denied") //실패 후 이동할 페이지
                .permitAll() //사용자 정의 로그인 페이지에 접근할 수 있는 권한을 승인함.

                //로그아웃 설정
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/users/logout")) //로그아웃을 실행할 주소.
                .logoutSuccessUrl("/users/login") //로그아웃 성공시 이동할 주소
                .invalidateHttpSession(true) //HTTP 세션을 초기화

                // 403예외처리 핸들링
                .and()
                .exceptionHandling().accessDeniedPage("/error") //예외가 발생했을 때/
                .and()
                   .authenticationProvider(customAuthenticationProvider);
    }

}
