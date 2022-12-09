package com.deliveryfood.service;

import com.deliveryfood.controller.model.request.UserRequest;
import com.deliveryfood.dao.MemberDao;
import com.deliveryfood.dao.model.MemberDto;
import com.deliveryfood.service.impl.MemberService;
import com.deliveryfood.service.model.MemberRegisterVO;
import com.deliveryfood.service.model.MemberUpdateVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberDao memberDao;

    private static final String EMAIL = "test@gmail.com";
    private static final String PW = "test1234";

    private MemberRegisterVO memberVO;
    private MemberDto authMemberDto;
    private MemberDto notAuthMemberDto;

    @BeforeEach
    public void init() {
        memberVO = createMemberVO();
        authMemberDto = createMemberDTO(true);
        notAuthMemberDto = createMemberDTO(false);
    }

    private MemberRegisterVO createMemberVO() {
        return MemberRegisterVO.builder()
                .name("테스트")
                .email(EMAIL)
                .password(PW)
                .phone("010-1234-5678")
                .build();
    }

    private MemberDto createMemberDTO(boolean isAuth) {
        return MemberDto.builder()
                .userId(UUID.randomUUID().toString())
                .name("테스트")
                .email(EMAIL)
                .password(BCrypt.hashpw(PW, BCrypt.gensalt()))
                .phone("010-1234-5678")
                .status(MemberDto.Status.REGISTER)
                .role("ROLE_USER," + (isAuth ? "ROLE_AUTH" : "ROLE_NOT_AUTH"))
                .regDt(LocalDateTime.now())
                .udtDt(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("회원 가입에 성공한다.")
    public void testRegisterThenSuccess() {
        // given
        when(memberDao.findByEmail(any())).thenReturn(null);

        // when & then
        assertTrue(memberService.register(memberVO, UUID.randomUUID().toString(), MemberDto.Role.ROLE_USER));
    }

    @Test
    @DisplayName("회원 가입시 같은 이메일, 다른 롤을 가진 경우 가입에 성공한다.")
    public void testRegisterDuplicatedEmailThenSuccess() {
        // given
        when(memberDao.findByEmail(EMAIL)).thenReturn(notAuthMemberDto);

        // when & then
        assertTrue(memberService.checkDuplicatedEmail(EMAIL, MemberDto.Role.ROLE_RIDER));
        assertTrue(memberService.register(memberVO, UUID.randomUUID().toString(), MemberDto.Role.ROLE_RIDER));
    }

    @Test
    @DisplayName("회원 가입시 같은 이메일, 같은 롤을 가진 경우 Exception을 발생시킨다.")
    public void testRegisterDuplicatedAllThenException() {
        // given
        when(memberDao.findByEmail(any())).thenReturn(notAuthMemberDto);

        // when & then
        assertThrows(RuntimeException.class, () -> {
            memberService.register(memberVO, any(), MemberDto.Role.ROLE_USER);
        });
    }

    @Test
    @DisplayName("해당 이메일로 가입된 회원이 존재하는 경우 정상적으로 회원을 조회한다.")
    public void testFindExistMemberThenSuccess() {
        // given
        when(memberDao.findByEmail(any())).thenReturn(notAuthMemberDto);

        // when
        MemberDto findMemberDto = memberService.findMemberByEmail(notAuthMemberDto.getEmail());

        // then
        assertThat(findMemberDto).isNotNull();
        assertThat(findMemberDto.getEmail()).isEqualTo(notAuthMemberDto.getEmail());
        assertThat(findMemberDto.getRole()).isEqualTo(notAuthMemberDto.getRole());
    }

    @Test
    @DisplayName("본인 인증에 성공한다.")
    public void testCertificationThenSuccess() {
        // given
        when(memberDao.findByUserId(any())).thenReturn(notAuthMemberDto);

        // when & then
        assertEquals(MemberService.CertificationResult.SUCEESS, memberService.certification(any(), "FLAB"));
    }

    @Test
    @DisplayName("본인 인증시 유저가 존재하지 않을 경우 Exception을 발생시킨다.")
    public void testCertificationNotFoundMemberThenException() {
        // given
        when(memberDao.findByUserId(any())).thenReturn(null);

        // when & then
        assertThrows(UsernameNotFoundException.class, () -> {
            memberService.certification(any(), "FLAB");
        });
    }

    @Test
    @DisplayName("본인 인증시 이미 인증된 유저인 경우 Exception을 발생시킨다.")
    public void testCertificationExistAuthRoleThenException() {
        // given
        when(memberDao.findByUserId(any())).thenReturn(authMemberDto);

        // when & then
        assertEquals(MemberService.CertificationResult.PREV_AUTH, memberService.certification(any(), "FLAB"));
    }

    @Test
    @DisplayName("본인 인증시 인증 코드가 다른 경우 인증에 실패한다.")
    public void testCertificationNotMatchCodeThenFail() {
        // given
        when(memberDao.findByUserId(any())).thenReturn(notAuthMemberDto);

        // when & then
        assertEquals(MemberService.CertificationResult.NOT_MATCH, memberService.certification(any(), "FLAC"));
    }

    @Test
    @DisplayName("회원 탈퇴에 성공한다.")
    public void testWithdrawThenSuccess() {
        // given
        when(memberDao.findByEmail(any())).thenReturn(authMemberDto);

        // when & then
        UserRequest userRequest = UserRequest.builder()
                .email(authMemberDto.getEmail())
                .password(PW)
                .build();
        assertTrue(memberService.withdraw(userRequest));
    }

    @Test
    @DisplayName("회원 탈퇴시 유저가 존재하지 않을 경우 Exception을 발생시킨다.")
    public void testWithdrawNotFoundMemberThenException() {
        when(memberDao.findByEmail(any())).thenReturn(null);

        // when & then
        UserRequest userRequest = UserRequest.builder()
                .email(EMAIL)
                .password(PW)
                .build();
        assertThrows(UsernameNotFoundException.class, () -> {
            memberService.withdraw(userRequest);
        });
    }

    @Test
    @DisplayName("회원 탈퇴시 비밀번호가 일치하지 않는 경우 Exception을 발생시킨다.")
    public void testWithdrawNotMatchPwThenException() {
        // given
        when(memberDao.findByEmail(any())).thenReturn(authMemberDto);

        // when & then
        UserRequest userRequest = UserRequest.builder()
                .email(authMemberDto.getEmail())
                .password("test5678")
                .build();
        assertThrows(BadCredentialsException.class, () -> {
            memberService.withdraw(userRequest);
        });
    }

    @Test
    @DisplayName("회원 정보 수정에 성공한다.")
    public void testModifyThenSuccess() {
        // given
        when(memberDao.findByUserId(any())).thenReturn(authMemberDto);

        // when & then
        MemberUpdateVO updateVO = MemberUpdateVO.builder()
                .phone("010-1234-9876")
                .build();
        assertTrue(memberService.modifyMember(any(), updateVO));
    }

    @Test
    @DisplayName("회원 정보 수정시 유저가 존재하지 않을 경우 Exception을 발생시킨다.")
    public void testModifyNotFoundMemberThenException() {
        // given
        when(memberDao.findByUserId(any())).thenReturn(null);

        // when & then
        MemberUpdateVO updateVO = MemberUpdateVO.builder()
                .phone("010-1234-9876")
                .build();
        assertThrows(UsernameNotFoundException.class, () -> {
            memberService.modifyMember(any(), updateVO);
        });
    }
}
