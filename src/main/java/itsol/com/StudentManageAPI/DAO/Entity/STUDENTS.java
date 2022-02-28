package itsol.com.StudentManageAPI.DAO.Entity;

import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class STUDENTS {
    private int id;
    private String name;
    private String gender;
    private Date dob;
    private String className;
    private String major;
    private String hometown;
    private double avgMark;
    private int isDeleted;
}
