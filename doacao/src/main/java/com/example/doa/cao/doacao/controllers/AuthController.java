package com.example.doa.cao.doacao.controllers;


import com.example.doa.cao.doacao.exception.TokenRefreshException;
import com.example.doa.cao.doacao.models.RefreshToken;
import com.example.doa.cao.doacao.payload.request.LoginRequest;
import com.example.doa.cao.doacao.payload.request.RefreshTokenRequest;
import com.example.doa.cao.doacao.payload.request.RegisterRequest;
import com.example.doa.cao.doacao.payload.response.MessageResponse;
import com.example.doa.cao.doacao.payload.response.TokenRefreshResponse;
import com.example.doa.cao.doacao.repository.UserRepository;
import com.example.doa.cao.doacao.security.jwt.JwtUtils;
import com.example.doa.cao.doacao.service.RefreshTokenService;
import com.example.doa.cao.doacao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService service;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(service.authenticate(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("O e-mail informado já está em uso!"));
        }

        return ResponseEntity.ok(service.register(registerRequest));

    }


    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody RefreshTokenRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getEmail());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                }).orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "O token de atualização não está no banco de dados!"));
    }


}
