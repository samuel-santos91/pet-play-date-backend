package com.sam.backend.user;

import com.sam.backend.auth.RegisterDTO;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

  @Autowired
  private UserRepository userRepository;

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
}
