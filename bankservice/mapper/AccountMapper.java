package com.works.bankservice.mapper;

import com.works.bankservice.entities.BankAccount;
import com.works.bankservice.model.AccountDTO;

public class AccountMapper {

    private AccountMapper() {

    }

    public static AccountDTO accountToDto(BankAccount account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setOwner(account.getOwner());
        return accountDTO;
    }

    public static BankAccount dtoToAccount(AccountDTO accountDTO) {
        BankAccount account = new BankAccount();
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setOwner(accountDTO.getOwner());
        return account;
    }
}
