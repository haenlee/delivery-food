package com.deliveryfood.common.mock.auth;

import com.deliveryfood.model.CustomUserDetails;
import com.deliveryfood.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.List;

@RequiredArgsConstructor
public class WithAuthMemberSecurityContextFactory implements WithSecurityContextFactory<WithAuthMember> {

    private final MemberService memberService;

    @Override
    public SecurityContext createSecurityContext(WithAuthMember annotation) {
        String username = annotation.username();
        String authority = annotation.authority();
        CustomUserDetails user = memberService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(user, user.getPassword(), List.of(new SimpleGrantedAuthority(authority)));

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(token);
        return context;
    }
}
