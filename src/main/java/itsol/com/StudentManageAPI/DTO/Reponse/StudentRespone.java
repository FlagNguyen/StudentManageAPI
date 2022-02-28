package itsol.com.StudentManageAPI.DTO.Reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRespone {
    private int id;
    private String name;
    private String dob;
    private String gender;
    private String className;
    private String major;
    private String hometown;
    private double avgMark;
}
