
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Class representing the window for all pop ups
 *
 * @author Josh Brown
 *
 */
public class PopUpView extends JFrame {

	private JButton okBtn = new JButton("Ok!"); // ok button

	/**
	 * Public constructor
	 *
	 * @param title
	 *            - String title for frame
	 * @param content
	 *            - String content for message contained in pop up
	 * @param status
	 *            - status
	 */
	public PopUpView(String title, String content, String status) {

		// set up window
		this.setLayout(new BorderLayout());
		this.setSize(400, 150);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// set up button
		this.okBtn.setPreferredSize(new Dimension(50, 50));

		// add components
		this.add(new JLabel(status), BorderLayout.CENTER);
		this.add(new JLabel(content), BorderLayout.CENTER);
		this.add(this.okBtn, BorderLayout.SOUTH);

		// set frame visibility to true
		this.setVisible(true);

	}

	/**
	 * Returns the button
	 *
	 * @return - JButton representing the okay button
	 */
	public JButton getOkBtn() {

		return this.okBtn;
	}

}
