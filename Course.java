package termProj;

public class Course {

	private int semesterTaken;
	private String courseName;
	
	public Course(int semesterTaken, String courseName) {
		
		this.semesterTaken = semesterTaken;
		this.courseName = courseName;
		
	}
	
	public int getSemesterTaken() {
		
		return this.semesterTaken;
		
	}
	
	public String getCourseName() {
		
		return this.courseName;
		
	}
	
	public String toString() {
		
		return this.courseName + " " + this.semesterTaken;
		
	}
	
}
