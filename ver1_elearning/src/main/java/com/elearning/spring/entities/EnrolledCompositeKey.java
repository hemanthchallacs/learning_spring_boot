package com.elearning.spring.entities;

import java.io.Serializable;

import javax.persistence.Column;

//import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class EnrolledCompositeKey implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5891261160758925513L;
	@Column(name = "user_id")
    private int user_id;
	@Column(name = "course_id")
    private int course_id;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
    
}
