package com.sam.backend.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> allUsers = this.userService.getAll();
    return new ResponseEntity<>(allUsers, HttpStatus.OK);
  }

  @GetMapping("/{userId}/pet")
  public ResponseEntity<Boolean> isCurrentUserOwner(@PathVariable Long userId) {
    boolean isOwner = userService.isCurrentUserOwner(userId);
    return ResponseEntity.ok(isOwner);
  }
}
