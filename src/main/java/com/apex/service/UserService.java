package com.apex.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.apex.beans.BankInfoBean;
import com.apex.beans.ContactBean;
import com.apex.beans.PersonalInfoBean;
import com.apex.dao.UserDAO;

public class UserService {
	
	private UserDAO userDAO = new UserDAO();

	public int populateUser(PersonalInfoBean personalinfobean) throws SQLException, ClassNotFoundException {
		int id = 0;
		try {
			id = userDAO.registerPersonalInfo(personalinfobean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
		
	}
	
public void populateContact(ContactBean contactbean) {
		
		try {
			userDAO.registerContactInfo(contactbean);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

public void populateBankInfo(BankInfoBean bankinfobean) {
	try {
		userDAO.registerBankInfo(bankinfobean);
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	
}

public List<String> getAllTables() throws ClassNotFoundException, SQLException {
	
	return userDAO.getAllTables();
}

//public List<String> getTableInfo(String table) throws ClassNotFoundException, SQLException {
//	
//	return userDAO.getTableInfo(table);
//}

public List<String> getTableColumns(String table) throws SQLException {
	
	return userDAO.getTableColumns(table);
}

public List<Map<String, Object>> getTableData(String table, List<String> columns) throws SQLException {
	return userDAO.getTableData(table, columns);
}

public List<Map<String, Object>> getUserDataFromTable(String string, int userId) throws ClassNotFoundException, SQLException {
	return userDAO.getUserDataFromTable(string, userId);
}

}
