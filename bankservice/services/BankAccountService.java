package com.works.bankservice.services;

import com.works.bankservice.entities.BankAccount;
import com.works.bankservice.entities.Transaction;
import com.works.bankservice.mapper.AccountMapper;
import com.works.bankservice.model.AccountDTO;
import com.works.bankservice.model.InsufficientBalanceException;
import com.works.bankservice.repositories.BankAccountRepository;
import com.works.bankservice.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BankAccountService {
    private final BankAccountRepository accountRepository;
    private final TransactionRepository transactionRepository;


    public AccountDTO createBankAccount(AccountDTO accountDTO) {
        BankAccount account = AccountMapper.dtoToAccount(accountDTO);
        account.setBalance(0.0);
        BankAccount createdAccount = accountRepository.save(account);
        return AccountMapper.accountToDto(createdAccount);
    }

    public BankAccount getBankAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Bank Account not found."));
    }

    public double getBalance(Long accountId) {
        BankAccount account = getBankAccount(accountId);
        return account.getBalance();
    }

    public ResponseEntity<BankAccount> credit(Long accountId, double amount) {
        BankAccount account = getBankAccount(accountId);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            accountRepository.save(account);
            recordTransaction(account, "Credit", amount);
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    public ResponseEntity<String> debit(Long accountId, double amount) {
        BankAccount account = getBankAccount(accountId);

        if (account != null) {
            if (account.getBalance() < amount) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance.");
            } else {
                account.setBalance(account.getBalance() - amount);
                accountRepository.save(account);
                recordTransaction(account, "Debit", amount);
                return ResponseEntity.ok("Debit successful.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bank Account not found.");
        }
    }

    private void recordTransaction(BankAccount account, String type, double amount) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setType(type);
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());
        transactionRepository.save(transaction);
    }
}

