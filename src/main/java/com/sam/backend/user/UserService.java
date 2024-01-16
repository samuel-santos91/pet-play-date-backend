package com.sam.backend.user;

import com.sam.backend.auth.RegisterDTO;
import com.sam.backend.match.Match;
import com.sam.backend.match.MatchRepository;
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
  private MatchRepository matchRepository;

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

  public boolean checkAndCreateMatch(Long userId1, Long userId2) {
    User user1 = userRepository
      .findById(userId1)
      .orElseThrow(() ->
        new EntityNotFoundException("User with ID " + userId1 + " not found")
      );

    User user2 = userRepository
      .findById(userId2)
      .orElseThrow(() ->
        new EntityNotFoundException("User with ID " + userId2 + " not found")
      );

    if (
      user1.getLikedUsers().contains(user2) &&
      user2.getLikedUsers().contains(user1)
    ) {
      Match match = createMatch(user1, user2);

      user1.getMatchList().add(match.getId());
      user2.getMatchList().add(match.getId());

      userRepository.save(user1);
      userRepository.save(user2);

      return true;
    }
    return false;
  }

  private Match createMatch(User user1, User user2) {
    Match match = new Match(List.of(user1.getId(), user2.getId()));
    return matchRepository.save(match);
  }
}
