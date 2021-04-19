package br.com.ftd.oneroster.service;

import br.com.ftd.oneroster.dto.OrgDTO;
import br.com.ftd.oneroster.dto.UserDTO;
import br.com.ftd.oneroster.entity.UserPerson;
import br.com.ftd.oneroster.entity.UserRole;
import br.com.ftd.oneroster.exception.UserNotFoundException;
import br.com.ftd.oneroster.model.UserModel;
import br.com.ftd.oneroster.repository.UserRepository;
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
public class UserService {

    @Autowired
    private ModelMapper userModelMapper;

    @Autowired
    private UserRepository repository;

    public List<UserDTO> findAll(Integer pageInitial, Integer pageFinal) {

        UserModel userModel = new UserModel();

        Pageable pagination = PageRequest.of(pageInitial, pageFinal, Sort.Direction.ASC, "createdAt");

        Page<UserPerson> users = repository.findAll(pagination);

        List<UserDTO> userDTOS = mapper(users);

        userModel.setUsers(userDTOS);

        return userDTOS;

    }

    public UserModel findOne(String userId) {

        UserModel userModel = new UserModel();

        UserPerson userPerson = repository.findUserByCrmId(userId);

        if(userPerson == null){
            throw new UserNotFoundException(userId);
        }

        OrgDTO orgDTO = userModelMapper.map(userPerson, OrgDTO.class);

        orgDTO.setSourceId(userPerson.getCrmId());

        try {
            orgDTO.setHref(getSchoolUri(userPerson));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        UserDTO userDTO = userModelMapper.map(userPerson,UserDTO.class);

        userDTO.setOrg(orgDTO);

        userModel.setUser(userDTO);

        return userModel;

    }

    private URI getSchoolUri(UserPerson userPerson) throws URISyntaxException{
        return new URI("http://localhost:8080/schools/" + userPerson.getSchools().stream().findFirst().get().getGuid());
    }

    public List<UserDTO> findUserByCrmIdAndFilters(String crmId, String email, String name, String lastName, String role, String status){

        List<UserDTO> usersDTO = mapper(repository.findUserByCrmIdAndFilters(crmId, email, name, lastName, role, status));

        return usersDTO;
    }

    public List<UserDTO> findUserByCrmIdAndCriteria(String crmId, String email, String name, String lastName, String role, String status){

        List<UserDTO> usersDTO = mapper(repository.findUserWithCriteria(crmId, email, name, lastName, role, status));

        return usersDTO;
    }

    public List<UserDTO> mapper(Page<UserPerson> users) {

        List<UserDTO> usersDTO = new ArrayList<>();

        UserRole role = new UserRole();

        users.get().forEach(u -> {

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

            UserDTO user = new UserDTO();
            user.setSourcedId(u.getCrmId());
            user.setGivenName(u.getName());
            user.setFamilyName(u.getLastName());
            user.setUsername(u.getEmail());
            user.setEmail(u.getEmail());
            user.setRole(u.getUserRole().getGuid());
            user.setStatus(status);
            user.setDateLastModified(dateLastModified);

            URI uri = null;
            try {
                if(!u.getSchools().isEmpty()) {
                    uri = new URI("http://localhost:8080/schools/" + u.getSchools().get(0).getSchool().getSchoolGuid());
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            OrgDTO org = new OrgDTO();
            org.setHref(uri);
            org.setSourceId(u.getSchools().get(0).getSchool().getSchoolGuid());
            org.setType("org");
            user.setOrg(org);
            usersDTO.add(user);

        });

        return usersDTO;
    }

    public List<UserDTO> mapper(List<UserPerson> users) {

        List<UserDTO> usersDTO = new ArrayList<>();

        UserRole role = new UserRole();

        users.forEach(u -> {

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

            UserDTO user = new UserDTO();
            user.setSourcedId(u.getCrmId());
            user.setGivenName(u.getName());
            user.setFamilyName(u.getLastName());
            user.setUsername(u.getEmail());
            user.setEmail(u.getEmail());
            user.setRole(u.getUserRole().getGuid());
            user.setStatus(status);
            user.setDateLastModified(dateLastModified);

            URI uri = null;
            try {
                if(user.getOrg() != null) {
                    uri = new URI("http://localhost:8080/" + user.getOrg().getSourceId());
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            OrgDTO org = new OrgDTO();
            org.setHref(uri);
            //org.setSourceId(user.getOrg().getSourceId());
            org.setType("org");
            user.setOrg(org);
            usersDTO.add(user);

        });

        return usersDTO;

    }

    public UserDTO mapper(UserPerson users) {

        UserRole role = new UserRole();
        String status = null;
        if(users.getIsActive() == 1 && users.getDeletedAt() == null){
            status = "active";
        }else{
            status = "inactive";
        }

        String dateLastModified = null;

        if(users.getDeletedAt() != null) {
            dateLastModified = users.getDeletedAt().toString();
        }else if(users.getUpdatedAt() != null){
            dateLastModified = users.getUpdatedAt().toString();
        }else{
            dateLastModified = users.getCreatedAt().toString();
        }

        UserDTO user = new UserDTO();
        user.setSourcedId(users.getCrmId());
        user.setGivenName(users.getName());
        user.setFamilyName(users.getLastName());
        user.setUsername(users.getEmail());
        user.setEmail(users.getEmail());
        user.setRole(users.getUserRole().getGuid());
        user.setStatus(status);
        user.setDateLastModified(dateLastModified);

        URI uri = null;
        try {
            if(user.getOrg() != null) {
                uri = new URI("http://localhost:8080/" + user.getOrg().getSourceId());
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        OrgDTO org = new OrgDTO();
        org.setHref(uri);
        //org.setSourceId(user.getOrg().getSourceId());
        org.setType("org");
        user.setOrg(org);

        return user;
    }
}