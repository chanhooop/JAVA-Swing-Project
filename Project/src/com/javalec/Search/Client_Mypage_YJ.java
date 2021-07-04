package com.javalec.Search;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.javalec.MainPackage.MainProcess;
import com.javalec.bean.Bean_Mypage_YJ;
import com.javalec.dbaction.DbAction_Mypage_YJ;

public class Client_Mypage_YJ extends JFrame {

	private MainProcess mainpr;
	
	
	private JFrame frame;
	private JLabel lbAdminLogo;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	// Table 환경 정의
	private final DefaultTableModel Outer_Table = new DefaultTableModel();
	private JLabel lbTest;
	private JLabel lbMainImg;
	private JLabel lbMypage;
	private JLabel lbMypageId;
	private JTextField tfMypageId;
	private JLabel lbMypagePw;
	private JPasswordField pwfMypagePw;
	private JLabel lbMypagePwCheck;
	private JPasswordField pwfMypagePwCheck;
	private JLabel lbMypageName;
	private JTextField tfMypageName;
	private JLabel lbMypageTelno;
	private JTextField tfMypageTelno;
	private JLabel lbMypageNick;
	private JTextField tfMypageNick;
	private JButton btnOverlap;
	private String userId;
	private JLabel lbPwCheckStr;
	private JButton btnMypageReset;
	private JButton btnMypageCancel;
	private JButton btnMypageResetOk;
	private String wkId = "";
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client_Mypage_YJ window = new Client_Mypage_YJ();
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
	public Client_Mypage_YJ() {
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
				getMypage();
			}
		});
		frame.setTitle("카페행");
		frame.setBounds(100, 100, 545, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getLbAdminLogo());
		frame.getContentPane().add(getLbMypage());
		frame.getContentPane().add(getLbMypageId());
		frame.getContentPane().add(getTfMypageId());
		frame.getContentPane().add(getLbMypagePw());
		frame.getContentPane().add(getPwfMypagePw());
		frame.getContentPane().add(getLbMypagePwCheck());
		frame.getContentPane().add(getPwfMypagePwCheck());
		frame.getContentPane().add(getLbMypageName());
		frame.getContentPane().add(getTfMypageName());
		frame.getContentPane().add(getLbMypageTelno());
		frame.getContentPane().add(getTfMypageTelno());
		frame.getContentPane().add(getLbMypageNick());
		frame.getContentPane().add(getTfMypageNick());
		frame.getContentPane().add(getBtnOverlap());
		frame.getContentPane().add(getLbPwCheckStr());
		frame.getContentPane().add(getBtnMypageReset());
		frame.getContentPane().add(getBtnMypageCancel());
		frame.getContentPane().add(getBtnMypageResetOk());
		frame.getContentPane().add(getLblNewLabel());
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


	private JLabel getLbMypage() {
		if (lbMypage == null) {
			lbMypage = new JLabel("마이페이지");
			lbMypage.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
			lbMypage.setBounds(26, 47, 126, 37);
		}
		return lbMypage;
	}

	private JLabel getLbMypageId() {
		if (lbMypageId == null) {
			lbMypageId = new JLabel("아이디");
			lbMypageId.setBounds(29, 102, 61, 16);
		}
		return lbMypageId;
	}

	private JTextField getTfMypageId() {
		if (tfMypageId == null) {
			tfMypageId = new JTextField();
			tfMypageId.setEditable(false);
			tfMypageId.setBounds(103, 97, 130, 26);
			tfMypageId.setColumns(10);
		}
		return tfMypageId;
	}

	private JLabel getLbMypagePw() {
		if (lbMypagePw == null) {
			lbMypagePw = new JLabel("비밀번호");
			lbMypagePw.setBounds(27, 142, 61, 16);
		}
		return lbMypagePw;
	}

	private JPasswordField getPwfMypagePw() {
		if (pwfMypagePw == null) {
			pwfMypagePw = new JPasswordField();
			pwfMypagePw.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			pwfMypagePw.setEditable(false);
			pwfMypagePw.setBounds(101, 137, 130, 26);
		}
		return pwfMypagePw;
	}

	private JLabel getLbMypagePwCheck() {
		if (lbMypagePwCheck == null) {
			lbMypagePwCheck = new JLabel("비밀번호확인");
			lbMypagePwCheck.setBounds(27, 184, 75, 16);
		}
		return lbMypagePwCheck;
	}

	private JPasswordField getPwfMypagePwCheck() {
		if (pwfMypagePwCheck == null) {
			pwfMypagePwCheck = new JPasswordField();
			pwfMypagePwCheck.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					checkPw();
				}
			});
			pwfMypagePwCheck.addMouseListener(new MouseAdapter() {
			});
			pwfMypagePwCheck.setEditable(false);
			pwfMypagePwCheck.setBounds(101, 179, 130, 26);
		}
		return pwfMypagePwCheck;
	}

	private JLabel getLbMypageName() {
		if (lbMypageName == null) {
			lbMypageName = new JLabel("이름");
			lbMypageName.setBounds(26, 230, 61, 16);
		}
		return lbMypageName;
	}

	private JTextField getTfMypageName() {
		if (tfMypageName == null) {
			tfMypageName = new JTextField();
			tfMypageName.setEditable(false);
			tfMypageName.setColumns(10);
			tfMypageName.setBounds(100, 225, 130, 26);
		}
		return tfMypageName;
	}

	private JLabel getLbMypageTelno() {
		if (lbMypageTelno == null) {
			lbMypageTelno = new JLabel("전화번호");
			lbMypageTelno.setBounds(25, 280, 61, 16);
		}
		return lbMypageTelno;
	}

	private JTextField getTfMypageTelno() {
		if (tfMypageTelno == null) {
			tfMypageTelno = new JTextField();
			tfMypageTelno.setEditable(false);
			tfMypageTelno.setColumns(10);
			tfMypageTelno.setBounds(99, 275, 130, 26);
		}
		return tfMypageTelno;
	}

	private JLabel getLbMypageNick() {
		if (lbMypageNick == null) {
			lbMypageNick = new JLabel("닉네임");
			lbMypageNick.setBounds(25, 335, 61, 16);
		}
		return lbMypageNick;
	}

	private JTextField getTfMypageNick() {
		if (tfMypageNick == null) {
			tfMypageNick = new JTextField();
			tfMypageNick.setEditable(false);
			tfMypageNick.setColumns(10);
			tfMypageNick.setBounds(99, 330, 130, 26);
		}
		return tfMypageNick;
	}

	private JButton getBtnOverlap() {
		if (btnOverlap == null) {
			btnOverlap = new JButton("중복확인");
			btnOverlap.setEnabled(false);
			btnOverlap.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					checkNick();
				}
			});
			btnOverlap.setBounds(246, 329, 117, 29);
		}
		return btnOverlap;
	}

	// method


	private void clientControl() {
		// 로그인 후 보여지는 것들
		lbAdminLogo.setVisible(true);
		lbMypage.setVisible(true);
		lbMypageId.setVisible(true);
		tfMypageId.setVisible(true);
		lbMypagePw.setVisible(true);
		pwfMypagePw.setVisible(true);
		lbMypagePwCheck.setVisible(true);
		pwfMypagePwCheck.setVisible(true);
		lbMypageName.setVisible(true);
		tfMypageName.setVisible(true);
		lbMypageTelno.setVisible(true);
		tfMypageTelno.setVisible(true);
		lbMypageNick.setVisible(true);
		tfMypageNick.setVisible(true);
		btnOverlap.setVisible(true);

	}
	
	private void getMypage() { // 로그인 한 사용자의 정보 불러오는 것도, 전역변수를 기준으로 DbAction 클래스로 넘겨준다.
		
		DbAction_Mypage_YJ dbAction = new DbAction_Mypage_YJ(wkId);
		Bean_Mypage_YJ bean = dbAction.getMypage();
		
		tfMypageId.setText(bean.getClientId());
		pwfMypagePw.setText(bean.getClientPw());
		pwfMypagePwCheck.setText(bean.getClientPw());
		tfMypageName.setText(bean.getClientName());
		tfMypageTelno.setText(bean.getClientTelno());
		tfMypageNick.setText(bean.getClientNick());
	}


	private void checkPw() {
		char[] pw1 = pwfMypagePw.getPassword();
		String pwCheck1 = new String(pw1);
		char[] pw2 = pwfMypagePwCheck.getPassword();
		String pwCheck2 = new String(pw2);


		if (pwCheck1.equals(pwCheck2)) {
			lbPwCheckStr.setText("비밀번호 동일합니다");
		} else {
			lbPwCheckStr.setVisible(true);
		}
	}
	
	
	private void updateUser() {
		
		char[] pw = pwfMypagePw.getPassword();
		String setPw = "";
		String setName = tfMypageName.getText().trim();
		String setTelno = tfMypageTelno.getText().trim();
		String setNick = tfMypageNick.getText().trim();
		for (int i = 0; i < pw.length; i++) {
			setPw += pw[i];
		}
		DbAction_Mypage_YJ dbAction = new DbAction_Mypage_YJ(wkId, setPw, setName, setTelno, setNick);
		boolean isUpdate = dbAction.updateUser();
		
		if (isUpdate == true) {
			JOptionPane.showMessageDialog(null, tfMypageName.getText().trim() + "님의 정보가 수정 되었습니다!");
			Search_CH search_CH = new Search_CH();
			frame.dispose();
			
		} else {
			JOptionPane.showMessageDialog(null, tfMypageName.getText().trim() + "님의 정보수정 중 오류가 생겼습니다.");
		}
	}

	
	private void checkNick() {
		String checkText = "";
		String userText = tfMypageNick.getText().trim();
		
		DbAction_Mypage_YJ dbAction = new DbAction_Mypage_YJ(wkId, userText);
		Bean_Mypage_YJ bean = dbAction.checkNick();
		
		if (userText.equals(bean.getClientNick())) {
			JOptionPane.showMessageDialog(null, "중복입니다. 다른 닉네임을 설정하세요.");
		} else {
			JOptionPane.showMessageDialog(null, userText + "은 가능한 닉네임 입니다.");
		}
	}


	private JLabel getLbPwCheckStr() {
		if (lbPwCheckStr == null) {
			lbPwCheckStr = new JLabel("비밀번호가 동일하지 않습니다.");
			lbPwCheckStr.setBounds(103, 202, 175, 16);
			lbPwCheckStr.setVisible(false);
		}
		return lbPwCheckStr;
	}

	private JButton getBtnMypageReset() {
		if (btnMypageReset == null) {
			btnMypageReset = new JButton("수정");
			btnMypageReset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnMypageReset.setVisible(false);
					btnMypageResetOk.setVisible(true);
					pwfMypagePw.setEditable(true);
					pwfMypagePwCheck.setEditable(true);
					tfMypageName.setEditable(true);
					tfMypageTelno.setEditable(true);
					tfMypageNick.setEditable(true);
					btnOverlap.setEnabled(true);
				}
			});
			btnMypageReset.setBounds(281, 411, 117, 29);
		}
		return btnMypageReset;
	}

	private JButton getBtnMypageCancel() {
		if (btnMypageCancel == null) {
			btnMypageCancel = new JButton("취소");
			btnMypageCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getMypage();
					tfMypageId.setEditable(false);
					pwfMypagePw.setEditable(false);
					pwfMypagePwCheck.setEditable(false);
					tfMypageName.setEditable(false);
					tfMypageTelno.setEditable(false);
					btnOverlap.setEnabled(false);
					
//					Client_Mypage_YJ client_Mypage_YJ = new Client_Mypage_YJ();
//					frame.dispose();
					
					Client_FirstView_YJ client_FirstView_YJ = new Client_FirstView_YJ();
					frame.dispose();
					
				}
			});
			btnMypageCancel.setBounds(404, 411, 117, 29);
		}
		return btnMypageCancel;
	}

	private JButton getBtnMypageResetOk() {
		if (btnMypageResetOk == null) {
			btnMypageResetOk = new JButton("완료");
			btnMypageResetOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					checkText();
					updateUser();
					btnMypageReset.setVisible(true);
				}
			});
			btnMypageResetOk.setBounds(281, 411, 117, 29);
			btnMypageResetOk.setVisible(false);
		}
		return btnMypageResetOk;
	}
	public void setMain(MainProcess main) {
		this.mainpr = main;
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
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon("/Users/gimminjae/Desktop/저장/background/background/client_mypage.png"));
			lblNewLabel_1.setBounds(0, -16, 545, 478);
		}
		return lblNewLabel_1;
	}
}