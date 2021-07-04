package com.javalec.Search;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.bean.Bean_Main_List_KMJ;
import com.javalec.bean.Bean_Main_Comment_KMJ;
import com.javalec.dbaction.DbAction_Main_Comment_KMJ;
import com.mysql.cj.x.protobuf.MysqlxCrud.Column;

import javax.swing.JTable;

import java.awt.Font;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @FileName : coffeeSearch_KMJ.java
 * @Project : Project2
 * @Date : 2021. 4. 30.
 * @작성자 : gimminjae
 * @변경이력 :
 * @프로그램설명 : 엑션 클래스
 */
public class Main_Main_Comment_KMJ extends JFrame{

	private JFrame frame;
	private JPanel panel;
	private JComboBox cbSerarch;
	private JTextField tfSearch;
	private JButton btnsearch;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JTable searchList_table;
	private JLabel lblNewLabel_1;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel_1_1;
	private JTextField textField_3;
	private JLabel lblNewLabel_1_2;
	private JLabel lblNewLabel_2;
	private JScrollPane spComment;
	private JTable comment_table;
	private JLabel lblNewLabel_1_3;
	private JTextField tfComment;
	private JButton btnCommentInsert;
	private final DefaultTableModel search_Out_Table = new DefaultTableModel();
	private final DefaultTableModel comment_Out_Table = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Main_Comment_KMJ window = new Main_Main_Comment_KMJ();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main_Main_Comment_KMJ() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				searchLisetOuttable();
				commentOuttable();
				searchLisetInnertable();
			}
		});
		frame.setBounds(100, 100, 450, 559);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(getPanel(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getCbSerarch());
			panel.add(getTfSearch());
			panel.add(getBtnsearch());
			panel.add(getLblNewLabel());
			panel.add(getScrollPane());
			panel.add(getLblNewLabel_1());
			panel.add(getTextField_1());
			panel.add(getTextField_2());
			panel.add(getLblNewLabel_1_1());
			panel.add(getTextField_3());
			panel.add(getLblNewLabel_1_2());
			panel.add(getLblNewLabel_2());
			panel.add(getSpComment());
			panel.add(getLblNewLabel_1_3());
			panel.add(getTfComment());
			panel.add(getBtnCommentInsert());
			panel.add(getTfLogin());
			panel.add(getTfAdmin());
			panel.add(getBtnCommentUpdate());
		}
		return panel;
	}

	private JComboBox getCbSerarch() {// 검색 주제 콤보
		if (cbSerarch == null) {
			cbSerarch = new JComboBox();
			cbSerarch.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					cbSearchItemChange(); // 콤보박스 주제 변경시 자동으로 순서대로 정렬
				}
			});
			cbSerarch.setModel(new DefaultComboBoxModel(new String[] { "메뉴타입", "메뉴명", "브랜드명", "가격" }));
			cbSerarch.setBounds(6, 59, 88, 27);
		}
		return cbSerarch;
	}

	private JTextField getTfSearch() {// 검색어
		if (tfSearch == null) {
			tfSearch = new JTextField();
			tfSearch.setToolTipText("");
			tfSearch.setBounds(94, 58, 281, 26);
			tfSearch.setColumns(10);
		}
		return tfSearch;
	}

	private JButton getBtnsearch() {// 검색버튼
		if (btnsearch == null) {
			btnsearch = new JButton("검색");
			btnsearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchBtnClick(); // 검색 버튼 클릭시 '%-%' 검색
				}
			});
			btnsearch.setBounds(371, 58, 73, 29);
		}
		return btnsearch;
	}

	private JLabel getLblNewLabel() {// 카페행 로고
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("카페행");
			lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(74, 33, 450, 47);
		}
		return lblNewLabel;
	}

	private JScrollPane getScrollPane() {// 메뉴 리스트아웃테이블
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(6, 98, 438, 165);
			scrollPane.setViewportView(getSearchList_table());
		}
		// private final DefaultTableModel Outer_Table = new DefaultTableModel();
		// 필수

		return scrollPane;
	}

	private JTable getSearchList_table() { // 메뉴리스트 테이블 데이터 리스트
		if (searchList_table == null) {
			searchList_table = new JTable();
			searchList_table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					commentLisetInnertable(); // 코멘트 테이블 초기화
				}
			});
			searchList_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			searchList_table.setModel(search_Out_Table); // 안쪽테이블과 바깥쪽 테이블이서로 연동
		}
		return searchList_table;
	}

	private JLabel getLblNewLabel_1() { // 하단 상세정보 브랜드 라벨
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("브랜드");
			lblNewLabel_1.setBounds(6, 275, 120, 16);
		}
		return lblNewLabel_1;
	}

	private JTextField getTextField_1() { // 하단 상세정보 브랜드 텍스트
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setBounds(47, 270, 79, 26);
			textField_1.setColumns(10);
		}
		return textField_1;
	}

	private JTextField getTextField_2() { // 하단 상세정보 메뉴 텍스트
		if (textField_2 == null) {
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(196, 270, 79, 26);
		}
		return textField_2;
	}

	private JLabel getLblNewLabel_1_1() { // 하단 상세정보 메뉴 라벨
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("메뉴");
			lblNewLabel_1_1.setBounds(170, 275, 105, 16);
		}
		return lblNewLabel_1_1;
	}

	private JTextField getTextField_3() { // 하단 상세정보 가격 텍스트
		if (textField_3 == null) {
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			textField_3.setBounds(346, 270, 79, 26);
		}
		return textField_3;
	}

	private JLabel getLblNewLabel_1_2() { // 하단 상제정보 가격 라
		if (lblNewLabel_1_2 == null) {
			lblNewLabel_1_2 = new JLabel("가격");
			lblNewLabel_1_2.setBounds(320, 275, 105, 16);
		}
		return lblNewLabel_1_2;
	}

	private JLabel getLblNewLabel_2() { // 하단 상세정보 ㅁㅁ원 라벨
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("원");
			lblNewLabel_2.setBounds(428, 275, 16, 16);
		}
		return lblNewLabel_2;
	}

	private JScrollPane getSpComment() { // 코멘트 판넬
		if (spComment == null) {
			spComment = new JScrollPane();
			spComment.setBounds(6, 322, 438, 165);
			spComment.setViewportView(getComment_table());
		}
		return spComment;
	}

	private JTable getComment_table() { // 댓글 아웃테이블
		if (comment_table == null) {
			comment_table = new JTable();
			comment_table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					commentClick();
				}
			});
			comment_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			comment_table.setModel(comment_Out_Table); // 안쪽테이블과 바깥쪽 테이블이서로 연동
		}
		return comment_table;
	}

	private JLabel getLblNewLabel_1_3() { // 댓글 입력 라벨
		if (lblNewLabel_1_3 == null) {
			lblNewLabel_1_3 = new JLabel("입력");
			lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1_3.setBounds(6, 499, 43, 16);
		}
		return lblNewLabel_1_3;
	}

	private JTextField getTfComment() { // 댓글 입력 텍스트
		if (tfComment == null) {
			tfComment = new JTextField();
			tfComment.setColumns(10);
			tfComment.setBounds(47, 494, 328, 26);
		}
		return tfComment;
	}

	private JButton getBtnCommentInsert() { // 댓글 입력 버튼
		if (btnCommentInsert == null) {
			btnCommentInsert = new JButton("전송");
			btnCommentInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addComment();// 댓글 버튼 눌러서 텍스트를 데이터베이스에 전송
				}
			});
			btnCommentInsert.setBounds(371, 494, 73, 29);
		}
		return btnCommentInsert;
	}

	private JTextField getTfLogin() { // 로그인 정보를 텍스트로 저장할 가상 공간
		if (tfLogin == null) {
			tfLogin = new JTextField();
			tfLogin.setText("");
			tfLogin.setBounds(6, 294, 130, 26);
			tfLogin.setColumns(10);
			tfLogin.setVisible(true);
		}
		return tfLogin;
	}

	private JTextField getTfAdmin() { // 어드민 정보를 텍스트로 저장할 가상 공간
		if (tfAdmin == null) {
			tfAdmin = new JTextField();
			tfAdmin.setText("");
			tfAdmin.setBounds(145, 294, 130, 26);
			tfAdmin.setColumns(10);
			tfLogin.setVisible(true);
		}
		return tfAdmin;
	}

	private JButton getBtnCommentUpdate() { // 텍스트 수정을 클릭할 떄 나타날 버튼
		if (btnCommentUpdate == null) {
			btnCommentUpdate = new JButton("수정");
			btnCommentUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					commentUpdate();
				}
			});
			btnCommentUpdate.setBounds(371, 494, 73, 29);
		}
		return btnCommentUpdate;
	}

