package com.example.crudTest.services;

import com.example.crudTest.entities.Student;
import com.example.crudTest.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        return savedStudent;
    }

    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students;
    }

    public Optional<Student> getStudent(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        return studentOptional;
    }

    public Optional<Student> updateStudente(Long id, Student student){
        Optional<Student> studenteDaAggiornare = studentRepository.findById(id);
        if (studenteDaAggiornare.isPresent()){
            studenteDaAggiornare.get().setName(student.getName());
            studenteDaAggiornare.get().setSurname(student.getSurname());
            studentRepository.save(studenteDaAggiornare.get());
        }
        return studenteDaAggiornare;
    }


    public Optional<Student> deleteStudent(Long id){
        Optional<Student> studentToDelete = studentRepository.findById(id);
        if (studentToDelete.isPresent()){
            studentRepository.deleteById(id);
        }
        return studentToDelete;
    }


    public Optional<Student> updateIsWorking(Long id, boolean working) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            studentOptional.get().setWorking(working);
            studentRepository.save(studentOptional.get());
        }
        return studentOptional;
    }

}