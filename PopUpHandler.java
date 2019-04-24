package termProj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * This class represents a handler for any popups the system may need.
 * 
 * @author brown8jt - Josh Brown
 *
 */
public class PopUpHandler {

	private PopUpView view;
	private MySqlConnection con;
	private JButton okBtn;

	/**
	 * Public constructor
	 * 
	 * @param title
	 *            - String representing what the pop up title should be
	 * @param content
	 *            - String representing the content of the pop up message
	 * @param status
	 *            - String representing the status that controls what window to open
	 *            next
	 * @param con
	 *            - MySqlConnection representing the connection to the database
	 */
	public PopUpHandler(String title, String content, String status, MySqlConnection con) {

		// initialize connection, view, okay button, and content area
		this.con = con;

		if (status.equals("MSC.li001")) {
			this.view = new PopUpView(title, content, "");
		} else if (status.equals("MSC.li002")) {
			this.view = new PopUpView(title, content, "Username/Password incorrect!");
		}

		this.okBtn = this.view.getOkBtn();

		// set title
		this.view.setTitle(title);

		// add action listener to button
		this.okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (status.equals("MSC.li001")) {

					// go to student view
					StudentHandler sh = new StudentHandler(con);
					view.dispose();

				} else {

					// go back to login view
					LoginHandler lh = new LoginHandler();
					view.dispose();

				}

			}

		});

	}

}
