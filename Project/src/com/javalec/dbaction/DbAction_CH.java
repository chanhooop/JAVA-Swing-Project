package com.javalec.dbaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.bean.Bean_CH;
import com.javalec.sharevar.ShareVar_Admin_Brand_YJ;
import com.javalec.sharevar.ShareVar_CH;


public class DbAction_CH {

	// Field
	private final String url_mysql = ShareVar_CH.url_mysql;
	private final String id_mysql = ShareVar_CH.id_mysql;
	private final String pw_mysql = ShareVar_CH.pw_mysqll;

	// 칼럼 선언자
	int menucode;
	String brandName;
	String menuType;
	String menuName;
	String menuprice;
	
	FileInputStream file;

	

	// constructor

	public DbAction_CH() {

	}




	public DbAction_CH(String brandName, String menuType, String menuName, String menuprice) {
		super();

		this.brandName = brandName;
		this.menuType = menuType;
		this.menuName = menuName;
		this.menuprice = menuprice;
	}

	
	
	public DbAction_CH(int menucode) {
	super();
	this.menucode = menucode;
}
	// method
	
	
	// 테이블에 데이터 불러오기
	public ArrayList<Bean_CH> selectList(){
		
		   ArrayList<Bean_CH> beanList = new ArrayList<Bean_CH>();  // 어레이리스트 타입의 생성자 만들어주기
		

		  String WhereDefault = "select b.brandName, m.menuType, m.menuName, mu.menuprice, m.menuCount from brand b, menu m, menuupdate mu where b.brandCode = m.brand_brandCode and m.menuCode = mu.menu_menuCode order by m.menuCount desc";
	     System.out.println(WhereDefault);
		  try{
	          Class.forName("com.mysql.cj.jdbc.Driver");
	          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	          Statement stmt_mysql = conn_mysql.createStatement();

	          ResultSet rs = stmt_mysql.executeQuery(WhereDefault);

	          while(rs.next()){
	        	 
	        	  String brandName = rs.getString(1);
	        	  String menuType = rs.getString(2);
	        	  String menuName = rs.getString(3);
	        	  String menuprice = rs.getString(4);
	        	  int menuCount = rs.getInt(5);

	        	  						//새로만든 빈형식으로 바꿔주기
	        	  						//Bean_CH(brandName, menuType, menuName, menuprice, menuCount)
	        	  Bean_CH bean = new Bean_CH(brandName, menuType, menuName, menuprice, menuCount);
	        	  beanList.add(bean);
	        	  
	          }
	          conn_mysql.close();
	       }
	       catch (Exception e){
	          e.printStackTrace();
	      }
		return beanList;
	      
	  }
	
		
	
	// 테이블에 데이터 하나 클릭하면 나타나게 만들기
	
