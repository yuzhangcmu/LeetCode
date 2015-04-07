//package Algorithms.algorithm.interviews.dropbox;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
//import com.opencsv.CSVReader;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.TreeSet;
//
//import com.opencsv.CSVReader;
//
//// Java reflection: http://www.360doc.com/content/06/0829/08/73_193230.shtml
//
//public class Register {
//    static HashMap<Integer, Course> courses = new HashMap<Integer, Course>();
//    static HashMap<Integer, Student> students = new HashMap<Integer, Student>();
//
//    private static void test(String fileName) throws NumberFormatException, IOException {
//        CSVReader reader = new CSVReader(new FileReader(fileName), ' ');
//        String[] nextLine;
//
//        boolean firstLine = true;
//
//        while ((nextLine = reader.readNext()) != null) {
//            if (firstLine) {
//                firstLine = false;
//                continue;
//            }
//            Object[] args = new Object[nextLine.length - 1];
//
//            // nextLine[] is an array of values from the line
//            //System.out.println("Length: " + nextLine.length);
//
//            for (int i = 1; i < nextLine.length; i++) {
//                //System.out.println(nextLine[i]);
//                args[i - 1] = Integer.parseInt(nextLine[i]);
//            }
//
//            // Call the function.
//            try {
//                System.out.println(invokeMethod("Algorithms.algorithm.interviews.dropbox.Register", nextLine[0], args));
//            } catch (NoSuchMethodException | SecurityException
//                    | IllegalAccessException | IllegalArgumentException
//                    | InvocationTargetException | ClassNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static Object invokeMethod (String className, String methodName, Object[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
//       //Class ownerClass = owner.getClass();
//        Class ownerClass = Class.forName(className);
//        Class[] argClass = new Class[args.length];
//
//        for(int i = 0, j = args.length; i < j; i++) {
//            argClass[i] = args[i].getClass();
//        }
//
//        HashMap<String, String> map = new HashMap<String, String>();
//
//        map.put("ADDCLASS", "addClass");
//        map.put("INFOCLASS", "infoClass");
//        map.put("REMOVECLASS", "removeClass");
//        map.put("ADDSTUDENT", "addStudent");
//        map.put("ENROLLSTUDENT", "enrollStudent");
//        map.put("INFOSTUDENT", "infoStudent");
//        map.put("UNENROLLSTUDENT", "unenrollStudent");
//        map.put("REMOVESTUDENT", "removeStudent");
//
//
//        if (map.get(methodName) != null) {
//            methodName = map.get(methodName);
//        }
//
//        Method method = ownerClass.getMethod(methodName, argClass);
//
//        return method.invoke(null, args);
//    }
//
//    public static void main(String[] strs) {
//        try {
//            String testFile = "input017.txt";
//            System.out.println(testFile);
//            test(testFile);
//            System.out.println();
//        } catch (NumberFormatException | IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
////        System.out.println(addClass(1, 1, 1));
////        System.out.println(addStudent(1, 1, 1, 1));
////        System.out.println(removeClass(1));
////        System.out.println(addClass(2, 1, 1));
////        System.out.println(enrollStudent(1, 2));
//
//        // test 2
////        System.out.println(addStudent(1, 1, 1, 1));
////        System.out.println(addClass(1, 1, 1));
////        System.out.println(enrollStudent(1, 1));
////
////        System.out.println(infoStudent(1));
////        System.out.println(removeClass(1));
////        System.out.println(infoStudent(1));
////
////        // test 3
////        System.out.println(addClass(1, 1, 1));
////
////        System.out.println(enrollStudent(1, 1));
////
////        System.out.println(infoClass(1));
////
////        System.out.println(removeStudent(1));
////
////        System.out.println(infoClass(1));
////        System.out.println(addClass(1, 1, 1));
//
////        System.out.println(addClass(1, 1, 1));
////        System.out.println(addStudent(1, 1, 1, 1));
////        System.out.println(enrollStudent(1, 1));
////        System.out.println(removeStudent(1));
////        System.out.println(infoClass(1));
////        System.out.println(addStudent(1, 1, 1, 1));
////        System.out.println(infoClass(1));
////        System.out.println(enrollStudent(1, 1));
////
////        System.out.println(addClass(1, 1, 1));
////        System.out.println(addStudent(1, 1, 1, 1));
////        System.out.println(enrollStudent(1, 1));
////        System.out.println(removeClass(1));
////        System.out.println(addClass(2, 1, 1));
////        System.out.println(enrollStudent(1, 2));
//
//        //System.out.println(addStudent(987654321, 123456789, 1, 999999999));
//        //System.out.println(addClass(999999999, 912345678, 853945));
//    }
//
//    private static class Course {
//        int remainCapacity;
//        int time;
//
//        TreeSet<Integer> students = new TreeSet<Integer>();
//
//        public Course(int capcity, int time) {
//            super();
//            this.remainCapacity = capcity;
//            this.time = time;
//        }
//    }
//
//    private static class Student {
//        TreeSet<Integer> courses = new TreeSet<Integer>();
//        HashSet<Integer> takeTimeSlots = new HashSet<Integer>();
//
//        int capacity;
//        int startTime;
//        int endTime;
//
//        public Student(int capacity, int startTime, int endTime) {
//            super();
//            this.capacity = capacity;
//            this.startTime = startTime;
//            this.endTime = endTime;
//        }
//    }
//
//    public static String addClass(Integer id, Integer capacity, Integer time) {
//        // If the class is added successfully,
//        // return "Successfully added class ID".
//        // Otherwise, return "Error adding class ID".
//        if (courses.containsKey(id)) {
//            return "Error adding class " + id;
//        }
//
//        courses.put(id, new Course(capacity, time));
//        return "Successfully added class " + id;
//    }
//
//    public static String removeClass(Integer id) {
//        // If the class is removed successfully,
//        // return "Successfully removed class ID".
//        // Otherwise, return "Error removing class ID".
//
//        if (!courses.containsKey(id)) {
//            return "Error removing class " + id;
//        }
//
//        Course course = courses.get(id);
//        for (Integer stuId : course.students) {
//            Student student = students.get(stuId);
//            if (student != null) {
//                student.courses.remove(id);
//
//                // Free the time slot
//                student.takeTimeSlots.remove(course.time);
//                student.capacity++;
//            }
//        }
//
//        courses.remove(id);
//        return "Successfully removed class " + id;
//    }
//
//    public static String infoClass(Integer id) {
//        // If the class does not
//        // return "Class ID does
//        // If the class is empty,
//        // return "Class ID is empty".
//        // Otherwise, return the string
//        // "Class ID has the following students: LIST"
//        // where LIST is a sorted, comma-separated list
//        // of student IDs corresponding to students currently
//        // in the class.
//        Course course = courses.get(id);
//        if (course == null) {
//            return "Class " + id + " does not exist";
//        }
//
//        if (course.students.isEmpty()) {
//            return "Class " + id + " is empty";
//        }
//
//        StringBuilder studList = new StringBuilder();
//        for (Integer student : course.students) {
//            studList.append(student + ",");
//        }
//
//        studList.deleteCharAt(studList.length() - 1);
//        return "Class " + id + " has the following students: "
//                + studList.toString();
//    }
//
//    public static String addStudent(Integer id, Integer capacity, Integer start, Integer end) {
//        // If the student is added successfully,
//        // return "Successfully added student ID".
//        // Otherwise, return "Error adding student ID".
//        if (students.containsKey(id)) {
//            return "Error adding student " + id;
//        }
//
//        Student student = new Student(capacity, start, end);
//
//        students.put(id, student);
//
//        return "Successfully added student " + id;
//    }
//
//    public static String removeStudent(Integer id) {
//        // If the student is removed successfully,
//        // return "Successfully removed student ID".
//        // Otherwise, return "Error removing student ID".
//        if (!students.containsKey(id)) {
//            return "Error removing student " + id;
//        }
//
//        // unenroll that student from all of his/her enrolled classes
//        Student student = students.get(id);
//        for (Integer courseId : student.courses) {
//            Course course = courses.get(courseId);
//            if (course != null) {
//                // unenroll that student.
//                course.students.remove(id);
//
//                // increase the capacity of that class.
//                course.remainCapacity++;
//            }
//        }
//
//        students.remove(id);
//        return "Successfully removed student " + id;
//    }
//
//    public static String infoStudent(Integer id) {
//        // If the student does not
//        // return "Student ID does
//        // If the student is not taking any classes,
//        // return "Student ID is not taking any classes".
//        // Otherwise, return the string
//        // "Student ID is taking the following classes: LIST"
//        // where LIST is a sorted, comma-separated list of class IDs
//        // corresponding to classes that the student is
//        // currently taking.
//        if (!students.containsKey(id)) {
//            return "Student " + id + " does not exist";
//        }
//
//        Student student = students.get(id);
//        StringBuilder courseList = new StringBuilder();
//        for (Integer courseId : student.courses) {
//            courseList.append(courseId + ",");
//        }
//
//        if (courseList.length() == 0) {
//            return "Student " + id + " is not taking any classes";
//        }
//
//        courseList.deleteCharAt(courseList.length() - 1);
//        return "Student " + id + " is taking the following classes: "
//                + courseList.toString();
//    }
//
//    public static String enrollStudent(Integer studentId, Integer classId) {
//        // If enrollment of the student in the class succeeded,
//        // return "Number of free spots left in class CLASSID: FREESPOTS"
//        // where FREESPOTS is the number of free spots left
//        // in the class after the student enrolls.
//        // Otherwise, return
//        // "Enrollment of student STUDENTID in class CLASSID failed".
//        String errorString = "Enrollment of student " + studentId
//                + " in class " + classId + " failed";
//        if (!students.containsKey(studentId) || !courses.containsKey(classId)) {
//            return errorString;
//        }
//
//        Student student = students.get(studentId);
//        Course course = courses.get(classId);
//        if (course.remainCapacity <= 0) {
//            return "Number of free spots left in class " + classId + ":0";
//        }
//
//        if (student.courses.contains(classId)
//                || student.capacity == 0
//                || student.takeTimeSlots.contains(course.time)
//                || (course.time < student.startTime || course.time > student.endTime)
//                ) {
//            return errorString;
//        }
//
//        course.remainCapacity--;
//        course.students.add(studentId);
//
//        student.takeTimeSlots.add(course.time);
//        student.courses.add(classId);
//        student.capacity--;
//
//        return "Number of free spots left in class " + classId + ": "
//                + course.remainCapacity;
//    }
//
//    public static String unenrollStudent(Integer studentId, Integer classId) {
//        String errorString = "Unenrollment of student " + studentId
//                + " in class " + classId + " failed";
//        if (!students.containsKey(studentId) || !courses.containsKey(classId)) {
//            return errorString;
//        }
//
//        Student student = students.get(studentId);
//        Course course = courses.get(classId);
//        if (student.courses.contains(classId)) {
//            // remove the course from the student's enroll course.
//            student.courses.remove(classId);
//
//            // add back the time of the class to the student.
//            student.takeTimeSlots.remove(course.time);
//            student.capacity++;
//
//            course.students.remove(studentId);
//
//            // increase the capacity of the course by one.
//            course.remainCapacity++;
//        } else {
//            return errorString;
//        }
//
//        return "Number of free spots left in class " + classId + ": " + course.remainCapacity;
//    }
//}
