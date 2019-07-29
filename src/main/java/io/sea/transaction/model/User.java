package io.sea.transaction.model;

public class User {
    private String userId;
    private float balance;

    public User(String userId, float balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public float getBalance() {
        return balance;
    }
}
