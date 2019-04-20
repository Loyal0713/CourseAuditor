package termProj;

public class Main {

	public static void main(String[] args) {

		// create first object
		MySqlConnection con = MySqlConnection.getInstance();

		// connect to server
		con.connect();

		// try to print student info without logging in -> error
		System.out.println(con.getAllStudentInfo());

		// login with last name and id
		con.login("Apple", "123456");

		// print only first and last name
		System.out.println(con.getStudentLastFirstName());
		
		// print all student information (all courses
		System.out.println(con.getAllStudentInfo());
		
		// print students start date
		System.out.println(con.getStartDate());
		
		// print number of student's completed credits
		System.out.println(con.getCreditsCompleted());
		
		// print number of credits student has left
		System.out.println(con.getCreditsLeft());
		
		// print number of credits student is enrolled in
		System.out.println(con.getCreditsEnrolled());
		
		// print estimated
		System.out.println(con.getEstGradDate());
		
		// logout and disconnect
		con.disconnect();

	}

}
