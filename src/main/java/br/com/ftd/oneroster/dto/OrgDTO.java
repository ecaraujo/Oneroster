package br.com.ftd.oneroster.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.net.URI;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrgDTO implements Serializable {
    private URI href;
    private String sourceId;
    private String type;
}
