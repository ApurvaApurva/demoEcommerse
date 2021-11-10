package com.example.genestore.service;

import java.util.Collection;

import com.example.genestore.model.User;

public interface UserService {
    User findOne(String email);

    Collection<User> findByRole(String role);

    User save(User user);

    User update(User user);
}
