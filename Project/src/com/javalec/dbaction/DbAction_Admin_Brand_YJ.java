package com.javalec.dbaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.javalec.Search.Login_YJ;
import com.javalec.bean.Bean_Admin_Brand_YJ;
import com.javalec.sharevar.ShareVar_Admin_Brand_YJ;

public class DbAction_Admin_Brand_YJ {

	private final String url_mysql = ShareVar_Admin_Brand_YJ.DBName;
	private final String id_mysql = ShareVar_Admin_Brand_YJ.DBUser;
	private final String pw_mysql = ShareVar_Admin_Brand_YJ.DBPass;

	FileInputStream file;
	private String brandCode;
	private String brandName;
	private String brandLogo;
	private String adminCode;
	private java.sql.Date CreateDate;
	private java.sql.Date updateDate;

	public DbAction_Admin_Brand_YJ(FileInputStream file, String brandCode, String adminCode, Date updateDate) {
		super();
		this.file = file;
		this.brandCode = brandCode;
		this.adminCode = adminCode;
		this.updateDate = updateDate;
	}

	public DbAction_Admin_Brand_YJ() {
		// TODO Auto-generated constructor stub
	}

	public DbAction_Admin_Brand_YJ(String adminCode, String brandCode, Date createDate, Date updateDate,
			FileInputStream file) {
		super();
		this.brandCode = brandCode;
		this.adminCode = adminCode;
		this.CreateDate = createDate;
		this.updateDate = updateDate;
		this.file = file;
	}

	public DbAction_Admin_Brand_YJ(String brandName, FileInputStream file, String brandCode) {
		super();
		this.file = file;
		this.brandCode = brandCode;
		this.brandName = brandName;
	}

	public DbAction_Admin_Brand_YJ(String brandName, String brandLogo) {
		super();
		this.brandName = brandName;
		this.brandLogo = brandLogo;
	}

	public DbAction_Admin_Brand_YJ(String brandName, FileInputStream file) {
		super();
		this.file = file;
		this.brandName = brandName;
	}

	public DbAction_Admin_Brand_YJ(String brandCode) {
		super();
		this.brandCode = brandCode;
	}

	public DbAction_Admin_Brand_YJ(String brandCode, Date updateDate) {
		super();
		this.brandCode = brandCode;
		this.updateDate = updateDate;
	}

//	public Bean_Admin_Brand_YJ login() {
//		Bean_Admin_Brand_YJ bean = new Bean_Admin_Brand_YJ();
//		PreparedStatement ps = null;
//		try {
//			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
//			String select = "SELECT login.adminLogin, login.adminOnOff from coffee.login";
//			ps = conn_mysql.prepareStatement(select);
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				String adminLogin = rs.getString(1);
//				String adminOnoff = rs.getString(2);
//				bean = new Bean_Admin_Brand_YJ(adminLogin, adminOnoff);
//				conn_mysql.close();
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return bean;
//	}

	public ArrayList<Bean_Admin_Brand_YJ> SelectList() {
		ArrayList<Bean_Admin_Brand_YJ> BeanList = new ArrayList<Bean_Admin_Brand_YJ>();

		String WhereDefault = "select brandCode, brandName from brand";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(WhereDefault);

			while (rs.next()) {

				int brandCode = rs.getInt(1);
				String brandName = rs.getString(2);

				Bean_Admin_Brand_YJ bean = new Bean_Admin_Brand_YJ(brandCode, brandName);
				BeanList.add(bean);
			}

			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return BeanList;
	}

