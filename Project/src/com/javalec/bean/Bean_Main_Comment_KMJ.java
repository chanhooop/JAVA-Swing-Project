package com.javalec.bean;

/**
  * @FileName : coffeeBean_KMJ2.java
  * @Project : Project2
  * @Date : 2021. 4. 30. 
  * @작성자 : gimminjae
  * @변경이력 :
  * @프로그램설명 : 코멘트 불러오는 빈
  */
public class Bean_Main_Comment_KMJ {

	String brandName;
	String menuType;
	String menuName;
	String clientCode;
	String clientNick;
	String comment;
	String commentCode;
	String userNick;
	String adminOnOff;

	public Bean_Main_Comment_KMJ(String clientNick, String comment, String clientCode, String commentCode, String userNick, String adminOnOff) {
		super();
		this.clientNick = clientNick;
		this.comment = comment;
		this.clientCode = clientCode;
		this.commentCode = commentCode;
		this.userNick = userNick;
		this.adminOnOff = adminOnOff;
	}

	public Bean_Main_Comment_KMJ(String userNick, String adminOnOff) {
		super();
		this.userNick = userNick;
		this.adminOnOff = adminOnOff;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getAdminOnOff() {
		return adminOnOff;
	}

	public void setAdminOnOff(String adminOnOff) {
		this.adminOnOff = adminOnOff;
	}

	public Bean_Main_Comment_KMJ() {
		// TODO Auto-generated constructor stub
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getClientNick() {
		return clientNick;
	}

	public void setClientNick(String clientNick) {
		this.clientNick = clientNick;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommentCode() {
		return commentCode;
	}

	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

}
