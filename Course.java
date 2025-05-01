import java.util.ArrayList;
import java.util.Random;

// class is abstract to force use of subclasses
// Uses Comparable for course ordering
abstract class Course implements Comparable<Course> {

    // instance variables
    private String courseName, professor;
    private int maxCap, numOfEnrolledStudents, courseID;
    private ArrayList <Student>enrolledStudents;

    /*
     * constructor if all elements of the course are known
     */
    public Course(String courseName, String professor, int maxCap) {
        Random random = new Random();

        this.courseName = courseName;
        this.professor = professor;
        this.maxCap = maxCap;
        this.numOfEnrolledStudents = 0;
        this.enrolledStudents = new ArrayList<>();
        this.courseID = random.nextInt(999); // randomize Course ID

    }

    /*
    /constructor if professor is not known yet
    */
    public Course(String courseName, int maxCap) {
        Random random = new Random();

        this.courseName = courseName;
        this.professor = "To Be Announced"; // sets the string to "To Be Announced"
        this.maxCap = maxCap;
        this.numOfEnrolledStudents = 0;
        this.enrolledStudents = new ArrayList<>();
        this.courseID = random.nextInt(999);

    }

    /*
     * Method to determine if there is space in the course or not
     * returns false if at max capacity, returns true otherwise
     */
    public boolean isFull() {
        return getNumOfEnrolledStudents() >= getMaxCap();
    }

    /*
     * Method to compare 2 courses.
     * Decides order based on course name, if course name is the same,
     * then decide based on the unique course ID (Smaller course ID is before larger)
     * Used for the arraylist in University class
     */
    @Override
    public int compareTo(Course c) {
        if (getCourseName() == c.getCourseName()) {
            return c.getCourseID() - getCourseID();
        }

        for (char c1: getCourseName().toCharArray()) {
            for (char c2: c.getCourseName().toCharArray()) {
                if (c1 != c2) {
                    return c1-c2;
                }
            }
        }

        return 0;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getCourseName() {
        return courseName;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public int getMaxCap() {
        return maxCap;
    }

    public void setMaxCap(int maxCap) {
        this.maxCap = maxCap;
    }

    public int getNumOfEnrolledStudents() {
        return numOfEnrolledStudents;
    }

    /*
     * Methods to increment and decrement the number of enrolled students.
     * number of enrolled students are only meant to move 1 at a time, so an incrementer and decrementer
     * are more useful than setters
     * 
     */
    public void incrementNumOfEnrolledStudents() {
        this.numOfEnrolledStudents++;
    }

    public void decrementNumOfEnrolledStudents() {
        this.numOfEnrolledStudents--;
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    
}
