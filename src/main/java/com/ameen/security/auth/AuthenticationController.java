package com.ameen.security.auth;

import com.ameen.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173"})
public class AuthenticationController {

    private final UserRepository userRepository;
    private final  AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body("Username already exists");
        }
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));

    }
}
