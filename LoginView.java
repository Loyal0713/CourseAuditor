package termProj;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JFrame {

	private JButton loginBtn = new JButton("Login");
	private JTextField username = new JTextField();
	private JPasswordField password = new JPasswordField();
	
	public LoginView() {
		
		this.setLayout(new GridLayout(5,1));
		
		this.setTitle("AH");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500, 300);
		
		this.add(new JLabel("Username:"));
		this.add(username);
		this.add(new JLabel("Password:"));
		this.add(password);
		this.add(loginBtn);
		
		this.setVisible(true);
		
		
		
	}
	
	
	public JButton getLoginBtn() {
		
		return this.loginBtn;
	}

	public String getUserName() {
		
		return this.username.getText();
	}

	public String getPassword() {
		
		return new String(this.password.getPassword());
	}

}
