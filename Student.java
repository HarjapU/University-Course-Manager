import java.util.ArrayList;
import java.util.Random;

// Uses Comparable for course ordering
public class Student implements Comparable<Student> {

    // Instance variables
    private String name;
    private int studentID;
    private ArrayList<InPersonCourse> enrolledInPersonCourses;
    private ArrayList<OnlineCourse> enrolledOnlineCourses;

    /*
     * Default constructor that takes a name
     */
    public Student(String name) {
        Random random = new Random();

        this.name = name;
        this.enrolledInPersonCourses = new ArrayList<>();
        this.enrolledOnlineCourses = new ArrayList<>();
        this.studentID = random.nextInt(99999); // randomize student ID
    
    }

    /*
     * Equals method for comparing between students
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) { 
            return true; // return true if o is a copy of this
        }

        if (!(o instanceof Student)) {
            return false; // return false if o is not a student
        }

       Student s = (Student) o;

       // returns true if they share the same student ID, otherwise return false
       if (getStudentID() == s.getStudentID()) {
        return true;
       }

       return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentID() {
        return studentID;
    }

    public ArrayList<InPersonCourse> getEnrolledInPersonCourses() {
        return enrolledInPersonCourses;
    }

    public ArrayList<OnlineCourse> getEnrolledOnlineCourses() {
        return enrolledOnlineCourses;
    }

    /*
     * toString method for displaying student's name, student ID, and all enrolled courses
     * Used by Students
     */
    @Override
    public String toString() {
        String enrolledCoursesString = "Enrolled Courses: ";

        if (getEnrolledInPersonCourses().isEmpty() && getEnrolledOnlineCourses().isEmpty()) {
            enrolledCoursesString += "None";
        }

        if (!getEnrolledInPersonCourses().isEmpty()) {
            for (int i = 0; i < getEnrolledInPersonCourses().size(); i++) {
                enrolledCoursesString += getEnrolledInPersonCourses().get(i).getCourseName() + " (" + getEnrolledInPersonCourses().get(i).getCourseID() + "), ";
            }
        }

        if (!getEnrolledOnlineCourses().isEmpty()) {
            for (int i = 0; i < getEnrolledOnlineCourses().size(); i++) {
                enrolledCoursesString += getEnrolledOnlineCourses().get(i).getCourseName() + " (" + getEnrolledOnlineCourses().get(i).getCourseID() + "), ";
            }
        }

        return "Student Name: " + getName() + "\nStudent ID: " + getStudentID() + "\n" + enrolledCoursesString;
    }

    /*
     * Method for comparing 2 students
     * Order decided based on name. If name is equal, then decide based on student ID (Smaller student ID before larger)
     * Used for the arraylist in University class
     */
    @Override
    public int compareTo(Student s) {
        if (getName() == s.getName()) {
            return s.getStudentID() - getStudentID();
        }
        
        for (char c: getName().toCharArray()) {
            for (char h: s.getName().toCharArray()) {
                if (c != h) {
                    return c-h;
                }
            }
        }

        return 0;
    }

}
