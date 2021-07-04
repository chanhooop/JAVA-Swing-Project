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
import java.util.List;

import com.javalec.Search.Login_YJ;
import com.javalec.bean.Bean_Admin_Menu_DS;
import com.javalec.sharevar.ShareVar_Admin_Menu_DS;

public class DbAction_Admin_Menu_DS {
	
	// field
	private final String url_mysql = ShareVar_Admin_Menu_DS.DBName;
	private final String id_mysql = ShareVar_Admin_Menu_DS.DBUser;
	private final String pw_mysql = ShareVar_Admin_Menu_DS.DBPass;
	FileInputStream file;

	int menu_brand_brandCode;
	int material_menu_menuCode;
	int brandCode;
	int menu_menuCode;
	int menuCode;
	int materialCode;
	String material;
	String material_materialCode;
	String brandName;
	String menuType;
	String menuName;
	String menuPrice;
	String adminCode;
	String materialAllergy;
	String MenuAllergy;
	java.sql.Date createDate;
	java.sql.Date updateDate;

	// constructor
	public DbAction_Admin_Menu_DS() {
		// TODO Auto-generated constructor stub
	}

	public DbAction_Admin_Menu_DS(String MenuAllergy) {
		super();
		this.MenuAllergy = MenuAllergy;
	}

