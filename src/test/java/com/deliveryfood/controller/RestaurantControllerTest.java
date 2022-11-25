package com.deliveryfood.controller;

import com.deliveryfood.controller.model.request.MenuRequest;
import com.deliveryfood.controller.model.request.RestaurantMenuOptionRequest;
import com.deliveryfood.controller.model.request.RestaurantRegisterRequest;
import com.deliveryfood.service.IMenuService;
import com.deliveryfood.service.IOptionService;
import com.deliveryfood.service.IRestaurantService;
import com.deliveryfood.service.ISubOptionService;
import com.deliveryfood.service.model.MenuRegisterVO;
import com.deliveryfood.service.model.OptionVO;
import com.deliveryfood.service.model.RestaurantRegisterVO;
import com.deliveryfood.service.model.SubOptionVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.testSecurityContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class RestaurantControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private RestaurantController restaurantController;

    @Autowired
    private IOptionService optionService;

    @Autowired
    private ISubOptionService subOptionService;

    @Autowired
    private IRestaurantService restaurantService;

    @Autowired
    private IMenuService menuService;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(restaurantController)
                .defaultRequest(get("/").with(testSecurityContext()))
                .build();

        createDummyData();
    }

    @AfterEach
    public void tearDown() {
        deleteDummyData();
    }

    private void createDummyData() {
        //create Option Dummy Data
        optionService.createOption(OptionVO.builder().optionId("1001").menuId("2001").name("create Option Dummy Data 1").build());
        optionService.createOption(OptionVO.builder().optionId("1002").menuId("2001").name("create Option Dummy Data 2").build());
        optionService.createOption(OptionVO.builder().optionId("1003").menuId("2002").name("create Option Dummy Data 3").build());
        optionService.createOption(OptionVO.builder().optionId("1004").menuId("2001").name("create Option Dummy Data 4").build());

        //create SubOption Dummy Data
        subOptionService.createSubOption(SubOptionVO.builder().optionId("1001").menuId("2001").subOptionId("3001").build());
        subOptionService.createSubOption(SubOptionVO.builder().optionId("1001").menuId("2001").subOptionId("3002").build());
        subOptionService.createSubOption(SubOptionVO.builder().optionId("1002").menuId("2001").subOptionId("3003").build());
        subOptionService.createSubOption(SubOptionVO.builder().optionId("1003").menuId("2002").subOptionId("3004").build());

        //create Restaurant Dummy Data
        restaurantService.register(RestaurantRegisterVO.builder().restaurantId("4001").userId("5001").name("create Restaurant Dummy Data 1").build());
        restaurantService.register(RestaurantRegisterVO.builder().restaurantId("4002").userId("5002").name("create Restaurant Dummy Data 2").build());

        //create Menu Dummy Data
        menuService.createMenuById(MenuRegisterVO.builder().menuId("2001").restaurantId("4001").name("create Menu Dummy Data 1").build());
        menuService.createMenuById(MenuRegisterVO.builder().menuId("2002").restaurantId("4002").name("create Menu Dummy Data 2").build());
    }

    private void deleteDummyData() {
        //delete Option Dummy Data
        optionService.deleteOptionById(OptionVO.builder().optionId("1001").build());
        optionService.deleteOptionById(OptionVO.builder().optionId("1002").build());
        optionService.deleteOptionById(OptionVO.builder().optionId("1003").build());
        optionService.deleteOptionById(OptionVO.builder().optionId("1004").build());

        //delete SubOption Dummy Data
        subOptionService.deleteSubOptionById(SubOptionVO.builder().subOptionId("3001").build());
        subOptionService.deleteSubOptionById(SubOptionVO.builder().subOptionId("3002").build());
        subOptionService.deleteSubOptionById(SubOptionVO.builder().subOptionId("3003").build());
        subOptionService.deleteSubOptionById(SubOptionVO.builder().subOptionId("3004").build());

        //delete Restaurant Dummy Data
        restaurantService.deleteByRestaurantId("4001");
        restaurantService.deleteByRestaurantId("4002");

        //delete Menu Dummy Data
        menuService.deleteByMenuId("2001");
        menuService.deleteByMenuId("2002");
    }

    @Test
    @DisplayName("restaurantId로부터 레스토랑 회원을 조회한다.")
    void findUserById() throws Exception {
        //given and when
        RestaurantRegisterRequest registerRequest = RestaurantRegisterRequest.builder()
                .restaurantId("4001")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        //then
        mockMvc.perform(get("/restaurants/" + registerRequest.getRestaurantId())
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("레스토랑 메뉴를 생성한다.")
    void createMenuById() throws Exception {
        //given and when
        MenuRequest menuRequest = MenuRequest.builder()
                .restaurantId("4003")
                .name("createMenuById 3")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        //then
        mockMvc.perform(post("/restaurants/"
                        + menuRequest.getRestaurantId()
                        + "/menus")
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuRequest)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("해당 레스토랑의 메뉴들을 조회한다.")
    void findMenuById() throws Exception {
        //given and when
        MenuRequest menuRequest = MenuRequest.builder()
                .restaurantId("4001")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        //then
        mockMvc.perform(get("/restaurants/"
                        + menuRequest.getRestaurantId()
                        + "/menus/")
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuRequest)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("메뉴 정보를 수정한다.")
    void modifyMenuById() throws Exception {
        //given and when
        MenuRequest menuRequest = MenuRequest.builder()
                .restaurantId("4002")
                .menuId("6002")
                .name("modifyMenuById")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        //then
        mockMvc.perform(put("/restaurants/"
                        + menuRequest.getRestaurantId()
                        + "/menus/"
                        + menuRequest.getMenuId())
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuRequest)))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @DisplayName("해당 메뉴의 하위옵션들을 조회한다.")
    void findSubOptions() throws Exception {
        //given and when
        RestaurantMenuOptionRequest restaurantMenuOptionRequest = RestaurantMenuOptionRequest.builder()
                .restaurantId("4001")
                .menuId("2001")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        //then
        mockMvc.perform(get("/restaurants/"
                        + restaurantMenuOptionRequest.getRestaurantId()
                        + "/menus/"
                        + restaurantMenuOptionRequest.getMenuId())
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurantMenuOptionRequest)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
