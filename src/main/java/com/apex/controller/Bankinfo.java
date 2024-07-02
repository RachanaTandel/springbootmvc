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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.apex.beans.BankInfoBean;
import com.apex.beans.ContactBean;
import com.apex.service.UserService;
import com.apex.util.UserUtil;

/**
 * Servlet implementation class Bankinfo
 */
public class Bankinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bankinfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String error = UserUtil.validateRequestBankInfo(request);
		HttpSession session = request.getSession();
		if (error == null) {
		
		//HttpSession session = request.getSession(false);
        if (session != null) {
        	int id = (int) session.getAttribute("id");
            response.getWriter().println("Session ID: " + id);
            String bankName = request.getParameter("bankName");
            int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
            int ssn = Integer.parseInt(request.getParameter("ssn"));
    		BankInfoBean bankinfobean = new BankInfoBean(id, bankName, accountNumber, ssn);
    		userService.populateBankInfo(bankinfobean);
    		
    		
    		//int userId = (Integer) session.getAttribute("userId");
            Map<String, List<Map<String, Object>>> userTablesData = new HashMap<>();

            try {
                // Example: Assuming you have tables "orders" and "profile"
                userTablesData.put("personalinfo", userService.getUserDataFromTable("personalinfo", id));
                userTablesData.put("contactinfo", userService.getUserDataFromTable("contactinfo", id));
                userTablesData.put("bankinfo", userService.getUserDataFromTable("bankinfo", id));
                request.setAttribute("userTablesData", userTablesData);
                request.getRequestDispatcher("success.jsp").forward(request, response);
            }
            catch (SQLException e) {
                throw new ServletException(e);
            } catch (ClassNotFoundException e) {
    			e.printStackTrace();
    		}
            } else {
                response.getWriter().println("No session found.");
            }
            
        }
		session.setAttribute("error", error);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("bankinfo.jsp");
		dispatcher.include(request, response);
		}
        
	}
//    		 try {
//    	            List<String> tables = userService.getAllTables();
//    	            Map<String, List<String>> tablesColumns = new HashMap<>();
//    	            Map<String, List<Map<String, Object>>> tablesData = new HashMap<>();
//
//    	            for (String table : tables) {
//    	                List<String> columns = userService.getTableColumns(table);
//    	                tablesColumns.put(table, columns);
//    	                List<Map<String, Object>> data = userService.getTableData(table, columns);
//    	                tablesData.put(table, data);
//    	            }
//
//    	            request.setAttribute("tablesColumns", tablesColumns);
//    	            request.setAttribute("tablesData", tablesData);
//    	            request.getRequestDispatcher("success.jsp").forward(request, response);
//
//    	        } 
    		 
        	
    		
//    		 try {
//    			 List<String> tables = userService.getAllTables();
//    	         Map<String, List<String>> tablesInfo = new HashMap<>();
//
//    	         for (String table : tables) {
//    	             List<String> columns = userService.getTableInfo(table);
//    	             tablesInfo.put(table, columns);
//    	         }
//    	         
//    	    
//    	         
//    	         request.setAttribute("tablesInfo", tablesInfo);
//    	         RequestDispatcher requestDispatcher = request.getRequestDispatcher("success.jsp");
//  	    		requestDispatcher.forward(request, response);
//    			}catch (SQLException e) {
//    	            throw new ServletException(e);
//    	        } catch (ClassNotFoundException e) {
//    				e.printStackTrace();
//    			}
//    		
//    		RequestDispatcher requestDispatcher = request.getRequestDispatcher("success.jsp");
//    		requestDispatcher.forward(request, response);

