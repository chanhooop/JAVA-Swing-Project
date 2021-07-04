package com.javalec.bean;

public class Bean_CH {

	// field
	
	int menuCode;
	int brandCode;
	String menuprice;
	String brandName;
	String menuName;
	String menuType;
	String materialAllerge;
	String tfsearch;
	String conditionQueryColumn;
	int cmbPriceSelect;
	int menuCount;
	
	//constructor
	
	public Bean_CH() {
		
	}







	// 테이블에 데이터 클릭하면 나타나게 만들기
	
	public Bean_CH(String brandName, String menuType, String menuName, String menuprice) {
		super();

		this.brandName = brandName;
		this.menuType = menuType;
		this.menuName = menuName;
		this.menuprice = menuprice;

	}


	//---------------------------------------------------------------------------------------------------------	
		public Bean_CH(String brandName, String menuType, String menuName, String menuprice, int menuCount) {
			super();
			this.menuprice = menuprice;
			this.brandName = brandName;
			this.menuName = menuName;
			this.menuType = menuType;
			this.menuCount = menuCount;
		}

	//--------------------------------------------------------------
	
	// method

		public int getMenuCount() {
			return menuCount;
		}







		public void setMenuCount(int menuCount) {
			this.menuCount = menuCount;
		}







		public int getMenuCode() {
			return menuCode;
		}












		public String getMenuType() {
			return menuType;
		}



		public void setMenuType(String menuType) {
			this.menuType = menuType;
		}



		public int getCmbPriceSelect() {
			return cmbPriceSelect;
		}



		public void setCmbPriceSelect(int cmbPriceSelect) {
			this.cmbPriceSelect = cmbPriceSelect;
		}



		public String getConditionQueryColumn() {
			return conditionQueryColumn;
		}



		public void setConditionQueryColumn(String conditionQueryColumn) {
			this.conditionQueryColumn = conditionQueryColumn;
		}



		public String getTfsearch() {
			return tfsearch;
		}



		public void setTfsearch(String tfsearch) {
			this.tfsearch = tfsearch;
		}



		public void setMenuCode(int menuCode) {
			this.menuCode = menuCode;
		}



		public int getBrandCode() {
			return brandCode;
		}



		public void setBrandCode(int brandCode) {
			this.brandCode = brandCode;
		}



		public String getmenuprice() {
			return menuprice;
		}



		public void setmenuprice(String menuprice) {
			this.menuprice = menuprice;
		}



		public String getBrandName() {
			return brandName;
		}



		public void setBrandName(String brandName) {
			this.brandName = brandName;
		}



		public String getMenuName() {
			return menuName;
		}



		public void setMenuName(String menuName) {
			this.menuName = menuName;
		}


		public String getMetarialName() {
			return materialAllerge;
		}


		public void setmaterialAllerge(String materialAllerge) {
			this.materialAllerge = materialAllerge;
		}
		
		
		
	}
