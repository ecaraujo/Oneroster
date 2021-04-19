package br.com.ftd.oneroster.service;

import br.com.ftd.oneroster.dto.ClassDTO;
import br.com.ftd.oneroster.dto.CourseDTO;
import br.com.ftd.oneroster.dto.OrgDTO;
import br.com.ftd.oneroster.entity.Course;
import br.com.ftd.oneroster.entity.SchoolGroups;
import br.com.ftd.oneroster.model.CourseModel;
import br.com.ftd.oneroster.repository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private ModelMapper classModelMapper;

    @Autowired
    private CourseRepository repository;

    public CourseModel findAll(Integer pageInitial, Integer pageFinal) {

        Pageable pagination = PageRequest.of(pageInitial, pageFinal, Sort.Direction.ASC, "createdAt");

        Page<Course> courses = repository.findAll(pagination);

        List<CourseDTO> classDTOS = mapper(courses);

        CourseModel courseModel = new CourseModel();

        courseModel.setCourses(classDTOS);

        return courseModel;

    }

    public List<CourseDTO> mapper(Page<Course> courses) {

        List<CourseDTO> CoursesDTO = new ArrayList<>();

        courses.get().forEach(u -> {

            String status = null;
            if(u.getIsActive() == 1 && u.getDeletedAt() == null){
                status = "active";
            }else{
                status = "inactive";
            }

            String dateLastModified = null;

            if(u.getDeletedAt() != null) {
                dateLastModified = u.getDeletedAt().toString();
            }else if(u.getUpdatedAt() != null){
                dateLastModified = u.getUpdatedAt().toString();
            }else{
                dateLastModified = u.getCreatedAt().toString();
            }

            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setSourcedId(u.getGuid());
            courseDTO.setDateLastModified(dateLastModified);
            courseDTO.setTitle(u.getName());
            courseDTO.setCourseCode(u.getCode());

            URI uri = null;
          //  try {
          //      if(!u.getSchoolGroup().getSchoolGuid().isEmpty()) {
          //          uri = new URI("http://localhost:8080/schools/" + u.getSchoolGroup().getSchoolGuid());
          //      }
          //  } catch (URISyntaxException e) {
          //      e.printStackTrace();
          //  }
            OrgDTO org = new OrgDTO();
            org.setHref(uri);
            org.setSourceId(u.getSchoolGuid());
            org.setType("org");
            courseDTO.setOrg(org);
            CoursesDTO.add(courseDTO);

        });

        return CoursesDTO;
    }
}
