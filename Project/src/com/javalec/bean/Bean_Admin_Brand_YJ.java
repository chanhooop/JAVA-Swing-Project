package com.javalec.bean;

public class Bean_Admin_Brand_YJ {
	
	int brandCode;
	String brandName;
	String adminLogin; // 닉네임 값
	String adminOnoff; // 어드민인지 아닌지 구분
	
	public Bean_Admin_Brand_YJ() {
		// TODO Auto-generated constructor stub
	}
	



	public Bean_Admin_Brand_YJ(String adminLogin, String adminOnoff) {
		super();
		this.adminLogin = adminLogin;
		this.adminOnoff = adminOnoff;
	}




	public Bean_Admin_Brand_YJ(int brandCode, String brandName) {
		super();
		this.brandCode = brandCode;
		this.brandName = brandName;
	}


	public int getBrandCode() {
		return brandCode;
	}


	public void setBrandCode(int brandCode) {
		this.brandCode = brandCode;
	}


	public String getBrandName() {
		return brandName;
	}


	public void setBrandName(String brandName) {
		this.brandName = brandName;
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
