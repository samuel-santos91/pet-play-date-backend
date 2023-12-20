package com.sam.backend.pet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetCreateDTO {

  @NotBlank
  @Size(min = 2, message = "pet name should have at least 2 characters")
  private String name;

  private int age;

  @NotNull
  private PetSizes size;

  private String description;

  @NotNull
  private Long userId;

  public PetCreateDTO() {}

  public PetCreateDTO(String name, int age, PetSizes size, String description, Long userId) {
    this.name = name;
    this.age = age;
    this.size = size;
    this.description = description;
    this.userId = userId;
  }
}
