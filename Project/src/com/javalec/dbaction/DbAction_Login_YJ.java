package com.javalec.dbaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.javalec.bean.Bean_Login_YJ;
import com.javalec.sharevar.ShareVar_Login_YJ;

public class DbAction_Login_YJ {
	private final String url_mysql = ShareVar_Login_YJ.url_mysql;
	private final String id_mysql = ShareVar_Login_YJ.id_mysql;
	private final String pw_mysql = ShareVar_Login_YJ.pw_mysql;
	
	
	private String clientId;
	private String clientPw;
	private String clientName;
	
	private String adminId;
	private String adminPw;
	
	String getAdminId = "";
	String getClientId = "";
	
	
	public DbAction_Login_YJ(String adminId, String adminPw) {
		super();
		this.adminId = adminId;
		this.adminPw = adminPw;
	}

	public DbAction_Login_YJ(String clientId, String clientPw, String clientName) {
		super();
		this.clientId = clientId;
		this.clientPw = clientPw;
		this.clientName = clientName;
	}
	
	public Bean_Login_YJ clientRoginAction() {
		Bean_Login_YJ bean = null;
		String wkId = "";
		String wkPw = "";
		String wkName = "";
		try {
			String query = "select clientId, clientPw, clientName from client where clientId = '" + clientId
					+ "' and clientPw = '" + clientPw + "'";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(query);

			if (rs.next()) {
				wkId = rs.getString(1);
				wkPw = rs.getString(2);
				wkName = rs.getString(3);
			}
			bean = new Bean_Login_YJ(wkId, wkPw, wkName);
			getClientId = bean.getClientId();
//			String delete = "Delete from coffee.login";
//			String insert = "INSERT INTO `coffee`.`login` VALUES ('0','0','0')";
//			String update = "update coffee.login set \n"
//					+ "login.userLogin = (select client.clientNick from coffee.client where client.clientId = '" + clientId + "'),\n"
//					+ "login.adminLogin = '',\n"
//					+ "login.adminOnOff = '';"; // 로그인 정보 업데이트쿼리
//			stmt_mysql.executeUpdate(delete); // 로그인정보 테이블 초기화 실행
//			stmt_mysql.executeUpdate(insert); // 로그인정보 테이블 행1개 추가 실행
//			stmt_mysql.executeUpdate(update); // 로그인정보 업데이트 실행
			conn_mysql.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	
	public Bean_Login_YJ adminRoginAction() {
		String wkId = "";
		String wkPw = "";
		Bean_Login_YJ bean = null;
		String query = "select adminId, adminPw from admin where adminId = '" + adminId + "' and adminPw = '" + adminPw + "'; ";

//		String delete = "Delete from coffee.login";
//		String insert = "INSERT INTO `coffee`.`login` VALUES ('0','0','0')";
//		String update = "update coffee.login set \n"
//				+ "login.userLogin = '',\n"
//				+ "login.adminLogin = (select admin.adminId from coffee.admin where admin.adminId = '" + adminId + "'),\n"
//				+ "login.adminOnOff = 'admin'\n";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();

				ResultSet rs = stmt_mysql.executeQuery(query);
				
				if (rs.next()) {
					wkId = rs.getString(1);
					wkPw = rs.getString(2);
				}
				bean = new Bean_Login_YJ(wkId, wkPw);
				getAdminId = bean.getAdminId();
//				System.out.println(update);
//				stmt_mysql.executeUpdate(delete); // 로그인정보 테이블 초기화 실행
//				stmt_mysql.executeUpdate(insert); // 로그인정보 테이블 행1개 추가 실행
//				stmt_mysql.executeUpdate(update); // 로그인정보 업데이트 실행
//				conn_mysql.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bean;
	}
	
	public String adminGetCode() {
		String adminCode = "";
		try {
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			String query1 = "select adminCode from admin where adminId = '" + getAdminId + "'";
			ResultSet rs1 = stmt_mysql.executeQuery(query1);
			System.out.println(rs1);
			if (rs1.next()) {
				adminCode = rs1.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adminCode;
	}
	
	public String clientgetCode() {
		String clientCode = "";
		try {
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			String query1 = "select clientCode from client where clientId = '" + getClientId + "'";
			ResultSet rs1 = stmt_mysql.executeQuery(query1);
			System.out.println(rs1);
			if (rs1.next()) {
				clientCode = rs1.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientCode;
	}
	
}
