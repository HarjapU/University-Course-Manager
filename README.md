# University-Course-Manager
Author: Harjap Uppal

The University Course Manager is an academic project made using Java and its built-in console

The goal with this project is to create a University Course Management System that allows students to register, enroll courses, etc. Student class represents a student in this system, they have arraylists of enrolled courses and each student has a special ID tied to their Student Account, and they have a Comparable to correctly sort students. Course class is a representation of a generic course in the system, containing an arraylist of registered students with a maximum capacity, unique Course ID for each course, and comparable to sort all types of courses correctly. InpersonCourse class is a subclass of course that represents an In-Person course in the system, with a In-Perons classroom location and functionality to enroll and drop students in In-Person courses. OnlineCourse is also a subclass of course to represent a Online course in the system, with a Online classroom location (Zoom, Teams, etc.) and functionality to enroll and drop students in Online Courses. University class acts as the system manager that keeps arraylists to manage all students, In Person and Online courses as well as searching for students or classes and displaying all information related the system as a whole, all with static methods and variables. 

Some things to note when usins this program:
- When the program first starts, there will be ZERO courses created, meaning the Admin will have to first make the course in order for students to enroll in them.
- Course IDs and Student IDs are very important!
- In order for students to login, they need their student IDs, so they will need to remember them when given. 
- Students can only enroll in courses or remove courses using the Course ID, so they will need to first check the course ID by displaying all the courses and seeing the course ID from there. 
- Course ID is also needed for Admin to delete courses too.
- Searching for courses are also based on Course ID
