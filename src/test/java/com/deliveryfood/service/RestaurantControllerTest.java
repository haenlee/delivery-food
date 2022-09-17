package com.deliveryfood.service;

import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.GetMapping;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("레스토랑ID로 레스토랑을 조회한다.")
    void searchRestaurants() throws Exception {
        //given and when
        int restaurantId = 1;

        //then
        mvc.perform(get("/restaurants/" + restaurantId))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("레스토랑을 생성 후 해당 레스토랑을 조회한다.")
    void createRestaurant() throws Exception {
        //given and when
        int restaurantId = 2;
        int userId = restaurantId + 10000;
        String name = "양념치킨 전문점";

        mvc.perform(post("/restaurants/" + restaurantId)
                        .param("userId", String.valueOf(userId))
                        .param("name", name))
                .andExpect(status().isOk());

        //then
        mvc.perform(get("/restaurants/" + restaurantId))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("레스토랑의 메뉴를 생성 후 해당 레스토랑의 메뉴들을 조회한다.")
    void createMenus_searchMenusByRestaurantId() throws Exception {
        //given and when
        int restaurantId = 1;
        int menuId = restaurantId + 20000;
        String name = "후라이드치킨";

        mvc.perform(post("/restaurants/" + restaurantId + "/menus")
                        .param("menuId", String.valueOf(menuId))
                        .param("name", name))
                .andExpect(status().isOk());

        //then
        mvc.perform(get("/restaurants/" + restaurantId + "/menus"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("레스토랑의 메뉴를 2개 생성 후 해당 레스토랑의 메뉴들을 모두 조회한다.")
    void createMenus_searchMenusByRestaurantId_many() throws Exception {
        //given and then
        int restaurantId = 2;
        int menuId_1st = restaurantId + 20000;
        String name_1st = "후라이드치킨";
        int menuId_2nd = restaurantId + 30000;
        String name_2nd = "양념치킨";

        mvc.perform(post("/restaurants/" + restaurantId + "/menus")
                        .param("menuId", String.valueOf(menuId_1st))
                        .param("name", name_1st))
                .andExpect(status().isOk());
        mvc.perform(post("/restaurants/" + restaurantId + "/menus")
                        .param("menuId", String.valueOf(menuId_2nd))
                        .param("name", name_2nd))
                .andExpect(status().isOk());

        //then
        mvc.perform(get("/restaurants/" + restaurantId + "/menus"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("메뉴를 조회한다.")
    void searchMenuByRestaurantIdAndMenuId() throws Exception {
        //given and when
        int restaurantId = 1;
        int menuId = restaurantId + 20000;
        /*
        MockHttpServletResponse response   = mvc.perform(get("/restaurants/" + restaurantId + "/menus/" + menuId))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString(Charset.forName("UTF-8"))).isEqualTo(resMsg);
        */

        //then
        mvc.perform(get("/restaurants/" + restaurantId + "/menus/" + menuId))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("메뉴 정보를 수정 후 해당 메뉴를 조회한다.")
    void updateMenusByRestaurantIdAndMenuId() throws Exception {
        //given
        int restaurantId = 1;
        int menuId = restaurantId + 20000;
        String name = "반반치킨";

        //when
        mvc.perform(put("/menu/" + restaurantId + "/" + menuId)
                        .param("name", name))
                .andExpect(status().isOk());

        //then
        mvc.perform(get("/restaurants/" + restaurantId + "/menus/" + menuId))
                .andExpect(status().isOk())
                .andDo(print());
    }



















}