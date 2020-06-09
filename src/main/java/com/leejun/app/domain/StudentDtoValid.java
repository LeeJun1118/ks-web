package com.leejun.app.domain;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class StudentDtoValid {

    public void validate(StudentDto studentDto, Errors errors){
        if(studentDto.getName().matches("^\\s*$")){
            errors.rejectValue("name","wrongValue","Wrong Name");
        }
    }
}
