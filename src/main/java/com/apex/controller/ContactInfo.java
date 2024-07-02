package com.apex.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.apex.beans.ContactBean;
import com.apex.beans.PersonalInfoBean;
import com.apex.service.UserService;
import com.apex.util.UserUtil;

/**
 * Servlet implementation class ContactInfo
 */
public class ContactInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = UserUtil.validateRequestContactInfo(request);
		
		HttpSession session = request.getSession();
		if (error == null) {
		
		
		//HttpSession session = request.getSession(false);
	        if (session != null) {
	        	int id = (int) session.getAttribute("id");
	            response.getWriter().println("Session ID: " + id);
	            String address = request.getParameter("address");
	    		String city = request.getParameter("city");
	    		String state = request.getParameter("state");
	    		String country = request.getParameter("country");
	    		String phone = request.getParameter("phone");
	    		ContactBean contactbean = new ContactBean(id, address, city, state, country, phone);
	    		userService.populateContact(contactbean);
	    		
   		
	    		RequestDispatcher requestDispatcher = request.getRequestDispatcher("bankinfo.jsp");
	    		requestDispatcher.forward(request, response);
	        } else {
	            response.getWriter().println("No session found.");
	        }
		}
		
		session.setAttribute("error", error);
		RequestDispatcher dispatcher = request.getRequestDispatcher("contactinfo.jsp");
		dispatcher.include(request, response);
		
		
//		String address = request.getParameter("address");
//		String city = request.getParameter("city");
//		String state = request.getParameter("state");
//		String country = request.getParameter("country");
//		String phone = request.getParameter("phone");
//		
//		ContactBean contactbean = new ContactBean(address, city, state, country, phone);
//		userService.populateContact(contactbean);
//		
//		RequestDispatcher requestDispatcher = request.getRequestDispatcher("bankinfo.jsp");
//		requestDispatcher.forward(request, response);
	}

}
