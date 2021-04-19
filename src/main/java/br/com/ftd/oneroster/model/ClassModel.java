package br.com.ftd.oneroster.model;

import br.com.ftd.oneroster.dto.ClassDTO;
import br.com.ftd.oneroster.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ClassModel {

    private List<ClassDTO> classes = new ArrayList<>();

    private ClassDTO group;
}
