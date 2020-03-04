
/**
 * Class that represents a course with a course name and a semester taken (0 if
 * not taken)
 *
 * @author Josh Brown
 *
 */
public class Course {

	private int semesterTaken; // semester course was taken
	private String courseName; // name of semester taken

	/**
	 * Public constructor for course class
	 *
	 * @param semesterTaken
	 *            - Integer representing the semester the course was taken
	 * @param courseName
	 *            - String representing the name of the course
	 */
	public Course(int semesterTaken, String courseName) {

		this.semesterTaken = semesterTaken;
		this.courseName = courseName;

	}

	/**
	 * Returns the semester the course was taken
	 *
	 * @return - Integer representing semester taken
	 */
	public int getSemesterTaken() {

		return this.semesterTaken;

	}

	/**
	 * Returns the name of the course
	 *
	 * @return - String representing the course name
	 */
	public String getCourseName() {

		return this.courseName;

	}

	/**
	 * Returns course name and semester taken
	 */
	public String toString() {

		return this.courseName + " " + this.semesterTaken;

	}

	/**
	 * Returns a string with the class name and the semester and year taken
	 *
	 * @return - String
	 */
	public String cleanString() {

		// start with blank string
		String semester = "";

		// parse year and season
		int year = Integer.parseInt(Integer.toString(this.semesterTaken).substring(0, 4));
		int season = Integer.parseInt(Integer.toString(this.semesterTaken).substring(4, 7));

		// determine what season course was taken
		if (season == 300)
			semester = semester.concat("Fall");
		else if (season == 500)
			semester = semester.concat("Spring");
		else if (season == 700)
			semester = semester.concat("Summer");

		// add year to string
		semester = semester.concat(", " + year);

		semester = this.courseName + " " + semester;

		return semester;

	}

}
