package io.sea.transaction.controller;

import io.grpc.StatusRuntimeException;
import io.sea.transaction.model.Transaction;
import io.sea.transaction.service.TransactionService;
import io.sea.transaction.template.ErrorTemplate;
import io.sea.transaction.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    final String ERROR_TITLE = "Transaction Error";

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transaction")
    public ResponseEntity<ResponseTemplate<Transaction>> makeTransaction(
            @RequestParam("sender_id") String senderId,
            @RequestParam("receiver_id") String receiverId,
            @RequestParam("amount") float amount
    ) {
        ResponseTemplate<Transaction> response = null;
        try {
            Transaction result = transactionService.createTransaction(senderId, receiverId, amount);
            response = new ResponseTemplate<>(result, true, ErrorTemplate.blankErrorTemplate());
        } catch (InterruptedException | RuntimeException e) {
            response = new ResponseTemplate<>(null, false, new ErrorTemplate(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), ERROR_TITLE));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.ok().body(response);
    }
}
