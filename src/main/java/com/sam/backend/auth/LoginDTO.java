package com.sam.backend.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class LoginDTO {

  @Getter
  @Setter
  @NotBlank
  private String username;

  @Getter
  @Setter
  @NotBlank
  private String password;
}
