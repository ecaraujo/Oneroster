package br.com.ftd.oneroster.service;

import br.com.ftd.oneroster.dto.ClassDTO;
import br.com.ftd.oneroster.dto.OrgDTO;
import br.com.ftd.oneroster.entity.SchoolGroups;
import br.com.ftd.oneroster.model.ClassModel;
import br.com.ftd.oneroster.repository.ClassRepository;
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
public class ClassService {

    @Autowired
    private ModelMapper classModelMapper;

    @Autowired
    private ClassRepository repository;

    public ClassModel findAll(Integer pageInitial, Integer pageFinal) {

        Pageable pagination = PageRequest.of(pageInitial, pageFinal, Sort.Direction.ASC, "createdAt");

        Page<SchoolGroups> classes = repository.findAll(pagination);

        List<ClassDTO> classDTOS = mapper(classes);

        ClassModel classModel = new ClassModel();

        classModel.setClasses(classDTOS);

        return classModel;

    }

    public List<ClassDTO> mapper(Page<SchoolGroups> classes) {

        List<ClassDTO> classesDTO = new ArrayList<>();

        classes.get().forEach(u -> {

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

            ClassDTO classDTO = new ClassDTO();
            classDTO.setSourcedId(u.getGuid());
            classDTO.setDateLastModified(dateLastModified);
            classDTO.setTitle(u.getName());
            classDTO.setClassCode(u.getCode());

            URI uri = null;
            try {
                if(!u.getSchools().isEmpty()) {
                    uri = new URI("http://localhost:8080/schools/" + u.getSchools().get(0).getSchoolGuid());
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            OrgDTO org = new OrgDTO();
            org.setHref(uri);
            org.setSourceId(u.getSchools().get(0).getSchoolGuid());
            org.setType("org");
            classDTO.setSchool(org);
            classesDTO.add(classDTO);

        });

        return classesDTO;
    }
}
