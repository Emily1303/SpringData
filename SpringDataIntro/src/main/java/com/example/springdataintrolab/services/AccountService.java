package com.example.springdataintrolab.services;

import com.example.springdataintrolab.models.User;

import java.math.BigDecimal;

public interface AccountService {

    void createAccount(User user);

    void withdrawMoney(BigDecimal money, Long id);

    void transferMoney(BigDecimal money, Long id);

}
