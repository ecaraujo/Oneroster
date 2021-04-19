package br.com.ftd.oneroster.repository.impl;

import br.com.ftd.oneroster.entity.UserPerson;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class UserRepositoryImpl {

    @PersistenceContext
    private EntityManager manager;

    public List<UserPerson> findUserByGuid(String guid){

        String jpql = "from user_persons where guid = :guid";

        return manager.createQuery(jpql, UserPerson.class)
                .setParameter("guid", guid)
                .getResultList();
    }

    public List<UserPerson> findUserByCrmIdAndFilters(String crmId, String email, String name, String lastName, String role, String status){

        StringBuilder jpql = new StringBuilder();

        jpql.append("from UserPerson where 0 = 0 ");

        var parameters = new HashMap<String, Object>();

        if(crmId != null){
            jpql.append(" and crmId = :crmId ");
            parameters.put("crmId",crmId);
        }

        if(email != null){
            jpql.append("and email = :email ");
            parameters.put("email", email);
        }

        if(lastName != null){
            jpql.append("and lastname <= :lastname");
            parameters.put("lastname", lastName);
        }

        if(role != null){
            jpql.append("and role = :role");
            parameters.put("role", role);
        }

        if(status != null){
            jpql.append("and is_active <= :status");
            parameters.put("is_active", status == "active" ? 1 : 0);
        }

        TypedQuery<UserPerson> query = manager.createQuery(jpql.toString(), UserPerson.class);

        parameters.forEach((key, value) -> query.setParameter(key, value));

        return query.getResultList();
    }

    public List<UserPerson> findUserWithCriteria(String crmId, String email, String name, String lastName, String role, String status){

        var predicates = new ArrayList<Predicate>();

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<UserPerson> criteria = builder.createQuery(UserPerson.class);
        Root<UserPerson> root = criteria.from(UserPerson.class);

        if(crmId != null) {
            predicates.add((builder.equal(root.get("crmId"), crmId)));
        }

        if(email != null){
            predicates.add(builder.equal(root.get("email"), email));
        }

        if(lastName != null) {
            predicates.add(builder.equal(root.get("lastName"), lastName));
        }

        if(name != null) {
            predicates.add(builder.equal(root.get("name"), name));
        }

        if(role != null) {
            predicates.add(builder.equal(root.get("UserRole").get("guid"), role));
        }

        if(status != null) {
            predicates.add(builder.equal(root.get("isActive"), status.equals("active") ? 1 : 0));
        }
        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<UserPerson> query = manager.createQuery(criteria);

        return query.getResultList();

    }

}
