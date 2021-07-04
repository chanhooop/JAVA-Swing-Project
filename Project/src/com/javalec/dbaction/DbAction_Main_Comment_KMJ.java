
package com.javalec.dbaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import com.javalec.bean.Bean_Main_List_KMJ;
import com.javalec.bean.Bean_Main_Comment_KMJ;
import com.javalec.sharevar.ShareVar_Main_Comment_KMJ;

/**
 * @FileName : coffeeSearchAction_KMJ.java
 * @Project : Project2
 * @Date : 2021. 4. 30.
 * @작성자 : gimminjae
 * @변경이력 :
 * @프로그램설명 : 메뉴리스트, 댓글리스트CRUD
 */
public class DbAction_Main_Comment_KMJ {

	private final String url_mysql = ShareVar_Main_Comment_KMJ.url_mysql;
	private final String id_mysql = ShareVar_Main_Comment_KMJ.id_mysql;
	private final String pw_mysql = ShareVar_Main_Comment_KMJ.pw_mysql;
	String brandName;
	String menuType;
	String menuName;
	String price;
	String comment;
	String clientCode;

	/**
	 * @Method Name : searchLisetInnertable
	 * @작성일 : 2021. 4. 30.
	 * @작성자 : gimminjae
	 * @변경이력 :
	 * @Method설명 : 메뉴리스트 초기화 SELECT
	 * @param beanData
	 * @return
	 */
	public Bean_Main_Comment_KMJ login () {
		Bean_Main_Comment_KMJ bean = new Bean_Main_Comment_KMJ();
		PreparedStatement ps = null;
		try {
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			String select = "SELECT login.userLogin, login.adminOnOff from coffee.login";
			ps = conn_mysql.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String userLogin = rs.getString(1);
				String adminOnOff = rs.getString(2);
				bean = new Bean_Main_Comment_KMJ(userLogin, adminOnOff);
		        conn_mysql.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bean;
	}
	public ArrayList<Bean_Main_List_KMJ> searchLisetInnertable(Bean_Main_List_KMJ beanData) {
		ArrayList<Bean_Main_List_KMJ> beanList = new ArrayList<Bean_Main_List_KMJ>();
		PreparedStatement ps = null;
		String topic = "";
		if (beanData.getTopic().trim() == "메뉴타입") {
			topic = "menu.menuType";
		}
		if (beanData.getTopic().trim() == "메뉴명") {
			topic = "menu.menuName";
		}
		if (beanData.getTopic().trim() == "브랜드명") {
			topic = "brand.brandName";
		}
		if (beanData.getTopic().trim() == "가격") {
			topic = "menuUpdate.menuprice";
		}
		try {
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			String select = "SELECT brand.brandName, menu.menuType, menu.menuName, menuUpdate.menuPrice \n";
			String from = "FROM coffee.menu, coffee.brand, coffee.menuUpdate\n";
			String where = "WHERE menu.brand_brandCode = brand.brandCode\n";
			String and1 = "and menu.menuCode = menuUpdate.menu_menuCode\n";
			String and2 = "and " + topic + " like ? \n";
			String orderBy = "order by " + topic;// 쿼리문 합치기 편하게 구분
			ps = conn_mysql.prepareStatement(select + from + where + and1 + and2 + orderBy); // 데이터베이스에 쿼리를 실행한 상태, 아직
			ps.setString(1, "%" + beanData.getSearchValue().trim() + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String selectBrandName = rs.getString(1);
				String selectMenuType = rs.getString(2);
				String selectMenuName = rs.getString(3);
				String selectMenuPrice = rs.getString(4);
				Bean_Main_List_KMJ bean = new Bean_Main_List_KMJ(selectBrandName, selectMenuType, selectMenuName,
						selectMenuPrice);
				beanList.add(bean);
			}
			conn_mysql.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return beanList;
	}

	/**
	 * @Method Name : commentLisetInnertable
	 * @작성일 : 2021. 4. 30.
	 * @작성자 : gimminjae
	 * @변경이력 : 
	 * 2021. 4. 31 => 쿼리문 login 정보 추가, 클라이언트 닉네임 가져오기 
	 * @Method설명 : 댓글리스트 초기화 SELECT
	 * @param beanData
	 * @return
	 */
	public ArrayList<Bean_Main_Comment_KMJ> commentLisetInnertable(Bean_Main_Comment_KMJ beanData) {
		ArrayList<Bean_Main_Comment_KMJ> beanList = new ArrayList<Bean_Main_Comment_KMJ>();
		PreparedStatement ps = null;
		try {
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			String select = "SELECT client.clientNick, comment.comment, comment.client_clientCode, comment.commentCode, login.userLogin, login.adminOnOff\n";
			String from = "FROM coffee.brand, coffee.menu, coffee.client, coffee.comment, coffee.login\n";
			String where = "WHERE coffee.comment.client_clientCode = coffee.client.clientCode\n";
			String and1 = "AND coffee.comment.menu_menuCode = coffee.menu.menuCode \n";
			String and2 = "AND coffee.comment.brand_brandCode = coffee.brand.brandCode\n";
			String and3 = "AND coffee.brand.brandName = ? AND coffee.menu.menuName = ?\n";
			String and4 = "AND commentOnOff = '1' \n";

			ps = conn_mysql.prepareStatement(select + from + where + and1 + and2 + and3 + and4); // 데이터베이스에 쿼리를 실행한 상태,
																									// 아직
			ps.setString(1, beanData.getBrandName().trim());
			ps.setString(2, beanData.getMenuName().trim());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String ClientNick = rs.getString(1);
				String commentComment = rs.getString(2);
				String clientCode = rs.getString(3);
				String commentCode = rs.getString(4);
				String userNick = rs.getString(5);
				String adminOnOff = rs.getString(6);
				Bean_Main_Comment_KMJ bean = new Bean_Main_Comment_KMJ(ClientNick, commentComment, clientCode, commentCode, userNick, adminOnOff);
				beanList.add(bean);
			}
			conn_mysql.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return beanList;
	}

	/**
	  * @Method Name : addCommend
	  * @작성일 : 2021. 4. 30.
	  * @작성자 : gimminjae
	  * @변경이력 : 
	  * @Method설명 : 댓글 달기 INSERT
	  * @param beanData
	  * @return
	  */
	public boolean addCommend(Bean_Main_Comment_KMJ beanData) {
		PreparedStatement selectps = null;
		PreparedStatement ps = null;
		try {
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			String select = "SELECT brand.brandCode, menu.menuCode, client.clientCode ";
			String from = "FROM coffee.menu, coffee.brand, coffee.login, coffee.client ";
			String where = "WHERE menu.brand_brandCode = brand.brandCode and brand.brandName = ? and menu.menuName = ? and login.userLogin = client.clientNick";
			selectps = conn_mysql.prepareStatement(select + from + where);
			selectps.setString(1, beanData.getBrandName());
			selectps.setString(2, beanData.getMenuName());
			ResultSet rs = selectps.executeQuery();
//			if (rs.next()) {
//				brandName = rs.getString(1);
//				menuName = rs.getString(2);
//				clientCode = rs.getString(3);
//			}
			String insert = "INSERT INTO coffee.comment (brand_brandCode, menu_menuCode, client_clientCode, coffee.comment.comment, commentDate, commentOnOff) ";
			String values1 = "VALUES (?, ?, ?, ?, now(), '1')";
			ps = conn_mysql.prepareStatement(insert + values1);
			if (rs.next()) {
			ps.setString(1, rs.getString(1));
			ps.setString(2, rs.getString(2));
			ps.setString(3, rs.getString(3));
			ps.setString(4, beanData.getComment());
			}
			ps.executeUpdate();
			conn_mysql.close();

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	/**
	 * @Method Name : commentUpdate
	 * @작성일 : 2021. 4. 30.
	 * @작성자 : gimminjae
	 * @변경이력 :
	 * @Method설명 : 댓글 수정 UPDATE
	 * @param beanData
	 * @return
	 */
	public boolean commentUpdate(Bean_Main_Comment_KMJ beanData) {
		PreparedStatement ps = null;
		try {
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			String update = "Update coffee.comment set comment.comment = ? where comment.commentCode = ?";
			ps = conn_mysql.prepareStatement(update);
			ps.setString(1, beanData.getComment());
			ps.setString(2, beanData.getCommentCode());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	/**
	  * @Method Name : commentDelete
	  * @작성일 : 2021. 4. 30.
	  * @작성자 : gimminjae
	  * @변경이력 : 
	  * @Method설명 : 댓글 삭제 DELETE
	  * @param beanData
	  * @return
	  */
	public boolean commentDelete(Bean_Main_Comment_KMJ beanData) {
		PreparedStatement ps = null;
		try {
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			String update = "Update coffee.comment set comment.commentOnOff = '0' where comment.commentCode = ?";
			ps = conn_mysql.prepareStatement(update);
			ps.setString(1, beanData.getCommentCode());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}


}
