package termProj;

import javax.swing.JButton;

public class StudentHandler {

	private StudentView view;
	private MySqlConnection con;
	private JButton logout;
	
	public StudentHandler(MySqlConnection con) {
		
		this.con = con;
		this.view = new StudentView(con.getProgramCourses(), con.getSemesterCourses(), con.getStudentFirstLastName(), con.getStudentStats());
		
		
		
		
	}

}
