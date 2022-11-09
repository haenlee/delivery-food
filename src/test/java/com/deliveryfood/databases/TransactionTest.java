package com.deliveryfood.databases;

import com.deliveryfood.model.MenuInput;
import com.deliveryfood.service.test.MenuServiceToTransactionTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        //테스트 조건
        // 1 : 테스트를 실행하는 클래스 및 메서드에서 @Transactional이 선언되어 있지 않음
        // 2 : 호출하는 서비스가 @Transactional이 선언되어 있음
        // 3 : 1st insert 성공, 2nd insert 성공 후 Unchecked Exception(RuntimeException) 발생
        // 4 : 트랜잭션 매니저에 의하여 rollback되어 count가 0으로 확인

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
    @DisplayName("2번째 insert에서 Checked이 발생 할 경우 롤백되므로 지 아니함을 확인")
    void txRollbackTest2() {
        //테스트 조건
        // 1 : 테스트를 실행하는 클래스 및 메서드에서 @Transactional이 선언되어 있지 않음
        // 2 : 호출하는 서비스가 @Transactional(rollbackFor = ClassNotFoundException.class)이 선언되어 있음
        // 3 : 1st insert 성공, 2nd insert 성공 후 Checked Exception(ClassNotFoundException) 발생
        // 4 : Checked Exception이면 롤백되지 않아서 count가 2여야 하지만, 명시된 옵션대로 트랜잭션 매니저에 의하여 rollback되어 count가 0로 확인

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
        assertEquals(0, menuServiceToTxTest.findMenuByIdToTxTest(menuInput3).size());
    }
}