package com.kashish.fortify.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/customer")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String customer() {
        return "Customer Access Granted";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "Admin Access Granted";
    }

    @GetMapping("/vendor")
    @PreAuthorize("hasRole('VENDOR')")
    public String vendor() {
        return "Vendor Access Granted";
    }
}