package com.apex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.apex.beans.BankInfoBean;
import com.apex.beans.ContactBean;
import com.apex.beans.PersonalInfoBean;

public class UserDAO {
//	public int registerPersonalInfo(PersonalInfoBean personalInfoBean) throws ClassNotFoundException {
//		int result = 0;
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		
//		Connection connection;
//		try {
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "admin");
//			PreparedStatement preparedStatement = connection.prepareStatement("insert into personalinfo (FirstName, LastName, MiddleName, Gender) values(?,?,?,?)");
//			preparedStatement.setString(1, personalInfoBean.getFirstName());
//			preparedStatement.setString(2, personalInfoBean.getLastName());
//			preparedStatement.setString(3, personalInfoBean.getMiddleName());
//			preparedStatement.setString(4, personalInfoBean.getGender());
//			result = preparedStatement.executeUpdate();
//			System.out.print(result);
//			
//			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                return generatedKeys.getInt(1);
//            } else {
//                throw new SQLException("Creating user failed, no ID obtained.");
//            }
//		}
//		
//		catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	 public int registerPersonalInfo(PersonalInfoBean personalInfoBean) throws SQLException, ClassNotFoundException {
		 Class.forName("com.mysql.cj.jdbc.Driver");
	        String sql = "insert into personalinfo (FirstName, LastName, MiddleName, Gender) values(?,?,?,?)";
	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "admin");
	             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	            
	        	statement.setString(1, personalInfoBean.getFirstName());
	        	statement.setString(2, personalInfoBean.getLastName());
	        	statement.setString(3, personalInfoBean.getMiddleName());
				statement.setString(4, personalInfoBean.getGender());
	            statement.executeUpdate();

	            ResultSet generatedKeys = statement.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                return generatedKeys.getInt(1);
	            } else {
	                throw new SQLException("Creating person info failed, no ID obtained.");
	            }
	        }
	    }
	
	public int registerContactInfo(ContactBean contactbean) throws ClassNotFoundException {
		int result = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "admin");
			PreparedStatement preparedStatement = connection.prepareStatement("insert into contactinfo (PersonID, Address, City, s, Country, Phone) values(?,?,?,?,?,?)");
			preparedStatement.setInt(1, contactbean.getId());
			preparedStatement.setString(2, contactbean.getAddress());
			preparedStatement.setString(3, contactbean.getCity());
			preparedStatement.setString(4, contactbean.getState());
			preparedStatement.setString(5, contactbean.getCountry());
			preparedStatement.setString(6, contactbean.getPhone());
			result = preparedStatement.executeUpdate();
			System.out.print(result);
			preparedStatement.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public int registerBankInfo(BankInfoBean bankinfobean)  throws ClassNotFoundException{
		int result = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "admin");
			PreparedStatement preparedStatement = connection.prepareStatement("insert into bankinfo (PersonID, BankName, AccountNumber, SSN) values(?,?,?,?)");
			preparedStatement.setInt(1, bankinfobean.getId());
			preparedStatement.setString(2, bankinfobean.getBankName());
			preparedStatement.setInt(3, bankinfobean.getAccountNumber());
			preparedStatement.setInt(4, bankinfobean.getSsn());
			result = preparedStatement.executeUpdate();
			System.out.print(result);
			preparedStatement.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	
	
	public List<Map<String, Object>> getUserDataFromTable(String tableName, int userId) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		List<Map<String, Object>> tableData = new ArrayList<>();
        String query = "SELECT * FROM " + tableName + " WHERE PersonID = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "admin");
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (resultSet.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.put(metaData.getColumnName(i), resultSet.getObject(i));
                    }
                    tableData.add(row);
                }
            }
        }
        return tableData;
    }
	
	
	public List<String> getAllTables() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
        List<String> tables = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "admin");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SHOW TABLES")) {

            while (resultSet.next()) {
                tables.add(resultSet.getString(1));
            }
        }
        return tables;
    }
	
	 public List<String> getTableColumns(String tableName) throws SQLException {
	        List<String> columns = new ArrayList<>();
	        String query = "DESCRIBE " + tableName;
	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "admin");
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(query)) {

	            while (resultSet.next()) {
	                columns.add(resultSet.getString("Field"));
	            }
	        }
	        return columns;
	    }
	 
	 public List<Map<String, Object>> getTableData(String tableName, List<String> columns) throws SQLException {
	        List<Map<String, Object>> tableData = new ArrayList<>();
	        String query = "SELECT * FROM " + tableName;
	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "admin");
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(query)) {

	            while (resultSet.next()) {
	                Map<String, Object> row = new HashMap<>();
	                for (String column : columns) {
	                    row.put(column, resultSet.getObject(column));
	                }
	                tableData.add(row);
	            }
	        }
	        return tableData;
	    }
	
//    public List<String> getTableInfo(String tableName) throws SQLException, ClassNotFoundException {
//    	Class.forName("com.mysql.cj.jdbc.Driver");
//        List<String> columns = new ArrayList<>();
//        String query = "DESCRIBE " + tableName;
//        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "admin");
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(query)) {
//
//            while (resultSet.next()) {
//                String columnInfo = resultSet.getString("Field");
//                columns.add(columnInfo);
//            }
//        }
//        return columns;
//    }

}
