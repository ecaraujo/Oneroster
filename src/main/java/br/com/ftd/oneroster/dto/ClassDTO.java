package br.com.ftd.oneroster.dto;

import br.com.ftd.oneroster.entity.School;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassDTO implements Serializable {

    private String sourcedId;
    private String status;
    private String dateLastModified;
    private String metadata;
    private String title;
    private String classCode;
    private String classType;
    private String location;
    private OrgDTO school;

}
