package com.deliveryfood.databases;

import com.deliveryfood.service.MenuServiceToTransactionTest;
import com.deliveryfood.model.MenuInput;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest
public class TransactionTest {

    @Autowired
    private MenuServiceToTransactionTest menuServiceToTxTest;

    private MenuInput menuInput;
    private MenuInput menuInput2;
    private MenuInput menuInput3;

    @Test
    @DisplayName("2번째 insert에서 Unchecked이 발생 할 경우 롤백됨을 확인")
    void txRollbackTest() {

        // given and when
        menuInput = MenuInput.builder()
                .menuId("menuIdToRollbackTest")
                .restaurantId("restaurantIdToRollbackTest_Unchecked")
                .name("test")
                .build();
        menuInput2 = MenuInput.builder()
                .menuId("menuIdToRollbackTest RuntimeException")
                .restaurantId("restaurantIdToRollbackTest_Unchecked")
                .name("test")
                .build();
        menuInput3 = MenuInput.builder()
                .restaurantId("restaurantIdToRollbackTest_Unchecked")
                .build();

        // then
        Exception e = assertThrows(Exception.class, () -> {
            menuServiceToTxTest.createMenuByIdToTxTest(menuInput, menuInput2);
        });
        assertThat(e.getMessage()).isEqualTo("Unchecked Exception rollback test");
        assertEquals(0, menuServiceToTxTest.findMenuByIdToTxTest(menuInput3).size());
    }


    @Test
    @DisplayName("2번째 insert에서 Checked이 발생 할 경우 롤백되지 아니함을 확인")
    void txRollbackTest2() {

        // given and when
        menuInput = MenuInput.builder()
                .menuId("menuIdToRollbackTest")
                .restaurantId("restaurantIdToRollbackTest_Checked")
                .name("test")
                .build();
        menuInput2 = MenuInput.builder()
                .menuId("menuIdToRollbackTest ClassNotFoundException")
                .restaurantId("restaurantIdToRollbackTest_Checked")
                .name("test")
                .build();
        menuInput3 = MenuInput.builder()
                .restaurantId("restaurantIdToRollbackTest_Checked")
                .build();

        // then
        Exception e = assertThrows(Exception.class, () -> {
            menuServiceToTxTest.createMenuByIdToTxTest(menuInput, menuInput2);
        });
        assertThat(e.getMessage()).isEqualTo("Checked Exception rollback test");
        assertEquals(2, menuServiceToTxTest.findMenuByIdToTxTest(menuInput3).size());
    }
}