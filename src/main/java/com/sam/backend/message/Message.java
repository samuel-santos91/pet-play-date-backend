package com.sam.backend.message;

import com.sam.backend.chatroom.ChatRoom;
import com.sam.backend.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "messages")
@Getter
@Setter
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String content;

  @ManyToOne
  @JoinColumn(name = "sender_id", referencedColumnName = "id")
  private User user;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "chat_room_id", referencedColumnName = "id")
  private ChatRoom chatroom;
}
