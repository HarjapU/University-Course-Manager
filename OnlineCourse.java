// Subclass of Course
public class OnlineCourse extends Course {
    
    // Instance Variable
    private String platform;

    /*
     * Constructor if all elements including professor are known
     */
    public OnlineCourse(String courseName, String professor, int maxCap, String platform) {
        super(courseName, professor, maxCap);
        this.platform = platform;
    }

    /*
     * Cosntructor if all elements except for the professor are known (to early to know professor)
     */
    public OnlineCourse(String courseName, int maxCap, String platform) {
        super(courseName, maxCap);
        this.platform = platform;
    }

    /*
     * Method for enrolling student into an online course
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
            student.getEnrolledOnlineCourses().add(this);
            incrementNumOfEnrolledStudents(); // increase enrolled students by 1
            return true; // true if sucessfully enrolled
        }

        return false; // false if the course is full
    }

    /*
     * Method for dropping students from a online course
     */
    public boolean dropStudent(Student student) {
        // only continue process if there are students enrolled in course
        if (getNumOfEnrolledStudents() > 0) {

            // iterate through all enrolled students. if student is found, remove student from
            // enrolled students list and from the student's enrolled courses list
            for (Student s : getEnrolledStudents()) {
                if (s.equals(student)) { 
                    getEnrolledStudents().remove(student);
                    student.getEnrolledOnlineCourses().remove(this);
                    decrementNumOfEnrolledStudents(); // decrease enrolled students by 1
                    return true; // true if sucessfully removed
                }
            }
            return false; // false if student was not found
        }

        return false; // false if there are no students in course
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /*
     * toString method for displaying the course name, ID, professor, platform, capacity left, and all enrolled students
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

        return "Course Name: " + getCourseName() + " (Online)\n" + "Course ID: " + getCourseID() + "\nProfessor: " + getProfessor() + "\nPlatform: " + getPlatform() + "\n" + (getMaxCap() - getNumOfEnrolledStudents()) + " out of " + getMaxCap() + " spots available\n" + enrolledStudentsString;
    }
}
