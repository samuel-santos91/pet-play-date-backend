package com.sam.backend.user;

import com.sam.backend.match.Match;
import com.sam.backend.pet.Pet;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(unique = true)
  private String username;

  @Column(unique = true)
  private String email;

  @Column
  private String password;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "pet_id", referencedColumnName = "id")
  private Pet pet;

  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "user1",
    cascade = CascadeType.ALL
  )
  private List<Match> MatchListAsUser1;

  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "user2",
    cascade = CascadeType.ALL
  )
  private List<Match> MatchListAsUser2;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(this.role.name()));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public User() {}

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = Role.ROLE_ADMIN;
  }
}
