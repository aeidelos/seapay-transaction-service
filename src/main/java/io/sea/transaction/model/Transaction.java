package io.sea.transaction.model;

public class Transaction {
    private String senderId;
    private String receiverId;
    private float amount;

    public Transaction(String senderId, String receiverId, float amount) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public float getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "senderId='" + senderId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
