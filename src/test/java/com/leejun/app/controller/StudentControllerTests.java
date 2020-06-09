package com.leejun.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leejun.app.domain.Student;
import com.leejun.app.domain.StudentDto;
import com.leejun.app.domain.StudentStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    StudentDto studentDto = StudentDto.builder()
            .name("홍길동")
            .email("hong@test.com")
            .phoneNo("01025236303")
            .status(StudentStatus.ATTENDING)
            .build();

    @Test
    public void testCreateStudentHTTPCode() throws Exception {

        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(studentDto))
        )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateStudentJsonFormat() throws Exception {

        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(studentDto))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("email").exists())
                .andExpect(jsonPath("phoneNo").exists())
                .andExpect(jsonPath("status").exists());

    }

    @Test
    public void testCreateStudentHeader() throws Exception {

        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(studentDto))
        )
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("email").exists())
                .andExpect(jsonPath("phoneNo").exists())
                .andExpect(jsonPath("status").exists());
    }

    @Test
    public void testCreateStudentRepository() throws Exception {

        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(studentDto))
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("id").value(Matchers.not(100)));
    }

    @Test
    public void testCreateStudentInputValid() throws Exception {

        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(studentDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists());

    }

    @Test
    public void testCreateStudentBadRequest() throws Exception {
        Student badRequest = Student.builder()
                .id(100L)
                .name("홍길동")
                .email("hong@test.com")
                .phoneNo("01012345678")
                .status(StudentStatus.ATTENDING)
                .build();

        mockMvc.perform(post("/api/students/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(badRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testCreateStudentEmptyInputValid() throws Exception {
        StudentDto emptyStudentDto = StudentDto.builder().build();

        mockMvc.perform(post("/api/students/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(emptyStudentDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateStudentWrongInputValid() throws Exception {
        StudentDto emptyStudentDto = StudentDto.builder()
                .name("  ")
                .email("hong@test.com")
                .phoneNo("010-1234-5678")
                .build();

        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(emptyStudentDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testCreateStudentHATEOS() throws Exception {
        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(studentDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())

                //만들면 읽을 수 있는 url이 있는데 그걸 여기 담아준다.
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.query-student").exists());


    }
}
