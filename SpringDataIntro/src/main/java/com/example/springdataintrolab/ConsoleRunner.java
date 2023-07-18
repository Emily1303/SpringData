package com.example.springdataintrolab;

import com.example.springdataintrolab.models.Account;
import com.example.springdataintrolab.models.User;
import com.example.springdataintrolab.services.AccountService;
import com.example.springdataintrolab.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private AccountService accountService;

    private UserService userService;

    public ConsoleRunner(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {

        User user = new User("Pesho", 23);
        Account account = new Account(BigDecimal.valueOf(23000), user);

        userService.registerUser(user);
        accountService.createAccount(user);

        accountService.transferMoney(new BigDecimal(20000), 1L);
        accountService.withdrawMoney(new BigDecimal(30), 1L);

    }

}
