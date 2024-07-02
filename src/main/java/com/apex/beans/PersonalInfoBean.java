package com.apex.beans;

public class PersonalInfoBean {
	
	private int id;
	private String firstName;
	private String lastName;
	private String middleName;
	private String gender;
	
	public PersonalInfoBean(String firstName2, String lastName2, String middleName2, String gender2) {
		this.firstName = firstName2;
		this.lastName = lastName2;
		this.middleName = middleName2;
		this.gender = gender2;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
