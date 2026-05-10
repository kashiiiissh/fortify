package com.kashish.fortify.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {

    @GetMapping
    @PreAuthorize("hasRole('VENDOR')")
    public String vendorAccess() {
        return "Welcome Vendor";
    }
}