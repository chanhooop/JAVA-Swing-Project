package com.javalec.bean;

public class Bean_Id_CJY {
	
	// field
	String name = "";
	String telno = ""; 
	
	
	// construction
	public Bean_Id_CJY() {

	
	}


	public Bean_Id_CJY(String name, String telno) {
		super();
		this.name = name;
		this.telno = telno;
	}

	// method (게터와 세터)

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTelno() {
		return telno;
	}


	public void setTelno(String telno) {
		this.telno = telno;
	}
	
	

} //-------------------------
