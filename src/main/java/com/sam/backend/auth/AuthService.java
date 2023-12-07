package com.sam.backend.auth;

import com.sam.backend.jwt.JwtService;
import com.sam.backend.user.User;
import com.sam.backend.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthService {

  @Autowired
  private UserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtService jwtService;

  public AuthResponse register(RegisterDTO data) {
    String encodedPass = passwordEncoder.encode(data.getPassword());
    data.setPassword(encodedPass);

    User newUser = this.userService.create(data);
    String token = this.jwtService.generateToken(newUser);

    return new AuthResponse(token);
  }
}
