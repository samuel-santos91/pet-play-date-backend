package com.sam.backend.pet;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PetService {

  @Autowired
  private PetRepository petRepository;

  @Autowired
  private ModelMapper modelMapper;

  public Pet createPet(PetCreateDTO data) {
    Pet newPet = modelMapper.map(data, Pet.class);
    Pet created = this.petRepository.save(newPet);
    return created;
  }
}
