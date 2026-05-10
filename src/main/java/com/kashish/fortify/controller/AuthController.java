package com.kashish.fortify.controller;

import com.kashish.fortify.dto.*;
import com.kashish.fortify.entity.User;
import com.kashish.fortify.enums.Role;
import com.kashish.fortify.repository.UserRepository;
import com.kashish.fortify.security.JwtTokenProvider;
import com.kashish.fortify.service.RefreshTokenService;
import com.kashish.fortify.entity.RefreshToken;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
public String register(@RequestBody RegisterRequest request) {

    User user = new User();

    user.setName(request.getName());
    user.setEmail(request.getEmail());

    // encrypted password
    user.setPassword(
            passwordEncoder.encode(request.getPassword())
    );

    // ROLE SETTING
    if(request.getRole().equalsIgnoreCase("ADMIN")){
        user.setRole(Role.ROLE_ADMIN);
    }
    else if(request.getRole().equalsIgnoreCase("VENDOR")){
        user.setRole(Role.ROLE_VENDOR);
    }
    else{
        user.setRole(Role.ROLE_CUSTOMER);
    }

    userRepository.save(user);

    return "User Registered Successfully";
}

    @PostMapping("/login")
public JwtResponse login(@RequestBody LoginRequest request) {

    User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    boolean isPasswordCorrect =
            passwordEncoder.matches(
                    request.getPassword(),
                    user.getPassword()
            );

    if (!isPasswordCorrect) {
        throw new RuntimeException("Invalid Password");
    }

    String accessToken =
            jwtTokenProvider.generateToken(user.getEmail());

    String refreshToken =
            refreshTokenService.createRefreshToken(user).getToken();

    return new JwtResponse(accessToken, refreshToken);
}

@PostMapping("/refresh")
public JwtResponse refreshToken(
        @RequestBody RefreshTokenRequest request) {

    RefreshToken refreshToken =
            refreshTokenService.verifyRefreshToken(
                    request.getRefreshToken()
            );

    String accessToken =
            jwtTokenProvider.generateToken(
                    refreshToken.getUser().getEmail()
            );

    return new JwtResponse(
            accessToken,
            refreshToken.getToken()
    );
}
}
