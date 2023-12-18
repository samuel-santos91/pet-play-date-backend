package com.sam.backend.pet;

import com.sam.backend.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pets")
public class Pet {

  @Getter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  @Getter
  @Setter
  private String name;

  @Column
  @Getter
  @Setter
  private Number age;

  @Column
  @Getter
  @Setter
  private String size;

  @Getter
  @Setter
  @OneToOne(mappedBy = "pet")
  private User user;
}
