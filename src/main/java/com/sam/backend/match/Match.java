package com.sam.backend.match;

import com.sam.backend.chatroom.ChatRoom;
import com.sam.backend.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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

  @ManyToOne
  @JoinColumn(name = "user1_id")
  private User user1;

  @ManyToOne
  @JoinColumn(name = "user2_id")
  private User user2;

  @OneToOne(mappedBy = "match")
  private ChatRoom chatroom;

  public Match() {}

  public Match(User user1, User user2) {
    this.user1 = user1;
    this.user2 = user2;
  }
}
