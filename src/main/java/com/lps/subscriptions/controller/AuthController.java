package com.lps.subscriptions.controller;

import com.lps.subscriptions.security.ClientAPI;
import com.lps.subscriptions.security.JwtResponse;
import com.lps.subscriptions.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Value("client.id")
    private String clientId;
    @Value("client.secret")
    private String secretAPI;

    @PostMapping("/token")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody ClientAPI clientCredentials) throws Exception {
        if (authenticateClient(clientCredentials.getClientId(), clientCredentials.getClientSecret())) {
            final String token = jwtTokenUtil.generateTokenForClient(clientCredentials.getClientId());
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            return ResponseEntity.status(401).body("Invalid Credentials");
        }
    }

    private boolean authenticateClient(String clientId, String clientSecret) {
        // Aquí validas las credenciales del cliente
        return this.clientId.equals(clientId) && secretAPI.equals(clientSecret);
    }
}
