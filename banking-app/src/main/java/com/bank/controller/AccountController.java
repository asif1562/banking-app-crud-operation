package com.bank.controller;

import com.bank.dto.AccountDto;
import com.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    // add account rest api
    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //get Account by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
       AccountDto accountDto= accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    //deposit rest api
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto=accountService.deposit(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(Long id,Map<String,Double> request){
        Double amount=request.get("amount");
        AccountDto accountDto=accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    //get all accounts Rest api
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccount(){
       List<AccountDto> accounts= accountService.getAllAccounts();
       return ResponseEntity.ok(accounts);
    }
    //delete Account rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted successfully");
    }

}
