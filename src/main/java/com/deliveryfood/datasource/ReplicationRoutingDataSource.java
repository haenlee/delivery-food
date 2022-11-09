package com.deliveryfood.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        log.info("[ReplicationRoutingDataSource] determineCurrentLookupKey=" + (TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? "slave" : "master"));
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? "slave" : "master";
    }

}