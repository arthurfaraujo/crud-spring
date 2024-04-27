package com.arthur.crudspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arthur.crudspring.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


}
