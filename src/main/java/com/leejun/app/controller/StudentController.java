package com.leejun.app.controller;

import com.leejun.app.domain.Student;
import com.leejun.app.domain.StudentDto;
import com.leejun.app.domain.StudentDtoValid;
import com.leejun.app.domain.StudentResource;
import com.leejun.app.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/students", produces = MediaTypes.HAL_JSON_VALUE)
public class StudentController {

    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    private final StudentDtoValid studentDtoValid;

    public StudentController(StudentRepository studentRepository, ModelMapper modelMapper, StudentDtoValid studentDtoValid) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
        this.studentDtoValid = studentDtoValid;
    }

    @PostMapping
    public ResponseEntity createStudent(@RequestBody @Valid StudentDto studentDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        studentDtoValid.validate(studentDto, errors);

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Student student = modelMapper.map(studentDto, Student.class);
        Student savedStudent = studentRepository.save(student);

        WebMvcLinkBuilder linkBuilder = linkTo(StudentController.class).slash(savedStudent.getId());
        // 링크를 헤더에 걸어준다. [Location:"http://localhost/api/students/100", ....] 이런식으로
        URI uri = linkBuilder.toUri();

        StudentResource studentResource = new StudentResource(savedStudent);
        studentResource.add(linkBuilder.withSelfRel());
        studentResource.add(linkTo(StudentController.class).withRel("query-student"));

        //반환할 때 uri 와 내용을 함께 반환한다.
        return ResponseEntity.created(uri).body(studentResource);
    }
}
