package br.com.ftd.oneroster.config;

import br.com.ftd.oneroster.dto.ClassDTO;
import br.com.ftd.oneroster.dto.OrgDTO;
import br.com.ftd.oneroster.dto.UserDTO;
import br.com.ftd.oneroster.entity.SchoolGroups;
import br.com.ftd.oneroster.entity.UserPerson;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;


@Configuration
public class ClassModelMapperConfig {

    @Bean
    public ModelMapper classModelMapper() throws URISyntaxException{

        var modelMapper = new ModelMapper();

        OrgDTO org = new OrgDTO();

        Converter<Integer, String> statusConverter = ctx -> ctx.getSource() == 1 ? "ACTIVE" : "INACTIVE";

        modelMapper.typeMap(SchoolGroups.class, OrgDTO.class)
                .<String>addMapping(src -> "school",
                                (dst, value) -> dst.setType(value));

        modelMapper.typeMap(SchoolGroups.class, ClassDTO.class)
                .<String>addMapping(src -> src.getGuid(),
                        (dst, value) -> dst.setSourcedId(value))
                .<String>addMapping(src -> src.getUpdatedAt(),
                        (dst, value) -> dst.setTitle(value))
                .<String>addMapping(src -> src.getUpdatedAt(),
                        (dst, value) -> dst.setDateLastModified(value))
                .<String>addMapping(src -> src.getName(),
                        (dst, value) -> dst.setTitle(value))
                .<String>addMapping(src -> src.getCode(),
                        (dst, value) -> dst.setClassCode(value))
                .addMappings(mapper -> mapper.using(statusConverter)
                        .map(SchoolGroups::getIsActive, ClassDTO::setStatus));

        return modelMapper;
    }
}
