package com.sam.backend.pet;

import com.sam.backend.user.User;
import com.sam.backend.user.UserRepository;
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
  private UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;

  public Pet createPet(PetCreateDTO data) {
    Pet newPet = modelMapper.map(data, Pet.class);

    User user = userRepository.findById(data.getUserId()).orElse(null);
    newPet.setOwner(user);

    Pet created = this.petRepository.save(newPet);

    return created;
  }
}
