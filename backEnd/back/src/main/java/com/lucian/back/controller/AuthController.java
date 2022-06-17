package com.lucian.back.controller;

import com.lucian.common.response.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

/**
 * @author denglingxiang
 * @date 2022/06/15 09:32
 **/
@RestController
public class AuthController {

    JwtEncoder encoder;

    @Autowired
    public void setEncoder(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    @PostMapping("login")
    public CommonResult<String> login(Authentication authentication){
        Instant now = Instant.now();
        long expiry = 30 * 60;
        // @formatter:off
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        // @formatter:on
        String jwtToken = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return CommonResult.success(jwtToken, "登陆成功！");
    }
}
