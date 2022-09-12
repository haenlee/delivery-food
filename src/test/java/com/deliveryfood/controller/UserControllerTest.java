package com.deliveryfood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @DisplayName("회원가입시 본인 인증을 처리한다.")
    public void testCertification() throws Exception {
        mockMvc.perform(post("/users/certification")
                .param("code", String.valueOf(ArgumentMatchers.anyInt())))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("회원 가입을 한다")
    public void testSignin() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.set("userId", String.valueOf(ArgumentMatchers.anyInt()));
        map.set("name", "테스트");
        map.set("email", "test@gmail.com");
        map.set("password", "testpassword");
        map.set("phone", "010-1234-5678");
        map.set("address", "서울시 구로구 디지털로");

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/users/signin")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(map)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("회원 탈퇴를 한다.")
    public void testSignout() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.set("userId", String.valueOf(ArgumentMatchers.anyInt()));
        map.set("password", "testpassword");

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/users/signout")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(map)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("회원 로그인을 한다.")
    public void testLogin() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.set("userId", String.valueOf(ArgumentMatchers.anyInt()));
        map.set("password", "testpassword");

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/users/login")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(map)))
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
}