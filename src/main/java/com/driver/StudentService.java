package com.driver;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Service
public class StudentService {

    StudentRepository studentRepository = new StudentRepository();

    public void addStudent(Student student){
        studentRepository.addStudent(student);
    }
    public void addTeacher(Teacher teacher){
        studentRepository.addTeacher(teacher);
    }
    public void addStudentTeacherPair(String teacherName, String studentName){
        studentRepository.addStudentTeacherPair(teacherName, studentName);
    }
    public Student getStudentByName(String studentName){
        HashMap<String, Student> studentDb = studentRepository.getStudentDb();
        for(Student student : studentDb.values()){
            if(student.getName().equals(studentName)){
                return student;
            }
        }
        return null;
    }
    public Teacher getTeacherByName(String teacherName){
        HashMap<String, Teacher> teacherDb = studentRepository.getTeacherDb();
        for(Teacher teacher : teacherDb.values()){
            if(teacher.getName().equals(teacherName)){
                return teacher;
            }
        }
        return null;
    }
    public List<String> getStudentsByTeacherName(String teacherName){
        HashMap<String, List<Student>> teacherStudentDb = studentRepository.getTeacherStudentDb();
        List<String> studentNames = new ArrayList<>();
        if(teacherStudentDb.size() == 0 || !teacherStudentDb.containsKey(teacherName)){
            return studentNames;
        }
        List<Student> students = teacherStudentDb.get(teacherName);
        for(Student student : students){
            studentNames.add(student.getName());
        }
        return studentNames;
    }
    public List<String> getAllStudents(){
        HashMap<String, Student> studentDb = studentRepository.getStudentDb();
        List<String> studentNames = new ArrayList<>();
        if(studentDb.size() == 0){
            return studentNames;
        }
        for(Student student : studentDb.values()){
            studentNames.add(student.getName());
        }
        return studentNames;
    }
    public void deleteTeacherByName(@RequestParam String teacherName){
        studentRepository.deleteTeacherByName(teacherName);
    }
    public void deleteAllTeachers(){
        studentRepository.deleteAllTeachers();
    }
}