	public Bean_CH tableClick(Bean_CH bean_CH) {

		Bean_CH bean_CH2 = null;

		String query1 = "select b.brandName, m.menuName, mu.menuprice, m.menuAllergy from menu m, brand b, menuupdate mu ";
		String query2 = "where b.brandCode = m.brand_brandCode and m.menuCode = mu.menu_menuCode and b.brandName = '";
		String query3 = "' and m.menuName = '";


		System.out.println(query1 + query2 + bean_CH.getBrandName() + query3 + bean_CH.getMenuName() + "'");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
		

			Statement stmt_mysql = conn_mysql.createStatement();
		
	
			
			ResultSet rs = stmt_mysql.executeQuery(query1 + query2 + bean_CH.getBrandName() + query3 + bean_CH.getMenuName() + "'");

		
			if(rs.next()) {		

				
				String tfbrandName = rs.getString(1);  		   

				String tfmenuName = rs.getString(2);  
				String tfmenuprice = rs.getString(3);  
				String tfmeterial = rs.getString(4);
				bean_CH.setBrandName(tfbrandName);  
				bean_CH.setMenuName(tfmenuName);  
				bean_CH.setmenuprice(tfmenuprice);  
				bean_CH.setmaterialAllerge(tfmeterial);     		   
				
				
				/**
				 * @Method Name : TableClick_showImg
				 * @작성일 : 2021. 5. 2
				 * @작성자 : yejin
				 * @변경이력 :
				 * @Method설명 :테이블클릭시 이미지 출력
				 */
				// File
				ShareVar_Admin_Brand_YJ.filename = ShareVar_Admin_Brand_YJ.filename + 1;
				File file = new File(Integer.toString(ShareVar_Admin_Brand_YJ.filename));
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(5);
				byte[] buffer = new byte[1024];
				while (input.read(buffer) > 0) {
					output.write(buffer);
				}
			}
			
			conn_mysql.close();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
		return bean_CH2;	
	}
	
	
	
	
	// -------------------------------------------------------------------------------------------------------------------------
	public void tableClickCount(String a, String b) {	
		PreparedStatement ps = null;
		
		String query1 = "UPDATE menu iNNER JOIN brand on brandCode = brand_brandCode SET menuCount = menuCount + 1 ";
		String query2 = "where brandName = '" + a + "' and menuName = '" + b + "'";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
					
			Bean_CH bean_CH = new Bean_CH();
			ps = conn_mysql.prepareStatement(query1 + query2 );
	
			ps.executeUpdate();
			
			System.out.println(ps);
			conn_mysql.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	
	}
	
	
	//********************************조건 검색 부분******************************************************************

	
	
	
	// 콤보상자 선택으로 조건검색 
	public ArrayList<Bean_CH> conditionQueryDb(Bean_CH beanget){
		
		ArrayList<Bean_CH> bean_CH = new ArrayList<Bean_CH>();
				
		String query1 = "select b.brandName, m.menuType, m.menuName, mu.menuprice, m.menuCount from brand b, menu m, menuupdate mu "
				+ "where b.brandCode = m.brand_brandCode and m.menuCode = mu.menu_menuCode "
				+ "and " + beanget.getConditionQueryColumn();
		String query2 = " like '%" + beanget.getTfsearch() + "%' order by m.menuCount desc";
		System.out.println(555555);
		System.out.println(query1 + query2);
		System.out.println(555555);			
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(query1 + query2);
			
			while(rs.next()) {
				

				String tfbrandName = rs.getString(1);
				String tfmenuType = rs.getString(2);
				String tfmenuName = rs.getString(3);
				String tfmenuPrice = rs.getString(4);
				int tfmenuCount = rs.getInt(5);
				
				Bean_CH conditonBean = new Bean_CH(tfbrandName, tfmenuType, tfmenuName, tfmenuPrice, tfmenuCount);
				
				bean_CH.add(conditonBean);
									
			}
			conn_mysql.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}		
		
		return bean_CH;
	}
	
	// 가격선택시 콤보상자 검색
	
	public ArrayList<Bean_CH> priceconditionQueryDB(Bean_CH beanget) {
			System.out.println(beanget);
		int i = beanget.getCmbPriceSelect();    // 몇번째인지 알아봐주는 메서드 겟셀렉티드 인덱스
				
		String conditionPriceColumn = "";
		switch(i) {
		case 0 : 
			conditionPriceColumn = " mu.menuPrice > '0' ";
			break;
		case 1 : 
			conditionPriceColumn = " mu.menuPrice between '1,000' and '3,000' ";

			break;
		case 2 : 
			conditionPriceColumn = " mu.menuPrice between '3,000' and '6,000' ";
			break;
		case 3 : 
			conditionPriceColumn = " mu.menuPrice between '6,000' and '9,000' ";
			break;
		default : 
			break;
		
		}

		ArrayList<Bean_CH> bean_CH = new ArrayList<Bean_CH>();
		
		String query1 = "select b.brandName, m.menuType, m.menuName, mu.menuprice, m.menuCount from brand b, menu m, menuupdate mu where b.brandCode = m.brand_brandCode and m.menuCode = mu.menu_menuCode and" + conditionPriceColumn;

		System.out.println(query1);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();	
			
			ResultSet rs = stmt_mysql.executeQuery(query1);
			
			while(rs.next()) {
			
				String tfbrandName = rs.getString(1);
				String tfmenuType = rs.getString(2);
				String tfmenuName = rs.getString(3);
				String tfmenuPrice = rs.getString(4);
				int tfmenuCount = rs.getInt(5);
				
				Bean_CH conditonBean = new Bean_CH(tfbrandName, tfmenuType, tfmenuName, tfmenuPrice, tfmenuCount);
				
				bean_CH.add(conditonBean);

			}
			conn_mysql.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
		
		return bean_CH;
	}
	
	
//  ************************************************************************************************************************************	
	
	
}//----------------------------------------------------------------------------
