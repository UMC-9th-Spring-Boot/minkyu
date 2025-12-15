package com.example.umc_9th.grobal.config;

import com.example.umc_9th.grobal.auth.CustomUserDetailsService;
import com.example.umc_9th.grobal.auth.JwtAuthFilter;
import com.example.umc_9th.grobal.auth.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// 어노테이션은 Spring Security 설정을 활성화시키는 역할
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;


    // 비밀번호 솔트를 위한 PasswordEncoder 설정
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    // 허용할 URL를 따로 뺴서 관리
    private final String[] allowUris = {
            "/api/members/login",      // API 경로로 수정
            "/api/members/sign-up",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/logout"
    };

    @Bean
    // SecurityFilterChain를 정의하는 메서드
    // HttpSecurity 객체를 통해 다양한 보안 설정구성

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // HTTP 요청에 대한 접근 제어를 설정
                .authorizeHttpRequests(requests -> requests
                        //requestMatchers 를 사용하여 특정 URL에 대한 권한 접근 설정
                        // allowUris배열로 빼서 정의
                        .requestMatchers(allowUris).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // ADMIN 역할을 가진 사용자만 접근 가능
                        // 그외 모든 요청 인증 요구 authenticated
                        .anyRequest().authenticated()
                )
                // 폼 기반 로그인에 대한 설정  로그인 성공 시 /swagger-ui/index.html로 디라이렉트
                // alwaysUse를 true 로 설정하면 로그인 성공 시 항상 Swagger로 리다이렉트
                     // 실습 1
                        .formLogin(form -> form
                        .defaultSuccessUrl("/swagger-ui/index.html", true)
                        //permitAll은 인증 없이 접근 가능한 경로지정
                        .permitAll())


//                // 실습 2
//                // 폼로그인 비활성화
//                .formLogin(AbstractHttpConfigurer::disable)
//                // JwtAuthFilter를 UsernamePasswordAuthenticationFilter 앞에 추가
//               // // JwtAuthFilter를 UsernamePasswordAuthenticationFilter
//                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
//                //세션 사용 안 함
//                .sessionManagement(session ->
//                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )

                .csrf(AbstractHttpConfigurer::disable)

                .logout(logout -> logout
                        // /logout 경로로 로그아웃 처리
                        .logoutUrl("/logout")

                        // 성공 시 리다이렉트
                        .logoutSuccessUrl("/swagger-ui/index.html")
                        .permitAll()
                );

        return http.build();
    }
    // 실습 1. 세션 방식 AuthenticationManager 빈등록
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    // 실습 2. jwt 방식 JwtAuthFilter 빈 등록
    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(jwtUtil, customUserDetailsService);
    }

}