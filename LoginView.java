package termProj;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Class representing the login window
 * 
 * @author brown8jt - Josh Brown
 *
 */
public class LoginView extends JFrame {

	private JButton loginBtn = new JButton("Login"); // login button
	private JTextField username = new JTextField(); // username field
	private JPasswordField password = new JPasswordField(); // password field

	/**
	 * Public constructor
	 */
	public LoginView() {

		// set up password field
		password.setPreferredSize(new Dimension(150, 10)); // set text field size

		// set up window
		this.setTitle("Course Auditor"); // set title
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // set close operation
		this.setSize(350, 150); // set size
		this.setLayout(new FlowLayout(FlowLayout.LEADING)); // set
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		// initialize jpanels
		JPanel content = new JPanel();
		JPanel userPanel = new JPanel();
		JPanel passPanel = new JPanel();

		// set jpanel layouts
		passPanel.setLayout(new GridLayout(1, 2));
		content.setLayout(new GridLayout(3, 1));

		// add components to the password panel
		passPanel.add(new JLabel("Login ID:"));
		passPanel.add(password);

		// add all panels to the main panel
		content.add(userPanel);
		content.add(passPanel);
		content.add(loginBtn);

		// add main panel to the frame
		this.add(content);

		// set frame to visible
		this.setVisible(true);

	}

	/**
	 * Returns the login button
	 * 
	 * @return - JButton representing login button
	 */
	public JButton getLoginBtn() {

		return this.loginBtn;
	}

	/**
	 * Returns the username
	 * 
	 * @return - String representing the username
	 */
	public String getUserName() {

		return this.username.getText();
	}

	/**
	 * Returns the password
	 * 
	 * @return - String representing the username
	 */
	public String getPassword() {

		return new String(this.password.getPassword());
	}

}