package com.masai.controller;

import com.masai.exception.CustomerException;
import com.masai.model.Customer;
import com.masai.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/createaccount/{bigDecimal}")
    public ResponseEntity<Customer> createAccount(@Valid @RequestBody Customer customer,@PathVariable BigDecimal bigDecimal) throws CustomerException {
        Customer customer1=walletService.createAccount(customer,bigDecimal);
        return new ResponseEntity<>(customer1, HttpStatus.CREATED);
    }

    @GetMapping("/showbalance/{mobileNumber}")
    public ResponseEntity<BigDecimal> showBalance(@PathVariable("mobileNumber") String num) throws CustomerException {
        BigDecimal bigDecimal=walletService.showBalance(num);
        return new ResponseEntity<>(bigDecimal, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateAccount(@Valid @RequestBody Customer customer) throws CustomerException {
        Customer customer1=walletService.updateAccount(customer);
        return new ResponseEntity<>(customer1, HttpStatus.OK);
    }


}