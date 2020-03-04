
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Class representing the window that shows all relevant student stuff
 *
 * @author Josh Brown; Avery Little
 *
 */
public class StudentView extends JFrame { // must extends for methods to work

	private TextPanelStudentView textPanel; // if you want one line of text you use JTextField, but this is a custom
											// class container
	private ToolbarStudentView toolbar;

	/**
	 * Public constructor
	 *
	 * @param program
	 *            - String representing what will be shown on the program view
	 * @param semester
	 *            - String representing what will be shown on the semester view
	 * @param stats
	 *            - String representing what will be shown on the stats view
	 * @param name
	 *            - String representing the student's name
	 */
	public StudentView(String program, String semester, String stats, String name) {

		// set up window
		setLayout(new BorderLayout());
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Course Auditor - " + name);
		setResizable(false);

		// set up tool bar and text panel
		toolbar = new ToolbarStudentView(program, semester, stats);
		textPanel = new TextPanelStudentView();

		// add tool bar and textpanel to frame
		add(toolbar, BorderLayout.NORTH);
		add(textPanel, BorderLayout.CENTER);

		// set up the intial view; normally nothing is shown - this fixes it
		setInitial(program);

	}

	/**
	 * This allows the view to show information upon initial start up of the frame
	 *
	 * @param program
	 *            - String representing program information which is the first thing
	 *            shown
	 */
	public void setInitial(String program) {

		this.textPanel.clearText(""); // clear the textpanel of previous data
		this.textPanel.appendText(program);// will want to have /n new lines

	}

}
