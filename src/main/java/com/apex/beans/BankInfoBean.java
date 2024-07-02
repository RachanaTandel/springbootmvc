package com.apex.beans;

public class BankInfoBean {
	private int id;
	private String bankName;
	private int accountNumber;
	private int ssn;
	
	public BankInfoBean(int id, String bankName, int accountNumber, int ssn) {
		this.id = id;
		this.bankName = bankName;
		this.accountNumber = accountNumber;
		this.ssn = ssn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getSsn() {
		return ssn;
	}
	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
}