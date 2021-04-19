package br.com.ftd.oneroster.entity.pk;

import br.com.ftd.oneroster.entity.School;
import br.com.ftd.oneroster.entity.UserPerson;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class SchoolUserPk implements Serializable {

    @ManyToOne
    @JoinColumn(name = "person_guid")
    private UserPerson userPerson;

    @ManyToOne
    @JoinColumn(name = "school_guid")
    private School school;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolUserPk that = (SchoolUserPk) o;
        return userPerson.equals(that.userPerson) && school.equals(that.school);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userPerson, school);
    }
}
