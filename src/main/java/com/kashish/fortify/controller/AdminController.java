package com.kashish.fortify.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Welcome Admin";
    }
}