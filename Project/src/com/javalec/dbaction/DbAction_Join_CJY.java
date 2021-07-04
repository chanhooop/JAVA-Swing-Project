package com.javalec.dbaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.javalec.sharevar.ShareVar_Join_CJY;

public class DbAction_Join_CJY {
	
	// field
	
	private final String url_mysql = ShareVar_Join_CJY.url_mysql;
	private final String id_mysql = ShareVar_Join_CJY.id_mysql;
	private final String pw_mysql = ShareVar_Join_CJY.pw_mysql;
	
	String id = "";
	String pw = "";
	String pw2 = "";
	String name = "";
	String telno = "";
	String nick = "";

	
	// construction
	public DbAction_Join_CJY() {
	
		
	}  // 기본 생성자는 생략가능하지만 있어주느 게 좋음
	

	
	public DbAction_Join_CJY(String id, String pw, String name, String telno, String nick) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.telno = telno;
		this.nick = nick;
	}
	

	
	// -----------------------------
	// Method
	// -----------------------------




	// 완료 버튼
	public boolean okAction() {

		PreparedStatement ps = null; // PreparedStatement 전용공간 ps를 만든다.

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			String query = "insert into client (clientId, clientPw, clientName, clientTelno, clientNick)"; // 다 들어가지만 길어서 뭐리문장 2개로 나눔																								// 나눠놓음
			String query1 = " values (?, ?, ?, ?, ?)"; // valuse 앞에 띄어쓰기 꼭 해야한다. : 윗 문장하고 다른 문장으로 인식하기 위해서
//			System.out.println(query + query1);
//			"join_password"클래스에서는 String query문장이 try 바깥에 있다 왜? DbAction클래스 처럼 선언해주는 곳이 따로 없으니깐!!			
			
			ps = conn_mysql.prepareStatement(query + query1);
				ps.setString(1, id); // tfName에 입력된 텍스트 1번 자리에 설정
				ps.setString(2, pw);
				ps.setString(3, name);
				ps.setString(4, telno);
				ps.setString(5, nick);
				ps.executeUpdate(); // sql 자료 업데이트 실행
				
			conn_mysql.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace(); // 화면에 에러 코드를 보여줘라
			return false;
		}
	}
	
	
	// 아이디 중복 체크
	public boolean checkIdAction(String checkId) {
		
//		PreparedStatement ps = null;  / " value ? "있을 때만 즉, 사용자가 입력했을 때만 필요
		boolean check = false;
		
		int dataCount = 0; // 다른 클래스에 넘겨줄 때 필요함, 0이면 없는애고 다른 숫자일 경우 중복된 것이다.
		// 여기서 dataCount는 쿼리문장에서 있는지를 확인한다. 만약 sql에 데이터가 있다면 while문을 돌 것이고 아니면 while 밖으로 빠져 나감.
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();	
			
			String query1 = "select clientId from client where clientId = '";
			String query2 = checkId + "'";
			
			ResultSet rs = stmt_mysql.executeQuery(query1 + query2);			
			
			while(rs.next()) {				// rs.next() = boolean
//	
				dataCount++;				
			}

			conn_mysql.close(); // sql을 정말 끝낸다고 선언함
			if (dataCount > 0){
				check = false;
			}else {
				check = true;				
			}

			
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return check;
	}
	
	// 닉네임 중복 체크
	public boolean checkNickAction(String checkNick) {
			
		boolean check = false;
		// 보통 "int = 0;"처럼 초기화 하듯이 boolean은 "boolean = false;"로 초기화를 시켜준다.
			
		int dataCount = 0; 
			
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();	
			
			String query1 = "select clientNick from client where clientNick = '";
			String query2 = checkNick + "'";
			
			ResultSet rs = stmt_mysql.executeQuery(query1 + query2);			
			
			while(rs.next()) {				// rs.next() = boolean

				dataCount++;				
			}

			conn_mysql.close(); 
			
			if (dataCount > 0) {
				check = false;
			} else  {
				check = true;
			}
		}catch(Exception e) {
			e.printStackTrace();					
		}
		return check;
	}
	
	

	
} //----------------------------
