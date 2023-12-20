package com.storedprocexample.storedproceduresexample.controller;

import com.storedprocexample.storedproceduresexample.model.Account;
import com.storedprocexample.storedproceduresexample.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @PutMapping("/create")
    public ResponseEntity createAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountRepository.save(account));
    }

    @GetMapping("/show-all")
    public ResponseEntity showAllAccounts() {
        List<Account> allAccounts = accountRepository.findAll();
        log.info(allAccounts.toString());
        return ResponseEntity.ok(allAccounts);
    }

    @PutMapping("/transfer/{from}/{to}")
    @Transactional(rollbackFor = UnexpectedRollbackException.class)
    public ResponseEntity transferAmount(@PathVariable Integer from, @PathVariable Integer to, @RequestParam Double amount) {
//        Account fromAccount=accountRepository.findById(from).get();
//        Account toAccount=accountRepository.findById(to).get();
//        fromAccount.setBalance(fromAccount.getBalance()-amount);
//        toAccount.setBalance(toAccount.getBalance()+amount);
//        accountRepository.save(fromAccount);
//        accountRepository.save(toAccount);

        try {
            accountRepository.transferAmountStoredProc(from, to, amount);
            double currentBalance = accountRepository.findById(from).get().getBalance();
            return ResponseEntity.status(HttpStatus.OK).body("Current Balance : " + currentBalance);
        } catch (Exception e) {
            log.info("Exception occured ");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }
}
