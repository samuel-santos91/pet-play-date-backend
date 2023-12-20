package com.sam.backend.pet;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pet")
public class PetController {

  @Autowired
  private PetService petService;

  @PostMapping
  public ResponseEntity<Pet> createEmployee(
    @Valid @RequestBody PetCreateDTO data
  ) {
    Pet newPet = this.petService.createPet(data);
    return new ResponseEntity<>(newPet, HttpStatus.CREATED);
  }
}
