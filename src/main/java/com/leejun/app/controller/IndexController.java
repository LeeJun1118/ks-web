package com.leejun.app.controller;

import com.leejun.app.domain.Person;
import com.leejun.app.repository.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    private final PersonRepository personRepository;

    public IndexController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        Person person = personRepository.findAll().stream().findFirst().orElse(null);
        model.addAttribute("person",person);
        return "resume";
    }

   /* private final StudentRepository studentRepository;

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

    @PostMapping("/update/{id}")
    public String updateStudent(Student student){
        studentRepository.save(student);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id,Model model){
        Student student = studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid student Id:"+id));
        studentRepository.delete(student);
        model.addAttribute("students",studentRepository.findAll());
        return "redirect:/";
    }*/
}