package com.deliveryfood.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class OrderControllerTest {

    private MockMvc mvc;

    @Autowired
    private OrderController orderController;

    @BeforeEach
    public void init() {
        mvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    @DisplayName("주문을 생성한다.")
    void createOrder() throws Exception {
        mvc.perform(post("/orders/checkout"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("주문을 조회한다.")
    void findOrder() throws Exception {
        int orderId = ArgumentMatchers.anyInt();
        mvc.perform(get("/orders/" + orderId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("주문을 수정한다.")
    void modifyOrder() throws Exception {
        int orderId = ArgumentMatchers.anyInt();
        mvc.perform(put("/orders/" + orderId))
                .andExpect(status().isOk());
    }
}