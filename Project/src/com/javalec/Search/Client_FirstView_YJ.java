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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.javalec.MainPackage.MainProcess;
import com.javalec.bean.Bean_Client_FirstView_YJ;
import com.javalec.dbaction.DbAction_Client_FirstView_YJ;
import javax.swing.ImageIcon;

public class Client_FirstView_YJ extends JFrame {
	private MainProcess mainpr;


	private JFrame frame;
	private JLabel lbBackGround;
	private JButton btnSerch;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	String adminLogin = "", adminOnOff = "";
	private JLabel lbMypage;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client_FirstView_YJ window = new Client_FirstView_YJ();
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
	public Client_FirstView_YJ() {
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
				System.out.println(Login_YJ.clientCode);
			}
		});
		frame.setBounds(100, 100, 545, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getBtnSerch());
		frame.getContentPane().add(getLblNewLabel());
		frame.getContentPane().add(getLblNewLabel_1());
		frame.getContentPane().add(getLblNewLabel_2());
		frame.getContentPane().add(getLbMypage());
		frame.getContentPane().add(getLblNewLabel_4());
		frame.getContentPane().add(getLblNewLabel_3());
	}

	private JButton getBtnSerch() {
		if (btnSerch == null) {
			btnSerch = new JButton("검색하러가기");
			btnSerch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Search_CH search_CH = new Search_CH();
					frame.dispose();
				}

			});
			btnSerch.setForeground(Color.BLACK);
			btnSerch.setBounds(160, 394, 218, 39);
		}
		return btnSerch;
	}

	
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("로그아웃");
			lblNewLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					mainpr.main(null);
					dispose();
					
				}
			});
			lblNewLabel.setBounds(417, 16, 49, 16);
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

	private void adminLoginInfo() {
		DbAction_Client_FirstView_YJ dbAction = new DbAction_Client_FirstView_YJ();
		Bean_Client_FirstView_YJ bean = dbAction.login(); // 엑션실행 해서 빈에다 로그인정보 저장
		adminLogin = bean.getAdminLogin(); // 저장되어있는 로그인정보를 필드변수에 저장
		adminOnOff = bean.getAdminOnoff();
		System.out.println(adminLogin + adminOnOff);

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
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("/");
			lblNewLabel_4.setBounds(466, 16, 16, 16);
			lblNewLabel_4.setForeground(Color.white);
		}
		return lblNewLabel_4;
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