	public boolean insertBrand() {
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			String query1 = "insert into brand (brandName, brandLogo)";
			String query2 = " values (?, ?)";

			ps = conn_mysql.prepareStatement(query1 + query2);

			ps.setString(1, brandName);
			ps.setBinaryStream(2, file);
			ps.executeUpdate();

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean insertBrandHistory() {
		PreparedStatement ps = null;
		try {
			PreparedStatement selectps = null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			selectps = conn_mysql.prepareStatement("select max(brandCode) from coffee.brand");
			ResultSet rs = selectps.executeQuery();
			if (rs.next()) {
				brandCode = rs.getString(1);
			}

			String query1 = "insert into brandUpdate (admin_adminCode, brand_brandCode, CreateDate, updateDate, updateImg)";
			String query2 = " values (?, ?, ?, ?, ?)";
			ps = conn_mysql.prepareStatement(query1 + query2);
			
			Bean_Admin_Brand_YJ bean = new Bean_Admin_Brand_YJ();

			ps.setString(1, adminCode);
			ps.setString(2, brandCode);
			ps.setDate(3, CreateDate);
			ps.setDate(4, updateDate);
			ps.setBinaryStream(5, file);
			ps.executeUpdate();
			System.out.println(Login_YJ.adminCode);
			conn_mysql.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Bean_Admin_Brand_YJ tableClick() {
		Bean_Admin_Brand_YJ bean = null;
		String WhereDefault = "select brandCode, brandName, brandLogo from brand ";
		String WhereDefault2 = "where brandCode = " + brandCode;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);

			if (rs.next()) {

				int brandCode = rs.getInt(1);
				String brandName = rs.getString(2);

				// File
				ShareVar_Admin_Brand_YJ.filename = ShareVar_Admin_Brand_YJ.filename + 1;
				File file = new File(Integer.toString(ShareVar_Admin_Brand_YJ.filename));
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(3);
				byte[] buffer = new byte[1024];
				while (input.read(buffer) > 0) {
					output.write(buffer);
				}

				bean = new Bean_Admin_Brand_YJ(brandCode, brandName);
			}

			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bean;
	}

	public Bean_Admin_Brand_YJ cencelInfo(int selected) {
		Bean_Admin_Brand_YJ bean = null;
		String WhereDefault = "select brandCode, brandName, brandLogo from brand ";
		String WhereDefault2 = "where brandCode = " + selected;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);

			if (rs.next()) {

				int brandCode = rs.getInt(1);
				String brandName = rs.getString(2);

				// File
				ShareVar_Admin_Brand_YJ.filename = ShareVar_Admin_Brand_YJ.filename + 1;
				File file = new File(Integer.toString(ShareVar_Admin_Brand_YJ.filename));
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(3);
				byte[] buffer = new byte[1024];
				while (input.read(buffer) > 0) {
					output.write(buffer);
				}

				bean = new Bean_Admin_Brand_YJ(brandCode, brandName);
			}

			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bean;
	}

	public boolean editAction() {

		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			@SuppressWarnings("unused")
			Statement stmt_mysql = conn_mysql.createStatement();
//			brand brandName brandLogo

			String A = "update brand set brandName = ?, brandLogo = ? ";
			String B = "where brandCode = ?";

			ps = conn_mysql.prepareStatement(A + B);

			ps.setString(1, brandName);
			ps.setBinaryStream(2, file);
			ps.setString(3, brandCode);
			ps.executeUpdate();

			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public boolean editActionHistory() {

		PreparedStatement ps = null;
		try {
			PreparedStatement selectps = null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			selectps = conn_mysql.prepareStatement("select brandCode from coffee.brand");
			ResultSet rs = selectps.executeQuery();
			if (rs.next()) {
				brandCode = rs.getString(1);
			}

			String query1 = "insert into brandUpdate (updateDate, updateImg, brand_brandCode, admin_adminCode)";
			String query2 = " value (?, ?, ?, ?)";
			ps = conn_mysql.prepareStatement(query1 + query2);

			ps.setDate(1, updateDate);
			ps.setBinaryStream(2, file);
			ps.setString(3, brandCode);
			ps.setString(4, adminCode);
			ps.executeUpdate();
			conn_mysql.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteAction(String brandCode) {
		String query1 = "delete from brand where brandCode = " + brandCode;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			stmt_mysql.executeUpdate(query1);
			conn_mysql.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
