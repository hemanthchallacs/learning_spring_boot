package com.elearning.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.spring.entities.Feedback;



@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

}
