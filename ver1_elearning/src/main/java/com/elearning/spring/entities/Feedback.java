package com.elearning.spring.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "feedback")
public class Feedback {

	@Id
	@Column(name = "f_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feed_generator")
    @SequenceGenerator(name="feed_generator", sequenceName = "feedback_seq2", allocationSize = 1)
	private int f_id;
	@JsonManagedReference
	@Column(name = "user_id")
	private int user_id;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;
	@Column(name = "feed")
	private String feed;
	
	@ManyToMany(mappedBy = "courseFeedbacks")
	List<Course> givenFeedbackTo = new ArrayList<Course>();
	
	public void addCourse(Course course)
	{
		givenFeedbackTo.add(course);
	}
	
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFeed() {
		return feed;
	}
	public void setFeed(String feedback) {
		this.feed = feedback;
	}

	public List<Course> getGivenFeedbackTo() {
		return givenFeedbackTo;
	}

	public void setGivenFeedbackTo(List<Course> givenFeedbackTo) {
		this.givenFeedbackTo = givenFeedbackTo;
	}
	
	
	
}
