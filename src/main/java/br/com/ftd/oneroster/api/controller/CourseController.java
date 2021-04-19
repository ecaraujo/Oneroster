package br.com.ftd.oneroster.api.controller;

import br.com.ftd.oneroster.entity.Course;
import br.com.ftd.oneroster.model.ClassModel;
import br.com.ftd.oneroster.model.CourseModel;
import br.com.ftd.oneroster.service.ClassService;
import br.com.ftd.oneroster.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/oneroster")
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping("/courses")
    public ResponseEntity<CourseModel> findAll(@RequestHeader Map<String, Object> headers,
                                                @RequestHeader(name = "pagenumber", defaultValue = "0") String pageNumber,
                                                @RequestHeader(name = "pagesize", defaultValue = "10") String pageSize){

        headers.forEach((key, value) -> System.out.println("Chave: "+ key + " Valor: " + value));
        CourseModel courses = service.findAll(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
        return ResponseEntity.ok().body(courses);
    }

}
