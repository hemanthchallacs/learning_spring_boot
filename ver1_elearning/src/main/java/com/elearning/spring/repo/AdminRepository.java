package com.elearning.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.spring.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
