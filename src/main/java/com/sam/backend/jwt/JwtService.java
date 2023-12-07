package com.sam.backend.jwt;

import com.sam.backend.user.User;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtService {

  @Autowired
  private Dotenv dotenv;

  public String generateToken(User user) {
    return Jwts
      .builder()
      .setClaims(null)
      .setSubject(user.getId().toString())
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + 1000 * 3600))
      .signWith(this.getSingInKey(), SignatureAlgorithm.HS256)
      .compact();
  }

  private Key getSingInKey() {
    String secret = dotenv.get("JWT_SECRET");
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
