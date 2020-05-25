package com.leejun.app.controller;

import com.leejun.app.domain.Student;
import com.leejun.app.repository.StudentRepository;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/students", produces = MediaTypes.HAL_JSON_VALUE)
public class StudentController {

    private StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public ResponseEntity createStudent(@RequestBody Student student){
        Student savedStudent = studentRepository.save(student);

        // 링크를 헤더에 걸어준다. [Location:"http://localhost/api/students/100", ....] 이런식으로
       URI uri = linkTo(StudentController.class).slash(student.getId()).toUri();

       //반환할 때 uri 와 내용을 함께 반환한다.
        return ResponseEntity.created(uri).body(savedStudent);
    }
}
