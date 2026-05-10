package com.kashish.fortify.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public String customerAccess() {
        return "Welcome Customer";
    }
}