package io.sea.transaction.repository;

import io.sea.transaction.model.Transaction;

public interface TransactionHistoryRepository {
    void publishHistory(Transaction transaction) throws InterruptedException;
}
