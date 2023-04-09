package com.example.appfinanceiro.controller;

import com.example.appfinanceiro.dto.AuthResponseDTO;
import com.example.appfinanceiro.dto.LoginDto;
import com.example.appfinanceiro.repository.UserRepository;
import com.example.appfinanceiro.security.JwtGenerator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication for users")
public class AuthController {
    public final AuthenticationManager authenticationManager;
    public final UserRepository userRepository;
    public final BCryptPasswordEncoder bCryptPasswordEncoder;
    public final JwtGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> authenticateUser(@Valid @RequestBody LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateJwtToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);


//        AuthResponseDTO authResponseDTO = new AuthResponseDTO(token);
//        authResponseDTO.setIdUser(userRepository.findByEmail(loginDto.getEmail()).getIdUser());

    }


}
