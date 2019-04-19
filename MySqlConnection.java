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
 * @author brown8jt - Josh Brown\
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
	 * Disconnects the program from the database. DOES NOT LOG STUDENT OFF!
	 */
	public void disconnect() {

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

				// create query - get individual student with given id
				Statement stmt = con.createStatement();
				String query = "select * from programview where ID = " + id;

				// execute query
				ResultSet rs = stmt.executeQuery(query);

				// move result set pointer
				rs.next();

				// create new student for the session
				currStudentSession = new Student(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10),
						rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14), rs.getInt(15), rs.getInt(16),
						rs.getInt(17), rs.getInt(18), rs.getInt(19), rs.getInt(20));

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
	 * This method prints a students last then first name to the console OR that
	 * there is no currently running session if there is no student logged in
	 */
	public void printStudentLastFirstName() {

		// there is no student currently logged in
		if (currStudentSession == null) {

			System.out.println("No current session!");

		} else {

			// there is a student logged in
			System.out.println(currStudentSession.getLastFirstName());

		}

	}

	/**
	 * This method prints all the currently logged in student's information to the console OR that
	 * there is no currently running session if there is no student logged in
	 */
	public void printAllStudentInfo() {

		// there is no student currently logged in
		if (currStudentSession == null) {

			System.out.println("No current session!");

		} else {

			// there is a student logged in
			System.out.println(currStudentSession);

		}

	}

}
