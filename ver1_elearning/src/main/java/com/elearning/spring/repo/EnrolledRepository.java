package com.elearning.spring.repo;



import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.spring.entities.Course;
import com.elearning.spring.entities.Enrolled;
import com.elearning.spring.entities.EnrolledCompositeKey;
import com.elearning.spring.entities.User;

@Repository
public interface EnrolledRepository extends JpaRepository<Enrolled, EnrolledCompositeKey> {
//	List<Enrolled> findByIdUser_id(int user_id);

}
