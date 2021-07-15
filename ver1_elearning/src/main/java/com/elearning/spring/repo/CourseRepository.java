package com.elearning.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.spring.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
