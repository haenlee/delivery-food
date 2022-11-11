package com.deliveryfood.service;

import com.deliveryfood.dao.CartDao;
import com.deliveryfood.dto.CartMenuDto;
import com.deliveryfood.service.impl.CartService;
import com.deliveryfood.service.model.CartMenuVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private CartDao cartDao;

    private String userId;
    private List<CartMenuDto> cartMenuList;
    private List<CartMenuDto> cartMenuFullList;

    @BeforeEach
    public void init() {
        userId = UUID.randomUUID().toString();
        cartMenuList = createCartList(userId);
        cartMenuFullList = createCartFullList(userId);
    }

    private List<CartMenuDto> createCartList(String userId) {
        List<CartMenuDto> cartMenuDtoList = new ArrayList<>();
        int menuId = 1000;
        for(int i = 1; i <= 4; ++i) {
            CartMenuDto menuDto = CartMenuDto.builder()
                    .userId(userId)
                    .idx(i)
                    .menuId(menuId++)
                    .count(1)
                    .regDt(LocalDateTime.now())
                    .build();
            cartMenuDtoList.add(menuDto);
        }
        return cartMenuDtoList;
    }

    private List<CartMenuDto> createCartFullList(String userId) {
        List<CartMenuDto> cartMenuDtoList = new ArrayList<>();
        int menuId = 1000;
        for(int i = 1; i <= CartService.MAX_CART_MENU_COUNT; ++i) {
            CartMenuDto menuDto = CartMenuDto.builder()
                    .userId(userId)
                    .idx(i)
                    .menuId(menuId++)
                    .count(1)
                    .regDt(LocalDateTime.now())
                    .build();
            cartMenuDtoList.add(menuDto);
        }
        return cartMenuDtoList;
    }

    @Test
    @DisplayName("장바구니 조회에 성공한다.")
    public void testFindCartThenSuccess() {
        // given
        when(cartDao.findCart(any())).thenReturn(cartMenuList);

        // when
        List<CartMenuDto> findCartMenuList =  cartService.findCart(any());

        // then
        for(CartMenuDto menuDto : findCartMenuList) {
            assertThat(menuDto.getUserId()).isEqualTo(userId);
        }
    }

    @Test
    @DisplayName("장바구니 조회시 해당 유저의 장바구니가 존재하지 않는 경우 Exception을 발생시킨다.")
    public void testFindCartNotFoundUserThenException() {
        // given
        when(cartDao.findCart(any())).thenReturn(null);

        // when & given
        assertThrows(NullPointerException.class, () -> {
            cartService.findCart(userId);
        });
    }

    @Test
    @DisplayName("장바구니 삭제에 성공한다.")
    public void testDeleteCartThenSuccess() {
        // given
        when(cartDao.findCart(any())).thenReturn(cartMenuList);

        // when & then
        assertTrue(cartService.deleteCart(userId));
    }

    @Test
    @DisplayName("장바구니 삭제시 해당 유저의 장바구니가 존재하지 않는 경우 Exception을 발생시킨다.")
    public void testDeleteCartNotFoundUserThenException() {
        // given
        when(cartDao.findCart(any())).thenReturn(null);

        // when & then
        assertThrows(NullPointerException.class, () -> {
            cartService.deleteCart(userId);
        });
    }

    @Test
    @DisplayName("장바구니에 메뉴 추가에 성공한다.")
    public void testAddMenuThenSuccess() {
        // given
        when(cartDao.findCart(any())).thenReturn(cartMenuList);

        // when & then
        CartMenuVO cartMenuVO = CartMenuVO.builder()
                .userId(userId)
                .idx(5)
                .menuId(2000)
                .count(1)
                .build();
        assertTrue(cartService.addMenu(cartMenuVO));
    }

    @Test
    @DisplayName("장바구니 메뉴 추가시 장바구니 최대 개수를 초과한 경우 Exception을 발생시킨다.")
    public void testAddMenuOverMaxCountThenException() {
        // given
        when(cartDao.findCart(any())).thenReturn(cartMenuFullList);

        // when & then
        CartMenuVO cartMenuVO = CartMenuVO.builder()
                .userId(userId)
                .idx(5)
                .menuId(2000)
                .count(1)
                .build();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            cartService.addMenu(cartMenuVO);
        });
    }

    @Test
    @DisplayName("장바구니 메뉴 추가시 장바구니 메뉴가 중복된 경우 Exception을 발생시킨다.")
    public void testAddMenuDuplicatedMenuIdThenException() {
        // given
        when(cartDao.findCart(any())).thenReturn(cartMenuList);

        // when & then
        CartMenuVO cartMenuVO = CartMenuVO.builder()
                .userId(userId)
                .idx(5)
                .menuId(1000)
                .count(1)
                .build();
        assertThrows(RuntimeException.class, () -> {
            cartService.addMenu(cartMenuVO);
        });
    }

    @Test
    @DisplayName("장바구니 메뉴 삭제에 성공한다.")
    public void testDeleteMenuThenSuccess() {
        // given
        when(cartDao.findCart(any())).thenReturn(cartMenuList);

        // when & then
        assertTrue(cartService.deleteMenu(userId, 1));
    }

    @Test
    @DisplayName("장바구니 메뉴 삭제시 장바구니 메뉴가 없는 경우 Exception을 발생시킨다.")
    public void testDeleteMenuNotFoundMenuThenException() {
        // given
        when(cartDao.findCart(any())).thenReturn(null);

        // when & then
        assertThrows(RuntimeException.class, () -> {
            cartService.deleteMenu(userId, 1);
        });
    }

    @Test
    @DisplayName("장바구니 메뉴 삭제시 장바구니 인덱스가 1보다 작은 경우 Exception을 발생시킨다.")
    public void testDeleteMenuUnder1ThenException() {
        // given
        when(cartDao.findCart(any())).thenReturn(cartMenuList);

        // when & then
        assertThrows(IndexOutOfBoundsException.class, () -> {
            cartService.deleteMenu(userId, 0);
        });
    }

    @Test
    @DisplayName("장바구니 메뉴 삭제시 장바구니 인덱스가 10보다 큰 경우 Exception을 발생시킨다.")
    public void testDeleteMenuOver10ThenException() {
        // given
        when(cartDao.findCart(any())).thenReturn(cartMenuList);

        // when & then
        assertThrows(IndexOutOfBoundsException.class, () -> {
            cartService.deleteMenu(userId, 13);
        });
    }

    @Test
    @DisplayName("장바구니 메뉴 삭제시 장바구니 인덱스가 존재하지 않는 경우 Exception을 발생시킨다.")
    public void testDeleteMenuNotExistIndexThenException() {
        // given
        when(cartDao.findCart(any())).thenReturn(cartMenuList);

        // when & then
        assertThrows(RuntimeException.class, () -> {
            cartService.deleteMenu(userId, 5);
        });
    }
}
