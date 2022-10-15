package com.deliveryfood.controller;

import com.deliveryfood.common.mock.auth.WithAuthMember;
import com.deliveryfood.model.request.UserRegisterRequest;
import com.deliveryfood.model.request.UserRequest;
import com.deliveryfood.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.testSecurityContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private UserController userController;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .defaultRequest(get("/").with(testSecurityContext()))
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @Test
    @DisplayName("회원가입시 본인 인증을 처리한다.")
    @WithAuthMember(username = "test@gmail.com", authority = "ROLE_USER,ROLE_NOT_AUTH")
    public void testCertification() throws Exception {
        mockMvc.perform(post("/users/certification")
                .characterEncoding("utf-8")
                .param("code", MemberService.REGISTER_CODE))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("회원 가입을 한다")
    public void testRegister() throws Exception {
        UserRegisterRequest registerRequest = UserRegisterRequest.builder()
                .name("테스트")
                .email("test@gmail.com")
                .password("testpassword")
                .phone("010-1234-5678")
                .address("서울시 구로구 디지털로")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/users/register")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("회원 탈퇴를 한다.")
    public void testWithdraw() throws Exception {
        UserRequest userRequest = UserRequest.builder()
                .email("test@gmail.com")
                .password("testpassword")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/users/withdraw")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("회원 로그인을 한다.")
    @WithMockUser(roles = "USER")
    public void testLogin() throws Exception {
        UserRequest userRequest = UserRequest.builder()
                .email("test@gmail.com")
                .password("testpassword")
                .build();

        mockMvc.perform(formLogin()
                .user(userRequest.getEmail())
                .password(userRequest.getPassword()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("회원 로그아웃을 한다.")
    public void testLogout() throws Exception {
        mockMvc.perform(post("/users/logout"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("userId 로부터 회원을 조회한다.")
    public void testFindUser() throws Exception {
        int userId = ArgumentMatchers.anyInt();
        mockMvc.perform(get("/users/" + userId))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("userId 로부터 회원 정보를 수정한다.")
    public void testModifyUser() throws Exception {
        int userId = ArgumentMatchers.anyInt();
        mockMvc.perform(put("/users/" + userId))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("유저의 모든 주문을 조회한다.")
    void findOrderByUserId() throws Exception {
        int userId = ArgumentMatchers.anyInt();
        mockMvc.perform(get("/users/" + userId + "/orders")
                        .param("userId", String.valueOf(userId)))
                .andExpect(status().isOk());
    }
}