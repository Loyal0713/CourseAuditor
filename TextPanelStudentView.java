
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Class that handles the text area view that shows student information
 *
 * @author Avery Little
 *
 */
public class TextPanelStudentView extends JPanel {

	private static JTextArea textArea;

	/**
	 * Public constructor
	 */
	public TextPanelStudentView() {

		// initialize attributes
		textArea = new JTextArea();
		textArea.setEditable(false);

		// set layout
		setLayout(new BorderLayout());

		// JScrollPane for when content goes beyond frame
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}

	/**
	 * Adds text to the text area
	 *
	 * @param text
	 *            - String to be appended to the text area
	 */
	public static void appendText(String text) {
		textArea.append(text);
	}

	/**
	 * Clears and appends text to the text area
	 *
	 * @param text
	 *            - String that the text area will show
	 */
	public static void clearText(String text) {
		textArea.setText(text);
	}
}
