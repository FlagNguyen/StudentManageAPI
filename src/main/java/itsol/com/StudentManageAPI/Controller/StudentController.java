package itsol.com.StudentManageAPI.Controller;

import itsol.com.StudentManageAPI.DTO.Request.SearchingRequest;
import itsol.com.StudentManageAPI.DTO.Request.StudentRequest;
import itsol.com.StudentManageAPI.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Students")
public class StudentController {

    @Autowired
    StudentService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
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
    public ResponseEntity updateStudent(@RequestBody StudentRequest students, @PathVariable int id) {
        return service.updateStudent(students, id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteStudent(@PathVariable int id) {
        return service.deleteStudent(id);
    }

    @GetMapping(path = "/search")
    public ResponseEntity searchStudents(@RequestBody SearchingRequest sRequest) {
        return service.searchStudents(sRequest);
    }

    @GetMapping(path = "/birthday")
    public ResponseEntity getBirthdayStudent() {
        return service.getBirthdayStudent();
    }
}
