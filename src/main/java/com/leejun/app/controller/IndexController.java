package com.leejun.app.controller;

import com.leejun.app.domain.Student;
import com.leejun.app.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {
    private final StudentRepository studentRepository;

    public IndexController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @GetMapping("/")
    public String index(Model model) {
        Iterable<Student> students = studentRepository.findAll();

        model.addAttribute("students", students);
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(Student student){
        return "add-student";
    }

    @PostMapping("/add")
    public String addStudent(Student student){
        studentRepository.save(student);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id")Long id,Model model){
        Student student = studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid student Id:" +id));
        model.addAttribute("student",student);
        return "update-student";
    }
}