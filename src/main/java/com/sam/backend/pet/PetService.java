package com.sam.backend.pet;

import com.sam.backend.user.User;
import com.sam.backend.user.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
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

  public boolean isCurrentUserOwner(Long userId) {
    List<Pet> allPets = petRepository.findAll();

    for (Pet pet : allPets) {
      if (pet.getOwner() != null && pet.getOwner().getId().equals(userId)) {
        return true;
      }
    }
    return false;
  }
}
