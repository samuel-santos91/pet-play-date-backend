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
@Getter
@Setter
public class Pet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Column
  private Number age;

  @Column
  private String size;

  @OneToOne(mappedBy = "pet")
  private User user;

  public Pet() {}

  public Pet(String name, Number age, String size) {
    this.name = name;
    this.age = age;
    this.size = size;
  }
}
