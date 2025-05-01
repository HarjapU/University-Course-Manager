import java.util.ArrayList;
import java.util.Collections;

// abstract so University cannot be instantiated
abstract class University {

    // instance variables
    // acts as a database for all things related to the university
    private static ArrayList<Student> registeredStudents = new ArrayList<>();
    private static ArrayList<InPersonCourse> availableInPersonCourses = new ArrayList<>();
    private static ArrayList<OnlineCourse> availableOnlineCourses = new ArrayList<>();
    
    private University() {} // constructor not needed (Everything will be used in a static way)

    /*
     * Method for creating and putting an In Person course into the University database for students to enroll in
     */
    public static InPersonCourse createInPersonCourse(String courseName, String professor, int maxCap) {
        InPersonCourse c;
        
        // if the professor was left blank, then use the In Person course constructor without professor, otherwise, use other constructor
        if (professor.equals("") || professor.equals(" ")) {
            c = new InPersonCourse(courseName, maxCap);
        }
        else {
            c = new InPersonCourse(courseName, professor, maxCap);
        }

        // if the course is not a duplicate, then add it to the list of courses, otherwise, display an error
        if (!availableInPersonCourses.contains(c)) {
            availableInPersonCourses.add(c);
            return c;
        }
        else {
            System.out.println("\nCourse already exists\n");

            return null;
        }
    }

    /*
     * Method for removing an In Person course from the University database if the course doesn't run anymore
     */
    public static boolean removeInPersonCourse(int courseID) {

        // iterate through all available In Person courses
        for (int i = 0; i < availableInPersonCourses.size(); i++) {
            InPersonCourse ipc = availableInPersonCourses.get(i);

            // if the course was found, then remove all students from the course
            if (ipc.getCourseID() == courseID) {

                // iterate through a CLONED arraylist to avoid iterating and changing the original arraylist at the same time (as a result of the dropStudent method)
                // resulting in an exception occuring
                for (Student s: new ArrayList<>(ipc.getEnrolledStudents())) {
                    ipc.dropStudent(s);
                }
                availableInPersonCourses.remove(i);
                return true;
            }
        }
        return false;
    }
    
    /*
     * Method for creating and putting an Online course into the University database for students to enroll into
     */
    public static OnlineCourse createOnlineCourse(String courseName, String professor, int maxCap, String platform) {
        OnlineCourse c;
        
        // if the professor was left blank, then use the Online course constructor without professor, otherwise, use other constructor
        if (professor.equals("") || professor.equals(" ")) {
            c = new OnlineCourse(courseName, maxCap, platform);
        }
        else {
            c = new OnlineCourse(courseName, professor, maxCap, platform);
        }
        // if the course is not a duplicate, then add it to the list of courses, otherwise, display an error
        if (!availableOnlineCourses.contains(c)) {
            availableOnlineCourses.add(c);

            return c;
        }
        else {
            System.out.println("\nCourse already exists\n");

            return null;
        }
    }


    /*
     * Method for removing an Online course from the University database if it doesn't run anymore
     */
    public static boolean removeOnlineCourse(int courseID) {

        // iterate through all available Online courses
        for (int i = 0; i < availableOnlineCourses.size(); i++) {
            OnlineCourse oc = availableOnlineCourses.get(i);

            // if the course was found, then remove all students from the course
            if (oc.getCourseID() == courseID) {

                // iterate through a CLONED arraylist to avoid iterating and changing the original arraylist at the same time (as a result of the dropStudent method)
                // resulting in an exception occuring
                for (Student s: new ArrayList<>(oc.getEnrolledStudents())) {
                    oc.dropStudent(s);
                }
                availableOnlineCourses.remove(i);
                return true;
            }
        }
        return false;
    }
    
    /*
     * Method to register new student into the University database
     */
    public static Student registerStudent(String name) {
        Student s = new Student(name);
        registeredStudents.add(s); // add student to the arraylist of registered students

        return s;
    }

