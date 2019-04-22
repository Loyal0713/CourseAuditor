package termProj;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PopUpView extends JFrame {

	private JButton okBtn = new JButton("Ok!");

	public PopUpView(String title, String content, String status) {

		this.setLayout(new GridLayout(3, 1));
		this.setSize(300, 150);

		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.add(new JLabel(status));
		this.add(new JLabel(content));
		this.add(this.okBtn);
		
		this.setVisible(true);

	}

	public JButton getOkBtn() {

		return this.okBtn;
	}

}
