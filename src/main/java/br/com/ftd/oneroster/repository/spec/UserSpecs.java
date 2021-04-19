package br.com.ftd.oneroster.repository.spec;

import br.com.ftd.oneroster.entity.UserPerson;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecs {

    public static Specification<UserPerson> findUserByEmail(String email){
        return (root, query, builder) -> builder.equal(root.get("email"), email);
    }

    public static Specification<UserPerson> findUserByName(String name){
        return (root, query, builder) -> builder.equal(root.get("name"),name);
    }

    public static Specification<UserPerson> findUserByLastName(String lastName){
        return (root, query, builder) -> builder.equal(root.get("lastname"),lastName);
    }
}
