package  com.scraptreasure.controller.auth;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import  com.scraptreasure.dto.AuthResponse;
import  com.scraptreasure.dto.LoginRequest;
import  com.scraptreasure.dto.RegisterRequest;
import  com.scraptreasure.service.auth.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }
    @PostMapping("/login")
public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

    String token = authService.login(request);
    return ResponseEntity.ok(new AuthResponse(token));
}

}


//eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraXNoYW5AZXhhbXBsZS5jb20iLCJpYXQiOjE3NzA1NTY5MTgsImV4cCI6MTc3MDY0MzMxOH0.wSU5bw1OPML-DtSHELVf7DjH_xvp20spBCMm6Wpd_jQ


