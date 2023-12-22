package com.sam.backend.match;

import com.sam.backend.chatroom.ChatRoom;
import com.sam.backend.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "matches")
@Getter
@Setter
public class Match {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToMany
  @JoinTable(
    name = "match_users",
    joinColumns = @JoinColumn(name = "match_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id")
  )
  private List<User> users;

  @OneToOne(mappedBy = "match")
  private ChatRoom chatroom;

  public Match() {}

  public Match(List<User> users) {
    this.users = users;
  }
}
