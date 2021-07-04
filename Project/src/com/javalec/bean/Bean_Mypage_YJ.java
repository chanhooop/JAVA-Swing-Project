package com.javalec.bean;

public class Bean_Mypage_YJ {
	String clientId;
	String clientPw;
	String clientPwCheck;
	String clientName;
	String clientTelno;
	String clientNick;
	
	
	
	
	public Bean_Mypage_YJ() {
		// TODO Auto-generated constructor stub
	}



	public Bean_Mypage_YJ(String clientId, String clientPw, String clientName) {
		super();
		this.clientId = clientId;
		this.clientPw = clientPw;
		this.clientName = clientName;
	}


	public Bean_Mypage_YJ(String clientPw, String clientName, String clientTelno, String clientNick) {
		super();
		this.clientPw = clientPw;
		this.clientName = clientName;
		this.clientTelno = clientTelno;
		this.clientNick = clientNick;
	}



	public Bean_Mypage_YJ(String clientId, String clientPw, String clientPwCheck, String clientName, String clientTelno,
			String clientNick) {
		super();
		this.clientId = clientId;
		this.clientPw = clientPw;
		this.clientPwCheck = clientPwCheck;
		this.clientName = clientName;
		this.clientTelno = clientTelno;
		this.clientNick = clientNick;
	}



	public Bean_Mypage_YJ(String clientNick) {
		super();
		this.clientNick = clientNick;
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




	public String getClientPwCheck() {
		return clientPwCheck;
	}




	public void setClientPwCheck(String clientPwCheck) {
		this.clientPwCheck = clientPwCheck;
	}




	public String getClientName() {
		return clientName;
	}




	public void setClientName(String clientName) {
		this.clientName = clientName;
	}




	public String getClientTelno() {
		return clientTelno;
	}




	public void setClientTelno(String clientTelno) {
		this.clientTelno = clientTelno;
	}




	public String getClientNick() {
		return clientNick;
	}




	public void setClientNick(String clientNick) {
		this.clientNick = clientNick;
	}
	
	
	
}
