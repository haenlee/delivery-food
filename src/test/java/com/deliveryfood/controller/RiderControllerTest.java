package com.deliveryfood.controller;

import com.deliveryfood.model.RiderInput;
import com.deliveryfood.model.UserRequest;
import com.deliveryfood.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class RiderControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private RiderController riderController;

    @Autowired
    private FilterChainProxy filterChainProxy;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(riderController)
                .apply(springSecurity(filterChainProxy))
                .build();
    }

    @Test
    @DisplayName("라이더 회원가입시 본인 인증을 처리한다.")
    public void testCertification() throws Exception {
        UserRequest userRequest = UserRequest.builder()
                .email("rider@gmail.com")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/riders/certification")
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest))
                        .param("code", MemberService.REGISTER_CODE))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("라이더 회원 가입을 한다")
    public void testRegister() throws Exception {
        RiderInput riderInput = RiderInput.builder()
                .name("라이더")
                .email("rider@gmail.com")
                .password("riderpassword")
                .phone("010-1234-5678")
                .commission(3000)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/riders/register")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(riderInput)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("라이더 회원 탈퇴를 한다.")
    public void testWithdraw() throws Exception {
        UserRequest userRequest = UserRequest.builder()
                .email("rider@gmail.com")
                .password("riderpassword")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/riders/withdraw")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("라이더 회원 로그인을 한다.")
    public void testLogin() throws Exception {
        UserRequest userRequest = UserRequest.builder()
                .email("rider@gmail.com")
                .password("riderpassword")
                .build();

        mockMvc.perform(formLogin()
                        .user(userRequest.getEmail())
                        .password(userRequest.getPassword()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("라이더 회원 로그아웃을 한다.")
    public void testLogout() throws Exception {
        mockMvc.perform(post("/riders/logout"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("userId 로부터 라이더 회원 정보를 수정한다.")
    public void testModifyUser() throws Exception {
        int userId = ArgumentMatchers.anyInt();
        mockMvc.perform(put("/riders/" + userId))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
