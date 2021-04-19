package br.com.ftd.oneroster.model;

import br.com.ftd.oneroster.dto.CourseDTO;
import br.com.ftd.oneroster.entity.Course;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CourseModel {

    private List<CourseDTO> courses = new ArrayList<>();

    private Course course;
}
