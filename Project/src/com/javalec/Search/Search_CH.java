
package com.javalec.Search;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.MainPackage.MainProcess;
import com.javalec.bean.Bean_CH;
import com.javalec.bean.Bean_Main_Comment_KMJ;
import com.javalec.dbaction.DbAction_CH;
import com.javalec.dbaction.DbAction_Main_Comment_KMJ;
import com.javalec.sharevar.ShareVar_Admin_Brand_YJ;
import com.javalec.sharevar.ShareVar_Main_Comment_KMJ;

public class Search_CH extends JFrame {

	private MainProcess main;

	private JFrame frame;
	private JScrollPane scrollPane;
	private JTable InnerTable;
	private JButton btnMypage;
	private JLabel lblNewLabel;
	private JTextField tfSearch;
	private JButton btnSearch;
	private JComboBox cmbList;

	// 데이터베이스 환경 정의

	// Table 환경 정의
	private final DefaultTableModel Outer_Table = new DefaultTableModel();
	private final DefaultTableModel comment_Out_Table = new DefaultTableModel();

	private MainProcess mainpr;
	private JTextField tfBrand;
	private JTextField tfName;
	private JTextField tfPice;
	private JTextField tfMeterial;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JComboBox cmbPriceSelect;
	private JLabel lbBrandProduct;
	private JScrollPane spComment;
	private JTextField tfComment;
	private JButton btnCommentInsert;
	private JButton btnCommentUpdate;
	private JTable comment_table;

