package termProj;

public class Main {

	public static void main(String[] args) {

		// create first object
		MySqlConnection con = MySqlConnection.getInstance();

		// connect to server
		con.connect();

		// try to print student info without logging in -> error
		con.printAllStudentInfo();

		// login with last name and id
		con.login("Apple", "123456");

		// print all student information (all courses
		con.printAllStudentInfo();
		
		// print only first and last name
		con.printStudentLastFirstName();
		
		// logout and disconnect
		con.disconnect();

	}

}
