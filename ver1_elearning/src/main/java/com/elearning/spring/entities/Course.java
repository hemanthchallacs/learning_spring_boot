package com.elearning.spring.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "course")
public class Course {

	@Id
	@Column(name = "course_id")
	private Integer course_id;
	private String c_name;
	private String c_desp;
	private String c_fees;
	private String c_resource;
	
	
	@ManyToMany
	@JoinTable(
			  name = "course_feedbacks",
			joinColumns = @JoinColumn(name = "course_id"),
			inverseJoinColumns = @JoinColumn(name = "f_id")
			)
	@JsonIgnore
	List<Feedback> courseFeedbacks = new ArrayList<Feedback>();
	
	public void addFeedback(Feedback fd)
	{
		courseFeedbacks.add(fd);
	}
	
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_desp() {
		return c_desp;
	}
	public void setC_desp(String c_desp) {
		this.c_desp = c_desp;
	}
	public String getC_fees() {
		return c_fees;
	}
	public void setC_fees(String c_fees) {
		this.c_fees = c_fees;
	}
	public String getC_resource() {
		return c_resource;
	}
	public void setC_resource(String c_resource) {
		this.c_resource = c_resource;
	}
	@Override
	public String toString() {
		return "Course [course_id=" + course_id + ", c_name=" + c_name + ", c_desp=" + c_desp + ", c_fees=" + c_fees
				+ ", c_resource=" + c_resource + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((c_desp == null) ? 0 : c_desp.hashCode());
		result = prime * result + ((c_fees == null) ? 0 : c_fees.hashCode());
		result = prime * result + ((c_name == null) ? 0 : c_name.hashCode());
		result = prime * result + ((c_resource == null) ? 0 : c_resource.hashCode());
		result = prime * result + ((course_id == null) ? 0 : course_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (c_desp == null) {
			if (other.c_desp != null)
				return false;
		} else if (!c_desp.equals(other.c_desp))
			return false;
		if (c_fees == null) {
			if (other.c_fees != null)
				return false;
		} else if (!c_fees.equals(other.c_fees))
			return false;
		if (c_name == null) {
			if (other.c_name != null)
				return false;
		} else if (!c_name.equals(other.c_name))
			return false;
		if (c_resource == null) {
			if (other.c_resource != null)
				return false;
		} else if (!c_resource.equals(other.c_resource))
			return false;
		if (course_id == null) {
			if (other.course_id != null)
				return false;
		} else if (!course_id.equals(other.course_id))
			return false;
		return true;
	}

	public List<Feedback> getCourseFeedbacks() {
		return courseFeedbacks;
	}

	public void setCourseFeedbacks(List<Feedback> courseFeedbacks) {
		this.courseFeedbacks = courseFeedbacks;
	}
}
