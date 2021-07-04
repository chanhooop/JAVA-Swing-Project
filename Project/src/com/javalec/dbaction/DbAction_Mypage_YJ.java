package com.javalec.dbaction;
//테스ㅌㅌ
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.javalec.Search.Login_YJ;
import com.javalec.bean.Bean_Mypage_YJ;
import com.javalec.sharevar.ShareVar_Mypage_YJ;

public class DbAction_Mypage_YJ {
	// field
	private final String url_mysql = ShareVar_Mypage_YJ.url_mysql;
	private final String id_mysql = ShareVar_Mypage_YJ.id_mysql;
	private final String pw_mysql = ShareVar_Mypage_YJ.pw_mysql;
//	clientId, clientPw, clientName, clientTelno, clientNick
	String clientId;
	String clientPw;
	String clientPwCheck;
	String clientName;
	String clientTelno;
	String clientNick;

	public DbAction_Mypage_YJ() {
		// TODO Auto-generated constructor stub
	}

	public DbAction_Mypage_YJ(String clientPw, String clientName, String clientTelno, String clientNick) {
		super();
		this.clientPw = clientPw;
		this.clientName = clientName;
		this.clientTelno = clientTelno;
		this.clientNick = clientNick;
	}

	public DbAction_Mypage_YJ(String clientId) {
		super();
		this.clientId = clientId;
	}

	public DbAction_Mypage_YJ(String clientId, String clientPw, String clientName) {
		super();
		this.clientId = clientId;
		this.clientPw = clientPw;
		this.clientName = clientName;
	}

	public DbAction_Mypage_YJ(String clientId, String clientPw, String clientPwCheck, String clientName, String clientTelno,
			String clientNick) {
		super();
		this.clientId = clientId;
		this.clientPw = clientPw;
		this.clientPwCheck = clientPwCheck;
		this.clientName = clientName;
		this.clientTelno = clientTelno;
		this.clientNick = clientNick;
	}

	public DbAction_Mypage_YJ(String clientId, String clientPw, String clientName, String clientTelno, String clientNick) {
		super();
		this.clientId = clientId;
		this.clientPw = clientPw;
		this.clientName = clientName;
		this.clientTelno = clientTelno;
		this.clientNick = clientNick;
	}

	public DbAction_Mypage_YJ(String clientId, String clientNick) {
		super();
		this.clientId = clientId;
		this.clientNick = clientNick;
	}

	public Bean_Mypage_YJ adminRoginAction() {
		Bean_Mypage_YJ bean = null;
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
			bean = new Bean_Mypage_YJ(wkId, wkPw, wkName);
			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	public Bean_Mypage_YJ getMypage() {
		Bean_Mypage_YJ bean = null;
		String query = "select clientId, clientPw, clientName, clientTelno, clientNick from client ";
		String query1 = "where clientCode = '" + Login_YJ.clientCode + "'";
//		System.out.println(userId);
		System.out.println(query + query1);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(query + query1);

			if (rs.next()) {
				clientId = rs.getString("clientId");
				clientPw = rs.getString("clientPw");
				clientPwCheck = rs.getString("clientPw");
				clientName = rs.getString("clientName");
				clientTelno = rs.getString("clientTelno");
				clientNick = rs.getString("clientNick");
			}

			bean = new Bean_Mypage_YJ(clientId, clientPw, clientPwCheck, clientName, clientTelno, clientNick);

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	public boolean updateUser() {
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
//		(clientId, clientPw, clientName, clientTelno, clientNick)
			String query = "update client set ";
			String query1 = "clientPw = ?, clientName = ?, clientTelno = ?, clientNick = ? where clientId = '"
					+ clientId + "'";

			ps = conn_mysql.prepareStatement(query + query1);
			System.out.println(query + query1);
			ps.setString(1, clientPw);
			ps.setString(2, clientName);
			ps.setString(3, clientTelno);
			ps.setString(4, clientNick);
			ps.executeUpdate(); // 입력

			conn_mysql.close();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Bean_Mypage_YJ checkNick() {
		Bean_Mypage_YJ bean = null;
		String checkNick = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			String query = "select clientNick from client where clientNick = '" + clientNick + "'";

			ResultSet rs = stmt_mysql.executeQuery(query);

			while (rs.next()) {
				checkNick = rs.getString(1);
			}
			bean = new Bean_Mypage_YJ(checkNick);

			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;

	}

}
