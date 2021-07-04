package com.javalec.bean;

public class Bean_Join_CJY {
	
	// bean은 내가 정보를 잠깐 저장한다.
	
	// field
	String id = "";
	String pw = "";
	String pw2 = "";
	String name = "";
	String telno = "";
	String nick = "";
	
	String tfId = "";
		
	// construction
	public Bean_Join_CJY() {

	
	}
	
	public Bean_Join_CJY(String id, String pw, String name, String telno, String nick) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.telno = telno;
		this.nick = nick;
	}
	
	
	public Bean_Join_CJY(String tfId) {
		super();
		this.tfId = tfId;
	}
	
	
	// method (게터와 세터)


	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
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
	
	public String getNick() {
		return nick;
	}
	
	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getTfId() {
		return tfId;
	}

	public void setTfId(String tfId) {
		this.tfId = tfId;
	}
	
	
	

	
	
	
	
	

} //---------------------------
