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

	public PopUpHandler(String title, String content, String status, MySqlConnection con) {

		// initialize connection, view, okay button, and content area
		this.con = con;
		this.view = new PopUpView(title, content, status);
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
