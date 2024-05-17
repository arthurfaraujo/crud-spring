package com.arthur.crudspring.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

import com.arthur.crudspring.enums.Category;
import com.arthur.crudspring.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@SQLDelete(sql = "UPDATE Course SET status = 'inactive' WHERE id = ?")
@SQLRestriction("status <> 'inactive'")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonProperty("_id")
  private long id;

  @NotBlank
  @Length(min = 3, max = 150)
  @Column(length = 150, nullable = false, unique = true)
  private String name;

  @NotNull
  @Column(length = 10, nullable = false)
  // @Convert(converter = CategoryConverter.class)
  private Category category;

  @NotNull
  @Column(length = 8, nullable = false)
  // @Convert(converter = StatusConverter.class)
  private Status status = Status.ACTIVE;
}
