package br.com.ftd.oneroster.api.controller;

import br.com.ftd.oneroster.dto.UserDTO;
import br.com.ftd.oneroster.model.UserModel;
import br.com.ftd.oneroster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/oneroster")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> findAll(@RequestHeader Map<String, Object> headers,
                                             @RequestHeader(name = "pagenumber", defaultValue = "1") String pageNumber,
                                             @RequestHeader(name = "pagesize", defaultValue = "5") String pageSize){

        headers.forEach((key, value) -> System.out.println("Chave: "+ key + " Valor: " + value));
        List<UserDTO> users = service.findAll(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserModel> findOne(@PathVariable(value="id") String userId){
        System.out.println("findOne: " + userId);
        UserDTO userDTO = new UserDTO();
        UserModel user = service.findOne(userId);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/filtros")
    public ResponseEntity<UserModel> findUserByCrmIdAndFilters(@RequestParam(value="crmId", required = false) String userId,
                                                               @RequestParam(value = "email", required = false) String email,
                                                               @RequestParam(value = "name", required = false) String name,
                                                               @RequestParam(value = "lastName", required = false) String lastName,
                                                               @RequestParam(value = "role", required = false) String role,
                                                               @RequestParam(value = "status", required = false) String status){
        System.out.println("findOne: " + userId);
        List<UserDTO> users = service.findUserByCrmIdAndFilters(userId, email, name, lastName, role, status);
        UserModel userModel = new UserModel();
        userModel.setUsers(users);
        return ResponseEntity.ok().body(userModel);
    }

    @GetMapping("/criterios")
    public ResponseEntity<UserModel> findUserByCrmIdAndCriteria(@RequestParam(value="crmId", required = false) String userId,
                                                                @RequestParam(value = "email", required = false) String email,
                                                                @RequestParam(value = "name", required = false) String name,
                                                                @RequestParam(value = "lastName", required = false) String lastName,
                                                                @RequestParam(value = "role", required = false) String role,
                                                                @RequestParam(value = "status", required = false) String status){

        List<UserDTO> users = service.findUserByCrmIdAndCriteria(userId, email, name, lastName, role, status);
        UserModel userModel = new UserModel();
        userModel.setUsers(users);
        return ResponseEntity.ok().body(userModel);
    }


}
