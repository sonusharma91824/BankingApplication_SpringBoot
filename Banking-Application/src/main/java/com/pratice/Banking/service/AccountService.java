package com.pratice.Banking.service;

import com.pratice.Banking.dto.AccountDto;
import com.pratice.Banking.entity.Account;

import java.util.List;

public interface AccountService
{
    AccountDto createAccount(AccountDto account);

     AccountDto getAccountById(Long id);

    AccountDto deposite(Long id, double amount);

    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);


    AccountDto updateAccount(Long id, String name, double amount);
}
