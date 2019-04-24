package termProj;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Class representing the tool bar contained in the student view
 * 
 * @author Avery Little
 *
 */
public class ToolbarStudentView extends JPanel implements ActionListener {

	private JButton programViewBtn; // button to view program info
	private JButton semesterViewBtn; // button to view semester info
	private JButton statsViewBtn; // button to view student stats
	private JButton logoutBtn; // button to logout and quit
	private String program; // String for program info
	private String semester; // String for semester info
	private String stats; // String for student stat info

	/**
	 * Public constructor
	 * 
	 * @param program
	 *            - String for program info
	 * @param semester
	 *            - String for semester info
	 * @param stats
	 *            - String for student stat info
	 */
	public ToolbarStudentView(String program, String semester, String stats) {

		// set border
		setBorder(BorderFactory.createEtchedBorder());

		// initialize buttons
		programViewBtn = new JButton("Program View");
		semesterViewBtn = new JButton("Semester View");
		statsViewBtn = new JButton("Statistics View");
		logoutBtn = new JButton("Logout");

		// set text
		this.program = program;
		this.semester = semester;
		this.stats = stats;

		// add action listeners to buttons
		programViewBtn.addActionListener(this);
		semesterViewBtn.addActionListener(this);
		statsViewBtn.addActionListener(this);
		logoutBtn.addActionListener(this);

		// set layout
		setLayout(new FlowLayout(FlowLayout.LEFT));

		// add buttons
		add(programViewBtn);
		add(semesterViewBtn);
		add(statsViewBtn);
		add(logoutBtn);

	}

	@Override
	public void actionPerformed(ActionEvent e) { // get source to differentiate buttons
		JButton clicked = (JButton) e.getSource();

		// This is where you append the text to the different text panel
		if (clicked == programViewBtn) {
			TextPanelStudentView.clearText(""); // clear the textpanel of previous data
			TextPanelStudentView.appendText(this.program);// will want to have /n new lines
		} else if (clicked == semesterViewBtn) {
			TextPanelStudentView.clearText(""); // clear the textpanel of previous data
			TextPanelStudentView.appendText(this.semester);
		} else if (clicked == statsViewBtn) {
			TextPanelStudentView.clearText(""); // clear the textpanel of previous data
			TextPanelStudentView.appendText(this.stats);
		} else if (clicked == logoutBtn) {

			System.exit(0);

		}
	}
}
