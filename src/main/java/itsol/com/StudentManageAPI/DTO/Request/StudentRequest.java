package itsol.com.StudentManageAPI.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    @NotEmpty(message = "Student's name is mandatory")
    private String name;
    @NotEmpty(message = "Gender is mandatory")
    private String gender;
    @NotEmpty(message = "Date of Birth is mandatory")
    private Date dob;
    @NotEmpty(message = "Class's name is mandatory")
    private String className;
    @NotEmpty(message = "Major is mandatory")
    private String major;
    @NotEmpty(message = "Hometown is mandatory")
    private String hometown;
    @NotEmpty(message = "Average mark is mandatory")
    private double avgMark;
}
