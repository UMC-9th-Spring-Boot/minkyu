package com.example.umc_9th.grobal.auth;

import com.example.umc_9th.grobal.apiPayload.ApiResponse;
import com.example.umc_9th.grobal.apiPayload.code.GeneralErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    // doFilterInternal 메서드를 실행
    // 원래 요청을 request, 응답할 객체를 response에 넣는다
    // 이 필터를 호출한 FilterChain을 filterChain에 넣는다
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            // 토큰 가져오기
            // 이 헤더를 읽어서
            String token = request.getHeader("Authorization");
            // token이 없거나 Bearer가 아니면 넘기기

            if (token == null || !token.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            // Bearer이면 추출,  // Bearer 접두사를 제거하고
            token = token.replace("Bearer ", "");
            // AccessToken 검증하기: 올바른 토큰이면
            // 타당한 토큰인지 확인하고
            //jwtUtil.isValid 메서드는 인자값으로 토큰을 정제한 토큰을 담아서
            // 타당한 토큰인지 확인하고 사용자 정보를 추출
            if (jwtUtil.isValid(token)) {
                // 토큰에서 이메일 추출
                String email = jwtUtil.getEmail(token);
                // 인증 객체 생성: 이메일로 찾아온 뒤, 인증 객체 생성
                UserDetails user = customUserDetailsService.loadUserByUsername(email);
                Authentication auth = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );
                // 인증 완료 후 SecurityContextHolder에 넣기
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            ApiResponse<Void> errorResponse = ApiResponse.onFailure(
                    GeneralErrorCode.UNAUTHORIZED,
                    null
            );

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), errorResponse);
        }
    }
}
