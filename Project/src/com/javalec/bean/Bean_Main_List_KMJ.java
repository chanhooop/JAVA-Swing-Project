package com.javalec.bean;


/**
  * @FileName : coffeeBean_KMJ.java
  * @Project : Project2
  * @Date : 2021. 4. 30. 
  * @작성자 : gimminjae
  * @변경이력 :
  * @프로그램설명 : 메뉴리스트 Bean 
  */
public class Bean_Main_List_KMJ {
	String Topic;
	String searchValue;
	String brandName;
	String menuType;
	String menuName;
	String price;

	public Bean_Main_List_KMJ() {
		// TODO Auto-generated constructor stub
	}

	public Bean_Main_List_KMJ(String brandName, String menuType, String menuName, String price) {
		super();
		this.brandName = brandName;
		this.menuType = menuType;
		this.menuName = menuName;
		this.price = price;
	}

	public String getTopic() {
		return Topic;
	}

	public void setTopic(String Topic) {
		this.Topic = Topic;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
