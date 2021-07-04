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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.javalec.MainPackage.MainProcess;
import com.javalec.bean.Bean_Login_YJ;
import com.javalec.dbaction.DbAction_Login_YJ;
import javax.swing.ImageIcon;

public class Login_YJ extends JFrame{

	private Main_Id_CJY main_Id_CJY;
	private MainProcess mainpr;
	private JFrame frame;
	private JLabel lbMainSlogan;
	private JRadioButton rdbtnClient;
	private JRadioButton rdbtnAdmin;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lbId;
	private JTextField tfId;
	private JLabel lbPw;
	private JPasswordField pfPW;
	private JButton btnRogin;
	private JLabel lbMainLogo;

	// Table 환경 정의
	private final DefaultTableModel Outer_Table = new DefaultTableModel();
	private JLabel lbTest;
	private String userId;
	private int clickRdClient = 0;
	private boolean isClickRdClient = false;
	private int clickRdAdmin = 0;
	private boolean isClickRdAdmin = false;
	private JLabel lbJoin;
	private JLabel lbIdSearch;
	private JLabel lblNewLabel_1;
	private JLabel lbPwSearch;
	
	public static String clientCode = "";
	public static String adminCode = "";
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_YJ window = new Login_YJ();
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
	public Login_YJ() {
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
//				getMypage();
			}
		});
		frame.setTitle("카페행");
		frame.setBounds(100, 100, 545, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getLbMainSlogan());
		frame.getContentPane().add(getRdbtnClient());
		frame.getContentPane().add(getRdbtnAdmin());
		frame.getContentPane().add(getLbId());
		frame.getContentPane().add(getTfId());
		frame.getContentPane().add(getLbPw());
		frame.getContentPane().add(getPfPW());
		frame.getContentPane().add(getBtnRogin());
		frame.getContentPane().add(getLbMainLogo());
		frame.getContentPane().add(getLbJoin());
		frame.getContentPane().add(getLbIdSearch());
		frame.getContentPane().add(getLblNewLabel_1());
		frame.getContentPane().add(getLbPwSearch());
		frame.getContentPane().add(getLblNewLabel());
