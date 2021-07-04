package com.javalec.MainPackage;
import com.javalec.Search.Admin_FirstView_YJ;
import com.javalec.Search.Client_FirstView_YJ;
import com.javalec.Search.Client_Mypage_YJ;
//werwfsf
import com.javalec.Search.Login_YJ;
import com.javalec.Search.Main_Id_CJY;
import com.javalec.Search.Main_Join_CJY;
import com.javalec.Search.Main_Pw_CJY;
import com.javalec.Search.Search_CH;


public class MainProcess {
	
	public Login_YJ login_YJ; // 로그인 
	Client_FirstView_YJ clientFirst; // 고객 -> 첫화면(검색하러가기) 
	// 실행시킬 클래스를 변수 설정
	Search_CH search;  // 검색창 
	Client_Mypage_YJ mypage; // 검색창 -> 마이페이지 
	Main_Id_CJY main_Id_CJY;
	Main_Join_CJY main_Join_CJY;
	Main_Pw_CJY main_Pw_CJY;
	Client_FirstView_YJ client_FirstView_YJ;  
	Admin_FirstView_YJ admin_FirstView_YJ;
	 
	
	public static void main(String[] args) {
		MainProcess main = new MainProcess();
		main.login_YJ = new Login_YJ();
		main.login_YJ.setMain(main);		
		}
	
	public void showClientFirst() { // 검색하러가기  
		login_YJ.dispose();
		clientFirst = new Client_FirstView_YJ();
		
	}
	// 아이스아메리카노 

	public void showMain_Id_CJY() {
		login_YJ.dispose();
		main_Id_CJY = new Main_Id_CJY();
		
	}

	public void showMain_Join_CJY() {
		login_YJ.dispose();
		main_Join_CJY = new Main_Join_CJY();
	}

	public void showMain_Pw_CJY() {
		login_YJ.dispose();
		main_Pw_CJY = new Main_Pw_CJY();
		
	}

	public void showClient_FirstView_YJ() {
		login_YJ.dispose();
		client_FirstView_YJ = new Client_FirstView_YJ();
		
	}

	public void showSearch_CH() {
		client_FirstView_YJ.dispose();
		search = new Search_CH();
		
	}

	public void showAdmin_FirstView_YJ() {
		login_YJ.dispose();
		admin_FirstView_YJ  = new Admin_FirstView_YJ();
	
	}
	


}
