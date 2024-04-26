package com.pratice.Banking.service.impl;

import com.pratice.Banking.dto.AccountDto;
import com.pratice.Banking.entity.Account;
import com.pratice.Banking.repository.AccountRepository;
import com.pratice.Banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

   @Autowired
     AccountRepository accountRepository;
//    AccountRepository accountRepository;
//    AccountServiceImpl(AccountRepository accountRepository){
//        this.accountRepository = accountRepository;
//    }

@Override
    public AccountDto createAccount(AccountDto accountDto)   {
        //accountDto to account
        Account account=new Account();
        account.setAccountHolderName(accountDto.getAccountHolderName());
        account.setBalance(accountDto.getBalance());
      accountRepository.save(account);
      return accountDto;
    }

    @Override
    public AccountDto getAccountById(Long id) {
      Account account = accountRepository
              .findById(id)
              .orElseThrow(()-> new RuntimeException("Account does not exit"));

        AccountDto accountDto = new AccountDto();
         accountDto.setId(account.getId());
         accountDto.setAccountHolderName(account.getAccountHolderName());
         accountDto.setBalance(account.getBalance());

        return accountDto;

    }

    @Override
    public AccountDto deposite(Long id,  double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exit"));
          double total =   account.getBalance() + amount;
          account.setBalance(total);
         accountRepository.save(account);
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setAccountHolderName(account.getAccountHolderName());
        accountDto.setBalance(account.getBalance());
        return accountDto;

    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exit"));
        if(account.getBalance() < amount)
        {
            throw new RuntimeException("insufficient amount");
        }
        double total =account.getBalance()-amount;
        account.setBalance(total);
       accountRepository.save(account);
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setAccountHolderName(account.getAccountHolderName());
        accountDto.setBalance(account.getBalance());
        return accountDto;

    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(account -> {
                    AccountDto accountDto = new AccountDto();
                    accountDto.setId(account.getId());
                    accountDto.setAccountHolderName(account.getAccountHolderName());
                    accountDto.setBalance(account.getBalance());
                    return accountDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exit"));
              accountRepository.deleteById(id);
    }

    @Override
    public AccountDto updateAccount(Long id, String name, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

        account.setAccountHolderName(name);
        account.setBalance(amount);

        Account updatedAccount = accountRepository.save(account);

        AccountDto updatedAccountDto = new AccountDto();
        updatedAccountDto.setId(updatedAccount.getId());
        updatedAccountDto.setAccountHolderName(updatedAccount.getAccountHolderName());
        updatedAccountDto.setBalance(updatedAccount.getBalance());
        return updatedAccountDto;
        }




 }



