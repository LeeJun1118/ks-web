package com.leejun.app.controller;

import com.leejun.app.domain.*;
import com.leejun.app.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    private final PersonRepository personRepository;
    private final SocialMediaRepository socialMediaRepository;
    private final InterestsRepository interestsRepository;
    private final SkillRepository skillRepository;
    private final WorkRepository workRepository;

    public IndexController(PersonRepository personRepository, SocialMediaRepository socialMediaRepository, InterestsRepository interestsRepository, SkillRepository skillRepository, WorkRepository workRepository) {
        this.personRepository = personRepository;
        this.socialMediaRepository = socialMediaRepository;
        this.interestsRepository = interestsRepository;
        this.skillRepository = skillRepository;
        this.workRepository = workRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        Person person = personRepository.findAll().stream().findFirst().orElse(null);
        model.addAttribute("person",person);

        SocialMedia socialMedia = socialMediaRepository.findAll().stream().findFirst().orElse(null);
        model.addAttribute("socialMedia",socialMedia);

        List<Interests> interests = interestsRepository.findAll();
        model.addAttribute("interests",interests);

        List<Skill> skills = skillRepository.findAll();
        model.addAttribute("skills",skills);

        List<Work> works = workRepository.findAll();
        model.addAttribute("works",works);

        return "resume";
    }

   /* 과제 1.

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