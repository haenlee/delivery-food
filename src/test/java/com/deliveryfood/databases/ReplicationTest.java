package com.deliveryfood.databases;

import com.deliveryfood.datasource.ReplicationRoutingDataSource;
import com.deliveryfood.model.MenuInput;
import com.deliveryfood.service.MenuServiceToTransactionTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest
public class ReplicationTest {

    private static final String Test_Method_Name = "determineCurrentLookupKey";

    @Autowired
    private MenuServiceToTransactionTest menuServiceToTxTest;

    private MenuInput menuInput;
    private MenuInput menuInput2;
    private MenuInput menuInput3;

    @Test
    @DisplayName("쓰기_전용_트랜잭션_테스트")
    @Transactional(readOnly = false)
    void writeOnlyTransactionTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // given
        ReplicationRoutingDataSource replicationRoutingDataSource = new ReplicationRoutingDataSource();

        // when
        Method determineCurrentLookupKey = ReplicationRoutingDataSource.class.getDeclaredMethod(Test_Method_Name);
        determineCurrentLookupKey.setAccessible(true);

        String dataSourceType = (String) determineCurrentLookupKey
                .invoke(replicationRoutingDataSource);

        // then
        assertThat(dataSourceType).isEqualTo("master");
    }

    @Test
    @DisplayName("읽기_전용_트랜잭션_테스트")
    @Transactional(readOnly = true)
    void readOnlyTransactionTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // given
        ReplicationRoutingDataSource replicationRoutingDataSource = new ReplicationRoutingDataSource();

        // when
        Method determineCurrentLookupKey = ReplicationRoutingDataSource.class.getDeclaredMethod(Test_Method_Name);
        determineCurrentLookupKey.setAccessible(true);

        String dataSourceType = (String) determineCurrentLookupKey
                .invoke(replicationRoutingDataSource);

        // then
        assertThat(dataSourceType).isEqualTo("slave");
    };



    @Test
    @DisplayName("Readonly 설정된 Replication DB 조회 테스트")
    void readOnlyTransactionTest2() throws Exception {

        // given and when
        menuInput = MenuInput.builder()
                .menuId("menuIdToRollbackTest master")
                .restaurantId("restaurantIdToReplicationTest_master")
                .name("test")
                .build();
        menuInput2 = MenuInput.builder()
                .menuId("menuIdToRollbackTest master2")
                .restaurantId("restaurantIdToReplicationTest_master")
                .name("test")
                .build();
        menuInput3 = MenuInput.builder()
                .restaurantId("restaurantIdToReplicationTest_master")
                .build();

        menuServiceToTxTest.createMenuByIdToTxTest(menuInput, menuInput2);

        // then
        assertEquals(2, menuServiceToTxTest.findMenuByIdToTxTest(menuInput3).size());
        assertEquals(0, menuServiceToTxTest.findMenuByIdToTxTestReadOnly(menuInput3).size());
    }
}