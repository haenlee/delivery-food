package com.deliveryfood.service;

import com.deliveryfood.dto.MenuDto;
import com.deliveryfood.mapper.MenuMapper;
import com.deliveryfood.model.MenuInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MenuServiceToTransactionTest {

    private final MenuMapper menuMapper;

    @Transactional(rollbackFor = ClassNotFoundException.class)
    public void createMenuByIdToTxTest(MenuInput menuInput, MenuInput menuInput2) throws Exception {
        MenuDto menuDto = MenuDto.builder()
                .menuId(menuInput.getMenuId())
                .restaurantId(menuInput.getRestaurantId())
                .name(menuInput.getName())
                .build();
        menuMapper.createMenuById(menuDto);

        MenuDto menuDto_2nd = MenuDto.builder()
                .menuId(menuInput2.getMenuId())
                .restaurantId(menuInput2.getRestaurantId())
                .name(menuInput2.getName())
                .build();
        menuMapper.createMenuById(menuDto_2nd);

        if ("menuIdToRollbackTest RuntimeException".equals(menuDto_2nd.getMenuId())) {
            log.info("createMenuById 에러발생");
            throw new RuntimeException("Unchecked Exception rollback test");
        }
        if ("menuIdToRollbackTest ClassNotFoundException".equals(menuDto_2nd.getMenuId())) {
            log.info("createMenuById 에러발생");
            throw new ClassNotFoundException("Checked Exception rollback test");
        }
    }

    public List<MenuDto> findMenuByIdToTxTest(MenuInput menuInput) {
        MenuDto menuDto = MenuDto.builder()
                .restaurantId(menuInput.getRestaurantId())
                .build();
        return menuMapper.findMenuById(menuDto);
    }


    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<MenuDto> findMenuByIdToTxTestReadOnly(MenuInput menuInput) {
        MenuDto menuDto = MenuDto.builder()
                .restaurantId(menuInput.getRestaurantId())
                .build();
        return menuMapper.findMenuById(menuDto);
    }

}