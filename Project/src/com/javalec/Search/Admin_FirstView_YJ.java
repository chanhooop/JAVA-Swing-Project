package com.javalec.Search;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.javalec.MainPackage.MainProcess;
import com.javalec.bean.Bean_Admin_FirstView_YJ;
import com.javalec.dbaction.DbAction_Admin_FirstView_YJ;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Admin_FirstView_YJ extends JFrame {

	private MainProcess mainpr;
	
	private JFrame frame;
	private JLabel lbBackGround;
	private JButton btnMenu;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnBrand;
	private JButton btnClient;
	
	String adminLogin = "", adminOnOff = "";
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_FirstView_YJ window = new Admin_FirstView_YJ();
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
	public Admin_FirstView_YJ() {
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
				adminLoginInfo();
			}
		});
		frame.setBounds(100, 100, 545, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getBtnMenu());
		frame.getContentPane().add(getLblNewLabel());
		frame.getContentPane().add(getLblNewLabel_1());
		frame.getContentPane().add(getLblNewLabel_2());
		frame.getContentPane().add(getBtnBrand());
		frame.getContentPane().add(getBtnClient());
		frame.getContentPane().add(getLblNewLabel_3());
	}
	private JButton getBtnMenu() {
		if (btnMenu == null) {
			btnMenu = new JButton("메뉴관리");
			btnMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Admin_Menu_DS admin_Menu_DS = new Admin_Menu_DS();
					frame.dispose();
				}
			});
			btnMenu.setForeground(Color.BLACK);
			btnMenu.setBounds(160, 394, 218, 39);
		}
		return btnMenu;
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
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("카페행");
			lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 43));
			lblNewLabel_1.setBounds(215, 77, 116, 79);
			lblNewLabel_1.setForeground(Color.white);
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("강한 이끌림, 현대인의 필수품");
			lblNewLabel_2.setBounds(199, 148, 148, 15);
			lblNewLabel_2.setForeground(Color.white);
		}
		return lblNewLabel_2;
	}
	private JButton getBtnBrand() {
		if (btnBrand == null) {
			btnBrand = new JButton("브랜드관리");
			btnBrand.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Admin_Brand_YJ admin_Brand_YJ = new Admin_Brand_YJ();
					frame.dispose();
					
				}
			});
			btnBrand.setForeground(Color.BLACK);
			btnBrand.setBounds(160, 336, 218, 39);
		}
		return btnBrand;
	}
	private JButton getBtnClient() {
		if (btnClient == null) {
			btnClient = new JButton("고객관리");
			btnClient.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Admin_ClientList_YJ admin_ClientList_YJ = new Admin_ClientList_YJ();
					frame.dispose();
				}
			});
			btnClient.setForeground(Color.BLACK);
			btnClient.setBounds(160, 274, 218, 39);
		}
		return btnClient;
	}
	
	
	  private void adminLoginInfo() {
		  DbAction_Admin_FirstView_YJ dbAction = new DbAction_Admin_FirstView_YJ();
	      Bean_Admin_FirstView_YJ bean = dbAction.login(); // 엑션실행 해서 빈에다 로그인정보 저장
	      adminLogin = bean.getAdminLogin(); //저장되어있는 로그인정보를 필드변수에 저장
	      adminOnOff = bean.getAdminOnoff();
	      System.out.println(adminLogin + adminOnOff);
	      
	   }
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setIcon(new ImageIcon("/Users/gimminjae/Desktop/저장/background/background/choiceBtn_client,admin.png"));
			lblNewLabel_3.setBounds(0, 0, 545, 478);
		}
		return lblNewLabel_3;
	}
}
