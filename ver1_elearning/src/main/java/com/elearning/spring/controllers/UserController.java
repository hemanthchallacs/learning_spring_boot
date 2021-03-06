package com.elearning.spring.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.annotation.MultipartConfig;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elearning.spring.entities.Contact;
import com.elearning.spring.entities.User;
import com.elearning.spring.repo.ContactRepository;
import com.elearning.spring.repo.FeedbackRepository;
import com.elearning.spring.repo.UserRepository;

@Controller
public class UserController {
	
//	final String profile_photo_folder = "C:\\profilephotos\\";
	final String profile_photo_folder = "\\elearning project\\ver1_elearning\\src\\main\\resources\\static\\images\\";

	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	FeedbackRepository feedRepo;
	
	@RequestMapping(value = "/testmav", method = RequestMethod.GET)	
	public ModelAndView gettestmav() {
		ModelAndView mav = new ModelAndView("dummy");
		
		mav.addObject("dummy", "Dummy value");
		return mav;
		
		
	}
	
	@RequestMapping(value = "/RegistrationForm", method = RequestMethod.GET)	
	public ModelAndView getRegistrationForm() {
		ModelAndView mav = new ModelAndView("RegistrationForm");
		mav.addObject("newuser", new User());
		return mav;
		
		
	}
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(@RequestParam MultipartFile image ,  @ModelAttribute User newuser,  RedirectAttributes redirAttrs) {
		
		try {
			
			byte[] bytes = image.getBytes();
			String imageName = image.getOriginalFilename();
			newuser.setPhotoname(imageName);
			Path path = Paths.get(profile_photo_folder+imageName);
			System.out.println(path.toString());
			Files.write(path, bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(userRepo.save(newuser));
		redirAttrs.addFlashAttribute("message", "Registered Successfully!" );
		return "redirect:/";
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)	
	public ModelAndView getloginForm() {
		
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("loginuser", new User());
		return mav;
		
	}
	
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public String loginUser(@ModelAttribute User loginuser) {
		
		
		try {
			User retUser = userRepo.findById(loginuser.getUser_id()).get();
			
			System.out.println(loginuser.getUser_id());
			System.out.println(loginuser.getPassword());
			if(retUser.getPassword().equals(loginuser.getPassword()))
			{	CoreController.setCurruser(loginuser);
				return "redirect:/courseList";
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
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)	
	public String logout() {
		
		CoreController.setCurruser(null);
		return "redirect:/";
		
	}
	
	@RequestMapping(value = "/myProfile", method = RequestMethod.GET)	
	public ModelAndView myProfile() {
		
		ModelAndView mav = new ModelAndView("myProfile");
		User curruser = CoreController.getCurruser();
		User curruser2 = userRepo.findById(curruser.getUser_id()).get();
		System.out.println(curruser2.getEmail());
		mav.addObject("curruser", curruser2);
		return mav;
		
	}
	
	@RequestMapping(value = "/users",produces = "application/json", method = RequestMethod.GET)	
	@ResponseBody
	public List<User> getUsers() {
		return userRepo.findAll();
		
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)	
	public ModelAndView contactAdmin() {
		
		ModelAndView mav = new ModelAndView("contact");
		User curruser = CoreController.getCurruser();
		Contact cc = new Contact();
		cc.setUser_id(curruser.getUser_id());
		mav.addObject("newcontact", cc);
		return mav;
		
	}
	
	@RequestMapping(value = "/contactSubmit", method = RequestMethod.POST)
	public String contactAdminSubmit(@ModelAttribute Contact cc) {
		User retUser = userRepo.findById(cc.getUser_id()).get();
		System.out.println(retUser.getAddress());
		retUser.getContacts().add(cc);
		userRepo.save(retUser);
		return "courseList";
		
		
		
	}
	
	

}
