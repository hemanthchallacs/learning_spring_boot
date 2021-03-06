package com.elearning.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.elearning.spring.entities.Course;
import com.elearning.spring.entities.Enrolled;
import com.elearning.spring.entities.EnrolledCompositeKey;
import com.elearning.spring.entities.User;
import com.elearning.spring.repo.CourseRepository;
import com.elearning.spring.repo.EnrolledRepository;


@Controller
public class CourseController {
	
	@Autowired
	CourseRepository courseRepo;	
	
	@Autowired
	EnrolledRepository enrollRepo;
	
	
	@RequestMapping(value = "/courseList", method = RequestMethod.GET)
	public ModelAndView courseList()
	{

		User curruser  = CoreController.getCurruser();
		
		List<Course> myCourses = new ArrayList<Course>();
		
		List<Enrolled> ecplist = enrollRepo.findAll();
		
		for(Enrolled aux:ecplist)
		{
			System.out.println(aux.toString());
			if(aux.getEnroll_cpkey().getUser_id() == curruser.getUser_id())
			{
				myCourses.add(courseRepo.findById(aux.getEnroll_cpkey().getCourse_id()).get());
				
			}
				
		}
		ModelAndView mav = new ModelAndView("courseList");
		List<Course> courses = courseRepo.findAll();
		courses.removeAll(myCourses);
		
		mav.addObject("courses", courses);
		return mav;
		
	}
	
	@RequestMapping(value = "/myCourses",produces = "application/json", method = RequestMethod.GET)
	public ModelAndView myCourseList()
	{
		
		User curruser  = CoreController.getCurruser();
		
		List<Course> myCourses = new ArrayList<Course>();
		
		List<Enrolled> ecplist = enrollRepo.findAll();
		
		for(Enrolled aux:ecplist)
		{
			System.out.println(aux.toString());
			if(aux.getEnroll_cpkey().getUser_id() == curruser.getUser_id())
			{
				myCourses.add(courseRepo.findById(aux.getEnroll_cpkey().getCourse_id()).get());
				
			}
				
		}
		ModelAndView mav = new ModelAndView("myCourses");
		
		mav.addObject("courses", myCourses);
		return mav;
		
	}
	
	
}
