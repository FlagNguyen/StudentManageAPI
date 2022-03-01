package itsol.com.StudentManageAPI.Controller;

import itsol.com.StudentManageAPI.DAO.Entity.STUDENTS;
import itsol.com.StudentManageAPI.DTO.Reponse.StudentRespone;
import itsol.com.StudentManageAPI.DTO.Request.SearchingRequest;
import itsol.com.StudentManageAPI.DTO.Request.StudentRequest;
import itsol.com.StudentManageAPI.Service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/Students")
public class StudentController {

    @Autowired
    StudentService service;

    @GetMapping
    public ResponseEntity getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getStudentByCode(@PathVariable int id) {
        return service.getStudentByCode(id);
    }

    @PostMapping(path = "")
    public ResponseEntity addStudent(@RequestBody StudentRequest students) {
        return service.addStudent(students);
    }

    @PutMapping(path = "/{id}")
    public List<STUDENTS> updateStudent(@RequestBody STUDENTS students, @PathVariable int id) {
        return service.updateStudent(students, id);
    }

    @DeleteMapping(path = "/{id}")
    public List<STUDENTS> deleteStudent(@PathVariable int id) {
        return service.deleteStudent(id);
    }

    @PostMapping(path = "/search")
    public List<STUDENTS> searchStudents(@RequestBody SearchingRequest sRequest) {
        return service.searchStudents(sRequest);
    }

    @GetMapping(path = "/birthday")
    public List<STUDENTS> getBirthdayStudent() {
        return service.getBirthdayStudent();
    }
}
