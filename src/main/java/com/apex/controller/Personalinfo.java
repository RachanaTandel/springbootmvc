package com.apex.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import com.apex.beans.PersonalInfoBean;
import com.apex.service.UserService;
import com.apex.util.UserUtil;

/**
 * Servlet implementation class Personalinfo
 */
public class Personalinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Personalinfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String error = UserUtil.validateRequest(request);
		
		HttpSession session = request.getSession();
		if (error == null) {
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String middleName = request.getParameter("middleName");
		String gender = request.getParameter("gender");
		
		 try {
			    PersonalInfoBean personalinfobean = new PersonalInfoBean(firstName, lastName, middleName, gender);
	            int userId = userService.populateUser(personalinfobean);
	            session.setAttribute("id", userId);
	            System.out.println(userId);
	    		RequestDispatcher requestDispatcher = request.getRequestDispatcher("contactinfo.jsp");
	    		requestDispatcher.forward(request, response);
	        } catch (SQLException e) {
	            throw new ServletException(e);
	        } catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		session.setAttribute("error", error);
		//RequestDispatcher requestDispatcher = request.getRequestDispatcher("personalinfo.jsp");
		//requestDispatcher.forward(request, response);
		
		//request.getRequestDispatcher("personalinfo.jsp").forward(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher("personalinfo.jsp");
		dispatcher.include(request, response);
		
//		HttpSession session = request.getSession();
//		
//		PersonalInfoBean personalinfobean = new PersonalInfoBean(firstName, lastName, middleName, gender);
//		userService.populateUser(personalinfobean);
//		
//		System.out.println(personalinfobean.getId());
//		//session.setAttribute("id", personalinfobean.getId());
//		RequestDispatcher requestDispatcher = request.getRequestDispatcher("contactinfo.jsp");
//		requestDispatcher.forward(request, response);
	}

}
