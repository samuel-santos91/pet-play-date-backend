package com.sam.backend.user;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class LikeDTO {

  @Getter
  @Setter
  @NotNull
  private Long userId;

  @Getter
  @Setter
  @NotNull
  private Long ownerId;
}
