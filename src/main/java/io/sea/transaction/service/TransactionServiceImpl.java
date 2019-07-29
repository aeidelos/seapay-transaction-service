package io.sea.transaction.service;

import io.sea.transaction.model.Transaction;
import io.sea.transaction.repository.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    @Qualifier("TransactionRepositoryGrpcBean")
    private TransactionHistoryRepository transactionHistoryRepository;

    @Override
    public Transaction createTransaction(String senderId, String receiverId, float amount) throws InterruptedException {
        Transaction transaction = new Transaction(senderId, receiverId, amount);
        transactionHistoryRepository.publishHistory(transaction);
        return transaction;
    }
}
