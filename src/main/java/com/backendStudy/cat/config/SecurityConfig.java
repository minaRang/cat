package com.backendStudy.cat.config;
import lombok.AllArgsConstructor;
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
public class SecurityConfig extends WebSecurityConfigurerAdapter{ //일반적으로 이걸 상속받음. WebSecurityConfigurer 인스턴스를 편리하게 생성하기 위한 클래스이다.

    @Bean
    public PasswordEncoder passwordEncoder() { //Service에서 비밀번호를 암호화할 수 있도록 Bean으로 등록
        return new BCryptPasswordEncoder(); //Spring Security에서 제공하는 비밀번호 암호화 객체
    }

    @Override
    public void configure(WebSecurity web)throws Exception //WebSecurity는 FilterChainProxy를 생성하는 필터
    {
        web.ignoring().antMatchers("/css/**","/js/**","/img/**","/lib/**"); // 해당 경로의 파일들은 Spring Security가 무시할 수 있도록 설정. 파일 기준은 resources/static 디렉터리
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { //HTTP 요청에 대한 웹 기반 보안을 구성
        http.authorizeRequests()
                //페이지 권한 설정
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/myinfo").hasRole("MEMBER")
                .antMatchers("/**").permitAll()

                .and()//로그인 설정
                .formLogin()
                .loginPage("/user/login")
                .defaultSuccessUrl("/user/login/result")
                .permitAll()

                .and()//로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/user/logout/result")
                .invalidateHttpSession(true)

                .and()// 403예외처리 핸들링
                .exceptionHandling().accessDeniedPage("/user/denied");
    }

}
