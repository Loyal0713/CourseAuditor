
public class Student {

	String firstName;
	String lastName;
	int ID;
	int[] progCourses = new int[16];
	String[] semCourses = new String[7];
	int curSemester = 2019500;
	int credCompleted = 0;
	
	public Student(String first, String last, int id, int[] progCourses, String[] semCourses) {
		
		this.firstName = first;
		this.lastName = last;
		this.ID = id;
		this.progCourses = progCourses;
		this.semCourses = semCourses;
		
	}
	
	public String startDate() {
		
		int startNum = this.progCourses[0];
		
		for(int i=1;i<this.progCourses.length;i++) {
			if(this.progCourses[i] != 0 && this.progCourses[i] < startNum) {
				startNum = this.progCourses[i];
			}
		}
		
		String startDate = "Your start date was the semester " + displaySemester(startNum);
		return startDate;
	}
	
	public String creditsCompleted() {
		int count = 0;
		for(int i=0;i<this.progCourses.length;i++) {
			if(this.progCourses[i] != 0)
				count++;
		}
		int creditsTaken = count * 3;
		credCompleted = creditsTaken;
		String retString = "Total Credits Taken: " + creditsTaken;
		return retString;
	}
	
	public String creditsLeft() {
		int credLeft = 49 - credCompleted;
		return ("Total Credits left to take in major: " + credLeft);
	}
	
	public String creditsEnrolled() {
		int count = 0;
		for(int i=0;i<semCourses.length;i++) {
			if(semCourses[i] != null) {
				count++;
			}
		}
		int credEnrolled = count * 3;
		String retString = "Current Credits Enrolled: " + credEnrolled;
		return retString;
	}
	
	public String estGradDate() {
		int addSem = 0;
		
		if(progCourses[8] == 0) {
			addSem++;
			if(progCourses[5] == 0 || progCourses[4] == 0) {
				addSem++;
				if(progCourses[2] == 0) {
					addSem++;
					if(progCourses[1] == 0) {
						addSem++;
						if(progCourses[0] == 0) {
							addSem++;
						}
					}
				}
			}
		}
		
		if(progCourses[6] == 0 || progCourses[7] == 0) {
			addSem++;
		}
		
		int electives = 0;
		if(progCourses[13] == 0)
			electives++;
		if(progCourses[14] == 0)
			electives++;
		if(progCourses[15] == 0)
			electives++;
		
		if(electives > 1) {
			addSem += 2;
		}else if(electives == 1){
			addSem++;
		}
		
		int mathClasses = 0;
		for(int i=9;i<13;i++) {
			if(progCourses[i] == 0)
				mathClasses++;
		}
		
		
		if(mathClasses > addSem) {
			if(progCourses[9] != 0) {
				if(mathClasses - addSem > 0) {
					addSem = addSem + (mathClasses - addSem);
				}
			}else {
				addSem += 3;
			}
		}
		
		int semester = curSemester;
		for(int i=0;i<addSem;i++) {
			if(i == 0) {
				semester += 800;
			}else if(i % 2 == 0) {
				semester += 800;
			}else {
				semester += 200;
			}
		}
		
		String estGrad = "Your Estimated graduation semester is " + displaySemester(semester);
		return estGrad;
	}
	
	
	private static String displaySemester(int sem) {
		String semester = "";
		
		int year = Integer.parseInt(Integer.toString(sem).substring(0, 4));
		int season = Integer.parseInt(Integer.toString(sem).substring(4, 7));
		
		if(season == 300)
			semester = semester.concat("Fall");
		else if (season == 500)
			semester = semester.concat("Spring");
		else if (season == 700)
			semester = semester.concat("Summer");
		
		semester = semester.concat(", " + year);
		
		return semester;
	}
	
	
	
}
