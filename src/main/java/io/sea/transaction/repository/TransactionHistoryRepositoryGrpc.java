package io.sea.transaction.repository;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.sea.grpc.wallet.PostTransactionRequest;
import io.sea.grpc.wallet.PostTransactionResponse;
import io.sea.grpc.wallet.WalletGrpc;
import io.sea.transaction.model.Transaction;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class TransactionHistoryRepositoryGrpc implements TransactionHistoryRepository {
    private static final Logger logger = Logger.getLogger(TransactionHistoryRepositoryGrpc.class.getName());

    private final ManagedChannel channel;
    private final WalletGrpc.WalletBlockingStub blockingStub;

    public TransactionHistoryRepositoryGrpc(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build());
    }

    TransactionHistoryRepositoryGrpc(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = WalletGrpc.newBlockingStub(channel);
    }

    private void close() throws InterruptedException {
        channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
    }

    @Override
    public void publishHistory(Transaction transaction) throws InterruptedException {
        try {
            logger.info("publish transaction.. " + transaction.toString());
            PostTransactionRequest request =
                    PostTransactionRequest.newBuilder()
                            .setFromId(transaction.getSenderId())
                            .setToId(transaction.getReceiverId())
                            .setValue(transaction.getAmount()).build();
            PostTransactionResponse response = blockingStub.postTransaction(request);
            if (response.getDefaultInstanceForType().getSuccess()) {
                logger.info("transaction complete.. " + transaction.toString() + response.getDefaultInstanceForType().toString());
            } else {
                throw new RuntimeException("failed to publish transaction" + transaction.toString() + "w/ msg:" + response.getError());
            }
        } finally {
            this.close();
        }
    }
}