    /*
     * Method to remove a student from the University database (maybe after graduation or transfer)
     */
    public static boolean removeStudent(int studentID) {
        Student studentToRemove = null;

        for (Student s : registeredStudents) {
            if (s.getStudentID() == studentID) {
                // make a CLONED version of each list to avoid iterating and changing the original list at the same time (as a result of the dropStudent method)
                // resulting in an exception occuring
                ArrayList<InPersonCourse> inPersonCourses = new ArrayList<>(s.getEnrolledInPersonCourses());
                ArrayList<OnlineCourse> onlineCourses = new ArrayList<>(s.getEnrolledOnlineCourses());

                // Drop student from all enrolled courses
                for (InPersonCourse IPC : inPersonCourses) {
                    IPC.dropStudent(s);
                }
                for (OnlineCourse OC : onlineCourses) {
                    OC.dropStudent(s);
                }

                // Mark student for removal
                studentToRemove = s;
                break; // Exit loop after finding student
            }
        }

        // Remove the student after iteration
        if (studentToRemove != null) {
            registeredStudents.remove(studentToRemove);
            return true;
        }

        return false;
    }

    
    
    /*
     * Method for finding a student in the database, mainly used for the admin
     */
    public static Student findStudent(int studentID) {

        // return the student if the student ID matches with the one in the database
        for (Student s: registeredStudents) {
            if (s.getStudentID() == studentID) {
                return s;
            }
        }

        return null;
    }

    /*
     * Method for finding a course in the database, used by students or admin
     */
    public static Course findCourse(int courseID) {

        // first search for the course in the In Person course database
        for (InPersonCourse ipc: availableInPersonCourses) {
            if (ipc.getCourseID() == courseID) {
                return ipc;
            }
        }

        // if not found, search for the course in the Online course database
        for (OnlineCourse oc: availableOnlineCourses) {
            if (oc.getCourseID() == courseID) {
                return oc;
            }
        }

        return null;
    }

    /*
     * Method for displaying all courses in the university, used by the Students to see which courses to enroll in
     * Get the elements from both course lists and return it in the form of a string
     */
    public static String displayCourses() {
        String allInPersonCourses = "", allOnlineCourses = "";

        if (!availableInPersonCourses.isEmpty()) {
            for (Course c: availableInPersonCourses) {
                allInPersonCourses += c.getCourseName() + " (" + c.getCourseID() + "), ";
            }
        }
        else {
            allInPersonCourses = "None";
        }

        if (!availableOnlineCourses.isEmpty()) {
            for (Course c: availableOnlineCourses) {
                allOnlineCourses += c.getCourseName() + " (" + c.getCourseID() + "), ";
            }
        }
        else {
            allOnlineCourses = "None";
        }

        return "All In-Person Courses: " + allInPersonCourses + "\nAll Online Courses: " + allOnlineCourses;
    }

    /*
     * Method for displaying all content related to the University (Courses and Students), used by admins to control the courses and students (delete or create)
     * Get the elements from every list and return it in the form of a string
     */
    public static String displayString() {
        String allstudents = "", allInPersonCourses = "", allOnlineCourses = "";
        if (!registeredStudents.isEmpty()) {
            Collections.sort(registeredStudents);

            for (Student s: registeredStudents) {
                allstudents += s.getName() + " (" + s.getStudentID() + "), ";
            }
        }
        else {
            allstudents = "None";
        }

        if (!availableInPersonCourses.isEmpty()) {
            Collections.sort(availableInPersonCourses);

            for (InPersonCourse c: availableInPersonCourses) {
                allInPersonCourses += c.getCourseName() + " (" + c.getCourseID() + "), ";
            }
        }
        else {
            allInPersonCourses = "None";
        }

        if (!availableOnlineCourses.isEmpty()) {
            Collections.sort(availableOnlineCourses);

            for (OnlineCourse c: availableOnlineCourses) {
                allOnlineCourses += c.getCourseName() + " (" + c.getCourseID() + "), ";
            }
        }
        else {
            allOnlineCourses = "None";
        }

        return "Registered Students: " + allstudents + "\nAll In-Person Courses: " + allInPersonCourses + "\nAll Online Courses: " + allOnlineCourses;
    }
}
