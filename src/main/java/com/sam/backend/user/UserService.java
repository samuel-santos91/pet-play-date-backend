package com.sam.backend.user;

import com.sam.backend.auth.RegisterDTO;
import com.sam.backend.pet.Pet;
import com.sam.backend.pet.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PetRepository petRepository;

  public User create(RegisterDTO data) {
    User newUser = new User(
      data.getUsername(),
      data.getEmail(),
      data.getPassword()
    );

    return this.userRepository.save(newUser);
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

  public void likePet(LikeDTO data) {
    Optional<User> userOptional = userRepository.findById(data.getUserId());
    Optional<User> ownerOptional = userRepository.findById(data.getOwnerId());

    if (userOptional.isPresent() && ownerOptional.isPresent()) {
      User user = userOptional.get();
      User owner = ownerOptional.get();

      user.getLikedUsers().add(owner);

      owner.getUserLikedBy().add(user);

      userRepository.save(user);
      userRepository.save(owner);
    } else {
      throw new EntityNotFoundException("User or owner not found");
    }
  }
}
