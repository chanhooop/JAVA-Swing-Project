package com.javalec.bean;

public class Bean_Client_FirstView_YJ {
	String adminLogin; // 닉네임 값
	String adminOnoff; // 어드민인지 아닌지 구분
	
	
	public Bean_Client_FirstView_YJ() {
		// TODO Auto-generated constructor stub
	}


	public Bean_Client_FirstView_YJ(String adminLogin, String adminOnoff) {
		super();
		this.adminLogin = adminLogin;
		this.adminOnoff = adminOnoff;
	}


	public String getAdminLogin() {
		return adminLogin;
	}


	public void setAdminLogin(String adminLogin) {
		this.adminLogin = adminLogin;
	}


	public String getAdminOnoff() {
		return adminOnoff;
	}


	public void setAdminOnoff(String adminOnoff) {
		this.adminOnoff = adminOnoff;
	}
	
	
}
