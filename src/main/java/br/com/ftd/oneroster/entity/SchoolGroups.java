package br.com.ftd.oneroster.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "school_groups")
@Data
public class SchoolGroups implements Serializable {

    @Id
    @Column(name = "guid")
    private String guid;

    @Column(name = "school_guid")
    private String schoolGuid;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "guid", referencedColumnName = "school_guid")
    private List<School> schools;

    @Column(name = "name")
    private String name;

    @Column(name = "education_year_guid")
    private String educationYearGuid;

    @Column(name = "school_year_guid")
    private String schoolYearGuid;

    @Column(name = "code")
    private String code;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @Column(name = "deleted_at")
    private ZonedDateTime deletedAt;

}
