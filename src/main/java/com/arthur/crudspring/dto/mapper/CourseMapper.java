package com.arthur.crudspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.arthur.crudspring.dto.CourseDTO;
import com.arthur.crudspring.model.Course;

@Component
public class CourseMapper {
  public CourseDTO toDTO(Course course) {
    if (course == null) {
      return null;
    }

    return new CourseDTO(course.getId(), course.getName(), course.getCategory());
  }

  public Course toEntity(CourseDTO courseDTO) {
    if (courseDTO == null) {
      return null;
    }

    Course course = new Course();
    course.setId(courseDTO._id());
    course.setName(courseDTO.name());
    course.setCategory(courseDTO.category());
    return course;
  }
}