	String loginId = "", adminOnOff = ""; // 로그인정보 selec 저장

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search_CH window = new Search_CH();
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
	public Search_CH() {
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				tableInit();
				searchAction();
				commentOuttable(); // 코멘트 테이블 상단 컬럼
			}
		});
		frame.setBounds(100, 100, 545, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getScrollPane());
		frame.getContentPane().add(getBtnMypage());
		frame.getContentPane().add(getLblNewLabel());
		frame.getContentPane().add(getTfSearch());
		frame.getContentPane().add(getBtnSearch());
		frame.getContentPane().add(getCmbList());
		frame.getContentPane().add(getTfBrand());
		frame.getContentPane().add(getTfName());
		frame.getContentPane().add(getTfPice());
		frame.getContentPane().add(getTfMeterial());
		frame.getContentPane().add(getLblNewLabel_1());
		frame.getContentPane().add(getLblNewLabel_2());
		frame.getContentPane().add(getLblNewLabel_3());
		frame.getContentPane().add(getLblNewLabel_4());
		frame.getContentPane().add(getCmbPriceSelect());
		frame.getContentPane().add(getLbBrandProduct());
		frame.getContentPane().add(getSpComment());
		frame.getContentPane().add(getTfComment());
		frame.getContentPane().add(getBtnCommentInsert());
		frame.getContentPane().add(getBtnCommentUpdate());
		frame.getContentPane().add(getTfFilePath());
		frame.getContentPane().add(getLblLogout());
		frame.getContentPane().add(getLblNewLabel_5());
		frame.getContentPane().add(getLblNewLabel_6());
		frame.getContentPane().add(getLbMypage());
		frame.getContentPane().add(getLblNewLabel_7());
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(25, 81, 487, 134);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}

	private JTable getInnerTable() {
		if (InnerTable == null) {
			InnerTable = new JTable();
			InnerTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					TableClick();

				}
			});
			InnerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			InnerTable.setModel(Outer_Table); // **************이너 아우터 연동 중요******************

		}
		return InnerTable;
	}

	private JButton getBtnMypage() {
		if (btnMypage == null) {
			btnMypage = new JButton("마이페이지");
			btnMypage.setVisible(false);
			btnMypage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Client_Mypage_YJ client_Mypage_YJ = new Client_Mypage_YJ();
					frame.dispose();
					
				}
			});
			btnMypage.setBounds(66, 14, 91, 23);
		}
		return btnMypage;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("검색 :");
			lblNewLabel.setBounds(24, 53, 50, 15);
		}
		return lblNewLabel;
	}

	private JTextField getTfSearch() {
		if (tfSearch == null) {
			tfSearch = new JTextField();
			tfSearch.setBounds(184, 45, 203, 28);
			tfSearch.setColumns(10);
		}
		return tfSearch;
	}

	private JComboBox getCmbList() {
		if (cmbList == null) {
			cmbList = new JComboBox();
			cmbList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					// 가격콤보박스 활성화시키기______________________________________________
					if (cmbList.getSelectedIndex() == 2) {
						tfSearch.setVisible(false);
					} else {
						tfSearch.setVisible(true);
					}
					// ________________________________________________________________
				}
			});
			cmbList.setModel(new DefaultComboBoxModel(new String[] { "이름", "브랜드", "가격" }));
			cmbList.setBounds(66, 50, 104, 23);

		}

		return cmbList;
	}

	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("검색");
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					// 조건검색 쿼리
					conditionQuery();

				}
			});
			btnSearch.setBounds(421, 53, 91, 23);
		}
		return btnSearch;
	}

	private JTextField getTfBrand() {
		if (tfBrand == null) {
			tfBrand = new JTextField();
			tfBrand.setEditable(false);
			tfBrand.setBounds(73, 227, 96, 21);
			tfBrand.setColumns(10);
		}
		return tfBrand;
	}

	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setEditable(false);
			tfName.setColumns(10);
			tfName.setBounds(219, 227, 134, 21);
		}
		return tfName;
	}

	private JTextField getTfPice() {
		if (tfPice == null) {
			tfPice = new JTextField();
			tfPice.setEditable(false);
			tfPice.setColumns(10);
			tfPice.setBounds(73, 251, 97, 21);
		}
		return tfPice;
	}

	private JTextField getTfMeterial() {
		if (tfMeterial == null) {
			tfMeterial = new JTextField();
			tfMeterial.setEditable(false);
			tfMeterial.setColumns(10);
			tfMeterial.setBounds(219, 251, 134, 21);
		}
		return tfMeterial;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("브랜드");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setBounds(25, 231, 49, 15);
		}
		return lblNewLabel_1;
	}

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("메뉴이름");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_2.setBounds(172, 231, 49, 15);
		}
		return lblNewLabel_2;
	}

	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("가격");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_3.setBounds(25, 255, 49, 15);
		}
		return lblNewLabel_3;
	}

	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("알레르기");
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_4.setBounds(170, 253, 51, 18);
		}
		return lblNewLabel_4;
	}

	private JComboBox getCmbPriceSelect() {
		if (cmbPriceSelect == null) {
			cmbPriceSelect = new JComboBox();
			cmbPriceSelect
					.setModel(new DefaultComboBoxModel(new String[] { "전체", "1000~3000", "3000~6000", "6000~9000" }));
			cmbPriceSelect.setBounds(185, 48, 188, 23);

		}
		return cmbPriceSelect;
	}

	// $$$$$$$$$ 메인 프로세스와 연동 $$$$$$$$$$$$$$$
	public void setMain(MainProcess main) {
		this.main = main;
	}

	// ------------------------------------------------------------------------------------------------------------------
	// 테이블 초기화
	private void tableInit() {
		
		Outer_Table.addColumn("브랜드");
		Outer_Table.addColumn("이름");
		Outer_Table.addColumn("메뉴타입");
		Outer_Table.addColumn("가격");
		Outer_Table.addColumn("조회수");
		Outer_Table.setColumnCount(5);
		
		int i = Outer_Table.getRowCount();
		for(int j=0; j<i; j++) {
			Outer_Table.removeRow(0);
		}
		
		InnerTable.setAutoResizeMode(InnerTable.AUTO_RESIZE_OFF);
		
		int vColIndex = 0;
		TableColumn col = InnerTable.getColumnModel().getColumn(vColIndex);
		int width = 100;
		col.setPreferredWidth(width);
		
		vColIndex = 1;
		col = InnerTable.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);
		
		vColIndex = 2;
		col = InnerTable.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);
		
		vColIndex = 3;
		col = InnerTable.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);
		
		vColIndex = 4;
		col = InnerTable.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);

	}
	
	// 데이터 불러오기
	private void searchAction() {

		DbAction_CH dbAction_CH = new DbAction_CH();
		DbAction_Main_Comment_KMJ dbAction_Main_Comment_KMJ = new DbAction_Main_Comment_KMJ(); // 댓글 엑션
		ArrayList<Bean_CH> beanList = dbAction_CH.selectList();
		Bean_Main_Comment_KMJ bean_Main_Comment_KMJ = dbAction_Main_Comment_KMJ.login();
		
		dbAction_Main_Comment_KMJ.login(); // 민재가 만든 로그인 엑션 실행
		loginId = bean_Main_Comment_KMJ.getUserNick();
		adminOnOff = bean_Main_Comment_KMJ.getAdminOnOff();
		
		int j = beanList.size();
		for(int i = 0 ; i < j; i++) {
			Bean_CH bean_CH = new Bean_CH();
			String menuCount = Integer.toString(beanList.get(i).getMenuCount());
		String[] arr = {beanList.get(i).getBrandName(), beanList.get(i).getMenuName(), beanList.get(i).getMenuType(),beanList.get(i).getmenuprice(), menuCount};
		Outer_Table.addRow(arr);
		}
		
	}

	// 데이터 하나 클릭했읗때 정보 뜨게 하는 기능

	private void TableClick() {
System.out.println(124);
        int i = InnerTable.getSelectedRow();

        Bean_CH bean_CH = new Bean_CH();

        
        String tmpSequence = (String)InnerTable.getValueAt(i, 0);

        String tmpSequence2 = (String)InnerTable.getValueAt(i, 1);
        bean_CH.setBrandName(tmpSequence);

        bean_CH.setMenuName(tmpSequence2);

        
        
        DbAction_CH dbAction_CH = new DbAction_CH();
        
        dbAction_CH.tableClick(bean_CH);
        dbAction_CH.tableClickCount(tmpSequence,tmpSequence2);
        System.out.println(bean_CH.getBrandName());
        
        tfBrand.setText(bean_CH.getBrandName());
        tfName.setText(bean_CH.getMenuName());
        tfPice.setText (bean_CH.getmenuprice());
        tfMeterial.setText(bean_CH.getMetarialName());

        System.out.println(tfBrand.getText());
        
//        tableInit();
//        searchAction();
//        priceconditionQuery();
        conditionQuery();
        commentLisetInnertable();

		/**
		 * @Method Name : TableClick_showImg
		 * @작성일 : 2021. 5. 2
		 * @작성자 : yejin
		 * @변경이력 :
		 * @Method설명 :테이블클릭시 이미지 출력
		 */

		// Image처리
		// File name이 틀려야 즉각 보여주기가 가능하여
		// ShareVar에서 int값으로 정의하여 계속 증가하게 하여 file name으로 사용후 삭제

		String filePath = Integer.toString(ShareVar_Admin_Brand_YJ.filename);
		tfFilePath.setText(filePath);

		lbBrandProduct.setIcon(new ImageIcon(filePath));
		lbBrandProduct.setHorizontalAlignment(SwingConstants.CENTER);

		File file = new File(filePath);
		file.delete();

	}

	// 화면지우기 메서드
	private void clearColumn() {
//		tfMcode.setText("");
		tfBrand.setText("");
		tfName.setText("");
		tfPice.setText("");
		tfMeterial.setText("");
	}

	// ####################################### 조건 검색 부분 ###############################################
	
		 	// 조건 검색 콤보상자 선택
		 	private void conditionQuery() {
		 		int i = cmbList.getSelectedIndex();  // 콤보상자의 몇번쨰인지 알아봐주는 메서드 겟셀렉티드 인덱스
		 		String conditionQueryColumn = "";
		 		switch(i) {
		 		case 0 : 
		 			conditionQueryColumn = "m.menuName";
		 	 		tableInit();		
//		 			clearColumn();
		 			conditionQueryAction(conditionQueryColumn);
		 			break;
		 		case 1 : 
		 			conditionQueryColumn = "b.brandName";
		 			tableInit();		
//		 			clearColumn();
		 			conditionQueryAction(conditionQueryColumn);
		 			break;
		 		case 2 : 
		 									
		 			tableInit();		
//		 			clearColumn();
		 			priceconditionQuery() ;		// 가격검색 콤보상자	메소드
		 			break;

		 		default : 
		 			break;		
		 		}
		 	}

		 	// 조건콤보상자에 맞는 조건검색
		 	private void conditionQueryAction(String a) {      // a 는 conditionQueryColumn
		 		// 필요한 값 빈으로 보내기 
		 		Bean_CH bean = new Bean_CH();
		 		bean.setTfsearch((String)tfSearch.getText());
		 		bean.setConditionQueryColumn(a);	 
		 		
		 		//필요한 메서드 가져오기		
		 		DbAction_CH dbAction = new DbAction_CH();
		 		dbAction.conditionQueryDb(bean);
		 		ArrayList<Bean_CH> beanlist = dbAction.conditionQueryDb(bean);
		 		int j = beanlist.size();
		 				
		 		for(int i = 0 ; i < j ; i++) {

					Bean_CH bean_CH = new Bean_CH();
					String menuCount = Integer.toString(beanlist.get(i).getMenuCount());
				String[] arr = {beanlist.get(i).getBrandName(), beanlist.get(i).getMenuName(), beanlist.get(i).getMenuType(),beanlist.get(i).getmenuprice(), menuCount};
				Outer_Table.addRow(arr);
		 		}
		 	
		 	}

		 	// 콤보상자에서 가격을 선택했을 때   
		 	
	 		private void priceconditionQuery() {
	 			  // 필요한 값 보내기
	 				Bean_CH bean = new Bean_CH();
	 				System.out.println(1);
	 				System.out.println(cmbPriceSelect.getSelectedIndex());
	 				bean.setCmbPriceSelect(cmbPriceSelect.getSelectedIndex());
	 				System.out.println(2);
	 				System.out.println(bean.getCmbPriceSelect());
	 				//필요한 값 가져오기
	 				DbAction_CH dbAction = new DbAction_CH();  
	 				System.out.println(3);
	 				ArrayList<Bean_CH> beanList = dbAction.priceconditionQueryDB(bean);
	 				System.out.println(4);
	 				int j = beanList.size();
	 				System.out.println(5);
	 				
	 				for(int i = 0 ; i < j ; i++) {
	 					Bean_CH bean_CH = new Bean_CH();
	 					String menuCount = Integer.toString(beanList.get(i).getMenuCount());
	 					System.out.println(6);
	 				String[] arr = {beanList.get(i).getBrandName(), beanList.get(i).getMenuName(), beanList.get(i).getMenuType(),beanList.get(i).getmenuprice(), menuCount};
	 				System.out.println(7);
	 				Outer_Table.addRow(arr);
	 		 		}
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
		col.setPreferredWidth(220);
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
	 * @Method Name : commentLisetInnertable
	 * @작성일 : 2021. 4. 30.
	 * @작성자 : gimminjae
	 * @변경이력 :
	 * @Method설명 : 메뉴 클릭시 댓글 리스트 불러오기
	 */

	private final String url_mysql = ShareVar_Main_Comment_KMJ.url_mysql;
	private final String id_mysql = ShareVar_Main_Comment_KMJ.id_mysql;
	private final String pw_mysql = ShareVar_Main_Comment_KMJ.pw_mysql;
	private JTextField tfFilePath;
	private JLabel lblLogout;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lbMypage;
	private JLabel lblNewLabel_7;

	private void commentLisetInnertable() { //
		commentOuttable();
		DbAction_Main_Comment_KMJ DbAction_Main_Comment_KMJ = new DbAction_Main_Comment_KMJ(); // coffeeSearchAction_KMJ
																								// 클래스를 변수로

		Bean_Main_Comment_KMJ Bean_Main_Comment_KMJ = new Bean_Main_Comment_KMJ();// 치환 선언 해준다.
		int selectedMenu = InnerTable.getSelectedRow(); // 댓글 선택 리스트 맨윗줄을 1로 기준
		String brandName = (String) InnerTable.getValueAt(selectedMenu, 0); // 해당 줄에서 0번쨰 컬럼명을 가져온다.
		String menuName = (String) InnerTable.getValueAt(selectedMenu, 1); // 해당 줄에서 1번째 컬럼명을 가져온다.
		Bean_Main_Comment_KMJ.setBrandName(brandName);
		Bean_Main_Comment_KMJ.setMenuName(menuName);
		ArrayList<Bean_Main_Comment_KMJ> beanList = DbAction_Main_Comment_KMJ
				.commentLisetInnertable(Bean_Main_Comment_KMJ);
		for (int i = 0; i < beanList.size(); i++) {
			if (beanList.get(i).getAdminOnOff().equals("admin")
					|| beanList.get(i).getClientNick().equals(beanList.get(i).getUserNick())) {
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
		DbAction_Main_Comment_KMJ DbAction_Main_Comment_KMJ = new DbAction_Main_Comment_KMJ(); // coffeeSearchAction_KMJ
																								// 클래스를 변수로
		Bean_Main_Comment_KMJ Bean_Main_Comment_KMJ = new Bean_Main_Comment_KMJ();
		int selectedMenu = InnerTable.getSelectedRow();
		String brandName = (String) InnerTable.getValueAt(selectedMenu, 0); // 해당 줄에서 0번쨰 컬럼명을 가져온다.
		String menuName = (String) InnerTable.getValueAt(selectedMenu, 1); // 해당 줄에서 1번째 컬럼명을 가져온다.
		String comment = tfComment.getText();

		Bean_Main_Comment_KMJ.setBrandName(brandName);
		Bean_Main_Comment_KMJ.setMenuName(menuName);
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
		DbAction_Main_Comment_KMJ DbAction_Main_Comment_KMJ = new DbAction_Main_Comment_KMJ(); // coffeeSearchAction_KMJ
																								// 클래스를 변수로
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
		DbAction_Main_Comment_KMJ DbAction_Main_Comment_KMJ = new DbAction_Main_Comment_KMJ(); // coffeeSearchAction_KMJ
																								// 클래스를 변수로
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

	// #########################################################################################################

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	// 찬호님 이 라벨에 이미지 들어갈거에요!!!!!! 이미지 불러오고 하는건 예진이와 함께해요!!!!
	private JLabel getLbBrandProduct() {
		if (lbBrandProduct == null) {
			lbBrandProduct = new JLabel("");
			lbBrandProduct.setBounds(365, 255, 137, 128);
		}
		return lbBrandProduct;
	}

	private JScrollPane getSpComment() { // 댓글 아웃테이블
		if (spComment == null) {
			spComment = new JScrollPane();
			spComment.setBounds(25, 284, 328, 128);
			spComment.setViewportView(getComment_table());
		}
		return spComment;
	}

	private JTable getComment_table() { // 댓글 이너테이블
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

	private JTextField getTfComment() { // 댓글 텍스트 입력
		if (tfComment == null) {
			tfComment = new JTextField();
			tfComment.setBounds(25, 414, 271, 26);
			tfComment.setColumns(10);
		}
		return tfComment;
	}

	private JButton getBtnCommentInsert() { // 댓글 전송
		if (btnCommentInsert == null) {
			btnCommentInsert = new JButton("전송");
			btnCommentInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addComment();// 댓글 버튼 눌러서 텍스트를 데이터베이스에 전송
				}
			});
			btnCommentInsert.setBounds(298, 413, 54, 29);
		}
		return btnCommentInsert;
	}

	private JButton getBtnCommentUpdate() { // 댓글 수정
		if (btnCommentUpdate == null) {
			btnCommentUpdate = new JButton("수정");
			btnCommentUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					commentUpdate();
				}
			});
			btnCommentUpdate.setBounds(298, 414, 54, 29);
		}
		return btnCommentUpdate;
	}

	private JTextField getTfFilePath() {
		if (tfFilePath == null) {
			tfFilePath = new JTextField();
			tfFilePath.setBounds(365, 386, 130, 26);
			tfFilePath.setColumns(10);
			tfFilePath.setVisible(false);
		}
		return tfFilePath;
	}
	private JLabel getLblLogout() {
		if (lblLogout == null) {
			lblLogout = new JLabel("로그아웃");
			lblLogout.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					mainpr.main(null);
					frame.dispose();
				}
			});
			lblLogout.setBounds(417, 16, 49, 16);
			lblLogout.setForeground(Color.white);
		}
		return lblLogout;
	}
	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("카페행");
			lblNewLabel_5.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
			lblNewLabel_5.setBounds(243, 0, 63, 41);
			lblNewLabel_5.setForeground(Color.white);
		}
		return lblNewLabel_5;
	}
	private JLabel getLblNewLabel_6() {
		if (lblNewLabel_6 == null) {
			lblNewLabel_6 = new JLabel("/");
			lblNewLabel_6.setBounds(466, 16, 16, 16);
			lblNewLabel_6.setForeground(Color.white);
		}
		return lblNewLabel_6;
	}
	private JLabel getLbMypage() {
		if (lbMypage == null) {
			lbMypage = new JLabel("마이페이지");
			lbMypage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Client_Mypage_YJ client_Mypage_YJ = new Client_Mypage_YJ();
					frame.dispose();
				}
			});
			lbMypage.setBounds(478, 16, 61, 16);
			lbMypage.setForeground(Color.white);
		}
		return lbMypage;
	}
	private JLabel getLblNewLabel_7() {
		if (lblNewLabel_7 == null) {
			lblNewLabel_7 = new JLabel("");
			lblNewLabel_7.setIcon(new ImageIcon("/Users/gimminjae/Desktop/저장/background/background/client_mypage.png"));
			lblNewLabel_7.setBounds(0, -16, 545, 478);
		}
		return lblNewLabel_7;
	}
} /// ------------------
