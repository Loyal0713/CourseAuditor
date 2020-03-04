
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles all communication made between the mysql database server
 * and the course auditor program itself
 *
 * @author Josh Brown
 *
 */

public class MySqlConnection {

	// data base related stuff
	private final static String jdbcDriver = "com.mysql.cj.jdbc.Driver"; // driver for mysql
	private final static String serverIP = "jdbc:mysql://localhost:3306/beaver_fever"; // ip for data base
	private final static String username = "root"; // username to connect to data base
	private final static String password = ""; // password for user

	private int currSemester = 2019;

	private static Connection con = null; // connection to the server
	private static MySqlConnection currDBSession = null; // single instance of this class
	private static Student currStudentSession = null; // current student logged into system

	/**
	 * Create private constructor so no one can instantiate an instance using
	 * default constructor
	 */
	private MySqlConnection() {

	}

	/**
	 * Returns a singular instance of the class
	 *
	 * @return MySqlConnection - Represents a communication link between the
	 *         database and the program
	 */
	public static MySqlConnection getInstance() {

		// check if there is an instance of this class
		if (currDBSession == null) {
			currDBSession = new MySqlConnection();
		}
		return currDBSession;
	}

	/**
	 * Connects class to the database and returns a code determining status
	 *
	 * MSC.c001 = could not find the jdbc driver; MSC.c002 = successful connection
	 * to database; MSC.c003 = unsuccessful connection to database
	 *
	 * @return String representing status of the connection process
	 */
	public String connect() {

		String msg = "";

		// try to get jdbc driver class
		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			// e.printStackTrace();
			msg = "MSC.c001";
		}

		// check if connection has already been made - connect if not
		if (con == null) {

			// try to create connection
			try {

				con = DriverManager.getConnection(serverIP, username, password);

				// successful connection
				msg = "MSC.c002";

			} catch (SQLException e) {

				// unsuccessful connection
				msg = "MSC.c003";

			}

		}

