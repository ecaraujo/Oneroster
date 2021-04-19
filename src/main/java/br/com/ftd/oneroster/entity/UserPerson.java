package br.com.ftd.oneroster.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_persons")
@Data
public class UserPerson implements Serializable {

    @Id
    @Column(name = "guid")
    private String guid;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "is_demo")
    private Integer isDemo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_guid", referencedColumnName = "guid")
    private UserRole UserRole;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "avatar")
    private String urlAvatar;

    @Column(name = "parent_consent")
    private String parentConsent;

    @Column(name = "devices_limit")
    private Integer devicesLimit;

    @Column(name = "last_login_at")
    private ZonedDateTime lastLogin;

    @Column(name = "source")
    private String source;

    @Column(name = "idp_id")
    private String idpId;

    @Column(name = "crm_id")
    private String crmId;

    @Column(name = "migrated_at")
    private ZonedDateTime migratedAt;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @Column(name = "deleted_at")
    private ZonedDateTime deletedAt;

    @OneToMany(mappedBy = "id.userPerson")
    private List<SchoolUser> schools = new ArrayList<>();
}
