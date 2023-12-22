package com.sam.backend.user;

import com.sam.backend.auth.RegisterDTO;
import com.sam.backend.pet.Pet;
import com.sam.backend.pet.PetRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PetRepository petRepository;

  @Autowired
  private ModelMapper modelMapper;

  public User create(RegisterDTO data) {
    User newUser = modelMapper.map(data, User.class);

    User created = this.userRepository.save(newUser);

    return created;
  }

  public User getById(Long id) {
    User found = this.userRepository.findById(id).orElse(null);
    return found;
  }

  public User getByUsername(String username) {
    return this.userRepository.findByUsername(username).orElse(null);
  }

  public List<User> getAll() {
    return this.userRepository.findAll();
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
