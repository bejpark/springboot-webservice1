package com.bejpark.springboot.config.auth;

import com.bejpark.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security설정을 활성화시켜준다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAUth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable() //h2-console 화면을 사용하기 위해 해당 옵션을을 disable해준다.
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests() //URL별 권한 관리를 설정하는 옵션의 시작점이다. 이게 선언되어야 antMatchers 사용가능
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                    //권한 관리 대상을 지정하는 옵션이다. URL, HTTP메소드별로 관리가 가능. permitAll을 통해 전체열람 권한을 준다.
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    //해당 API는 USER권한을 가진 사람만 가능하도록 함.
                    .anyRequest().authenticated()
                    //설정값 이외 나머지 URL들을 나타낸다., authenticeted로 인증된 사용자만 허용하게 한다. (로그인한 사용자)
                .and()
                    .logout()
                        .logoutSuccessUrl("/") //로그아웃 성공시 주소로 이동
                .and()
                    .oauth2Login() //로그인 설정의 진입점
                        .userInfoEndpoint() //로그인 성공 후 사용자 정보를 가져올 때의 설정을 담당한다.
                            .userService(customOAUth2UserService); //후속 조치를 진행할 UserService 인터페이스의 구현체를 등록한다.
                            //리소스 서버(소셜 서비스)에서 사용자정보 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있다.
    }
}
