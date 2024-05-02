package com.arthur.crudspring.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@SQLDelete(sql = "UPDATE Course SET status = 'inactive' WHERE id = ?")
@SQLRestriction("status = 'active'")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonProperty("_id")
  private Long id;

  @NotBlank
  @Length(min = 3, max = 150)
  @Column(length = 150, nullable = false, unique = true)
  private String name;

  @Length(max = 10)
  @Pattern(regexp = "Back-end|Front-end")
  @Column(length = 20, nullable = false)
  private String category;

  @JsonIgnore
  @Length(max = 8)
  @Pattern(regexp = "active|inactive")
  @Column(length = 8, nullable = false)
  private String status = "active";
}
