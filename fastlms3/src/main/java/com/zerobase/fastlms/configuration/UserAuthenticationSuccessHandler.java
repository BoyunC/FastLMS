package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.member.entity.MemberLoginHistory;
import com.zerobase.fastlms.member.repository.MemberLoginHistoryRepository;
import com.zerobase.fastlms.member.repository.MemberRepository;
import com.zerobase.fastlms.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final MemberRepository memberRepository;
    private final MemberLoginHistoryRepository memberLoginHistoryRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String userId = authentication.getName();
        String userAgent = RequestUtils.getUserAgent(request);
        String clientIp = RequestUtils.getClientIP(request);

        System.out.println("userId = " + userId);

        //로그인 히스토리 저장
        memberLoginHistoryRepository.save(MemberLoginHistory.builder()
                .ipAddr(clientIp)
                .regDt(LocalDateTime.now())
                .userAgent(userAgent)
                .userId(userId)
                .build());

        //마지막 로그인 일자 저장
        memberRepository.findById(userId).ifPresent(e -> {
            e.setLastLoginDt(LocalDateTime.now());
            memberRepository.save(e);
        });

        super.onAuthenticationSuccess(request, response, authentication);
    }
}