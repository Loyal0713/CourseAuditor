
/**
 * This class represents a student. It holds a first and last name with a unique
 * id. It also holds an integer array signifying what/when classes the
 * individual has taken
 *
 * @author Josh Brown; Zach Richard
 *
 */
public class Student {

	private String firstName, lastName; // first and last name of student
	private int id; // unique id for student
	private int currSemester = 2019500; // current semester
	private int numCredCompleted = 0; // number of credits the students has completed
	private Course[] programCourses; // array of all courses student has taken for the program
	private String[] semesterCourses; // array of all courses student is taking in the current semester

	// list of all courses in CPS program
	private String[] courseName = { "CPS 180", "CPS 181", "CPS 210", "CPS 240", "CPS 340", "CPS 360", "CPS 410",
			"CPS 450", "CPS 470", "MTH 132", "MTH 175", "MTH 223", "STA 382QR", "Elec 1", "CPS 396D", "Elec 2",
			"CPS 395", "Elec 3" };

	/**
	 * Constructor for student
	 *
	 * @param lastName
	 *            - String for last name of student
	 * @param firstName
	 *            - String for first name of student
	 * @param id
	 *            - Integer for unique id of student
	 * @param courses
	 *            - Array of integers signifying dates courses were/will be taken
	 */
	public Student(String lastName, String firstName, int id, int[] courses, String[] semCourses) {

		this.lastName = lastName; // set last name
		this.firstName = firstName; // set first name
		this.id = id; // set id

		this.programCourses = new Course[courses.length]; // create program course array
		int counter = 0; // create counter to count how many courses the student is currently taking

		// fill program course array
		for (int i = 0; i < this.programCourses.length; i++) {

			// add course to array
			this.programCourses[i] = new Course(courses[i], courseName[i]);

			// check if course is currently being taken
			if (courses[i] == this.currSemester) {
				counter++;
			}

		}

		// create semester course array
		this.semesterCourses = semCourses;

	}

	/**
	 * This method gets the start date the student started courses on
	 *
	 * @return String representing when the student first started courses
	 */
	public String startDate() {

		// by default, it will return the start date of the first course
		int startNum = this.programCourses[0].getSemesterTaken();

		// loop through all courses in program
		for (int i = 1; i < this.programCourses.length; i++) {

			// check if course was taken AND if it was taken before the current earliest
			// start date
			if (this.programCourses[i].getSemesterTaken() != 0
					&& this.programCourses[i].getSemesterTaken() < startNum) {

				startNum = this.programCourses[i].getSemesterTaken();

			}

		}

		// create string showing what the start date was
		String startDate = "You started in: " + displaySemester(startNum);
		return startDate;

	}

	/**
	 * Parses an integer into the year and season a course was taken
	 *
	 * @param sem
	 *            - Integer in format XXXXYYY where XXXX is the year and YYY is the
	 *            semester the course was taken
	 * @return String representing a clean format of what season and year a course
	 *         was taken
	 */
	private static String displaySemester(int sem) {

		// start with blank string
		String semester = "";

		// parse year and season
		int year = Integer.parseInt(Integer.toString(sem).substring(0, 4));
		int season = Integer.parseInt(Integer.toString(sem).substring(4, 7));

		// determine what season course was taken
		if (season == 300)
			semester = semester.concat("Fall");
		else if (season == 500)
			semester = semester.concat("Spring");
		else if (season == 700)
			semester = semester.concat("Summer");

		// add year to string
		semester = semester.concat(", " + year);

		return semester;

	}

	/**
	 * This method calculates how many credits a student has completed
	 *
	 * @return String representing how many credits the student has completed
	 */
	public String creditsCompleted() {

		// start count at zero
		int count = 0;

		// loop through entire program course array
		for (int i = 0; i < this.programCourses.length; i++) {

			// check if course was taken
			if (this.programCourses[i].getSemesterTaken() != 0) {
				count++;
			}

		}

		// multiply credits by 3
		int creditsTaken = count * 3;

		// set the number of credits completed
		this.numCredCompleted = creditsTaken;

		// create clean string to return
		String retString = "Total Credits Taken: " + creditsTaken;

		return retString;

	}

