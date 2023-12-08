package com.sam.backend.auth;

import com.sam.backend.jwt.JwtService;
import com.sam.backend.user.User;
import com.sam.backend.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

  @Autowired
  private AuthenticationManager authenticationManager;

  public AuthResponse register(RegisterDTO data) {
    String encodedPass = passwordEncoder.encode(data.getPassword());
    data.setPassword(encodedPass);

    User newUser = this.userService.create(data);
    String token = this.jwtService.generateToken(newUser);

    return new AuthResponse(token);
  }

  public AuthResponse login(LoginDTO data) {
    UsernamePasswordAuthenticationToken userPassToken = new UsernamePasswordAuthenticationToken(
      data.getUsername(),
      data.getPassword()
    );

    authenticationManager.authenticate(userPassToken);

    User user = this.userService.getByUsername(data.getUsername());

    if (user == null) {
      return null;
    }

    String token = this.jwtService.generateToken(user);
    return new AuthResponse(token);
  }
}
