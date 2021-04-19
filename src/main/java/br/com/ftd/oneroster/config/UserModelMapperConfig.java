package br.com.ftd.oneroster.config;

import br.com.ftd.oneroster.dto.OrgDTO;
import br.com.ftd.oneroster.dto.UserDTO;
import br.com.ftd.oneroster.entity.School;
import br.com.ftd.oneroster.entity.UserPerson;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.net.URI;
import java.net.URISyntaxException;


@Configuration
public class UserModelMapperConfig {

    @Bean
    public ModelMapper userModelMapper() throws URISyntaxException{

        var modelMapper = new ModelMapper();

        OrgDTO org = new OrgDTO();

        Converter<Integer, String> statusConverter = ctx -> ctx.getSource() == 1 ? "ACTIVE" : "INACTIVE";

        modelMapper.typeMap(UserPerson.class, OrgDTO.class)
                .<String>addMapping(src -> "org",
                                (dst, value) -> dst.setType(value));

        modelMapper.typeMap(UserPerson.class, UserDTO.class)
                .<String>addMapping(src -> src.getCrmId(),
                        (dst, value) -> dst.setSourcedId(value))
                .<String>addMapping(src -> src.getName(),
                        (dst, value) -> dst.setGivenName(value))
                .<String>addMapping(src -> src.getLastName(),
                        (dst, value) -> dst.setFamilyName(value))
                .<String>addMapping(src -> src.getEmail(),
                        (dst, value) -> dst.setEmail(value))
                .<String>addMapping(src -> src.getUserRole().getGuid(),
                        (dst, value) -> dst.setRole(value))
                .<String>addMapping(src -> src.getEmail(),
                        (dst, value) -> dst.setUsername(value))
                .addMappings(mapper -> mapper.using(statusConverter)
                        .map(UserPerson::getIsActive, UserDTO::setStatus));

        return modelMapper;
    }
}
