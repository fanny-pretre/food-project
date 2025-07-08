package in.fpretre.foodiesapi.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.fpretre.foodiesapi.io.AuthenticationRequest;
import in.fpretre.foodiesapi.io.AuthenticationResponse;
import in.fpretre.foodiesapi.service.AppUserDetailsService;
import in.fpretre.foodiesapi.util.JwtUtil;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager; 
    private final AppUserDetailsService userDetailsService; 
    private final JwtUtil jwtUtil; 

@PostMapping("/login")
public AuthenticationResponse login(@RequestBody AuthenticationRequest request){
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
    final String  jwtToken = jwtUtil.generateToken(userDetails);
    return new AuthenticationResponse(request.getEmail(), jwtToken);
}
}
