package Algorithms.algorithm.interviews.ea.epic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class Register {
    static HashMap<Integer, Course> courses = new HashMap<Integer, Course>();
    static HashMap<Integer, Student> students = new HashMap<Integer, Student>();

    private static class Course {
        int id;
        int remainCapcity;
        int time;

        TreeSet<Integer> students = new TreeSet<Integer>();

        public Course(int id, int capcity, int time) {
            super();
            this.id = id;
            this.remainCapcity = capcity;
            this.time = time;
        }
    }

    private static class Student {
        HashSet<Course> courses = new HashSet<Course>();
        HashSet<Integer> timeSlots = new HashSet<Integer>();

        int id;
        int remainCourseSlots;

        public Student(int id, int remainCourseSlots) {
            super();
            this.id = id;
            this.remainCourseSlots = remainCourseSlots;
        }
    }

    public static String addClass(int id, int capacity, int time) {
        // If the class is added successfully,
        // return "Successfully added class ID".
        // Otherwise, return "Error adding class ID".
        if (courses.containsKey(id)) {
            return "Error adding class " + id;
        }

        courses.put(id, new Course(id, capacity, time));
        return "Successfully added class " + id;
    }

    public String removeClass(int id) {
        // If the class is removed successfully,
        // return "Successfully removed class ID".
        // Otherwise, return "Error removing class ID".

        if (!courses.containsKey(id)) {
            return "Error removing class " + id;
        }

        Course course = courses.get(id);
        for (Integer stuId : course.students) {
            Student student = students.get(stuId);
            if (student != null) {
                student.courses.remove(id);
            }
        }
        
        courses.remove(id);
        return "Successfully removed class " + id;
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
        
        StringBuilder studList = new StringBuilder();
        for (Integer student: course.students) {
            studList.append(student + ",");
        }
        
        studList.deleteCharAt(studList.length() - 1);
        return "Class " + id + " has the following students: " + studList.toString();
    }

    public String addStudent(int id, int capacity, int start, int end) {
        // If the student is added successfully,
        // return "Successfully added student ID".
        // Otherwise, return "Error adding student ID".
        if (students.containsKey(id)) {
            return "Error adding student " + id;
        }
        
        Student student = new Student(id, capacity);
        HashSet<Integer> timeSlots = student.timeSlots;
        for (int i = start; i <= end; i++) {
            timeSlots.add(i);
        }
        
        return "Successfully added student " + id;
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
        // If enrollment of the student in the class succeeded,
        // return "Number of free spots left in class CLASSID: FREESPOTS" 
        // where FREESPOTS is the number of free spots left 
        // in the class after the student enrolls. 
        // Otherwise, return "Enrollment of student STUDENTID in class CLASSID failed".
        String errorString = "Enrollment of student " + studentId + " in class " + classId + " failed";
        if (!students.containsKey(studentId) 
            || !courses.containsKey(classId)
            ) {
            return errorString;
        }
        
        Student student = students.get(studentId);
        Course course = courses.get(classId);
        if (student.courses.contains(classId)
            || student.remainCourseSlots <= 0
            || course.remainCapcity <= 0
            || !student.timeSlots.contains(course.time)) {
            return errorString;
        }
        
        course.remainCapcity--;
        student.remainCourseSlots--;
        student.timeSlots.remove(course.time);
        
        return "Number of free spots left in class " + classId + ": " + course.remainCapcity;
    }

    public String unenrollStudent(int studentId, int classId) {
    }
}
