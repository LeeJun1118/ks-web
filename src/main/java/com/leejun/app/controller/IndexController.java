package com.leejun.app.controller;

import com.leejun.app.domain.Student;
import com.leejun.app.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final StudentRepository studentRepository;

    public IndexController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        Iterable<Student> students = studentRepository.findAll();

        model.addAttribute("students",students);
        return "index";
    }
}
