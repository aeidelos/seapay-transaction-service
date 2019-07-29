package io.sea.transaction.configuration;

import io.sea.transaction.repository.TransactionHistoryRepositoryGrpc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionGrpcConfig {

    @Value("${history-service.grpc.host}")
    private String grpcHost;

    @Value("${history-service.grpc.port}")
    private int grpcPort;

    @Bean(name = "TransactionRepositoryGrpcBean")
    public TransactionHistoryRepositoryGrpc rpcHostBean() {
        return new TransactionHistoryRepositoryGrpc(grpcHost, grpcPort);
    }
}
