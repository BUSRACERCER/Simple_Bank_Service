package com.works.bankservice.controllers;

import com.works.bankservice.entities.BankAccount;
import com.works.bankservice.model.AccountDTO;
import com.works.bankservice.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class BankAccountRestController {
    private final BankAccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createBankAccount(@RequestBody @Valid AccountDTO accountDTO) {
        return ResponseEntity.ok(accountService.createBankAccount(accountDTO));
    }

    @GetMapping("/{accountId}")
    public BankAccount getBankAccount(@PathVariable @NotNull Long accountId) {
        return accountService.getBankAccount(accountId);
    }

    @GetMapping("/{accountId}/balance")
    public double getBalance(@PathVariable Long accountId) {
        return accountService.getBalance(accountId);
    }

    @PostMapping("/{accountId}/credit")
    public ResponseEntity<BankAccount> creditAccount(@PathVariable Long accountId, @RequestParam double amount) {
        return accountService.credit(accountId, amount);
    }

    @PostMapping("/{accountId}/debit")
    public ResponseEntity<String> debitAccount(@PathVariable Long accountId, @RequestParam double amount) {
        return accountService.debit(accountId, amount);
    }
}
