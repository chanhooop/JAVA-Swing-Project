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
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.MainPackage.MainProcess;
import com.javalec.bean.Bean_Admin_Brand_YJ;
import com.javalec.bean.Bean_Admin_ClientList_YJ;
import com.javalec.dbaction.DbAction_Admin_ClientList_YJ;
// test
public class Admin_ClientList_YJ extends JFrame{

	private MainProcess mainpr;
	private JFrame frame;
	private JLabel lbAdminLogo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lbClientControl;
	private JScrollPane scrollPane;
	private JTable Inner_table;
	private JLabel lbClientCode;
	private JTextField tfClientCode;
	private JLabel lbClientId;
	private JTextField tfClientId;
	private JLabel lbClientPw;
	private JTextField tfClientPw;
	private JLabel lbClientTelno;
	private JTextField tfClientTelno;
	private JLabel lbClientNick;
	private JTextField tfClientNick;
	private JButton btnDelete;
	private JButton btnReset;

	// Table 환경 정의
	private final DefaultTableModel Outer_Table = new DefaultTableModel();
	private JLabel lbTest;
	private JLabel lbClientName;
	private JTextField tfClientName;
	private JButton btnCheck;
	private JLabel lblNewLabel;
	
	String adminLogin = "", adminOnOff = "";
	private JLabel lblBack;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_ClientList_YJ window = new Admin_ClientList_YJ();
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
	public Admin_ClientList_YJ() {
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
				DbAction_Admin_ClientList_YJ dbAction = new DbAction_Admin_ClientList_YJ();
				Bean_Admin_ClientList_YJ bean = dbAction.login(); // 엑션실행 해서 빈에다 로그인정보 저장
				adminLogin = bean.getAdminLogin(); // 저장되어있는 로그인정보를 필드변수에 저장
				adminOnOff = bean.getAdminOnoff();
				tableInit();
//				searchAction();
				showAll();
			}
		});
		frame.setTitle("카페행");
		frame.setBounds(100, 100, 545, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getLbAdminLogo());
		frame.getContentPane().add(getLbClientControl());
		frame.getContentPane().add(getScrollPane());
		frame.getContentPane().add(getLbClientCode());
		frame.getContentPane().add(getTfClientCode());
		frame.getContentPane().add(getLbClientId());
		frame.getContentPane().add(getTfClientId());
		frame.getContentPane().add(getLbClientPw());
		frame.getContentPane().add(getTfClientPw());
		frame.getContentPane().add(getLbClientTelno());
		frame.getContentPane().add(getTfClientTelno());
		frame.getContentPane().add(getLbClientNick());
		frame.getContentPane().add(getTfClientNick());
		frame.getContentPane().add(getBtnDelete());
		frame.getContentPane().add(getBtnReset());
		frame.getContentPane().add(getLbClientName());
		frame.getContentPane().add(getTfClientName());
		frame.getContentPane().add(getBtnCheck());
		frame.getContentPane().add(getLblNewLabel());
		frame.getContentPane().add(getLblBack());
		frame.getContentPane().add(getLblNewLabel_1());
