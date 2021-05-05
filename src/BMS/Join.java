package BMS;

import java.awt.Color; //JFrame import
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BMS.LogIn;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class Join extends JFrame {
	private JPanel panel;
	private JLabel lblName;
	private JLabel lblId;
	private JLabel lblPassword;
	private JTextField tfName;
	private JTextField tfId;
	private JPasswordField pfPassword;
	private JButton btnSignUp;
	private JLabel label;
	private JButton btnNewButton;
	
	public Join() {
		setTitle("\uD68C\uC6D0\uAC00\uC785");
		setSize(800, 600); //전체 창의 크기
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel(); 
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 794, 565);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setContentPane(panel);
		
		lblName = new JLabel("NAME");
		lblName.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 40));
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(123, 140, 111, 46);
		panel.add(lblName);
		
		tfName = new JTextField();
		tfName.setBounds(226, 140, 236, 46);
		tfName.setColumns(10);
		panel.add(tfName);
		
		lblId = new JLabel("ID"); //ID Label
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 40));
		lblId.setBounds(173, 221, 46, 46);
		panel.add(lblId);
		
		tfId = new JTextField();
		tfId.setColumns(10);
		tfId.setBounds(225, 221, 237, 46);
		panel.add(tfId);
		
		lblPassword = new JLabel("PW"); //PW Label
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 38));
		lblPassword.setBounds(159, 308, 46, 46);
		panel.add(lblPassword);
		
		pfPassword = new JPasswordField();
		pfPassword.setBounds(225, 308, 237, 46);
		panel.add(pfPassword);
		
		btnSignUp = new JButton("Join"); //Join Button
		btnSignUp.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 40));
		btnSignUp.setBackground(Color.WHITE);
		btnSignUp.setBounds(541, 140, 130, 222);
		panel.add(btnSignUp);
		
		label = new JLabel("\uD68C\uC6D0 \uC815\uBCF4\uB97C \uC785\uB825\uD558\uC138\uC694");
		label.setFont(new Font("HY헤드라인M", Font.PLAIN, 25));
		label.setForeground(Color.WHITE);
		label.setBounds(40, 27, 266, 54);
		panel.add(label);
		
		btnNewButton = new JButton("Login"); //Login Button
		btnNewButton.setFont(new Font("HY헤드라인M", Font.PLAIN, 25));
		btnNewButton.setBounds(123, 414, 548, 54);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							LogIn frame = new LogIn();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		setVisible(true);
		
		btnSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//Client client = new Client(tfName.getText(), tfId.getText(), pfPassword.getText());
					
					String sql = "INSERT INTO client VALUES(?, ?, ?)"; //client에 이름, 아이디, 비밀번호 값을 넣음
					Connection conn = DriverManager.getConnection(Main.url, Main.user, Main.password);
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, tfName.getText()); //이름
					pstmt.setString(2, tfId.getText()); //아이디
					pstmt.setString(3, pfPassword.getText()); //비밀번호
					
					pstmt.executeUpdate();
					//clearText();
					
					JOptionPane.showMessageDialog(null, "회원가입 완료 ! 로그인 가능 !"); //회원가입 완료시
					
					pstmt.close();
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} //end of actionPerformed

		});
	}
}