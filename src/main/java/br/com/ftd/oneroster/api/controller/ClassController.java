package br.com.ftd.oneroster.api.controller;

import br.com.ftd.oneroster.dto.ClassDTO;
import br.com.ftd.oneroster.dto.UserDTO;
import br.com.ftd.oneroster.entity.SchoolGroups;
import br.com.ftd.oneroster.model.ClassModel;
import br.com.ftd.oneroster.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/oneroster")
public class ClassController {

    @Autowired
    private ClassService service;

    @GetMapping("/classes")
    public ResponseEntity<ClassModel> findAll(@RequestHeader Map<String, Object> headers,
                                              @RequestHeader(name = "pagenumber", defaultValue = "1") String pageNumber,
                                              @RequestHeader(name = "pagesize", defaultValue = "5") String pageSize){

        headers.forEach((key, value) -> System.out.println("Chave: "+ key + " Valor: " + value));
        ClassModel classes = service.findAll(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
        return ResponseEntity.ok().body(classes);
    }

}
