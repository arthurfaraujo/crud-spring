package com.arthur.crudspring.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CourseDTO(
    Long _id,
    @NotBlank @Length(min = 3, max = 150) String name,
    @Length(max = 10) @Pattern(regexp = "Back-end|Front-end") String category) {
      
}
