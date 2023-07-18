package com.example.springdataintrolab.services;

import com.example.springdataintrolab.models.Account;
import com.example.springdataintrolab.models.User;
import com.example.springdataintrolab.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void createAccount(User user) {
        Account account = new Account(BigDecimal.ZERO, user);

        accountRepository.save(account);

    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        Optional<Account> byId = accountRepository.findById(id);

        if (byId.isEmpty()) {
            throw new IllegalArgumentException("Account is missing!");
        }

        Account account = byId.get();
        BigDecimal oldBalance = account.getBalance();

        if (oldBalance.compareTo(money) < 0) {
            throw new IllegalArgumentException("Not enough balance!");
        }

        BigDecimal newBalance = oldBalance.subtract(money);

        account.setBalance(newBalance);
        accountRepository.save(account);

    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {

        Optional<Account> byId = accountRepository.findById(id);

        if (byId.isEmpty()) {
            throw new IllegalArgumentException("Account is missing!");
        }

        Account account = byId.get();

        Optional<Account> byUserId = accountRepository.findUserById(id);

        if (byUserId.isEmpty()) {
            throw new IllegalArgumentException("No user found!");
        }

        if (money.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Transfer value is negative!");
        }

        BigDecimal newBalance = account.getBalance().add(money);

        account.setBalance(newBalance);
        accountRepository.save(account);

    }

}
