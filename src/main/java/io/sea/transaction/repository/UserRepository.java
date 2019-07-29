package io.sea.transaction.repository;

import io.sea.transaction.model.User;

import java.util.List;

public interface UserRepository {
    void updateBalance(List<User> users);
}