	/**
	 * This method calculates how many credits the student has left
	 *
	 * @return String showing how many credits student has yet to complete
	 */
	public String creditsLeft() {

		// subtract the current number of credits completed from the total needed
		int credLeft = 49 - this.numCredCompleted;
		return ("Total Credits Remaining: " + credLeft);

	}

	/**
	 * This method calculates how many credits the student currently is enrolled in
	 *
	 * @return String showing how many credits the student currently has
	 */
	public String creditsEnrolled() {

		// multiply how many classes the student is currently in by 3
		int credEnrolled = this.semesterCourses.length * 3;

		String retString = "Current Credits Enrolled: " + credEnrolled;
		return retString;

	}

	/**
	 * This method calculates the estimated graduation date of the student
	 *
	 * @return
	 */
	public String estGradDate() {

		int addSem = 0;

		if (programCourses[8].getSemesterTaken() == 0) {
			addSem++;
			if (programCourses[5].getSemesterTaken() == 0 || programCourses[4].getSemesterTaken() == 0) {
				addSem++;
				if (programCourses[2].getSemesterTaken() == 0) {
					addSem++;
					if (programCourses[1].getSemesterTaken() == 0) {
						addSem++;
						if (programCourses[0].getSemesterTaken() == 0) {
							addSem++;
						}
					}
				}
			}
		}

		if (programCourses[6].getSemesterTaken() == 0 || programCourses[7].getSemesterTaken() == 0) {
			addSem++;
		}

		int electives = 0;
		if (programCourses[13].getSemesterTaken() == 0)
			electives++;
		if (programCourses[14].getSemesterTaken() == 0)
			electives++;
		if (programCourses[15].getSemesterTaken() == 0)
			electives++;

		if (electives > 1) {
			addSem += 2;
		} else if (electives == 1) {
			addSem++;
		}

		int mathClasses = 0;
		for (int i = 9; i < 13; i++) {
			if (programCourses[i].getSemesterTaken() == 0)
				mathClasses++;
		}

		if (mathClasses > addSem) {
			if (programCourses[9].getSemesterTaken() != 0) {
				if (mathClasses - addSem > 0) {
					addSem = addSem + (mathClasses - addSem);
				}
			} else {
				addSem += 3;
			}
		}

		int semester = this.currSemester;
		for (int i = 0; i < addSem; i++) {
			if (i == 0) {
				semester += 800;
			} else if (i % 2 == 0) {
				semester += 800;
			} else {
				semester += 200;
			}
		}

		String estGrad = "Your Estimated Graduation Semeseter: " + displaySemester(semester);
		return estGrad;
	}

	/**
	 * This method returns a string in the format: Last Name, First Name, Id then a
	 * list of all course information
	 *
	 * @return String showing all information of the student object
	 */
	public String toString() {

		// create new string builder object - will be used to build string
		StringBuilder sb = new StringBuilder();

		// add last name, first name, and id
		sb.append(this.lastName + ", " + this.firstName + ", " + id + ", ");

		// then add all course info
		for (int i = 0; i < this.programCourses.length; i++) {

			if (i < this.programCourses.length - 1) {

				// i has not reached last element - add comma separator
				sb.append(this.programCourses[i] + ", ");

			} else {

				// hit last element - do not add comma
				sb.append(programCourses[i]);

			}

		}

		return sb.toString();

	}

	/**
	 * This method returns a string in the format: Last Name, First Name
	 *
	 * @return String showing only the first and last name
	 */
	public String getLastFirstName() {

		return this.lastName + ", " + this.firstName;

	}

	/**
	 * Returns a string of the students first and last name
	 *
	 * @return - String
	 */
	public String getFirstLastName() {

		return this.firstName + " " + this.lastName;

	}

	/**
	 * Returns Course array containing all the courses in students program
	 *
	 * @return - Course array
	 */
	public Course[] getProgramCourses() {

		return this.programCourses;
	}

	/**
	 * Returns String array containing all the courses the student is in
	 *
	 * @return - String array
	 */
	public String[] getSemesterCourses() {

		return this.semesterCourses;
	}

}
