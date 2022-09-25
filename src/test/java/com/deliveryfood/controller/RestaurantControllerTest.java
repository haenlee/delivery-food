package com.deliveryfood.controller;

import com.deliveryfood.model.UserInput;
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
        mockMvc.perform(post("/restaurants/certification")
                .param("code", String.valueOf(ArgumentMatchers.anyInt())))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("가게 회원 가입을 한다")
    public void testRegister() throws Exception {
        UserInput userInput = UserInput.builder()
                .name("테스트")
                .email("test@gmail.com")
                .password("testpassword")
                .phone("010-1234-5678")
                .address("서울시 구로구 디지털로")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/restaurants/register")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userInput)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("가게 회원 탈퇴를 한다.")
    public void testWithdraw() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.set("userId", String.valueOf(ArgumentMatchers.anyInt()));
        map.set("password", "testpassword");

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/restaurants/withdraw")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(map)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("가게 회원 로그인을 한다.")
    public void testLogin() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.set("userId", String.valueOf(ArgumentMatchers.anyInt()));
        map.set("password", "testpassword");

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/restaurants/login")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(map)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("가게 회원 로그아웃을 한다.")
    public void testLogout() throws Exception {
        mockMvc.perform(post("/restaurants/logout"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("userId로부터 가게 회원을 조회한다.")
    public void testFindUser() throws Exception {
        int userId = ArgumentMatchers.anyInt();
        mockMvc.perform(get("/restaurants/" + userId))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("userId 로부터 가게 회원 정보를 수정한다.")
    public void testModifyUser() throws Exception {
        int userId = ArgumentMatchers.anyInt();
        mockMvc.perform(put("/restaurants/" + userId))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("레스토랑ID로 레스토랑을 조회한다.")
    void searchRestaurants() throws Exception {
        //given and when
        int restaurantId = 1;

        //then
        mockMvc.perform(get("/restaurants/" + restaurantId))
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

        mockMvc.perform(post("/restaurants/" + restaurantId)
                        .param("userId", String.valueOf(userId))
                        .param("name", name))
                .andExpect(status().isOk());

        //then
        mockMvc.perform(get("/restaurants/" + restaurantId))
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

        mockMvc.perform(post("/restaurants/" + restaurantId + "/menus")
                        .param("menuId", String.valueOf(menuId))
                        .param("name", name))
                .andExpect(status().isOk());

        //then
        mockMvc.perform(get("/restaurants/" + restaurantId + "/menus"))
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

        mockMvc.perform(post("/restaurants/" + restaurantId + "/menus")
                        .param("menuId", String.valueOf(menuId_1st))
                        .param("name", name_1st))
                .andExpect(status().isOk());
        mockMvc.perform(post("/restaurants/" + restaurantId + "/menus")
                        .param("menuId", String.valueOf(menuId_2nd))
                        .param("name", name_2nd))
                .andExpect(status().isOk());

        //then
        mockMvc.perform(get("/restaurants/" + restaurantId + "/menus"))
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
        mockMvc.perform(get("/restaurants/" + restaurantId + "/menus/" + menuId))
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
        mockMvc.perform(put("/menu/" + restaurantId + "/" + menuId)
                        .param("name", name))
                .andExpect(status().isOk());

        //then
        mockMvc.perform(get("/restaurants/" + restaurantId + "/menus/" + menuId))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
