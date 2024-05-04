package com.arthur.crudspring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.arthur.crudspring.exception.RecordNotFoundException;
import com.arthur.crudspring.model.Course;
import com.arthur.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CourseService {

  private final CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public List<Course> getAllCourses() {
    return courseRepository.findAll();
  }

  public Course getById(@NotNull @Positive long id) {
    return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
  }

  public Course create(@Valid Course course) {
    return courseRepository.save(course);
  }

  public Course edit(@NotNull @Positive long id, @Valid Course course) {
    return courseRepository.findById(id).map(found -> {
      found.setName(course.getName());
      found.setCategory(course.getCategory());
      return courseRepository.save(found);
    }).orElseThrow(() -> new RecordNotFoundException(id));
  }

  public void delete(@NotNull @Positive long id) {
    courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
  }
}
