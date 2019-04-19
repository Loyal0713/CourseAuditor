package termProj;

/**
 * This class represents a student. It holds a first and last name with a unique
 * id. It also holds an integer array signifying what/when classes the
 * individual has taken
 * 
 * @author brown8jt - Josh Brown
 * @version 1.0.5
 *
 */
public class Student {

	private String firstName, lastName; // first and last name of student
	private int id; // unique id for student
	private int[] courses; // array of the courses student has taken or will take

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
	public Student(String lastName, String firstName, int id, int... courses) {

		this.lastName = lastName; // set last name
		this.firstName = firstName; // set first name
		this.id = id; // set id

		// initialize course array
		this.courses = new int[courses.length];

		// fill course array
		for (int i = 0; i < this.courses.length; i++) {

			this.courses[i] = courses[i];

		}

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
		for (int i = 0; i < this.courses.length; i++) {

			if (i < this.courses.length - 1) {

				// i has not reached last element - add comma separator
				sb.append(this.courses[i] + ", ");

			} else {

				// hit last element - do not add comma
				sb.append(courses[i]);

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

}
