package org.pokeherb.userservice.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.pokeherb.userservice.application.dto.TokenInfo;
import org.pokeherb.userservice.application.dto.UserRegister;
import org.pokeherb.userservice.application.dto.UserRegisterRequest;
import org.pokeherb.userservice.application.service.TokenGenerateService;
import org.pokeherb.userservice.application.service.UserRegisterService;
import org.pokeherb.userservice.presentation.dto.TokenRequest;
import org.pokeherb.userservice.presentation.dto.TokenResponse;
import org.pokeherb.userservice.presentation.validator.UserRegisterValidator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

    private final TokenGenerateService tokenService;
    private final UserRegisterService registerService;

    // 토큰 발급
    @PostMapping("/token")
    public TokenResponse generateToken(@Valid @RequestBody TokenRequest req) {
        TokenInfo tokenInfo = tokenService.generate(req.username(), req.password());

        return new TokenResponse(tokenInfo.access_token(),
                tokenInfo.expires_in(),
                tokenInfo.refresh_expires_in(),
                tokenInfo.refresh_token(),
                tokenInfo.token_type());
    }

    // 회원 가입
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@Valid @RequestBody UserRegisterRequest req) {

        // 추가 검증 처리
        new UserRegisterValidator().validate(req);

        UserRegister data = UserRegister.builder()
                .username(req.username())
                .password(req.password())
                .email(req.email())
                .firstName(req.firstName())
                .lastName(req.lastName())
                .phone(req.phone())
                .build();

        registerService.registerUser(data);
    }
}
