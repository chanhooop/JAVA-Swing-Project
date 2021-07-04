package com.javalec.bean;

public class Bean_Login_YJ {
	private String clientId;
	private String clientPw;
	private String clientName;
	
	private String adminId;
	private String adminPw;	
	
	
	public Bean_Login_YJ(String adminId, String adminPw) {
		super();
		this.adminId = adminId;
		this.adminPw = adminPw;
	}



	public Bean_Login_YJ(String clientId, String clientPw, String clientName) {
		super();
		this.clientId = clientId;
		this.clientPw = clientPw;
		this.clientName = clientName;
	}



	public String getClientId() {
		return clientId;
	}



	public void setClientId(String clientId) {
		this.clientId = clientId;
	}



	public String getClientPw() {
		return clientPw;
	}



	public void setClientPw(String clientPw) {
		this.clientPw = clientPw;
	}



	public String getClientName() {
		return clientName;
	}



	public void setClientName(String clientName) {
		this.clientName = clientName;
	}



	public String getAdminId() {
		return adminId;
	}



	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}



	public String getAdminPw() {
		return adminPw;
	}



	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}
	
	
	
}
