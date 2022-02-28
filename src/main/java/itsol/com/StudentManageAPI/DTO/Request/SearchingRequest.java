package itsol.com.StudentManageAPI.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchingRequest {
    private String searchingAttributes;
    private String searchingMessage;
}
