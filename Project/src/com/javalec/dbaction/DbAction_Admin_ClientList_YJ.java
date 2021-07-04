package com.javalec.dbaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.bean.Bean_Admin_Brand_YJ;
import com.javalec.bean.Bean_Admin_ClientList_YJ;
import com.javalec.sharevar.ShareVar_Admin_ClientList_YJ;

public class DbAction_Admin_ClientList_YJ {

	// field
	private final String url_mysql = ShareVar_Admin_ClientList_YJ.url_mysql;
	private final String id_mysql = ShareVar_Admin_ClientList_YJ.id_mysql;
	private final String pw_mysql = ShareVar_Admin_ClientList_YJ.pw_mysql;

	int clientCode;
	String clientId;
	String clientPw;
	String clientName;
	String clientTelno;
	String clientNick;
	String adminId;
	String adminPw;

	// constructor
	public DbAction_Admin_ClientList_YJ() {
		// TODO Auto-generated constructor stub
	}

	public DbAction_Admin_ClientList_YJ(int clientCode, String clientId, String clientPw) {
		super();
		this.clientCode = clientCode;
		this.clientId = clientId;
		this.clientPw = clientPw;
	}

	public DbAction_Admin_ClientList_YJ(int clientCode, String clientId, String clientPw, String clientName,
			String clientTelno, String clientNick) {
		super();
		this.clientCode = clientCode;
		this.clientId = clientId;
		this.clientPw = clientPw;
		this.clientName = clientName;
		this.clientTelno = clientTelno;
		this.clientNick = clientNick;
	}

	public DbAction_Admin_ClientList_YJ(int clientCode) {
		super();
		this.clientCode = clientCode;
	}

	public DbAction_Admin_ClientList_YJ(String adminId, String adminPw) {
		super();
		this.adminId = adminId;
		this.adminPw = adminPw;
	}

	// method

	public Bean_Admin_ClientList_YJ login() {
		Bean_Admin_ClientList_YJ bean = new Bean_Admin_ClientList_YJ();
		PreparedStatement ps = null;
		try {
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			String select = "SELECT login.adminLogin, login.adminOnOff from coffee.login";
			ps = conn_mysql.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String adminLogin = rs.getString(1);
				String adminOnoff = rs.getString(2);
				bean = new Bean_Admin_ClientList_YJ(adminLogin, adminOnoff);
				conn_mysql.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bean;
	}

	public ArrayList<Bean_Admin_ClientList_YJ> selectList() {
		ArrayList<Bean_Admin_ClientList_YJ> beanList = new ArrayList<Bean_Admin_ClientList_YJ>();
		String WhereDefault = "select clientCode, clientId, clientName, clientTelno, clientNick from client";
		;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(WhereDefault);

			while (rs.next()) {
				int clientCode = rs.getInt(1);
				String clientId = rs.getString(2);
				String clientName = rs.getString(3);
				String clientTelno = rs.getString(4);
				String clientNick = rs.getString(5);

				Bean_Admin_ClientList_YJ bean = new Bean_Admin_ClientList_YJ(clientCode, clientId, clientName,
						clientTelno, clientNick);
//				Bean bean = new Bean(wkSeq, wkName, wkTelno, wkRelation);
				beanList.add(bean);
			}
			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beanList;
	}

	public Bean_Admin_ClientList_YJ tableClick() {
		Bean_Admin_ClientList_YJ bean = null;
		String query = "select clientCode, clientId, clientPw, clientName, clientTelno, clientNick from client where clientCode = "
				+ clientCode;

//		System.out.println(query);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(query);

			while (rs.next()) {
				int wkCode = rs.getInt(1);
				String wkId = rs.getString(2);
				String wkPw = rs.getString(3);
				String wkName = rs.getString(4);
				String wkTelno = rs.getString(5);
				String wkNick = rs.getString(6);

				bean = new Bean_Admin_ClientList_YJ(wkCode, wkId, wkPw, wkName, wkTelno, wkNick);
			}
			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	public boolean UpdateAction() {
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			@SuppressWarnings("unused")
			Statement stmt_mysql = conn_mysql.createStatement();

			String A = "update client set clientPw = ?, clientTelno = ?, clientNick = ? ";
			String B = " where clientCode = ? ";

			ps = conn_mysql.prepareStatement(A + B);
			Bean_Admin_ClientList_YJ bean = new Bean_Admin_ClientList_YJ();

			ps.setString(1, clientPw);
			ps.setString(2, clientTelno);
			ps.setString(3, clientNick);
			ps.setInt(4, clientCode);
			ps.executeUpdate();

			conn_mysql.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
