package io.sea.transaction.service;

import com.sun.tools.javac.util.List;
import io.sea.transaction.model.Transaction;
import io.sea.transaction.model.User;
import io.sea.transaction.repository.TransactionHistoryRepository;
import io.sea.transaction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    @Qualifier("TransactionRepositoryGrpcBean")
    private TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Transaction createTransaction(String senderId, String receiverId, float amount) throws InterruptedException {
        User sender = new User(senderId, -amount);
        User receiver = new User(senderId, amount);
        userRepository.updateBalance(List.of(sender, receiver));
        Transaction transaction = new Transaction(senderId, receiverId, amount);
        transactionHistoryRepository.publishHistory(transaction);
        return transaction;
    }
}
