package termProj;

public class Main {

	public static void main(String[] args) {

		//LoginHandler lh = new LoginHandler();
		
		MySqlConnection con = MySqlConnection.getInstance();
		con.connect();
		con.login(" ", "324567");

		System.out.println(con.getAllStudentInfo());
		
		

	}

}
