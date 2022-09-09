package com.deliveryfood.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class RestaurantControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private RestaurantController restaurantController;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
    }

    @Test
    @DisplayName("가게 회원가입시 본인 인증을 처리한다.")
    public void testCertification() throws Exception {
        mockMvc.perform(post("/restaurants/certification"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("가게 회원 가입을 한다")
    public void testSignin() throws Exception {
        mockMvc.perform(post("/restaurants/signin"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("가게 회원 탈퇴를 한다.")
    public void testSignout() throws Exception {
        mockMvc.perform(post("/restaurants/signout"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("가게 회원 로그인을 한다.")
    public void testLogin() throws Exception {
        mockMvc.perform(post("/restaurants/login"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("가게 회원 로그아웃을 한다.")
    public void testLogout() throws Exception {
        mockMvc.perform(post("/restaurants/logout"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("userId로부터 가게 회원을 조회한다.")
    public void testFindUser() throws Exception {
        int userId = ArgumentMatchers.anyInt();
        mockMvc.perform(get("/restaurants/" + userId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("userId 로부터 가게 회원 정보를 수정한다.")
    public void testModifyUser() throws Exception {
        int userId = ArgumentMatchers.anyInt();
        mockMvc.perform(put("/restaurants/" + userId))
                .andExpect(status().isOk());
    }
}