//__________________________________________________________________
//	데이터베이스 환경정의 검색리스트
	private final String url_mysql = "jdbc:mysql://127.0.0.1/useraddress?serverTimezone=UTC&characterEncoding=utf8&useSSL=FALSE";
	private final String id_mysql = "root";
	private final String pw_mysql = "qwer1234";
	private JTextField tfLogin;
	private JButton btnTest;
	private JTextField tfAdmin;
	private JButton btnCommentUpdate;

	/**
	 * @Method Name : searchLisetOuttable
	 * @작성일 : 2021. 4. 30.
	 * @작성자 : gimminjae
	 * @변경이력 :
	 * @Method설명 : 리스트 아웃테이블 기본 설
	 */
	private void searchLisetOuttable() { // 리스트 아웃테이블 기본 설
		search_Out_Table.addColumn("브랜드"); // 첫번쨰 리스트 컬럼
		search_Out_Table.addColumn("메뉴타입"); // 두번쨰 리스트 컬럼
		search_Out_Table.addColumn("메뉴명"); // 세번쨰 리스트 컬럼
		search_Out_Table.addColumn("가격"); // 네번쨰 리스트 컬럼
		search_Out_Table.setColumnCount(4); // 몇개까지 붙러온다
		int i = search_Out_Table.getRowCount();// 카운트숫자를 가지고
		for (int j = 0; j < i; j++) {
			search_Out_Table.removeRow(0); // 행마다 리스트를 초기화 한다.
		}
		searchList_table.setAutoResizeMode(searchList_table.AUTO_RESIZE_OFF); // 테이블사이즈 조정불가
		TableColumn col = searchList_table.getColumnModel().getColumn(0); // 컬럼 데이터를 가지고온다 0번쨰 컬럼
		col.setPreferredWidth(100); // 기본 넓이 값 100

		col = searchList_table.getColumnModel().getColumn(1);
		col.setPreferredWidth(100);

		col = searchList_table.getColumnModel().getColumn(2);
		col.setPreferredWidth(130);

		col = searchList_table.getColumnModel().getColumn(3);
		col.setPreferredWidth(70);
	}

	/**
	 * @Method Name : commentOuttable
	 * @작성일 : 2021. 4. 30.
	 * @작성자 : gimminjae
	 * @변경이력 :
	 * @Method설명 :코멘트 아웃테이블 기본 설정
	 */
	private void commentOuttable() { //
		comment_Out_Table.addColumn("댓글");
		comment_Out_Table.addColumn("수정");
		comment_Out_Table.addColumn("삭제");
		comment_Out_Table.addColumn("코멘트코드");
		comment_Out_Table.addColumn("코멘트텍스트");
		comment_Out_Table.setColumnCount(5); // 몇개까지 붙러온다
		int i = comment_Out_Table.getRowCount();// 카운트숫자를 가지고
		for (int j = 0; j < i; j++) {
			comment_Out_Table.removeRow(0);
		}
		comment_table.setAutoResizeMode(comment_table.AUTO_RESIZE_OFF); // 테이블사이즈 조정불가
		int vColIndex = 0;
		TableColumn col = comment_table.getColumnModel().getColumn(vColIndex);
		col.setPreferredWidth(250);
		vColIndex = 1;
		col = comment_table.getColumnModel().getColumn(vColIndex);
		col.setPreferredWidth(50);
		vColIndex = 2;
		col = comment_table.getColumnModel().getColumn(vColIndex);
		col.setPreferredWidth(50);
		vColIndex = 3;
		col = comment_table.getColumnModel().getColumn(vColIndex);
		col.setMinWidth(0);
		col.setMaxWidth(0);
		vColIndex = 4;
		col = comment_table.getColumnModel().getColumn(vColIndex);
		col.setMinWidth(0);
		col.setMaxWidth(0);
	}

	/**
	  * @Method Name : searchLisetInnertable
	  * @작성일 : 2021. 4. 30.
	  * @작성자 : gimminjae
	  * @변경이력 : 
	  * @Method설명 : 검색어 입력후 리스트 출력 , 빈값일경우 전체리스트
	  */
	private void searchLisetInnertable() {
		DbAction_Main_Comment_KMJ DbAction_Main_Comment_KMJ = new DbAction_Main_Comment_KMJ(); // coffeeSearchAction_KMJ 클래스를 변수로
																						// 치환 선언 해준다.
		String topic = (String) cbSerarch.getSelectedItem(); // 콤보박스 데이터가 들여온다
		String searchValue = (String) tfSearch.getText(); // 검색어 텍스트를 들여온다
		Bean_Main_List_KMJ Bean_Main_List_KMJ = new Bean_Main_List_KMJ(); // coffeeBean_KMJ 을 변수로 치환 한다
		Bean_Main_List_KMJ.setTopic(topic); // topic 을 coffeeBean_KMJ 의 Topic 에 데이터를 넣는다
		Bean_Main_List_KMJ.setSearchValue(searchValue); // searchValue 을 coffeeBean_KMJ 의 SearchValue 에 데이터를 넣는다

		ArrayList<Bean_Main_List_KMJ> beanList = DbAction_Main_Comment_KMJ.searchLisetInnertable(Bean_Main_List_KMJ);
		// ArrayList 를 coffeeBean_KMJ 배열로 만든다.
		// 그리고 coffeeSearchAction_KMJ 의 searchLisetInnertable 에 coffeeBean_KMJ 데이터를 계산해서
		// 가져온다
		for (int i = 0; i < beanList.size(); i++) { // 리스트를 한줄씩 listCount 만큼 가져온다
			String[] queryArray = { beanList.get(i).getBrandName(), beanList.get(i).getMenuType(),
					beanList.get(i).getMenuName(), beanList.get(i).getPrice() };
			// beanList 에 있는 getBrandName, getMenuType , ... 등 배열의 i번쨰 배열값을 가져온다
			search_Out_Table.addRow(queryArray);
		}
	}

	/**
	  * @Method Name : commentLisetInnertable
	  * @작성일 : 2021. 4. 30.
	  * @작성자 : gimminjae
	  * @변경이력 : 
	  * @Method설명 : 메뉴 클릭시 댓글 리스트 불러오기
	  */
	private void commentLisetInnertable() { // 
		commentOuttable();
		DbAction_Main_Comment_KMJ DbAction_Main_Comment_KMJ = new DbAction_Main_Comment_KMJ(); // coffeeSearchAction_KMJ 클래스를 변수로
		
		Bean_Main_Comment_KMJ Bean_Main_Comment_KMJ = new Bean_Main_Comment_KMJ();// 치환 선언 해준다.
		int selectedMenu = searchList_table.getSelectedRow(); // 댓글 선택 리스트 맨윗줄을 1로 기준
		String brandName = (String) searchList_table.getValueAt(selectedMenu, 0); // 해당 줄에서 0번쨰 컬럼명을 가져온다.
		String menuName = (String) searchList_table.getValueAt(selectedMenu, 2); // 해당 줄에서 2번째 컬럼명을 가져온다.
		Bean_Main_Comment_KMJ.setBrandName(brandName);
		Bean_Main_Comment_KMJ.setMenuName(menuName);
		ArrayList<Bean_Main_Comment_KMJ> beanList = DbAction_Main_Comment_KMJ.commentLisetInnertable(Bean_Main_Comment_KMJ);

		for (int i = 0; i < beanList.size(); i++) {
			if (tfAdmin.getText().equals("admin") || beanList.get(i).getClientCode().equals(tfLogin.getText())) {
				String[] queryArray = { beanList.get(i).getClientNick() + " : " + beanList.get(i).getComment(), "수정",
						"삭제", beanList.get(i).getCommentCode(), beanList.get(i).getComment() };
				comment_Out_Table.addRow(queryArray);
			} else {
				String[] queryArray = { beanList.get(i).getClientNick() + " : " + beanList.get(i).getComment(), "", "",
						beanList.get(i).getCommentCode(), beanList.get(i).getComment() };
				comment_Out_Table.addRow(queryArray);
			}
		}
	}

	/**
	  * @Method Name : addComment
	  * @작성일 : 2021. 4. 30.
	  * @작성자 : gimminjae
	  * @변경이력 : 
	  * @Method설명 : '전송' 버튼 클릭시 댓글 추가 및 초기화해서 출력
	  */
	private void addComment() {
		DbAction_Main_Comment_KMJ DbAction_Main_Comment_KMJ = new DbAction_Main_Comment_KMJ(); // coffeeSearchAction_KMJ 클래스를 변수로
																						// 치환 선언 해준다.
		Bean_Main_Comment_KMJ Bean_Main_Comment_KMJ = new Bean_Main_Comment_KMJ();
		String loginName = tfLogin.getText();
		int selectedMenu = searchList_table.getSelectedRow();
		String brandName = (String) searchList_table.getValueAt(selectedMenu, 0);
		String menuName = (String) searchList_table.getValueAt(selectedMenu, 2);
		String comment = tfComment.getText();

		Bean_Main_Comment_KMJ.setBrandName(brandName);
		Bean_Main_Comment_KMJ.setMenuName(menuName);
		Bean_Main_Comment_KMJ.setClientCode(loginName); // 로그인이름 클라이언트코드 찾기위해 필
		Bean_Main_Comment_KMJ.setComment(comment);
		DbAction_Main_Comment_KMJ.addCommend(Bean_Main_Comment_KMJ);
		commentLisetInnertable();
	}

	/**
	  * @Method Name : commentClick
	  * @작성일 : 2021. 4. 30.
	  * @작성자 : gimminjae
	  * @변경이력 : 
	  * @Method설명 : 댓글을 누르면 해당 댓글 컬럼값 가져와서 수정인지, 삭제인지 구분 및 수정 버튼 생성
	  */
	private void commentClick() {
		int i = comment_table.getSelectedRow();
		int UpdateDelete = comment_table.getSelectedColumn();
		String SelectedCommentCode = (String) comment_table.getValueAt(i, 3); // 숨겨있는 commentCode 값 불러오기
		String SelectedComment = (String) comment_table.getValueAt(i, 4); // 숨겨있는 comment 값 불러오기
		String SelectedUpdate = (String) comment_table.getValueAt(i, UpdateDelete); // 내가 선택한 값 텍스트 불러오기 수정,삭제 버튼

		if (SelectedUpdate.equals("수정")) {
			btnCommentInsert.setVisible(false);
			btnCommentUpdate.setVisible(true);
			tfComment.setText(SelectedComment);
		} else if (SelectedUpdate.equals("삭제")) {
			commentDelete();
		}
	}

	/**
	  * @Method Name : commentUpdate
	  * @작성일 : 2021. 4. 30.
	  * @작성자 : gimminjae
	  * @변경이력 : 
	  * @Method설명 : 댓글 수정 버튼 누르면 댓글 수정 및 댓글리스트 초기화
	  */
	private void commentUpdate() {
		DbAction_Main_Comment_KMJ DbAction_Main_Comment_KMJ = new DbAction_Main_Comment_KMJ(); // coffeeSearchAction_KMJ 클래스를 변수로
																						// 치환 선언 해준다.
		int i = comment_table.getSelectedRow();
		int UpdateDelete = comment_table.getSelectedColumn();
		String SelectedCommentCode = (String) comment_table.getValueAt(i, 3); // 숨겨있는 commentCode 값 불러오기
		String SelectedComment = (String) comment_table.getValueAt(i, 4); // 숨겨있는 comment 값 불러오기

		Bean_Main_Comment_KMJ Bean_Main_Comment_KMJ = new Bean_Main_Comment_KMJ();
		Bean_Main_Comment_KMJ.setComment(tfComment.getText());
		Bean_Main_Comment_KMJ.setCommentCode(SelectedCommentCode);
		DbAction_Main_Comment_KMJ.commentUpdate(Bean_Main_Comment_KMJ);
		btnCommentInsert.setVisible(true);
		btnCommentUpdate.setVisible(false);
		commentLisetInnertable();
		tfComment.setText("");
	}

	/**
	  * @Method Name : commentDelete
	  * @작성일 : 2021. 4. 30.
	  * @작성자 : gimminjae
	  * @변경이력 : 
	  * @Method설명 : 삭제버튼 클릭시 comment.commentOnOff = 0 으로 업데이트 하고 리스트 초기화
	  */
	private void commentDelete() {
		DbAction_Main_Comment_KMJ DbAction_Main_Comment_KMJ = new DbAction_Main_Comment_KMJ(); // coffeeSearchAction_KMJ 클래스를 변수로
																						// 치환 선언 해준다.
		int i = comment_table.getSelectedRow();
		int UpdateDelete = comment_table.getSelectedColumn();
		String SelectedCommentCode = (String) comment_table.getValueAt(i, 3); // 숨겨있는 commentCode 값 불러오기
		String SelectedComment = (String) comment_table.getValueAt(i, 4); // 숨겨있는 comment 값 불러오기

		Bean_Main_Comment_KMJ Bean_Main_Comment_KMJ = new Bean_Main_Comment_KMJ();
		Bean_Main_Comment_KMJ.setComment(tfComment.getText());
		Bean_Main_Comment_KMJ.setCommentCode(SelectedCommentCode);
		DbAction_Main_Comment_KMJ.commentDelete(Bean_Main_Comment_KMJ);
		btnCommentInsert.setVisible(true);
		btnCommentUpdate.setVisible(false);
		commentLisetInnertable();
		tfComment.setText("");
	}

	private void cbSearchItemChange() {
		searchLisetOuttable(); // 테이블 초기화
		searchLisetInnertable(); // 리스트 재출력
		commentOuttable();
	}

	private void searchBtnClick() {
		searchLisetOuttable(); // 테이블 초기화
		searchLisetInnertable(); // 리스트 재출력
		commentOuttable();
	}

//__________________________________________________________________
}
