package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    HashMap<String, Student> studentDb = new HashMap<>();
    HashMap<String, Teacher> teacherDb = new HashMap<>();
    HashMap<String, List<Student>> teacherStudentDb = new HashMap<>();

    public void addStudent(Student student){
        studentDb.put(student.getName(), student);
    }
    public void addTeacher(Teacher teacher){
        teacherDb.put(teacher.getName(), teacher);
    }
    public HashMap<String, Student> getStudentDb()
    {
        return studentDb;
    }
    public HashMap<String, Teacher> getTeacherDb() { return teacherDb; }

    public void setTeacherDb(HashMap<String, Teacher> teacherDb) { this.teacherDb = teacherDb; }
    public void setTeacherStudentDb(HashMap<String, List<Student>> teacherStudentDb) { this.teacherStudentDb = teacherStudentDb; }
    public HashMap<String, List<Student>> getTeacherStudentDb()
    {
        return teacherStudentDb;
    }
    public void addStudentTeacherPair(String teacherName, String studentName){
        if(!studentDb.containsKey(studentName) || !teacherDb.containsKey(teacherName)) return;
        Student student = studentDb.get(studentName);
        Teacher teacher = teacherDb.get(teacherName);
        List<Student> students = teacherStudentDb.getOrDefault(teacherName, new ArrayList<>());
        if(students.contains(student)) return;
        teacher.setNumberOfStudents(teacher.getNumberOfStudents()+1);
        students.add(student);
        teacherStudentDb.put(teacherName, students);
        teacherDb.put(teacherName, teacher);
    }
}
