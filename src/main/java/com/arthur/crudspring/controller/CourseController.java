package com.arthur.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.arthur.crudspring.model.Course;
import com.arthur.crudspring.repository.CourseRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

  private final CourseRepository courseRepository;

  @GetMapping
  public List<Course> getAllCourses() {
    return courseRepository.findAll();
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Course create(@RequestBody Course course) {
    return courseRepository.save(course);
  }

  @PutMapping(path = "/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public Course edit(@RequestBody Course course) {
    return courseRepository.save(course);
  }

  @DeleteMapping(path = "/{id}")
  public void deleteById(@PathVariable long id) {
    courseRepository.deleteById(id);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Course> getById(@PathVariable long id) {
    return courseRepository.findById(id).map(course -> ResponseEntity.ok().body(course))
        .orElse(ResponseEntity.notFound().build());
  }
}
