package com.elearning.spring.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elearning.spring.entities.Admin;
import com.elearning.spring.entities.Course;
import com.elearning.spring.entities.Enrolled;
import com.elearning.spring.entities.User;
import com.elearning.spring.repo.AdminRepository;
import com.elearning.spring.repo.CourseRepository;
@Controller
public class AdminController {

	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private CourseRepository courseRepo;
	
	final String course_video_folder = "\\elearning project\\ver1_elearning\\src\\main\\resources\\static\\videos\\";

	
	@RequestMapping(value = "/adminlogin", method = RequestMethod.GET)	
	public ModelAndView getloginForm() {
		
		ModelAndView mav = new ModelAndView("adminlogin");
		mav.addObject("loginuser", new Admin());
		return mav;
		
	}
	
	@RequestMapping(value = "/adminloginUser", method = RequestMethod.POST)
	public String loginUser(@ModelAttribute Admin loginuser) {
		
		
		try {
			Admin retUser = adminRepo.findById(loginuser.getAdmin_id()).get();
			
			System.out.println(loginuser.getAdmin_id());
			System.out.println(loginuser.getPassword());
			if(retUser.getPassword().equals(loginuser.getPassword()))
			{	CoreController.setCurradmin(loginuser);
				return "redirect:/AdminDashboard";
			}
			else
				return "welcome";
		}
		catch(Exception e)
		{
			System.out.println("Couldnt login");
			return "welcome";
		}
		
		
	}
	
	@RequestMapping(value = "/adminlogout", method = RequestMethod.GET)	
	public String logout() {
		
		CoreController.setCurradmin(null);
		return "redirect:/";
		
	}
	
	@RequestMapping(value = "/AdminDashboard", method = RequestMethod.GET)
	public ModelAndView adminDashboardt()
	{

		Admin curradmin  = CoreController.getCurradmin();
		
		ModelAndView mav = new ModelAndView("AdminDashboard");
		List<Course> courses = courseRepo.findAll();
		
		
		mav.addObject("courses", courses);
		return mav;
		
	}
	
	@RequestMapping(value = "/courseForm", method = RequestMethod.GET)	
	public ModelAndView getRegistrationForm() {
		ModelAndView mav = new ModelAndView("courseForm");
		mav.addObject("newcourse", new Course());
		return mav;
		
		
	}
	
	@RequestMapping(value = "/courseForm", method = RequestMethod.POST)
	public String registerUser(@RequestParam MultipartFile video ,  @ModelAttribute Course newcourse,  RedirectAttributes redirAttrs) {
		
		try {
			
			byte[] bytes = video.getBytes();
			String videoName = video.getOriginalFilename();
			newcourse.setC_resource(videoName);
			Path path = Paths.get(course_video_folder+videoName);
			System.out.println(path.toString());
			Files.write(path, bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String message = "Successfully Added Course";
		System.out.println(courseRepo.save(newcourse));
		redirAttrs.addFlashAttribute("message", message );
		return "redirect:/AdminDashboard";
		
	}

}
