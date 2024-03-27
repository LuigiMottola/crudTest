package com.example.crudTest.controllers;

import com.example.crudTest.entities.Student;
import com.example.crudTest.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        studentService.createStudent(student);
        return ResponseEntity.ok().body(student);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> allStudents =   studentService.getAllStudents();
        return ResponseEntity.ok().body(allStudents);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Student> findStudent(@PathVariable Long id){
        Optional<Student> studentOptional = studentService.getStudent(id);
        if (studentOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(studentOptional.get());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Student> updateStudentById(@RequestBody Student student,@PathVariable Long id){
        Optional<Student> studentOptional = studentService.updateStudente(id,student);
        if(studentOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(student);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id){
        Optional<Student> studentDaCancellare = studentService.deleteStudent(id);
        if (studentDaCancellare.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(studentDaCancellare.get());
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<Student> updateIsWorking(@PathVariable Long id, @RequestParam boolean working) {
        Optional<Student> studentDaModificare = studentService.updateIsWorking(id,working);
        if (studentDaModificare.isEmpty()){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(studentDaModificare.get());
    }
}