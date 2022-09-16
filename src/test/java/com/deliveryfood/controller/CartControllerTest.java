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
class CartControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private CartController cartController;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    @Test
    @DisplayName("userId 로부터 장바구니를 조회한다.")
    public void testFindCart() throws Exception {
        int userId = ArgumentMatchers.anyInt();
        mockMvc.perform(get("/carts/" + userId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("userId 로부터 장바구니를 삭제한다.")
    public void testDeleteCart() throws Exception {
        int userId = ArgumentMatchers.anyInt();
        mockMvc.perform(delete("/carts/" + userId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("menuId, optionId, subOptionId 를 사용해서 장바구니에 메뉴를 추가한다.")
    public void testAddMenu() throws Exception {
        int menuId = ArgumentMatchers.anyInt();
        int optionId = ArgumentMatchers.anyInt();
        int subOptionId = ArgumentMatchers.anyInt();
        mockMvc.perform(put("/carts/add/" + menuId + "/" + optionId + "/" + subOptionId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("menuId 로부터 장바구니에 매뉴를 삭제한다.")
    public void testDeleteMenu() throws Exception {
        int menuId = ArgumentMatchers.anyInt();
        mockMvc.perform(delete("/carts/sub/" + menuId))
                .andExpect(status().isOk());
    }
}