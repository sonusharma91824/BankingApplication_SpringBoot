package com.pratice.Banking.controller;

import com.pratice.Banking.dto.AccountDto;
import com.pratice.Banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "http://localhost:3000") // Adjust the origin as needed
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/save")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> depositeBalance(@PathVariable Long id,
                                                      @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposite(id, amount);
        return ResponseEntity.ok(accountDto);
    }
        @PutMapping("/{id}/withdraw")
        public ResponseEntity<AccountDto> withdrawBalance(@PathVariable Long id,
                @RequestBody Map<String, Double> request) {
            Double amount = request.get("amount");
            AccountDto accountDto = accountService.withdraw(id, amount);
            return ResponseEntity.ok(accountDto);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<AccountDto>> getAllAcount() {
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }
     @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id)
    {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("account delete succesfully");
    }

//

 @PutMapping("/{id}")
 public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id,
                                                @RequestBody Map<String, Object> request) {
   String name = (String) request.get("acountHolderName");
    Double amount =(Double) request.get("amount");
    AccountDto updatedAccountDto = accountService.updateAccount(id, name, amount);
    return ResponseEntity.ok(updatedAccountDto);
}}




