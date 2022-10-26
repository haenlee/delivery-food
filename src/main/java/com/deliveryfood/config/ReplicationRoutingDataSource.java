package com.deliveryfood.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Map;

@Slf4j
public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        log.info("###### TESTLOG determineCurrentLookupKey");
        log.info("###### TESTLOG conn DB name=" + (TransactionSynchronizationManager.isCurrentTransactionReadOnly()  ? "slave" : "master"));
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? "slave" : "master";
    }

}