	public DbAction_Admin_Menu_DS(int material_menu_menuCode, String material_materialCode, String adminCode,
			Date createDate, Date updateDate) {
		super();
		this.material_menu_menuCode = material_menu_menuCode;
		this.material_materialCode = material_materialCode;
		this.adminCode = adminCode;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public DbAction_Admin_Menu_DS(int menu_brand_brandCode, int menu_menuCode, String menuPrice, String adminCode,
			Date createDate, Date updateDate) {
		super();
		this.menu_brand_brandCode = menu_brand_brandCode;
		this.menu_menuCode = menu_menuCode;
		this.menuPrice = menuPrice;
		this.adminCode = adminCode;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public DbAction_Admin_Menu_DS(int menu_menuCode, int menuCode, String brandName, String menuType, String menuName,
			String menuPrice) {
		super();
		this.menu_menuCode = menu_menuCode;
		this.menuCode = menuCode;
		this.brandName = brandName;
		this.menuType = menuType;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
	}

	public DbAction_Admin_Menu_DS(int menuCode, String brandName, String menuName, String menuPrice) {
		super();
		this.menuCode = menuCode;
		this.brandName = brandName;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
	}

	public DbAction_Admin_Menu_DS(int menuCode, String brandName, String menuType, String menuName, String menuPrice) {
		super();
		this.menuCode = menuCode;
		this.brandName = brandName;
		this.menuType = menuType;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
	}

	public DbAction_Admin_Menu_DS(int menuCode) {
		super();
		this.menuCode = menuCode;
	}

	public DbAction_Admin_Menu_DS(String brandName, String menuName, String menuPrice) {
		super();
		this.brandName = brandName;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
	}

	public DbAction_Admin_Menu_DS(int menuCode, String menuName) {
		super();
		this.menuCode = menuCode;
		this.menuName = menuName;
	}

	public DbAction_Admin_Menu_DS(String brandName, String menuType, String menuName, String menuPrice) {
		super();
		this.brandName = brandName;
		this.menuType = menuType;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
	}

	// method
	// 메뉴 출력
	public Bean_Admin_Menu_DS login() {
		Bean_Admin_Menu_DS bean = new Bean_Admin_Menu_DS();
		PreparedStatement ps = null;
		try {
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			String select = "SELECT login.adminLogin, login.adminOnOff from coffee.login";
			ps = conn_mysql.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String adminLogin = rs.getString(1);
				String adminOnoff = rs.getString(2);
				bean = new Bean_Admin_Menu_DS(adminLogin, adminOnoff);
				conn_mysql.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bean;
	}
	public List<Bean_Admin_Menu_DS> selectAllMenu() {

		Statement stmt = null;
		ResultSet rs = null;
		List<Bean_Admin_Menu_DS> menuList = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			stmt = conn_mysql.createStatement();
			String query = "select menuCode, menuType, menuName, menuPrice, brandName, menuAllergy "
					+ "from menu m ,menuupdate mu, brand b "
					+ "where m.menucode = mu.menu_menucode and b.brandcode = m.brand_brandCode";

			stmt = conn_mysql.createStatement();
			rs = stmt.executeQuery(query);

			menuList = new ArrayList<>();

			while (rs.next()) {
				Bean_Admin_Menu_DS menu = new Bean_Admin_Menu_DS();
				menu.setMenuCode(rs.getInt("menuCode"));
				menu.setBrandName(rs.getString("brandName"));
				menu.setMenuType(rs.getString("menuType"));
				menu.setMenuName(rs.getString("menuName"));
				menu.setMenuPrice(rs.getString("menuPrice"));
				menu.setMenuAllergy(rs.getString("menuAllergy"));
				
				menuList.add(menu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menuList;
	}

	// 테이블 클릭
	public Bean_Admin_Menu_DS tableClick() {
		Bean_Admin_Menu_DS bean = null;

		String query = "select menuCode, brandName,  menuType, menuName, menuPrice, menuAllergy, menuImg "
				+ "from menu m ,menuupdate mu, brand b "
				+ "where m.menucode = " + menuCode
				+ " and mu.menu_menuCode = m.menuCode and mu.menu_brand_brandCode = b.brandCode";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(query);

			while (rs.next()) {
				int wkCode = rs.getInt(1);
				String wkbrandName = rs.getString(2);
				String wkmenuType = rs.getString(3);
				String wkmenuName = rs.getString(4);
				String wkmenuPrice = rs.getString(5);
				String wkmenuAllergy = rs.getString(6);

				// File
				ShareVar_Admin_Menu_DS.filename = ShareVar_Admin_Menu_DS.filename + 1;
				File file = new File(Integer.toString(ShareVar_Admin_Menu_DS.filename));
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(7);
				byte[] buffer = new byte[1024];
				while (input.read(buffer) > 0) {
					output.write(buffer);
				}

				bean = new Bean_Admin_Menu_DS(wkCode, wkbrandName, wkmenuType, wkmenuName, wkmenuPrice, wkmenuAllergy);
			}
			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	// 메뉴 가격 수정
	public boolean MenuPriceUpdate(String adminLogin, String menu_menuCode, int menu_brand_brandCode,
			java.sql.Date updateDate, String menuPrice, FileInputStream file) {
		PreparedStatement ps = null;

		String query = "insert into menuupdate (admin_adminCode, menu_menuCode, menu_brand_brandCode, updateDate, menuPrice, menuupdateImg) "
				+ "values (?,?,?,?,?,?)";
		try {
			PreparedStatement selectps = null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			selectps = conn_mysql.prepareStatement("select menucode from coffee.menu");
			ResultSet rs = selectps.executeQuery();
			if (rs.next()) {
				brandName = rs.getString(1);
			}

			ps = conn_mysql.prepareStatement(query);

			Bean_Admin_Menu_DS bean = new Bean_Admin_Menu_DS();
			
			ps.setString(1, Login_YJ.adminCode);
			ps.setString(2, brandName);
			ps.setInt(3, menu_brand_brandCode);
			ps.setDate(4, updateDate);
			ps.setString(5, menuPrice);
			ps.setBinaryStream(6, file);

			ps.executeUpdate();

			conn_mysql.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 메뉴 수정
	public boolean menuUpdate(int brandCode, String menutype, String menuname) {
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			String query1 = "update menu set brand_brandCode = ? , menuType = ?, menuName = ? " + "where menuCode = "
					+ menuCode;

			ps = conn_mysql.prepareStatement(query1);

			ps.setInt(1, brandCode);
			ps.setString(2, menutype);
			ps.setString(3, menuname);

			System.out.println(ps);

			ps.executeUpdate();

			conn_mysql.close();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 메뉴 삭제
	public boolean menuDelete() {
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			String A = "delete from menu where menuCode = ?";

			ps = conn_mysql.prepareStatement(A);

			ps.setInt(1, menuCode);
			ps.executeUpdate();

			conn_mysql.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 메뉴 등록
	public boolean insertAction(int brandCode, String menuType, String menuName, String menuAllergy,
			FileInputStream file) {
		PreparedStatement ps = null;
		String query = "insert into menu (brand_brandCode, menuType, menuName, menuAllergy, menuImg) values (?,?,?,?,?)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);

			ps = conn_mysql.prepareStatement(query);

			ps.setInt(1, brandCode);
			ps.setString(2, menuType);
			ps.setString(3, menuName);
			ps.setString(4, menuAllergy);
			ps.setBinaryStream(5, file);
			ps.executeUpdate();

			conn_mysql.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	// 메뉴 가격 등록
	public boolean insertMenuUpdate(String adminLogin, String menu_menuCode, int menu_brand_brandCode,
			java.sql.Date createDate, java.sql.Date updateDate, String menuPrice, FileInputStream file) {

		PreparedStatement ps = null;
		String query = "insert into menuupdate (admin_adminCode, menu_menuCode, "
				+ "menu_brand_brandCode, createDate, updateDate, menuPrice, menuupdateImg) " + "values (?,?,?,?,?,?,?)";
		try {

			PreparedStatement selectps = null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			selectps = conn_mysql.prepareStatement("select max(menucode) from coffee.menu");
			ResultSet rs = selectps.executeQuery();
			if (rs.next()) {
				brandName = rs.getString(1);
			}

			ps = conn_mysql.prepareStatement(query);
			
			Bean_Admin_Menu_DS bean = new Bean_Admin_Menu_DS();

			ps.setString(1, Login_YJ.adminCode);
			ps.setString(2, brandName);
			ps.setInt(3, menu_brand_brandCode);
			ps.setDate(4, createDate);
			ps.setDate(5, updateDate);
			ps.setString(6, menuPrice);
			ps.setBinaryStream(7, file);

			System.out.println(ps);

			ps.executeUpdate();

			conn_mysql.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
