package com.sam.backend.match;

import com.sam.backend.chatroom.ChatRoom;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

  @ElementCollection
  @CollectionTable(
    name = "match_users",
    joinColumns = @JoinColumn(name = "match_id")
  )
  @Column(name = "user_id") 
  private List<Long> usersId;

  @OneToOne(mappedBy = "match")
  private ChatRoom chatroom;

  public Match() {}

  public Match(List<Long> usersId) {
    this.usersId = usersId;
  }
}
