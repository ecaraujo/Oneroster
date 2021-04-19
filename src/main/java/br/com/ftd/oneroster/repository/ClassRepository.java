package br.com.ftd.oneroster.repository;


import br.com.ftd.oneroster.entity.SchoolGroups;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<SchoolGroups, String>, JpaSpecificationExecutor<SchoolGroups> {

    Page<SchoolGroups> findAll(Pageable pageable);

    SchoolGroups findClassByGuid(String crmId);

}