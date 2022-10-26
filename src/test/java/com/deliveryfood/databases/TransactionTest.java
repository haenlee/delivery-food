package com.deliveryfood.databases;

import com.deliveryfood.controller.RestaurantController;
import com.deliveryfood.model.MenuInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
@SpringBootTest
public class TransactionTest {

    private MockMvc mockMvc;

    @Autowired
    private RestaurantController restaurantController;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
    }

    @Test
    @DisplayName("Transaction_롤백_테스트")
    void txRollbackTest() {
        // given
        MenuInput menuInput = MenuInput.builder()
                .restaurantId("9419ab0c-9353-491a-aaee-fe0b8d175d5d")
                .name("name 테스트")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        // when
        try {
            mockMvc.perform(post("/restaurants/"
                            + menuInput.getRestaurantId()
                            + "/menus")
                            .characterEncoding("utf-8")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(menuInput)))
                    .andDo(print())
                    .andReturn();
        } catch (Exception e) {
            log.warn("INSERT runtime exception to txRollbackTest");
        }

        //then
        menuInput = MenuInput.builder()
                .restaurantId("9419ab0c-9353-491a-aaee-fe0b8d175d5d")
                .menuId("22ef7e6c-c5c2-4b24-ba2c-d7fd2bb615f2")
                .build();

        // when
        try {
            mockMvc.perform(get("/restaurants/"
                            + menuInput.getRestaurantId()
                            + "/menus/"
                            + menuInput.getMenuId())
                            .characterEncoding("utf-8")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andReturn();
        } catch (Exception e) {
            log.warn("SELECT runtime exception to txRollbackTest 2");
        }

    }
}