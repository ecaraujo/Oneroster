package br.com.ftd.oneroster.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDTO implements Serializable {

    private String sourcedId;
    private String status;
    private String dateLastModified;
    private String metadata;
    private String title;
    private String courseCode;
    private OrgDTO org;

}