//		frame.getContentPane().add(getLbTest());
	}

	private JLabel getLbAdminLogo() {
		if (lbAdminLogo == null) {
			lbAdminLogo = new JLabel("카페행");
			lbAdminLogo.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
			lbAdminLogo.setBounds(243, 0, 63, 41);
			lbAdminLogo.setForeground(Color.white);
		}
		return lbAdminLogo;
	}

	private JLabel getLbClientControl() {
		if (lbClientControl == null) {
			lbClientControl = new JLabel("고객관리");
			lbClientControl.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			lbClientControl.setBounds(28, 42, 75, 31);
		}
		return lbClientControl;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(30, 76, 483, 147);
			scrollPane.setViewportView(getInner_table());
		}
		return scrollPane;
	}

	private JTable getInner_table() {
		if (Inner_table == null) {
			Inner_table = new JTable();
			Inner_table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tableClick();
				}
			});
			Inner_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			Inner_table.setModel(Outer_Table);
		}
		return Inner_table;
	}

	private JLabel getLbClientCode() {
		if (lbClientCode == null) {
			lbClientCode = new JLabel("회원번호");
			lbClientCode.setBounds(28, 235, 61, 16);
		}
		return lbClientCode;
	}

	private JTextField getTfClientCode() {
		if (tfClientCode == null) {
			tfClientCode = new JTextField();
			tfClientCode.setEditable(false);
			tfClientCode.setBounds(89, 230, 51, 26);
			tfClientCode.setColumns(10);
		}
		return tfClientCode;
	}

	private JLabel getLbClientId() {
		if (lbClientId == null) {
			lbClientId = new JLabel("아이디");
			lbClientId.setBounds(29, 298, 61, 16);
		}
		return lbClientId;
	}

	private JTextField getTfClientId() {
		if (tfClientId == null) {
			tfClientId = new JTextField();
			tfClientId.setEditable(false);
			tfClientId.setBounds(90, 293, 130, 26);
			tfClientId.setColumns(10);
		}
		return tfClientId;
	}

	private JLabel getLbClientPw() {
		if (lbClientPw == null) {
			lbClientPw = new JLabel("비밀번호");
			lbClientPw.setBounds(29, 330, 61, 16);
		}
		return lbClientPw;
	}

	private JTextField getTfClientPw() {
		if (tfClientPw == null) {
			tfClientPw = new JTextField();
			tfClientPw.setEditable(false);
			tfClientPw.setBounds(90, 325, 130, 26);
			tfClientPw.setColumns(10);
		}
		return tfClientPw;
	}

	private JLabel getLbClientTelno() {
		if (lbClientTelno == null) {
			lbClientTelno = new JLabel("전화번호");
			lbClientTelno.setBounds(29, 368, 61, 16);
		}
		return lbClientTelno;
	}

	private JTextField getTfClientTelno() {
		if (tfClientTelno == null) {
			tfClientTelno = new JTextField();
			tfClientTelno.setEditable(false);
			tfClientTelno.setBounds(90, 363, 130, 26);
			tfClientTelno.setColumns(10);
		}
		return tfClientTelno;
	}

	private JLabel getLbClientNick() {
		if (lbClientNick == null) {
			lbClientNick = new JLabel("닉네임");
			lbClientNick.setBounds(29, 401, 61, 16);
		}
		return lbClientNick;
	}

	private JTextField getTfClientNick() {
		if (tfClientNick == null) {
			tfClientNick = new JTextField();
			tfClientNick.setEditable(false);
			tfClientNick.setBounds(90, 396, 130, 26);
			tfClientNick.setColumns(10);
		}
		return tfClientNick;
	}

	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("삭제");
			btnDelete.setBounds(430, 402, 83, 29);
		}
		return btnDelete;
	}

	private JButton getBtnReset() {
		if (btnReset == null) {
			btnReset = new JButton("수정");
			btnReset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tableClick();
					btnReset.setVisible(false);
					tfClientPw.setEditable(true);
					tfClientName.setEditable(true);
					tfClientTelno.setEditable(true);
					tfClientNick.setEditable(true);
					
				}
			});
			btnReset.setBounds(335, 402, 83, 29);
		}
		return btnReset;
	}



	private void tableInit() {
		Outer_Table.addColumn("회원번호");
		Outer_Table.addColumn("아이디");
		Outer_Table.addColumn("이름");
		Outer_Table.addColumn("전화번호");
		Outer_Table.addColumn("닉네임");
		Outer_Table.setColumnCount(5);

//		System.out.println("1"); // 테스트

		int i = Outer_Table.getRowCount();
		System.out.println(i);
		for (int j = 0; j < i; j++) {
			Outer_Table.removeRow(0);
		}

//		System.out.println("2"); // 테스트

		Inner_table.setAutoResizeMode(Inner_table.AUTO_RESIZE_OFF);

		int vColIndex = 0;
		TableColumn col = Inner_table.getColumnModel().getColumn(vColIndex);
		int width = 30;
		col.setPreferredWidth(width);

		vColIndex = 1;
		col = Inner_table.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);

		vColIndex = 2;
		col = Inner_table.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);

		vColIndex = 3;
		col = Inner_table.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);

		vColIndex = 4;
		col = Inner_table.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);
