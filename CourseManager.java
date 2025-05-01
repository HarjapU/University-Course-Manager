import java.util.Scanner;

public class CourseManager {
    /*
     * The goal with this project is to create a University Course Management System that allows students to register, enroll
     * in courses, and drop courses. Administrators can also login to manage students and courses, such as deleting courses, creating
     * courses, etc. Student class represents a student in this system, they have arraylists of enrolled courses and each student
     * has a special ID tied to their Student Account, and they have a Comparable to correctly sort students. Course class is a
     * representation of a generic course in the system, containing an arraylist of registered students with a maximum capacity,
     * unique Course ID for each course, and comparable to sort all types of courses correctly. InpersonCourse class is a subclass 
     * of course that represents an In-Person course in the system, with a In-Perons classroom location and functionality to enroll 
     * and drop students in In-Person courses. OnlineCourse is also a subclass of course to represent a Online course in the system, 
     * with a Online classroom location (Zoom, Teams, etc.) and functionality to enroll and drop students in Online Courses. University
     * class acts as the system manager that keeps arraylists to manage all students, In Person and Online courses as well as searching 
     * for students or classes and displaying all information related the system as a whole, all with static methods and variables.
     * 
     * Some things to note when usins this program:
     * When the program first starts, there will be ZERO courses created, meaning the Admin will have to first make the course in order for students to enroll in them.
     * Course IDs and Student IDs are very important!
     * In order for students to login, they need their student IDs, so they will need to remember them when given. 
     * Students can only enroll in courses or remove courses using the Course ID, so they will need to first check the course ID by displaying all the courses and seeing the course ID from there. 
     * Course ID is also needed for Admin to delete courses too.
     * Searching for courses are also based on Course ID
     * 
     */
    public static void main(String[] args) {
        // variables
        Scanner scanner = new Scanner(System.in);
        Student s;
        Course c;
        InPersonCourse ipc;
        OnlineCourse oc;
        String strInput, courseName, professor, platform;
        int numInput = 0, maxCap, courseID, studentID, stage = 0;

        // Stage 0 is for Student Login, Admin Login, or Exiting the program
        while (stage == 0) {

            System.out.println("\nWelcome to the UMT Course Manager.\nPlease sign in below\n\n1 - Student Login\n2 - Admin Login\n3 - Help\n4 - Exit");

            // Stay in loop until valid input is receieved
            while (true) {
                System.out.println("\nEnter an integer from 1 to 4:");
                String input = scanner.nextLine().trim(); // Read the full line and remove spaces
            
                // if student clicks enter key without typing anyting
                if (input.isEmpty()) { 
                    System.out.println("Invalid input. Please enter a number from 1 to 4");
                    continue; // Restart loop if input is empty
                }
            
                if (input.matches("[1-4]")) { // makes sure input is from 1 to 4
                    numInput = Integer.parseInt(input);
                    break; // leave loop on valid input
                } else {
                    System.out.println("Invalid input. Please enter a number from 1 to 4.");
                }
            }
            

            // Student Login
            if (numInput == 1) {
                stage = 1;
                
                // This Stage 1 is for Student Login or Register
                while (stage == 1) {
                    numInput = 0;
                    System.out.println("\n1 - Register student acccount\n2 - Login with existing account\n3 - Back");

                    // stay in loop until valid input
                    while (true) {
                        System.out.println("\nEnter an integer from 1 to 3:");
                        String input = scanner.nextLine().trim(); // Read the full line and remove spaces
                    
                        // if student clicks enter key without typing anything
                        if (input.isEmpty()) { 
                            System.out.println("Invalid input. Please enter a number from 1 to 3.");
                            continue; // Restart loop if input is empty
                        }
                    
                        if (input.matches("[1-3]")) { // makes sure input is from 1 to 3
                            numInput = Integer.parseInt(input);
                            break; // leave loop on valid input
                        } else {
                            System.out.println("Invalid input. Please enter a number from 1 to 3.");
                        }
                    }

                    // Student Registration
                    if (numInput == 1) {
                        numInput = 0;
                        System.out.println("\nEnter your name: ");
                        strInput = scanner.nextLine();

                        s = University.registerStudent(strInput);
                        System.out.println("\nSuccessfully registered\n\nYour Student ID: " + s.getStudentID() + "\nDO NOT FORGET YOUR STUDENT ID");
                        
                        continue;
                    }
                    // Student Login
                    else if (numInput == 2) {
                        numInput = 0;
                        System.out.println("\nEnter your Student ID: ");

                        // stay in loop until valid input
                        while (true) {
                            System.out.println("\nEnter an positive integer:");
                            String input = scanner.nextLine().trim(); // Read the full line and remove spaces
                        
                            // if student clicks enter key without typing anything
                            if (input.isEmpty()) { 
                                System.out.println("Invalid input. Please enter a positive number.");
                                continue; // Restart loop if input is empty
                            }
                        
                            if (input.matches("^\\d+$")) { // ^\\d+$ is a regex expression that makes sure the input is an positive integer
                                studentID = Integer.parseInt(input);
                                break; // leave loop on valid input
                            } else {
                                System.out.println("Invalid input. Please enter a positive number.");
                            }
                        }

                        s = University.findStudent(studentID);
                        
                        // if findstudent method returned null, it didn't find the student, give error
                        if (s == null) {
                            System.out.println("\nLogin failed! Incorrect Student ID");
                            numInput = 0;
                            continue;
                        }

                        System.out.println("\nLogin Successful!");

                        stage = 2;

                        // This stage 2 is after the student login. lets students enroll in courses, remove their courses, etc.
                        while (stage == 2) {

                            System.out.println("\n\nWelcome, " + s.getName() + "\n\nWhat would you like to do:\n1 - Display all courses\n2 - Display Your Details\n3 - Course Search\n4 - Enroll course\n5 - Remove Course\n6 - Delete Student Account\n7 - Log out");

                            // stay in loop until valid input
                            while (true) {
                                System.out.println("\nEnter an integer from 1 to 7:");
                                String input = scanner.nextLine().trim(); // Read the full line and remove spaces
                            
                                // if student clicks enter key without typing anything
                                if (input.isEmpty()) { 
                                    System.out.println("Invalid input. Please enter a number from 1 to 7.");
                                    continue; // Restart loop if input is empty
                                }
                            
                                if (input.matches("[1-7]")) { // makes sure input is from 1 to 7
                                    numInput = Integer.parseInt(input);
                                    break; // leave loop on valid input
                                } else {
                                    System.out.println("Invalid input. Please enter a number from 1 to 7.");
                                }
                            }

                            // display all courses
                            if (numInput == 1) {
                                numInput = 0;
                                System.out.println("\n" + University.displayCourses());

                                numInput = 0;
                                continue;
                            }
                            // display student details
                            else if (numInput == 2) {
                                numInput = 0;
                                System.out.println("\n" + s.toString());
                                continue;
                            }
                            // course search
                            else if (numInput == 3) {
                                numInput = 0;
                                System.out.println("Enter the course ID of the course to be found");

                                // stay in loop until valid input
                                while (true) {
                                    System.out.println("\nEnter an positive integer:");
                                    String input = scanner.nextLine().trim(); // Read the full line and remove spaces
                                
                                    // if student clicks enter key without typing anything
                                    if (input.isEmpty()) { 
                                        System.out.println("Invalid input. Please enter a positive number.");
                                        continue; // Restart loop if input is empty
                                    }
                                
                                    if (input.matches("^\\d+$")) { // ^\\d+$ is a regex expression that makes sure the input is an positive integer
                                        courseID = Integer.parseInt(input);
                                        break; // leave loop on valid input
                                    } else {
                                        System.out.println("Invalid input. Please enter a positive number.");
                                    }
                                }

                                c = University.findCourse(courseID);

                                // if findcourse method returned null, it didn't find the course, give error
                                if (c == null) {
                                    System.out.println("Course was not found");
                                }
                                // if course is found, confirm which type of course and convert to correct course and display course details
                                else if (c instanceof InPersonCourse) {
                                    ipc = (InPersonCourse) c;

                                    System.out.println("\n" + ipc.toString());
                                }
                                else if (c instanceof OnlineCourse) {
                                    oc = (OnlineCourse) c;

                                    System.out.println("\n" + oc.toString());
                                }

                                numInput = 0;
                                continue;
                            }
                            // student enroll in course
                            else if (numInput == 4) {
                                numInput = 0;
                                System.out.println("\nEnter the Course ID of the course to be enrolled into: ");

                                    
                                // stay in loop until valid input
                                while (true) {
                                    System.out.println("\nEnter an positive integer:");
                                    String input = scanner.nextLine().trim(); // Read the full line and remove spaces
                                
                                    // if student clicks enter key without typing anything
                                    if (input.isEmpty()) { 
                                        System.out.println("Invalid input. Please enter a positive number.");
                                        continue; // Restart loop if input is empty
                                    }
                                
                                    if (input.matches("^\\d+$")) { // ^\\d+$ is a regex expression that makes sure the input is an positive integer
                                        courseID = Integer.parseInt(input);
                                        break; // leave loop on valid input
                                    } else {
                                        System.out.println("Invalid input. Please enter a positive number.");
                                    }
                                }

                                c = University.findCourse(courseID);

                                // if findcourse method returned null, it didn't find the course, give error
                                if (c == null) {
                                    System.out.println("\nCourse was not found");
                                }
                                // if course is found, confirm which type of course and convert to correct course and enroll student
                                else if (c instanceof InPersonCourse) {
                                    ipc = (InPersonCourse) c;
                                    // method returns true if successful, otherwise it returns false
                                    if (ipc.enrollStudent(s)) {
                                        System.out.println("Sucessfully enrolled!");
                                    }
                                    else {
                                        System.out.println("Failed! You are already enrolled or there are no spots remaining");
                                    }
                                }
                                else if (c instanceof OnlineCourse) {
                                    oc = (OnlineCourse) c;
                                    // method returns true if successful, otherwise it returns false
                                    if (oc.enrollStudent(s)) {
                                        System.out.println("Sucessfully enrolled!");
                                    }
                                    else {
                                        System.out.println("Failed! You are already enrolled or there are no spots remaining");
                                    }
                                }

                                numInput = 0;
                                continue;

                            }
                            // student remove from course
                            else if (numInput == 5) {
                                numInput = 0;
                                System.out.println("\nEnter the Course ID of the course to be removed from: ");

                                    
                                // stay in loop until valid input
                                while (true) {
                                    System.out.println("\nEnter an positive integer:");
                                    String input = scanner.nextLine().trim(); // Read the full line and remove spaces
                                
                                    // if student clicks enter key without typing anything
                                    if (input.isEmpty()) { 
                                        System.out.println("Invalid input. Please enter a positive number.");
                                        continue; // Restart loop if input is empty
                                    }
                                
                                    if (input.matches("^\\d+$")) { // ^\\d+$ is a regex expression that makes sure the input is an positive integer
                                        courseID = Integer.parseInt(input);
                                        break; // leave loop on valid input
                                    } else {
                                        System.out.println("Invalid input. Please enter a positive number.");
                                    }
                                }

                                c = University.findCourse(courseID);

                                // if findcourse method returned null, it didn't find the course, give error
                                if (c == null) {
                                    System.out.println("\nCourse was not found");
                                }
                                // if course is found, confirm which type of course and convert to correct course and remove student from course
                                else if (c instanceof InPersonCourse) {
                                    ipc = (InPersonCourse) c;
                                    // method returns true if successful, otherwise it returns false
                                    if (ipc.dropStudent(s)) {
                                        System.out.println("Sucessfully dropped!");
                                    }
                                    else {
                                        System.out.println("Failed! You were not enrolled in this course");
                                    }
                                }
                                else if (c instanceof OnlineCourse) {
                                    oc = (OnlineCourse) c;
                                    // method returns true if successful, otherwise it returns false
                                    if (oc.dropStudent(s)) {
                                        System.out.println("Sucessfully dropped!");
                                    }
                                    else {
                                        System.out.println("Failed! You were not enrolled in this course");
                                    }
                                }

                                numInput = 0;
                                continue;
                            }
                            // student delete account
                            else if (numInput == 6) {
                                numInput = 0;
                                System.out.println("\nAre you sure?:\n1 - Yes\n2 - No");

                                // stay in loop until valid input
                                while (true) {
                                    System.out.println("\nEnter an integer from 1 to 2:");
                                    String input = scanner.nextLine().trim(); // Read the full line and remove spaces
                                
                                    // if student clicks enter key without typing anything
                                    if (input.isEmpty()) { 
                                        System.out.println("Invalid input. Please enter a number from 1 to 2");
                                        continue; // Restart loop if input is empty
                                    }
                                
                                    if (input.matches("[1-2]")) { // makes sure input is from 1 to 2
                                        numInput = Integer.parseInt(input);
                                        break; // leave loop on valid input
                                    } else {
                                        System.out.println("Invalid input. Please enter a number from 1 to 2.");
                                    }
                                }

                                // yes to remove account
                                if (numInput == 1) {
                                    numInput = 0;
                                    University.removeStudent(s.getStudentID()); // delete student and all references to the student 
                                    stage = 0;
                                    System.out.println("\nAccount deleted");
                                    break;
                                }
                                // no to remove student account
                                else if (numInput == 2) {
                                    numInput = 0;
                                    continue;
                                }
                            }
                            // log out of student account
                            else if (numInput == 7) {
                                numInput = 0;
                                stage = 1;
                                break;
                            }
                        }
                    }
                    // Back
                    else if (numInput == 3) {
                        stage = 0;
                        numInput = 0;
                        break;
                    }
                }
            }
            // Admin login
            else if (numInput == 2) {
                numInput = 0;
                System.out.println("\nLogin using the admin password (P.S the admin password is \"CPS209\"): ");
                strInput = scanner.nextLine();

                // if password is correct, allow access
                if (strInput.equals("CPS209")) {
                    stage = 1;

                    System.out.println("\nLogin Sucessful!");

                    // This stage 1 is for after Admin login. Lets Admin create/delete courses, delete students, etc.
                    while (stage == 1) {   
                        System.out.println("\n\nWelcome, Admin\n\nWhat would you like to do:\n1 - Display University Details\n2 - Course Search\n3 - Create Course\n4 - Delete Course\n5 - Delete a Student Account\n6 - Log out");
                        
                        // stay in loop until valid input
                        while (true) {
                            System.out.println("\nEnter an integer from 1 to 6:");
                            String input = scanner.nextLine().trim(); // Read the full line and remove spaces
                        
                            // if student clicks enter key without typing anything
                            if (input.isEmpty()) { 
                                System.out.println("Invalid input. Please enter a number from 1 to 6");
                                continue; // Restart loop if input is empty
                            }
                        
                            if (input.matches("[1-6]")) { // makes sure input is from 1 to 6
                                numInput = Integer.parseInt(input);
                                break; // leave loop on valid input
                            } else {
                                System.out.println("Invalid input. Please enter a number from 1 to 6.");
                            }
                        }

                        // display university details
                        if (numInput == 1) {
                            numInput = 0;
                            System.out.println("\n\n" + University.displayString());
                            
                            numInput = 0;
                            continue;
                        }
                        // Course search
                        else if (numInput == 2) {
                            numInput = 0;
                            System.out.println("Enter the course ID of the course to be found");

                            // stay in loop until valid input
                            while (true) {
                                System.out.println("\nEnter an positive integer:");
                                String input = scanner.nextLine().trim(); // Read the full line and remove spaces
                            
                                // if student clicks enter key without typing anything
                                if (input.isEmpty()) { 
                                    System.out.println("Invalid input. Please enter a positive number.");
                                    continue; // Restart loop if input is empty
                                }
                            
                                if (input.matches("^\\d+$")) { // ^\\d+$ is a regex expression that makes sure the input is an positive integer
                                    courseID = Integer.parseInt(input);
                                    break; // leave loop on valid input
                                } else {
                                    System.out.println("Invalid input. Please enter a positive number.");
                                }
                            }

                            c = University.findCourse(courseID);

                            // if findcourse method returned null, it didn't find the course, give error
                            if (c == null) {
                                System.out.println("Course was not found");
                            }
                            // if course is found, confirm which type of course and convert to correct course and display course details
                            else if (c instanceof InPersonCourse) {
                                ipc = (InPersonCourse) c;

                                System.out.println("\n" + ipc.toString());
                            }
                            else if (c instanceof OnlineCourse) {
                                oc = (OnlineCourse) c;

                                System.out.println("\n" + oc.toString());
                            }

                            numInput = 0;
                            continue;
                        }
                        // Admin create course
                        else if (numInput == 3) {
                            numInput = 0;

                            // prompt for all course details
                            System.out.println("\n\nCreate course below:\nCourse Name: ");
                            courseName = scanner.nextLine();
                            System.out.println("\nProfessor (Leave blank if unknown): ");
                            professor = scanner.nextLine();
                            System.out.println("\nMax Student Capacity: ");
                            
                            // stay in loop until valid input
                            while (true) {
                                System.out.println("\nEnter an positive integer:");
                                String input = scanner.nextLine().trim(); // Read the full line and remove spaces
                            
                                // if student clicks enter key without typing anything
                                if (input.isEmpty()) { 
                                    System.out.println("Invalid input. Please enter a positive number.");
                                    continue; // Restart loop if input is empty
                                }
                            
                                if (input.matches("^\\d+$")) { // ^\\d+$ is a regex expression that makes sure the input is an positive integer
                                    maxCap = Integer.parseInt(input);
                                    break; // leave loop on valid input
                                } else {
                                    System.out.println("Invalid input. Please enter a positive number.");
                                }
                            }

                            System.out.println("\nWhat type of course is this?:\n1 - In-Person Course\n2 - Online Course");

                            // stay in loop until valid input
                            while (true) {
                                System.out.println("\nEnter an integer from 1 to 2:");
                                String input = scanner.nextLine().trim(); // Read the full line and remove spaces
                            
                                // if student clicks enter key without typing anything
                                if (input.isEmpty()) { 
                                    System.out.println("Invalid input. Please enter a number from 1 to 2.");
                                    continue; // Restart loop if input is empty
                                }
                            
                                if (input.matches("[1-2]")) { // makes sure input is from 1 to 2
                                    numInput = Integer.parseInt(input);
                                    break; // leave loop on valid input
                                } else {
                                    System.out.println("Invalid input. Please enter a number from 1 to 2.");
                                }
                            }

                            // create In Person course
                            if (numInput == 1) {
                                numInput = 0;
                                ipc = University.createInPersonCourse(courseName, professor, maxCap);
                            }
                            // create Online course
                            else if (numInput == 2) {
                                numInput = 0;
                                System.out.println("\nWhat platform are classes hosted on (Ex. Zoom, Google Meet, etc): ");

                                platform = scanner.nextLine();

                                oc = University.createOnlineCourse(courseName, professor, maxCap, platform);
                            }
                            
                            System.out.println("\nCourse sucessfully created");
                            numInput = 0;
                            continue;
                        }
                        // Admin delete course
                        else if (numInput == 4) {
                            numInput = 0;
                            System.out.println("Enter the Course ID of the course to be deleted: ");

                            // stay in loop until valid input
                            while (true) {
                                System.out.println("\nEnter an positive integer:");
                                String input = scanner.nextLine().trim(); // Read the full line and remove spaces
                            
                                // if student clicks enter key without typing anything
                                if (input.isEmpty()) { 
                                    System.out.println("Invalid input. Please enter a positive number.");
                                    continue; // Restart loop if input is empty
                                }
                            
                                if (input.matches("^\\d+$")) { // ^\\d+$ is a regex expression that makes sure the input is an positive integer
                                    courseID = Integer.parseInt(input);
                                    break; // leave loop on valid input
                                } else {
                                    System.out.println("Invalid input. Please enter a positive number.");
                                }
                            }

                            System.out.println("\nWhat type of course is it:\n1 - In-Person Course\n2 - Online Course");

                            // stay in loop until valid input
                            while (true) {
                                System.out.println("\nEnter an integer from 1 to 2:");
                                String input = scanner.nextLine().trim(); // Read the full line and remove spaces
                            
                                // if student clicks enter key without typing anything
                                if (input.isEmpty()) { 
                                    System.out.println("Invalid input. Please enter a number from 1 to 2.");
                                    continue; // Restart loop if input is empty
                                }
                            
                                if (input.matches("[1-2]")) { // makes sure input is from 1 to 2
                                    numInput = Integer.parseInt(input);
                                    break; // leave loop on valid input
                                } else {
                                    System.out.println("Invalid input. Please enter a number from 1 to 2.");
                                }
                            }
                            // remove In Person course
                            if (numInput == 1) {
                                numInput = 0;

                                // if method returns true, it sucessfully removed, otherwise, it failed to delete course
                                if (University.removeInPersonCourse(courseID)) {
                                    System.out.println("\nCourse removed!");
                                }
                                else {
                                    System.out.println("\nCourse was not found");
                                }
                            }
                            // remove Online course
                            else if (numInput == 2) {
                                numInput = 0;
                                // if method returns true, it sucessfully removed, otherwise, it failed to delete course
                                if (University.removeOnlineCourse(courseID)) {
                                    System.out.println("\nCourse removed!");
                                }
                                else {
                                    System.out.println("\nCourse was not found");
                                }
                            }

                            numInput = 0;
                            continue;
                        }
                        // Admin delete student
                        else if (numInput == 5) {
                            numInput = 0;
                            System.out.println("\nEnter the Student ID of the student to be removed: ");

                            // stay in loop until valid input
                            while (true) {
                                System.out.println("\nEnter an positive integer:");
                                String input = scanner.nextLine().trim(); // Read the full line and remove spaces
                            
                                // if student clicks enter key without typing anything
                                if (input.isEmpty()) { 
                                    System.out.println("Invalid input. Please enter a positive number.");
                                    continue; // Restart loop if input is empty
                                }
                            
                                if (input.matches("^\\d+$")) { // ^\\d+$ is a regex expression that makes sure the input is an positive integer
                                    studentID = Integer.parseInt(input);
                                    break; // leave loop on valid input
                                } else {
                                    System.out.println("Invalid input. Please enter a positive number.");
                                }
                            }

                            // if method returns true, it sucessfully removed, otherwise, it failed to delete the student
                            if (University.removeStudent(studentID)) {
                                System.out.println("Student was removed!");
                            }
                            else {
                                System.out.println("Student was not found");
                            }

                            numInput = 0;
                            continue;
                        }
                        // Admin log out
                        else if (numInput == 6) {
                            numInput = 0;
                            stage = 0;
                            break;
                        }

                    }
                }

                // if admin fails to enter password
                System.out.println("Login failed! Incorrect password");
                numInput = 0;
                continue;
            }
            else if (numInput == 3) {
                System.out.println("\n\nHere's the general basis of the program:\n\nWhen the program first starts, there will be ZERO courses created, meaning the Admin will have to first make the course in order for students to enroll in them.\nCourse IDs and Student IDs are very important!\nIn order for students to login, they need their student IDs, so they will need to remember them when given.\nStudents can only enroll in courses or remove courses using the Course ID, so they will need to first check the course ID by displaying all the courses and seeing the course ID from there.\nCourse ID is also needed for Admin to delete courses too.\nSearching for courses are also based on Course ID");
                numInput = 0;
                continue;
            }
            // Exit program
            else if (numInput == 4) {
                numInput = 0;
                stage = -1;
                break;
            }
        }

        System.out.println("\n\nGoodbye!");
    }
}
