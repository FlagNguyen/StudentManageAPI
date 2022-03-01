package itsol.com.StudentManageAPI.Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
    public boolean checkDateFormat(String dob, String format) {
        SimpleDateFormat sfd = new SimpleDateFormat(format);
        try{
            Date date = sfd.parse(dob);
            return true;
        } catch (Exception e) {
            System.err.println("Wrong date's format");
            return false;
        }
    }
}
