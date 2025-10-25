package com.workintech.s18d4.controller;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final CustomerService customerService;

    public AccountController(AccountService accountService,
                             CustomerService customerService){
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @GetMapping
    public List<Account> findAll(){
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account find(@PathVariable Long id){
        return accountService.find(id);
    }

    @PostMapping("/{customerId}")
    public Account save(@PathVariable Long customerId,
                        @RequestBody Account account){

        // müşteri bul
        Customer customer = customerService.find(customerId);
        // ilişkilendir
        account.setCustomer(customer);
        // kaydet
        return accountService.save(account);
    }

    @PutMapping("/{customerId}")
    public Account update(@PathVariable Long customerId,
                          @RequestBody Account updated){

        // customerService.find(customerId) mutlaka çağrılıyor olmalı (test verify ediyor)
        Customer customer = customerService.find(customerId);

        // accountService.find(updated.getId()) da testte stub'lanıyor
        Account existing = accountService.find((long) updated.getId());

        // alanları güncelle
        existing.setAccountName(updated.getAccountName());
        existing.setMoneyAmount(updated.getMoneyAmount());
        existing.setCustomer(customer);

        // kaydet ve dön
        return accountService.save(existing);
    }

    @DeleteMapping("/{id}")
    public Account remove(@PathVariable Long id){
        // test önce accountService.find(id) stub'luyor ama response olarak "delete sonrası dönen account" istiyor.
        accountService.find(id);
        return accountService.delete(id);
    }
}
