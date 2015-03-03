package Algorithms.algorithm.interviews.ea.epic;

import java.util.HashMap;
import java.util.HashSet;

public class Register {
    static HashMap<Integer, Course> courses = new HashMap<Integer, Course>();
    static HashMap<Integer, Student> students = new HashMap<Integer, Student>();

    private static class Course {
        int id;
        int capcity;
        int time;

        HashSet<Integer> students = new HashSet<Integer>();

        public Course(int id, int capcity, int time) {
            super();
            this.id = id;
            this.capcity = capcity;
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCapcity() {
            return capcity;
        }

        public void setCapcity(int capcity) {
            this.capcity = capcity;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }

    private static class Student {
        HashSet<Course> courses = new HashSet<Course>();
        HashSet<Integer> timeSlot = new HashSet<Integer>();

        int id;
        int maxCourse;

        public Student(int id, int maxCourse) {
            super();
            this.id = id;
            this.maxCourse = maxCourse;
        }
    }

    public static String addClass(int id, int capacity, int time) {
        // If the class is added successfully,
        // return "Successfully added class ID".
        // Otherwise, return "Error adding class ID".
        if (courses.containsKey(id)) {
            return "Error adding class ID";
        }

        courses.put(id, new Course(id, capacity, time));
        return "Successfully added class " + id;
    }

    public String removeClass(int id) {
        // If the class is removed successfully,
        // return "Successfully removed class ID".
        // Otherwise, return "Error removing class ID".

        if (!courses.containsKey(id)) {
            return "Error removing class ID";
        }

        Course course = courses.get(id);
        for (Integer stuId : course.students) {
            Student student = students.get(stuId);
            if (student != null) {
                student.courses.remove(id);
            }
        }
        
        courses.remove(id);
        return "Successfully removed class ID";
    }

    public String infoClass(int id) {
        // If the class does not
        // return "Class ID does
        // If the class is empty,
        // return "Class ID is empty".
        // Otherwise, return the string
        // "Class ID has the following students: LIST"
        // where LIST is a sorted, comma-separated list
        // of student IDs corresponding to students currently
        // in the class.
        Course course = courses.get(id);
        if (course == null) {
            return "Class " + id + " does not exist";
        }
        
        if (course.students.isEmpty()) {
            return "Class " + id + " is empty";
        }
    }

    public String addStudent(int id, int capacity, int start, int end) {
        // If the student is added successfully,
        // return "Successfully added student ID".
        // Otherwise, return "Error adding student ID".
    }

    public String removeStudent(int id) {
        // If the student is removed successfully,
        // return "Successfully removed student ID".
        // Otherwise, return "Error removing student ID".

    }

    public String infoStudent(int id) {
        // If the student does not
        // return "Student ID does
        // If the student is not taking any classes,
        // return "Student ID is not taking any classes".
        // Otherwise, return the string
        // "Student ID is taking the following classes: LIST"
        // where LIST is a sorted, comma-separated list of class IDs
        // corresponding to classes that the student is
        // currently taking.
    }

    public String enrollStudent(int studentId, int classId) {

    }

    public String unenrollStudent(int studentId, int classId) {
    }
}