//		frame.getContentPane().add(getLbTest());
	}

    public void setMain(MainProcess main) {
        this.mainpr = main;
    }
	private JLabel getLbMainSlogan() {
		if (lbMainSlogan == null) {
			lbMainSlogan = new JLabel("강한 이끌림, 현대인의 필수품");
			lbMainSlogan.setBounds(199, 148, 148, 15);
			lbMainSlogan.setForeground(Color.white);
		}
		return lbMainSlogan;
	}

	private JRadioButton getRdbtnClient() {
		if (rdbtnClient == null) {
			rdbtnClient = new JRadioButton("고객");
			rdbtnClient.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clickRdClient = 1;
					
				}
			});
			buttonGroup.add(rdbtnClient);
			rdbtnClient.setBounds(198, 169, 63, 23);
		}
		return rdbtnClient;
	}

	private JRadioButton getRdbtnAdmin() {
		if (rdbtnAdmin == null) {
			rdbtnAdmin = new JRadioButton("관리자");
			rdbtnAdmin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clickRdClient = 2;
				}
			});
			buttonGroup.add(rdbtnAdmin);
			rdbtnAdmin.setBounds(281, 169, 96, 23);
		}
		return rdbtnAdmin;
	}

	private JLabel getLbId() {
		if (lbId == null) {
			lbId = new JLabel("아이디");
			lbId.setBounds(188, 200, 57, 15);
		}
		return lbId;
	}

	private JTextField getTfId() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.setBounds(242, 197, 116, 21);
			tfId.setColumns(10);
		}
		return tfId;
	}

	private JLabel getLbPw() {
		if (lbPw == null) {
			lbPw = new JLabel("비밀번호");
			lbPw.setBounds(188, 236, 57, 15);
		}
		return lbPw;
	}

	private JPasswordField getPfPW() {
		if (pfPW == null) {
			pfPW = new JPasswordField();
			pfPW.setBounds(242, 233, 116, 21);
		}
		return pfPW;
	}

	private JButton getBtnRogin() {
		if (btnRogin == null) {
			btnRogin = new JButton("로그인");
			btnRogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					switch(clickRdClient) {
					case 1 :
						clientLoginAction();


						
						break;
					case 2 :
						adminLoginAction();
						break;
					default :
						JOptionPane.showMessageDialog(null, "버튼을 체크해주세요.");
						break;
					}
					
					
					
				}
			});
			btnRogin.setBounds(191, 268, 167, 44);
		}
		return btnRogin;
	}

	private JLabel getLbMainLogo() {
		if (lbMainLogo == null) {
			lbMainLogo = new JLabel("카페행");
			lbMainLogo.setFont(new Font("Dialog", Font.PLAIN, 43));
			lbMainLogo.setBounds(215, 77, 116, 79);
			lbMainLogo.setForeground(Color.white);
		}
		return lbMainLogo;
	}

	// method
	// 로그인확인 메소
	private void clientLoginAction() {
		String wkId = tfId.getText().trim(); // 전역변수인 변수에 사용자 입력값을 저장한다.
		char[] wkPws = pfPW.getPassword();
		String wkPw = new String(wkPws);
		String wkName = "";

		DbAction_Login_YJ dbAction = new DbAction_Login_YJ(wkId, wkPw, wkName); // DbAction 클래스로 넘기는것도, 전역변수로 넘김
		Bean_Login_YJ bean = dbAction.clientRoginAction();
		Login_YJ.clientCode = dbAction.clientgetCode();

		if (wkId.equals(bean.getClientId()) && wkPw.equals(bean.getClientPw())) {
			JOptionPane.showMessageDialog(null, bean.getClientName() + "님 환영합니다.");
			
			mainpr.showClient_FirstView_YJ();
			frame.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "아이디 및 비밀번호를 확인하세요.");
		}

	}

	private void adminLoginAction() {
		String adminId = tfId.getText().trim();
		char[] wkPws = pfPW.getPassword();
		String wkPw = new String(wkPws);

		DbAction_Login_YJ dbAction = new DbAction_Login_YJ(adminId, wkPw);
		Bean_Login_YJ bean = dbAction.adminRoginAction();
		Login_YJ.adminCode = dbAction.adminGetCode();

		if (adminId.equals(bean.getAdminId()) && wkPw.equals(bean.getAdminPw())) {
			JOptionPane.showMessageDialog(null, "관리자님 환영합니다.");
			mainpr.showAdmin_FirstView_YJ();
			frame.dispose();
			
			
		} else {
			JOptionPane.showMessageDialog(null, "아이디 및 비밀번호를 확인하세요.");
		}

//		if (wkId.equals(bean.getAdminId()) && wkPw.equals(bean.getAdminPw())) {
//			JOptionPane.showMessageDialog(null, "관리자님 환영합니다.");
//		} else {
//			JOptionPane.showMessageDialog(null, "아이디 및 비밀번호를 확인하세요.");
//		}
	}
	private JLabel getLbJoin() {
		if (lbJoin == null) {
			lbJoin = new JLabel("회원가입");
			lbJoin.setHorizontalAlignment(SwingConstants.TRAILING);
			lbJoin.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
			lbJoin.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					mainpr.showMain_Join_CJY();
					frame.dispose();					
				}
			});
			lbJoin.setBounds(309, 316, 49, 16);
		}
		return lbJoin;
	}
	private JLabel getLbIdSearch() {
		if (lbIdSearch == null) {
			lbIdSearch = new JLabel("아이디");
			lbIdSearch.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					mainpr.showMain_Id_CJY();
					frame.dispose();
				}


			});
			lbIdSearch.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
			lbIdSearch.setBounds(191, 316, 34, 16);
		}
		return lbIdSearch;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("/");
			lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
			lblNewLabel_1.setBounds(223, 316, 34, 16);
		}
		return lblNewLabel_1;
	}
	private JLabel getLbPwSearch() {
		if (lbPwSearch == null) {
			lbPwSearch = new JLabel("비밀번호 찾기");
			lbPwSearch.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					mainpr.showMain_Pw_CJY();
					frame.dispose();
					
				}
			});
			lbPwSearch.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
			lbPwSearch.setBounds(233, 316, 76, 16);
		}
		return lbPwSearch;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon("/Users/gimminjae/Desktop/저장/background/background/login.png"));
			lblNewLabel.setBounds(0, 0, 545, 478);
		}
		return lblNewLabel;
	}
}