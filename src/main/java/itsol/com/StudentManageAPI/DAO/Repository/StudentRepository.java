package itsol.com.StudentManageAPI.DAO.Repository;

import itsol.com.StudentManageAPI.DAO.Entity.STUDENTS;
import itsol.com.StudentManageAPI.DTO.Request.SearchingRequest;
import itsol.com.StudentManageAPI.DTO.Request.StudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository  {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public List<STUDENTS> getAll() {
        String sql = "SELECT * FROM STUDENTS WHERE isdeleted = 0";
        List<STUDENTS> students = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(STUDENTS.class));
        return students;
    }

    public List<STUDENTS> getStudentByCode(int code) {
        String sql = "SELECT * FROM STUDENTS s WHERE s.id = ? AND s.isdeleted = 0";
        List<STUDENTS> students = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(STUDENTS.class), code);
        return students;
    }

    public List<STUDENTS> addStudent(StudentRequest students) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO STUDENTS");
        sql.append(" (id,name,gender,dob,class_name,major,hometown,avg_mark,isdeleted) VALUES (?, ?, ?, ?, ?, ? ,? ,? ,? )");
        jdbcTemplate.update(sql.toString(), getNextId(), students.getName(), students.getGender(),
                students.getDob(), students.getClassName(), students.getMajor(), students.getHometown()
                , students.getAvgMark(), 0);
        return getStudentByCode(getNextId()-1);
    }

    public List<STUDENTS> updateStudent(STUDENTS students, int code) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE STUDENTS s ");
        sql.append("SET s.name= ? ,s.gender = ? ,s.dob = ? ,s.class_name = ? ,s.major = ? ,s.hometown = ? ,s.avg_mark = ?");
        sql.append("WHERE s.id = ?");
        jdbcTemplate.update(sql.toString(), students.getName(), students.getGender(), students.getDob(), students.getClassName(),
                students.getMajor(), students.getHometown(), students.getAvgMark(), code);
        return getStudentByCode(code);
    }

    public List<STUDENTS> deleteStudent(int code){
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE STUDENTS s SET s.isdeleted = 1 WHERE s.id = ?");
        jdbcTemplate.update(sql.toString(),code);
        return getStudentByCode(code);
    }

    public List<STUDENTS> searchStudent(SearchingRequest sRequest) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM STUDENTS ");
        sql.append(" WHERE isdeleted = 0 AND ");
        String[] searchAttributes = sRequest.getSearchingAttributes().split(",");
        for(int i = 0; i < searchAttributes.length ; i++){
            sql.append( "LOWER(" + searchAttributes[i].trim() + ") LIKE LOWER(" + "'%" + sRequest.getSearchingMessage().trim() + "%')");
            if(i != searchAttributes.length -1 ){
                sql.append(" OR ");
            }
        }
        return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(STUDENTS.class));
    }

    public List<STUDENTS> getBirthdayStudent() {
        String sql = "SELECT * FROM STUDENTS WHERE isdeleted = 0 AND extract(month from dob) = extract(month from sysdate) AND extract(day from dob) = extract(day from sysdate)";
        List<STUDENTS> students = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(STUDENTS.class));
        return students;
    }

    public int getNextId(){
        String sql = "SELECT COUNT(*)\n" +
                "FROM STUDENTS";
        return jdbcTemplate.queryForObject(sql, Integer.class) + 100001;
    }
}
