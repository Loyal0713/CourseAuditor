
/**
 * Handler that handles communication between the window and the system
 *
 * @author Josh Brown
 *
 */
public class StudentHandler {

	private StudentView view; // window

	public StudentHandler(MySqlConnection con) {

		this.view = new StudentView(con.programCourseToString(), con.semesterCourseToString(), con.getStudentStats(),
				con.getStudentFirstLastName());

	}

}
