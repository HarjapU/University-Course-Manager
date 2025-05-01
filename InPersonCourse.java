import java.util.Random;

// subclass of Course
public class InPersonCourse extends Course {

    // instance variables
    final private String[] locations = {"ENG", "VIC", "LIB", "KHN", "KHE", "KHS", "KHW", "ARC", "JOR"};
    private String location;

    /*
     * Constructor if all course elements are known
     */
    public InPersonCourse(String courseName, String professor, int maxCap) {
        super(courseName, professor, maxCap);

        // randomize the location from the list and randomize the room number
        Random random = new Random();
        
        String building = locations[random.nextInt(locations.length)];
        int roomNumber = 1 + random.nextInt(300);

        this.location = building + " " + roomNumber;


    }

    /*
     * Constructor if all course elements except for professor are known (to early to know professor)
     */
    public InPersonCourse(String courseName, int maxCap) {
        super(courseName, maxCap);

        // randomize the location from the list and randomize the room number
        Random random = new Random();
        
        String building = locations[random.nextInt(locations.length)];
        int courseNumber = 1 + random.nextInt(300);

        this.location = building + " " + courseNumber;
    }

    /*
     * Method to enroll student into an In Person course
     */
    public boolean enrollStudent(Student student) {
        // iterate through all enrolled students and if it matches, then don't enroll again
        for (Student s : getEnrolledStudents()) {
            if (s.equals(student)) { 
                return false; // false if student is already enrolled in course
            }
        }

        // If course has room, then add student to the arraylist of enrolled students
        // and add the course to the student's enrolled courses
        if (!isFull()) {    
            getEnrolledStudents().add(student);
            student.getEnrolledInPersonCourses().add(this);
            incrementNumOfEnrolledStudents(); // increase enrolled students by 1
            return true; // true if sucessfully enrolled
        }

        return false; // false if the course is full
    }

    /*
     * Method for dropping students from a In Person course
     */
    public boolean dropStudent(Student student) {
        // only continue process if there are students enrolled in course
        if (getNumOfEnrolledStudents() > 0) {

            // iterate through all enrolled students. if student is found, remove student from
            // enrolled students list and from the student's enrolled courses list
            for (Student s : getEnrolledStudents()) {
                if (s.equals(student)) { 
                    getEnrolledStudents().remove(student);
                    student.getEnrolledInPersonCourses().remove(this);
                    decrementNumOfEnrolledStudents(); // decrease enrolled students by 1
                    return true; // true if sucessfully removed
                }
            }
            return false; // false if there are no students in course
        }

        return false;
    }

    public String[] getLocations() {
        return locations;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    /*
     * toString method for displaying the course name, ID, professor, location, capacity left, and all enrolled students
     * Used by students and admin
     */
    @Override
    public String toString() {
        String enrolledStudentsString = "No students enrolled";

        if (!getEnrolledStudents().isEmpty()) {
            enrolledStudentsString = "Enrolled Students: " + getEnrolledStudents().getFirst().getName();

            for (int i = 1; i < getEnrolledStudents().size(); i++) {
                enrolledStudentsString += ", " + getEnrolledStudents().get(i).getName();
            }
        }

        return "Course Name: " + getCourseName() + " (In-Person)\n" + "Course ID: " + getCourseID() + "\nProfessor: " + getProfessor() + "\nLocation: " + getLocation() + "\n" + (getMaxCap() - getNumOfEnrolledStudents()) + " out of " + getMaxCap() + " spots available\n" + enrolledStudentsString;
    }
}
