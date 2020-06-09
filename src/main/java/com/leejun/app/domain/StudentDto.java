package com.leejun.app.domain;


import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

//getter,setter,hashAnd~,NoArgu~,AllArgu~ 다 해줌
@Data //@Entity와는 함께 쓰지 않는다.

@Builder
public class StudentDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @NotEmpty
    private String phoneNo;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;
}
