package com.apex.util;

import com.apex.constants.UserConstants;

import jakarta.servlet.http.HttpServletRequest;

public class UserUtil {

	public static String validateRequest(HttpServletRequest request) {
		String error = null;
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String middleName = request.getParameter("middleName");
		String gender = request.getParameter("gender");
		
		if (firstName.isEmpty() ||  lastName.isEmpty() || middleName.isEmpty() ||  gender.isEmpty()) {
			error = UserConstants.EMPTY_USERNAME_PASSWORD;
		}
		return error;
	}

	public static String validateRequestContactInfo(HttpServletRequest request) {
		String error = null;
		String address = request.getParameter("address");
  		String city = request.getParameter("city");
  		String state = request.getParameter("state");
  		String country = request.getParameter("country");
  		String phone = request.getParameter("phone");
		
		if (address.isEmpty() ||  city.isEmpty() || state.isEmpty() ||  country.isEmpty() || phone.isEmpty()) {
			error = UserConstants.EMPTY_USERNAME_PASSWORD;
		}
		return error;
	}

	public static String validateRequestBankInfo(HttpServletRequest request) {
		String error = null;
		String bankName = request.getParameter("bankName");
        String accountNumber =request.getParameter("accountNumber");
        String ssn = request.getParameter("ssn");
		
		if (bankName.isEmpty() ||  accountNumber.isEmpty() || ssn.isEmpty()) {
			error = UserConstants.EMPTY_USERNAME_PASSWORD;
		}
		return error;
	}

}
