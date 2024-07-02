package com.apex.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.apex.beans.BankInfoBean;
import com.apex.beans.ContactBean;
import com.apex.beans.PersonalInfoBean;
import com.apex.beans.User;
import com.apex.dao.UserDAO;
import com.apex.service.UserService;
import com.apex.util.UserUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
//	@Autowired
//	private UserDAO userDao;
	private UserService userService = new UserService();
	
	@RequestMapping(value = "/personalinfo", method = RequestMethod.GET)
	public ModelAndView personalinfo() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("personalinfo");
		//modelAndView.addObject("message", "Welcome to Spring MVC Project. Hi");
		return modelAndView;
	}
	
	@RequestMapping(value = "/personalinfo", method = RequestMethod.POST)
	public ModelAndView getpersonalinfo(HttpServletRequest request, @ModelAttribute User user1) throws ClassNotFoundException, ServletException {
		String error = UserUtil.validateRequest(request);
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		
		 try {
			    PersonalInfoBean personalinfobean = new PersonalInfoBean(firstName, lastName, userName, password);
	            int userId = userService.populateUser(personalinfobean);
	            session.setAttribute("id", userId);
	            System.out.println(userId);
	            //userDao.registerUser(user);
                modelAndView.addObject("user",user1);
                modelAndView.setViewName("contactinfo");
	    		//RequestDispatcher requestDispatcher = request.getRequestDispatcher("contactinfo.jsp");
	    		//requestDispatcher.forward(request, response);
	        } catch (SQLException e) {
	            throw new ServletException(e);
	        } catch (ClassNotFoundException e) {
				e.printStackTrace();
			}catch (Exception e) {
                e.printStackTrace();
                session.setAttribute("error", "An error occurred during registration. Please try again.");
                modelAndView.addObject("message", "An error occurred during registration. Please try again.");
                modelAndView.setViewName("personalinfo");
            }
		  return modelAndView;
	}

	
	@RequestMapping(value = "/contactinfo", method = RequestMethod.GET)
	public ModelAndView contactinfo() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("contactinfo");
		//modelAndView.addObject("message", "Welcome to Spring MVC Project. Hi");
		return modelAndView;
	}
	
	@RequestMapping(value = "/contactinfo", method = RequestMethod.POST)
	public ModelAndView getcontactinfo(HttpServletRequest request, @ModelAttribute User user1) throws ClassNotFoundException, ServletException {
		String error = UserUtil.validateRequestContactInfo(request);
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("id");
		
		String address = request.getParameter("address");
  		String city = request.getParameter("city");
  		String state = request.getParameter("state");
  		String country = request.getParameter("country");
  		String phone = request.getParameter("phone");
		
		 try {
			 ContactBean contactbean = new ContactBean(id, address, city, state, country, phone);
	            userService.populateContact(contactbean);
	            session.setAttribute("id", id);
	            System.out.println(id);
	            //userDao.registerUser(user);
                modelAndView.addObject("user",user1);
                modelAndView.setViewName("bankinfo");
	    		//RequestDispatcher requestDispatcher = request.getRequestDispatcher("contactinfo.jsp");
	    		//requestDispatcher.forward(request, response);
	        } catch (Exception e) {
	        	session.setAttribute("error", error);
                e.printStackTrace();
                session.setAttribute("error", "An error occurred during registration. Please try again.");
                modelAndView.addObject("message", "An error occurred during registration. Please try again.");
                modelAndView.setViewName("contactinfo");
            }
		  return modelAndView;
	}
	
	
	@RequestMapping(value = "/bankinfo", method = RequestMethod.GET)
	public ModelAndView bankinfo() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("bankinfo");
		//modelAndView.addObject("message", "Welcome to Spring MVC Project. Hi");
		return modelAndView;
	}
	
	@RequestMapping(value = "/bankinfo", method = RequestMethod.POST)
	public ModelAndView getbankinfo(HttpServletRequest request, @ModelAttribute User user1) throws ClassNotFoundException, ServletException {
		String error = UserUtil.validateRequestBankInfo(request);
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("id");
		
		 //response.getWriter().println("Session ID: " + id);
         String bankName = request.getParameter("bankName");
         int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
         int ssn = Integer.parseInt(request.getParameter("ssn"));
  		
  		Map<String, List<Map<String, Object>>> userTablesData = new HashMap<>();
		
		 try {
			    BankInfoBean bankinfobean = new BankInfoBean(id, bankName, accountNumber, ssn);
	    		userService.populateBankInfo(bankinfobean);
	            session.setAttribute("id", id);
	            System.out.println(id);
	            //userDao.registerUser(user);
	            userTablesData.put("personalinfo", userService.getUserDataFromTable("personalinfo", id));
                userTablesData.put("contactinfo", userService.getUserDataFromTable("contactinfo", id));
                userTablesData.put("bankinfo", userService.getUserDataFromTable("bankinfo", id));
                modelAndView.addObject("userTablesData", userTablesData);
                modelAndView.setViewName("success");
	    		//RequestDispatcher requestDispatcher = request.getRequestDispatcher("contactinfo.jsp");
	    		//requestDispatcher.forward(request, response);
	        } catch (Exception e) {
	        	session.setAttribute("error", error);
                e.printStackTrace();
                session.setAttribute("error", "An error occurred during registration. Please try again.");
                modelAndView.addObject("message", "An error occurred during registration. Please try again.");
                modelAndView.setViewName("bankinfo");
            }
		  return modelAndView;
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView welcome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("welcome");
		modelAndView.addObject("message", "Welcome to Spring MVC Project. Hi");
		return modelAndView;
	}

	@RequestMapping(value = "/userRegister", method = RequestMethod.GET)
	public String studentForm(ModelMap modelMap) {
		modelMap.addAttribute("message", "This is User Registration");
		return "register";
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, @ModelAttribute User user1) throws ClassNotFoundException {
		
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		
//		   if (!firstName.isEmpty() && !lastName.isEmpty() && !userName.isEmpty() && !password.isEmpty()) {
//	            try {
//	                if (userDao.checkUser(userName)) {
//	                    session.setAttribute("error", "Username already exists");
//	                    modelAndView.addObject("message", "Username already exists");
//	                    modelAndView.setViewName("register");
//	                } else {
//	                    User user = new User();
//	                    user.setFirstName(firstName);
//	                    user.setLastName(lastName);
//	                    user.setUserName(userName);
//	                    user.setPassword(password);
//
//	                    //userDao.registerUser(user);
//	                    modelAndView.addObject("user",user1);
//	                    modelAndView.setViewName("displayUser");
//	                }
//	            } catch (Exception e) {
//	                e.printStackTrace();
//	                session.setAttribute("error", "An error occurred during registration. Please try again.");
//	                modelAndView.addObject("message", "An error occurred during registration. Please try again.");
//	                modelAndView.setViewName("register");
//	            }
//	        } else {
//	            session.setAttribute("error", "Enter all fields");
//	            modelAndView.addObject("message", "Enter all fields");
//	            modelAndView.setViewName("register");
//	        }

	        return modelAndView;

//		User user = new User();
//		UserDAO userDao = new UserDAO();
//		user.setFirstName(firstName);
//		user.setLastName(lastName);
//		user.setUserName(userName);
//		user.setPassword(password);
//		userDao.registerUser(user);
//		modelAndView.setViewName("displayUser");
//		modelAndView.addObject("user",user1);
//		return modelAndView;
	}
	@RequestMapping("/displayUser")
	public String displayUser(Model model) {
	    // additional logic
	    return "displayUser"; // make sure this view exists
	}
}
