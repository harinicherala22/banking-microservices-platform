package com.harini.account_service.service;

import com.harini.account_service.dto.TransferRequest;
import com.harini.account_service.entity.Account;
import com.harini.account_service.repository.AccountRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repo;
    private final KafkaProducerService producer;

    public String create(Account account) {

        account.setBalance(0.0);
        repo.save(account);

        return "Account Created successfully";
    }

    @Cacheable(value = "accounts", key = "#email")
    public Account get(String email) {
        System.out.println("Fetching from DB...");
        return repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @CacheEvict(value = "accounts", key = "#email")
    public String deposit(String email, Double amount) {

        Account acc = get(email);

        acc.setBalance(acc.getBalance() + amount);

        repo.save(acc);

        try {
            producer.send("Deposit success...");
        } catch (Exception e) {
            System.out.println("Kafka unavailable");
        }

        return "Amount Deposited successfully";
    }

    @CacheEvict(value = "accounts", key = "#email")
    public String withdraw(String email, Double amount) {

        Account acc = get(email);

        if (acc.getBalance() < amount) {
            throw new RuntimeException("Insufficient Balance");
        }

        acc.setBalance(acc.getBalance() - amount);

        repo.save(acc);

        return "Amount Withdrawn";
    }

    @CacheEvict(value = "accounts",allEntries = true)
    @Transactional
    public String transfer(TransferRequest request) {

        Account sender = repo.findByEmail(request.getFromEmail())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        Account receiver = repo.findByEmail(request.getToEmail())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        if (sender.getBalance() < request.getAmount()) {
            throw new RuntimeException("Insufficient Balance");
        }

        sender.setBalance(sender.getBalance() - request.getAmount());
        receiver.setBalance(receiver.getBalance() + request.getAmount());

        repo.save(sender);
        repo.save(receiver);

        return "Transfer Successful";
    }
}