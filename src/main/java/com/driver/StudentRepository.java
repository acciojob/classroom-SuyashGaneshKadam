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
    HashMap<String, List<Teacher>> studentTeacherDb = new HashMap<>();

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

    public void setStudentDb(HashMap<String, Student> studentDb) {
        this.studentDb = studentDb;
    }

    public void setTeacherDb(HashMap<String, Teacher> teacherDb) {
        this.teacherDb = teacherDb;
    }

    public void setTeacherStudentDb(HashMap<String, List<Student>> teacherStudentDb) {
        this.teacherStudentDb = teacherStudentDb;
    }

    public void setStudentTeacherDb(HashMap<String, List<Teacher>> studentTeacherDb) {
        this.studentTeacherDb = studentTeacherDb;
    }

    public HashMap<String, List<Student>> getTeacherStudentDb()
    {
        return teacherStudentDb;
    }
    public HashMap<String, List<Teacher>> getStudentTeacherDb()
    {
        return studentTeacherDb;
    }
    public void addStudentTeacherPair(String teacherName, String studentName){
        if(!studentDb.containsKey(studentName) || !teacherDb.containsKey(teacherName)) return;
        Student student = studentDb.get(studentName);
        Teacher teacher = teacherDb.get(teacherName);
        List<Student> list = new ArrayList<>();
        List<Student> students = teacherStudentDb.getOrDefault(teacherName, new ArrayList<>());
        List<Teacher> teachers = studentTeacherDb.getOrDefault(studentName, new ArrayList<>());
        if(students.contains(student) || teachers.contains(teacher)) return;
        teacher.setNumberOfStudents(teacher.getNumberOfStudents()+1);
        students.add(student);
        teachers.add(teacher);
        teacherStudentDb.put(teacherName, students);
        studentTeacherDb.put(studentName, teachers);
        teacherDb.put(teacherName, teacher);
    }
}
