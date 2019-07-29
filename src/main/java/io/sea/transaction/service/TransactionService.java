package io.sea.transaction.service;

import io.sea.transaction.model.Transaction;

public interface TransactionService {
    Transaction createTransaction(String senderId, String receiverId, float amount) throws InterruptedException;
}