		return msg;

	}

	/**
	 * Logs student off of system and returns a code signifying successful logout
	 *
	 * @return String representing successful logout code
	 */
	public String logout() {

		// remove student info
		currStudentSession = null;

		// print to console that student was logged out
		return "MSC.lo001";

	}

	/**
	 *
	 * Disconnects class from database and returns code signifying successful
	 * disconnection
	 *
	 * @return String representing successful disconnection
	 */
	public String disconnect() {

		if (currStudentSession != null) {
			logout();
		}

		// disconnect from server
		con = null;

		// print to console that program was disconnected from the database
		return "MSC.d001";

	}

	/**
	 * Logs a student in using an id (ADD USERNAME VERIFICATION!!) and returns a
	 * code signifying whether the login was successful or not
	 *
	 * MSC.li001 = successful login; MSC.li002 = could not find student with given
	 * id; MSC.li003 = a student was already logged in
	 *
	 * @param lastName
	 *            - String representing last name of student trying to log in
	 * @param id
	 *            - Integer representing id of student trying to log in
	 */
	public String login(String lastName, String id) {

		String msg = "";

		// there is no student logged in
		if (currStudentSession == null) {

			try {

				// create query - get individual students program view
				Statement stmt = con.createStatement();
				String query = "select * from programview where ID = " + id;

				// execute query
				ResultSet rs = stmt.executeQuery(query);

				// move result set pointer
				rs.next();

				// get the last and first name and id of the student
				String lastNameResult = rs.getString(1);
				String firstNameResult = rs.getString(2);
				int idResult = rs.getInt(3);

				int[] programCourses = new int[18];
				String[] semesterCourses = new String[7];

				// get the program history of the student
				for (int i = 0; i < programCourses.length; i++) {

					programCourses[i] = rs.getInt(i + 4);

				}

				query = "select * from semview where ID = " + id;
				rs = stmt.executeQuery(query);
				rs.next();

				for (int i = 0; i < semesterCourses.length; i++) {

					semesterCourses[i] = rs.getString(i + 5);

				}

				// create new student for the session
				currStudentSession = new Student(lastNameResult, firstNameResult, idResult, programCourses,
						semesterCourses);

				// log to console that student was logged in
				msg = "MSC.li001";

			} catch (SQLException e) {

				msg = "MSC.li002";
				
			}

		} else {

			// there is a student logged in
			msg = "MSC.li003";

		}

		return msg;

	}

	/**
	 * This method gets a students last then first name OR that there is no
	 * currently running session if there is no student logged in
	 *
	 * @return String representing the first and last name of the student logged in
	 *         OR that there was no student logged in
	 */
	public String getStudentLastFirstName() {

		String retString = "";

		// there is no student currently logged in
		if (currStudentSession == null) {

			retString = "No current session!";

		} else {

			// there is a student logged in
			retString = currStudentSession.getLastFirstName();

		}

		return retString;

	}

	/**
	 * This method gets all the currently logged in student's information as a
	 * String OR that there is no currently running session if there is no student
	 * logged in
	 *
	 * @return String representing the first and last name, id, and all course
	 *         information of the currently logged in student OR that there was no
	 *         student logged in
	 *
	 */
	public String getAllStudentInfo() {

		String retString = "";

		// there is no student currently logged in
		if (currStudentSession == null) {

			retString = "No current session!";

		} else {

			retString = currStudentSession.toString();

		}

		return retString;

	}

	/**
	 * This method gets the start date of the student currently logged in OR show
	 * that there is no current session
	 *
	 * @return String representing the start date of the student
	 */
	public String getStartDate() {

		String retString = "";

		// there is no student currently logged in
		if (currStudentSession == null) {

			retString = "No current session!";

		} else {

			// there is a student logged in
			retString = currStudentSession.startDate();

		}

		return retString;

	}

	/**
	 * Returns a string showing how many credits the student has completed
	 *
	 * @return String representing the number of credits completed
	 */
	public String getCreditsCompleted() {

		String retString = "";

		// there is no student currently logged in
		if (currStudentSession == null) {

			retString = "No current session!";

		} else {

			// there is a student logged in
			retString = currStudentSession.creditsCompleted();

		}

		return retString;

	}

	/**
	 * Returns a string showing how many credits the student has yet to complete
	 *
	 * @return String representing the number of credits the student has yet to
	 *         complete
	 */
	public String getCreditsLeft() {

		String retString = "";

		// there is no student currently logged in
		if (currStudentSession == null) {

			retString = "No current session!";

		} else {

			// there is a student logged in
			retString = currStudentSession.creditsLeft();

		}

		return retString;

	}

	/**
	 * Returns a string showing how many credits the student is enrolled in
	 *
	 * @return String representing the number of credits the student is enrolled in
	 */
	public String getCreditsEnrolled() {

		String retString = "";

		// there is no student currently logged in
		if (currStudentSession == null) {

			retString = "No current session!";

		} else {

			// there is a student logged in
			retString = currStudentSession.creditsEnrolled();

		}

		return retString;
	}

	/**
	 * Returns a string showing when the student will graduate
	 *
	 * @return String representing the expected graduation date
	 */
	public String getEstGradDate() {

		String retString = "";

		// there is no student currently logged in
		if (currStudentSession == null) {

			retString = "No current session!";

		} else {

			// there is a student logged in
			retString = currStudentSession.estGradDate();

		}

		return retString;
	}

	/**
	 * Returns a string that shows all the stats relevant to the student
	 *
	 * @return - String
	 */
	public String getStudentStats() {

		// there is no student currently logged in
		if (currStudentSession == null) {

			return "No current session!";

		} else {

			StringBuilder sb = new StringBuilder();

			sb.append(this.currStudentSession.startDate() + "\n");
			sb.append(this.currStudentSession.creditsCompleted() + "\n");
			sb.append(this.currStudentSession.creditsLeft() + "\n");
			sb.append(this.currStudentSession.creditsEnrolled() + "\n");
			sb.append(this.currStudentSession.estGradDate());

			return sb.toString();

		}

	}

	/**
	 * Returns all the program courses the student is in
	 *
	 * @return - Course array containing all program courses
	 */
	public Course[] getProgramCourses() {

		// there is no student currently logged in
		if (currStudentSession == null) {

			return null;

		} else {

			// there is a student logged in
			return this.currStudentSession.getProgramCourses();
		}

	}

	/**
	 * Returns the names of the courses the student is currently enrolled in
	 *
	 * @return - String array holding all semester courses
	 */
	public String[] getSemesterCourses() {

		// there is no student currently logged in
		if (currStudentSession == null) {

			return null;

		} else {

			// there is a student logged in
			return this.currStudentSession.getSemesterCourses();
		}

	}

	/**
	 * Returns the first and last name of the currently logged in student
	 *
	 * @return - String
	 */
	public String getStudentFirstLastName() {

		// there is no student currently logged in
		if (currStudentSession == null) {

			return null;

		} else {

			// there is a student logged in
			return this.currStudentSession.getFirstLastName();
		}

	}

	/**
	 * Returns a string of all the program courses and whether they were completed
	 * or if they need to be completed
	 *
	 * @return - String
	 */
	public String programCourseToString() {

		// there is no student currently logged in
		if (currStudentSession == null) {

			return null;

		} else {

			StringBuilder sb = new StringBuilder();

			Course[] course = currStudentSession.getProgramCourses(); // get courses

			List<Course> needToTake = new ArrayList<Course>();

			sb.append("Completed:\n");

			// add all completed courses
			for (int i = 0; i < course.length; i++) {

				if (course[i].getSemesterTaken() == 0) {

					needToTake.add(course[i]);
				}

			}

			// loop through all courses
			for (int i = 0; i < course.length; i++) {

				if (course[i].getSemesterTaken() != 0) {
					sb.append(course[i].cleanString() + "\n");
				}

			}

			sb.append("\nNeed to complete:\n");

			for (int i = 0; i < needToTake.size(); i++) {

				sb.append(needToTake.get(i).getCourseName() + "\n");

			}

			// there is a student logged in
			return sb.toString();
		}

	}

	/**
	 * Returns a String that shows the current semester courses
	 *
	 * @return
	 */
	public String semesterCourseToString() {

		// there is no student currently logged in
		if (currStudentSession == null) {

			return null;

		} else {

			StringBuilder sb = new StringBuilder();

			sb.append("You are currently enrolled in:\n");

			String[] course = currStudentSession.getSemesterCourses();

			// loop through all courses
			for (int i = 0; i < course.length; i++) {

				if (!(course[i] == null)) {

					sb.append(course[i].toString() + "\n");

				}

			}

			// there is a student logged in
			return sb.toString();
		}

	}

}
