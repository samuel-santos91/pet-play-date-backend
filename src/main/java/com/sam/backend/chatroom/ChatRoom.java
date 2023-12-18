package com.sam.backend.chatroom;

import com.sam.backend.match.Match;
import com.sam.backend.message.Message;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "chat_rooms")
@Getter
@Setter
public class ChatRoom {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "match_id", referencedColumnName = "id")
  private Match match;

  @OneToOne(mappedBy = "chatrooom")
  private Message message;

  public ChatRoom() {}
}
