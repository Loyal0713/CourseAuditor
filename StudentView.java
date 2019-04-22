package termProj;

import javax.swing.JButton;
import javax.swing.JFrame;

public class StudentView extends JFrame {
	
	private Course[] proCourses;
	private Course[] semCourses;
	
	private String studentName;
	private String studentInfo;
	
	private JButton logoutBtn;
	
	public StudentView(Course[] program, Course[] semester, String name, String info) {
		
		this.proCourses = program;
		this.semCourses = semester;
		
		this.studentName = name;
		this.studentInfo = info;
		
		
	}

}
