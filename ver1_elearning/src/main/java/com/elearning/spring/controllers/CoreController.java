package com.elearning.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.elearning.spring.entities.Admin;
import com.elearning.spring.entities.Course;
import com.elearning.spring.entities.Enrolled;
import com.elearning.spring.entities.EnrolledCompositeKey;
import com.elearning.spring.entities.User;
import com.elearning.spring.repo.CourseRepository;
import com.elearning.spring.repo.EnrolledRepository;

@Controller
public class CoreController {
	@Autowired
	public static EnrolledRepository enrollRepo;
	@Autowired
	public static CourseRepository courseRepo;
	
	public static User curruser = null;
	public static Admin curradmin = null;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getStringer() {
		return "welcome"; 
		
	}

	public static User getCurruser() {
		return curruser;
	}

	public static void setCurruser(User curruser) {
		CoreController.curruser = curruser;
	}

	public static Admin getCurradmin() {
		return curradmin;
	}

	public static void setCurradmin(Admin curradmin) {
		CoreController.curradmin = curradmin;
	}
	
	
	
	
	
	
	
	
}
