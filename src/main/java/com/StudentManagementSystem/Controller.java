package com.StudentManagementSystem;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.StudentManagementSystem.entity.Student;
import com.StudentManagementSystem.service.StudentService;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private StudentService service;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/students")
    public String getAllStudents(Model model) {
        List<Student> students = service.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/student/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "add_student";
    }

    @PostMapping("/student/new")
    @ResponseBody
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        service.save(student);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/student/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Student student = service.getStudentById(id);
        model.addAttribute("student", student);
        return "edit_student";
    }

    @PostMapping("/student/edit/{id}")
    @ResponseBody
    public ResponseEntity<?> updateStudent(@PathVariable int id, @RequestBody Student updated) {
        Student existing = service.getStudentById(id);
        if (existing != null) {
            existing.setFirstname(updated.getFirstname());
            existing.setLastName(updated.getLastName());
            existing.setEmail(updated.getEmail());
            service.save(existing);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/student/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
        service.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }
}
