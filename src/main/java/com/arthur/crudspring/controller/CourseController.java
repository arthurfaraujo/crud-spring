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
  public ResponseEntity<Course> edit(@PathVariable long id, @RequestBody Course course) {
    return courseRepository.findById(id).map(found -> {
      found.setName(course.getName());
      found.setCategory(course.getCategory());
      Course updated = courseRepository.save(found);
      return ResponseEntity.ok(updated);
    })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable long id) {
    return courseRepository.findById(id).map((found) -> {
      courseRepository.deleteById(id);
      return ResponseEntity.noContent().<Void>build();
    })
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Course> getById(@PathVariable long id) {
    return courseRepository.findById(id).map(course -> ResponseEntity.ok().body(course))
        .orElse(ResponseEntity.notFound().build());
  }
}
