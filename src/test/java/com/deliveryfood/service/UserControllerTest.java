package com.deliveryfood.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {

    private MockMvc mvc;

    @Autowired
    private UserController userController;

    @BeforeEach
    public void init() {
        mvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @DisplayName("유저의 모든 주문을 조회한다.")
    void findOrderByUserId() throws Exception {
        int userId = ArgumentMatchers.anyInt();
        mvc.perform(get("/users/" + userId + "/orders")
                        .param("userId", String.valueOf(userId)))
                .andExpect(status().isOk());
    }
}