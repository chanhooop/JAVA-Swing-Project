package com.javalec.dbaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.javalec.bean.Bean_Client_FirstView_YJ;
import com.javalec.sharevar.ShareVar_Client_FirstView_YJ;

public class DbAction_Client_FirstView_YJ {
	private final String url_mysql = ShareVar_Client_FirstView_YJ.DBName;
	private final String id_mysql = ShareVar_Client_FirstView_YJ.DBUser;
	private final String pw_mysql = ShareVar_Client_FirstView_YJ.DBPass;

	
	
	public Bean_Client_FirstView_YJ login() {
		Bean_Client_FirstView_YJ bean = new Bean_Client_FirstView_YJ();
		PreparedStatement ps = null;
		try {
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			String select = "SELECT login.adminLogin, login.adminOnOff from coffee.login";
			ps = conn_mysql.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String adminLogin = rs.getString(1);
				String adminOnoff = rs.getString(2);
				bean = new Bean_Client_FirstView_YJ(adminLogin, adminOnoff);
				conn_mysql.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bean;
	}
}
