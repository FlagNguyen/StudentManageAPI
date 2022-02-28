package itsol.com.StudentManageAPI.Service;

import itsol.com.StudentManageAPI.DAO.Entity.STUDENTS;
import itsol.com.StudentManageAPI.DAO.Repository.StudentRepository;
import itsol.com.StudentManageAPI.DTO.Request.SearchingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<STUDENTS> getAll() {
        List<STUDENTS> students = studentRepository.getAll();
        return students;
    }

    public STUDENTS getStudentByCode(int id) {
        return studentRepository.getStudentByCode(id);
    }

    public STUDENTS addStudent(STUDENTS students) {

        return studentRepository.addStudent(students);
    }

    public STUDENTS updateStudent(STUDENTS students,int id) {
        return studentRepository.updateStudent(students,id);
    }

    public STUDENTS deleteStudent(int id){
        return studentRepository.deleteStudent(id);
    }

    public List<STUDENTS> searchStudents(SearchingRequest sRequest){
        return studentRepository.searchStudent(sRequest);
    }

    public List<STUDENTS> getBirthdayStudent(){
        return studentRepository.getBirthdayStudent();
    }

}
