package br.com.ftd.oneroster.repository;

import br.com.ftd.oneroster.entity.UserPerson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserPerson, String>, JpaSpecificationExecutor<UserPerson> {

    @Override
    Page<UserPerson> findAll(Pageable pageable);

    UserPerson findUserByCrmId(String crmId);
    List<UserPerson> findUserByCrmIdAndFilters(String crmId, String email, String name, String lastName, String role, String status);
    List<UserPerson> findUserWithCriteria(String crmId, String email, String name, String lastName, String role, String status);


}
