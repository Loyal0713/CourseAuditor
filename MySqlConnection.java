package termProj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class handles all communication made between the mysql database server
 * and the course auditor program itself
 * 
 * @author brown8jt - Josh Brown
 * @version 1.0.3
 *
 */

public class MySqlConnection {

	// data base related stuff
	private final static String jdbcDriver = "com.mysql.cj.jdbc.Driver"; // driver for mysql
	private final static String serverIP = "jdbc:mysql://localhost:3306/beaver_fever"; // ip for data base
	private final static String username = "root"; // username to connect to data base
	private final static String password = "cps410"; // password for user

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

			// create new instance of this class
			currDBSession = new MySqlConnection();

		}

		// return sole instance
		return currDBSession;

	}

	/**
	 * Connects the program to the database server
	 */
	public void connect() {

		// try to get jdbc driver class
		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// check if connection has already been made - connect if not
		if (con == null) {

			// try to create connection
			try {
				con = DriverManager.getConnection(serverIP, username, password);
				System.out.println("Connected to database!");
			} catch (SQLException e) {
				System.out.println("Error connecting to database!");
			}

		}

	}

	/**
	 * Logs a student out of the system by removing any reference to any previously
	 * created student object
	 */
	public static void logout() {

		// remove student info
		currStudentSession = null;

		// print to console that student was logged out
		System.out.println("Logged out!");

	}

	/**
	 * Disconnects the program from the database
	 */
	public void disconnect() {

		if (currStudentSession != null) {
			logout();
		}

		// disconnect from server
		con = null;

		// print to console that program was disconnected from the database
		System.out.println("Disconnected from database!");

	}

	/**
	 * This method logs a student into the program (currently only uses an id to
	 * verify)
	 * 
	 * @param lastName
	 *            - String representing last name of student trying to log in
	 * @param id
	 *            - Integer representing id of student trying to log in
	 */
	public void login(String lastName, String id) {

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

				// get the program history of the student
				for (int i = 0; i < programCourses.length; i++) {

					programCourses[i] = rs.getInt(i + 4);

				}

				// create new student for the session
				currStudentSession = new Student(lastNameResult, firstNameResult, idResult, programCourses);

				// log to console that student was logged in
				System.out.println("Logged in: " + currStudentSession.getLastFirstName());

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {

			// there is a student logged in
			System.out.println("Someone is already logged in!");

		}

	}

	/**
	 * This method gets a students last then first name OR that there is no
	 * currently running session if there is no student logged in
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
	 * This method gets all the currently logged in student's information OR that
	 * there is no currently running session if there is no student logged in
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
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return
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

}
