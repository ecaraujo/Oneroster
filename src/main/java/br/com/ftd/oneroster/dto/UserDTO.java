package br.com.ftd.oneroster.dto;

import br.com.ftd.oneroster.entity.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO implements Serializable {

    private String sourcedId;
    private String status;
    private String dateLastModified;
    private String username;
    private String enabledUser;
    private String givenName;
    private String familyName;
    private String role;
    private String identifier;
    private String email;
    private String sms;
    private String phone;
    private OrgDTO org;
}
