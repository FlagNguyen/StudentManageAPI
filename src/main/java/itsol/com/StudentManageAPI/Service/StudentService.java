package itsol.com.StudentManageAPI.Service;

import itsol.com.StudentManageAPI.Controller.StudentController;
import itsol.com.StudentManageAPI.DAO.Entity.STUDENTS;
import itsol.com.StudentManageAPI.DAO.Repository.StudentRepository;
import itsol.com.StudentManageAPI.DTO.Reponse.ErrorResponse;
import itsol.com.StudentManageAPI.DTO.Reponse.StudentRespone;
import itsol.com.StudentManageAPI.DTO.Request.SearchingRequest;
import itsol.com.StudentManageAPI.DTO.Request.StudentRequest;
import itsol.com.StudentManageAPI.Utility.DataUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    private static final Logger LOGGER = LogManager.getLogger(StudentController.class);

    DataUtil dataUtil = new DataUtil();

    public ResponseEntity getAll() {
        List<STUDENTS> students = studentRepository.getAll();
        List<StudentRespone> studentRespones = new ArrayList<>();
        for (STUDENTS s : students) {
            studentRespones.add(new StudentRespone(s.getId(), s.getName(), s.getDob().toString(), s.getGender(), s.getClassName(), s.getMajor(), s.getHometown(), s.getAvgMark()));
        }
        if (students.isEmpty()) {
            ErrorResponse response = new ErrorResponse();
            response.setErrorCode("000");
            response.setMessage("resource not found");
            LOGGER.error("Resource not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            LOGGER.info("Get all successfully");
            return new ResponseEntity<>(studentRespones, HttpStatus.OK);
        }
    }

    public ResponseEntity getStudentByCode(int id) {
        List<STUDENTS> s = studentRepository.getStudentByCode(id);
        STUDENTS s0 = s.get(0);
        StudentRespone sp = new StudentRespone(s0.getId(), s0.getName(), s0.getDob().toString(), s0.getGender(),
                s0.getClassName(), s0.getMajor(), s0.getHometown(), s0.getAvgMark());
        if (s.isEmpty()) {
            ErrorResponse response = new ErrorResponse();
            response.setErrorCode("404");
            response.setMessage("Not found this id");
            LOGGER.error("Not found this id");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            LOGGER.log(Level.INFO, "Get Student By Code Successfully");
            return new ResponseEntity<>(sp, HttpStatus.OK);
        }
    }

    public ResponseEntity addStudent(StudentRequest students) {
        if (students.getName().isEmpty() || students.getGender().isEmpty() || students.getDob().toString().isEmpty() ||
                students.getClassName().isEmpty() || students.getMajor().isEmpty() || students.getHometown().isEmpty()) {
            ErrorResponse response = new ErrorResponse();
            response.setErrorCode("404");
            response.setMessage("Lack of attributes");
            LOGGER.error("Lack of attributes");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        students.setName(dataUtil.standardlizeString(students.getName()));
        students.setHometown(dataUtil.standardlizeString(students.getHometown()));
        studentRepository.addStudent(students);
        LOGGER.info("Add student successfully");
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    public ResponseEntity updateStudent(StudentRequest students, int id) {
        if (students.getName().isEmpty() || students.getGender().isEmpty() || students.getDob().toString().isEmpty() ||
                students.getClassName().isEmpty() || students.getMajor().isEmpty() || students.getHometown().isEmpty()) {
            ErrorResponse response = new ErrorResponse();
            response.setErrorCode("404");
            response.setMessage("Lack of attributes");
            LOGGER.error("Lack of attributes");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        students.setName(dataUtil.standardlizeString(students.getName()));
        students.setHometown(dataUtil.standardlizeString(students.getHometown()));
        studentRepository.updateStudent(students, id);
        LOGGER.info("Update student's id: " + id + " successfully");
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    public ResponseEntity deleteStudent(int id) {
        if (studentRepository.getStudentByCode(id).isEmpty()) {
            LOGGER.info("Already deleted");
            ErrorResponse response = new ErrorResponse();
            response.setErrorCode("003");
            response.setMessage("Already deleted");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        LOGGER.info("Delete Successfully");
        return new ResponseEntity<>(studentRepository.deleteStudent(id), HttpStatus.OK);
    }

    public ResponseEntity searchStudents(SearchingRequest sRequest) {
        if (sRequest.getSearchingAttributes().isEmpty() || sRequest.getSearchingMessage().isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setErrorCode("004");
            errorResponse.setMessage("Lack of attribute's searching request");
            LOGGER.error("Lack of attribute's searching request");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        List<STUDENTS> students = studentRepository.searchStudent(sRequest);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    public ResponseEntity getBirthdayStudent() {
        List<STUDENTS> students = studentRepository.getBirthdayStudent();
        if (students.isEmpty()) {
            ErrorResponse response = new ErrorResponse();
            response.setErrorCode("005");
            response.setMessage("Resource not found or no one have birthday today");
            LOGGER.error("Resource not found or no one have birthday today");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

}
