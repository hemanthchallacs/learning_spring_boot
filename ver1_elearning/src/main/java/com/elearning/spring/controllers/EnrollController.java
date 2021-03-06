package com.elearning.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elearning.spring.entities.Course;
import com.elearning.spring.entities.Enrolled;
import com.elearning.spring.entities.EnrolledCompositeKey;
import com.elearning.spring.entities.Feedback;
import com.elearning.spring.entities.User;
import com.elearning.spring.repo.CourseRepository;
import com.elearning.spring.repo.EnrolledRepository;
import com.elearning.spring.repo.FeedbackRepository;
import com.elearning.spring.repo.UserRepository;

@Controller
public class EnrollController {

	
	
	
	@Autowired
	private CourseRepository courseRepo;
	@Autowired
	private EnrolledRepository enrollRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private FeedbackRepository feedRepo;

	@RequestMapping(value = "/course/enroll/{course_id}/enrollme", method = RequestMethod.GET)
	public ModelAndView enrollMe(@PathVariable int course_id)
	{
		ModelAndView mav = new ModelAndView("courseDetails");
		Course course = courseRepo.findById(course_id).get();
		mav.addObject("course", course);
		return mav;
		
	}
	@RequestMapping(value = "/course/enroll/{course_id}/enrolled", method = RequestMethod.POST)
	public String enrolled(@PathVariable int course_id, RedirectAttributes redirAttrs)
	{
		String message;
		
		 message = "Enrolled in course!"+ course_id;
		
		User curruser = CoreController.getCurruser();
		Enrolled enrollme = new Enrolled();
		EnrolledCompositeKey enroll_cpkey =  new EnrolledCompositeKey();
		enroll_cpkey.setCourse_id(course_id);
		enroll_cpkey.setUser_id(curruser.getUser_id());
		enrollme.setEnroll_cpkey(enroll_cpkey);
		enrollRepo.save(enrollme);
	
		redirAttrs.addFlashAttribute("message", message );
		return "redirect:/courseList";
		
		
	}
	
	
	@RequestMapping(value = "/myCourses/{course_id}/details", method = RequestMethod.GET)
	public ModelAndView myCourseDetails(@PathVariable int course_id)
	{
		ModelAndView mav = new ModelAndView("myCourseDetails");
		Course course = courseRepo.findById(course_id).get();
		mav.addObject("course", course);
		return mav;
		
	}
	@RequestMapping(value = "/myCourses/{course_id}/feedbackPage", method = RequestMethod.GET)
	public ModelAndView myCourseFeedbackPage(@PathVariable int course_id)
	{
		ModelAndView mav = new ModelAndView("feedbackPage");
		Feedback feed = new Feedback();
		System.out.println("Hello");
		int id = CoreController.getCurruser().getUser_id();
		System.out.println("Id:"+id);
		User curruser = userRepo.findById(CoreController.getCurruser().getUser_id()).get();
		System.out.println(curruser.getEmail());
		feed.setUser_id(curruser.getUser_id());
		feed.setEmail(curruser.getEmail());
		feed.setName(curruser.getName());
		
		mav.addObject("feedbackOb", feed);
		return mav;
		
	}
	@RequestMapping(value = "/myCourses/{course_id}/feedbackPage", method = RequestMethod.POST)
	public String myCourseFeedbackPost(@PathVariable int course_id, @ModelAttribute Feedback feed)
	{
		System.out.println("Hello2");

		Course course = courseRepo.getById(course_id);
		course.addFeedback(feed);
		feedRepo.save(feed);
		courseRepo.save(course);
//		String str="/myCourses/"+course_id +"/details";
		return "redirect:/myCourses";
//		ModelAndView mav = new ModelAndView("feedbackPage");
//		Feedback feed = new Feedback();
//		User curruser = CoreController.getCurruser();
//		feed.setUser_id(curruser.getUser_id());
//		feed.setEmail(curruser.getEmail());
//		feed.setName(curruser.getName());
//		mav.addObject("feedback", feed);
//		return mav;
		
	}
	
	
}
