package itsol.com.StudentManageAPI.Service;

import itsol.com.StudentManageAPI.Constant.DateTime;
import itsol.com.StudentManageAPI.DAO.Entity.STUDENTS;
import itsol.com.StudentManageAPI.DAO.Repository.StudentRepository;
import itsol.com.StudentManageAPI.DTO.Reponse.ErrorResponse;
import itsol.com.StudentManageAPI.DTO.Reponse.StudentRespone;
import itsol.com.StudentManageAPI.DTO.Request.SearchingRequest;
import itsol.com.StudentManageAPI.DTO.Request.StudentRequest;
import itsol.com.StudentManageAPI.Utility.DataUtil;
import itsol.com.StudentManageAPI.Utility.DateFormat;
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

    DataUtil dataUtil = new DataUtil();
    DateFormat dateFormat = new DateFormat();

    public ResponseEntity<?> getAll() {
        List<STUDENTS> students = studentRepository.getAll();
        List<StudentRespone> studentRespones = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            STUDENTS s = students.get(i);
            studentRespones.add(new StudentRespone(s.getId(), s.getName(), s.getDob().toString(), s.getGender(), s.getClassName(), s.getMajor(), s.getHometown(), s.getAvgMark()));
        }
        if (students.isEmpty()) {
            ErrorResponse response = new ErrorResponse();
            response.setErrorCode("000");
            response.setMessage("resource not found");
            return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<StudentRespone>>(studentRespones, HttpStatus.OK);
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
            return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<StudentRespone>(sp, HttpStatus.OK);
        }
    }

    public ResponseEntity addStudent(StudentRequest students) {
        if (students.getName().isEmpty() || students.getGender().isEmpty() || students.getDob().toString().isEmpty() ||
                students.getClassName().isEmpty() || students.getMajor().isEmpty() || students.getHometown().isEmpty()) {
            ErrorResponse response = new ErrorResponse();
            response.setErrorCode("404");
            response.setMessage("Lack of attributes");
            return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
        }

        students.setName(dataUtil.standardlizeString(students.getName()));
        students.setHometown(dataUtil.standardlizeString(students.getHometown()));

        return new ResponseEntity<StudentRequest>(students, HttpStatus.OK);
    }

    public List<STUDENTS> updateStudent(STUDENTS students, int id) {
        return studentRepository.updateStudent(students, id);
    }

    public List<STUDENTS> deleteStudent(int id) {
        return studentRepository.deleteStudent(id);
    }

    public List<STUDENTS> searchStudents(SearchingRequest sRequest) {
        return studentRepository.searchStudent(sRequest);
    }

    public List<STUDENTS> getBirthdayStudent() {
        return studentRepository.getBirthdayStudent();
    }

}
