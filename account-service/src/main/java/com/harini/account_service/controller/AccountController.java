package com.harini.account_service.controller;

import com.harini.account_service.dto.TransferRequest;
import com.harini.account_service.entity.Account;
import com.harini.account_service.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping("/create")
    public String create(@RequestBody Account account) {
        return service.create(account);
    }

    @GetMapping("/{email}")
    public Account get(@PathVariable String email) {
        return service.get(email);
    }

    @PutMapping("/deposit/{email}/{amount}")
    public String deposit(@PathVariable String email,
                          @PathVariable Double amount) {
        return service.deposit(email, amount);
    }

    @PutMapping("/withdraw/{email}/{amount}")
    public String withdraw(@PathVariable String email,
                           @PathVariable Double amount) {
        return service.withdraw(email, amount);
    }
    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferRequest request) {
        return service.transfer(request);
    }
}