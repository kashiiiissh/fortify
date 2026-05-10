package com.kashish.fortify.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private String accessToken;
    private String refreshToken;
}