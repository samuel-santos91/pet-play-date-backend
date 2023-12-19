package com.sam.backend.pet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NotBlank
public class PetCreateDTO {

  @Size(min = 2, message = "pet name should have at least 2 characters")
  private String name;

  private Number age;

  private PetSizes size;

  private String description;

  public PetCreateDTO() {}

  public PetCreateDTO(String name, Number age, PetSizes size, String description) {
    this.name = name;
    this.age = age;
    this.size = size;
    this.description = description;
  }
}