//		System.out.println(3);
	}

	private void showAll() {
		DbAction_Admin_ClientList_YJ dbAction = new DbAction_Admin_ClientList_YJ();
		ArrayList<Bean_Admin_ClientList_YJ> beanList = dbAction.selectList();

		int listCount = beanList.size();
		for (int i = 0; i < listCount; i++) {
			String temp = Integer.toString(beanList.get(i).getClientCode());
			String[] qTxt = { temp, beanList.get(i).getClientId(), beanList.get(i).getClientName(),
					beanList.get(i).getClientTelno(), beanList.get(i).getClientNick() };
			Outer_Table.addRow(qTxt);
		}
//		System.out.println(4);
	}

	private void tableClick() {
//		System.out.println(5);
		int i = Inner_table.getSelectedRow();
		String wkCode = (String) Inner_table.getValueAt(i, 0);
//		System.out.println(wkCode);
		int wkCodeInt = Integer.parseInt(wkCode);

		DbAction_Admin_ClientList_YJ dbAction = new DbAction_Admin_ClientList_YJ(wkCodeInt);
		Bean_Admin_ClientList_YJ bean = dbAction.tableClick();

		tfClientCode.setText(Integer.toString(bean.getClientCode()));
		tfClientId.setText(bean.getClientId());
		tfClientPw.setText(bean.getClientPw());
		tfClientName.setText(bean.getClientName());
		tfClientTelno.setText(bean.getClientTelno());
		tfClientNick.setText(bean.getClientNick());
	}

	private JLabel getLbClientName() {
		if (lbClientName == null) {
			lbClientName = new JLabel("이름");
			lbClientName.setBounds(28, 268, 61, 16);
		}
		return lbClientName;
	}

	private JTextField getTfClientName() {
		if (tfClientName == null) {
			tfClientName = new JTextField();
			tfClientName.setBounds(90, 263, 130, 26);
			tfClientName.setColumns(10);
			tfClientName.setEditable(false);
		}
		return tfClientName;
	}

	private JButton getBtnCheck() {
		if (btnCheck == null) {
			btnCheck = new JButton("확인");
			btnCheck.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clientReset();
					btnReset.setVisible(true);
					btnCheck.setVisible(false);
					clearColumn();
				}
			});
			btnCheck.setBounds(335, 402, 83, 29);
		}
		return btnCheck;
	}

	private void clientReset() {
		int code = Integer.parseInt(tfClientCode.getText().trim());
		String id = tfClientId.getText().trim();
		String passWord = tfClientPw.getText().trim();
		String name = tfClientName.getText().trim();
		String telno = tfClientTelno.getText().trim();
		String nick = tfClientNick.getText().trim();

		DbAction_Admin_ClientList_YJ dbAction = new DbAction_Admin_ClientList_YJ(code, id, passWord, name, telno, nick);

		boolean isupdate = dbAction.UpdateAction();

		if (isupdate == true) {
			JOptionPane.showMessageDialog(null, tfClientCode.getText() + " 번의 정보가 수정 되었습니다.!", "수정 완료!",
					JOptionPane.INFORMATION_MESSAGE);
	
			
		} else {
			JOptionPane.showMessageDialog(null, "수정중 에러가 발생했습니다! \n 시스템관리자에 문의하세요!", "Critical Error!",
					JOptionPane.ERROR_MESSAGE);
		}
		tableInit();
		showAll();
	}
	
	private void clearColumn() {
		tfClientCode.setText("");
		tfClientId.setText("");
		tfClientPw.setText("");
		tfClientName.setText("");
		tfClientTelno.setText("");
		tfClientNick.setText("");
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("로그아웃");
			lblNewLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					mainpr.main(null);
					frame.dispose();
				}
			});
			lblNewLabel.setBounds(478, 16, 61, 16);
			lblNewLabel.setForeground(Color.white);
		}
		return lblNewLabel;
	}
	private JLabel getLblBack() {
		if (lblBack == null) {
			lblBack = new JLabel("돌아가기");
			lblBack.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					Admin_FirstView_YJ admin_FirstView_YJ = new Admin_FirstView_YJ();
					frame.dispose();
				}
			});
			lblBack.setBounds(28, 17, 50, 15);
			lblBack.setForeground(Color.white);
		}
		return lblBack;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon("/Users/gimminjae/Desktop/저장/background/background/client_mypage.png"));
			lblNewLabel_1.setBounds(0, -16, 545, 478);
		}
		return lblNewLabel_1;
	}
}