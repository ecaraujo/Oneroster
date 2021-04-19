package br.com.ftd.oneroster.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "school_delegated")
@Data
public class School implements Serializable {

    @Id
    @Column(name = "guid")
    String schoolGuid;

    @Transient
    private List<UserPerson> userPersons = new ArrayList<>();

    @Column(name = "status")
    String status;

    @Column(name = "commit")
    Integer commit;

    @Column(name = "brand")
    String brand;

    @Column(name = "brand_info")
    String brandInfo;

    @Column(name = "code")
    String code;

    @Column(name = "name")
    String name;

    @Column(name = "image_brand")
    String imageBrand;

    @Column(name = "image_cover")
    String imageCover;

    @Column(name = "is_active")
    Integer isActive;

    @Column(name = "is_demo")
    Integer isDemo;

    @Column(name = "migration_need")
    Integer migrationNeed;

    @Column(name = "enable_adaptive_learning")
    Integer enableAdaptiveLearning;

    @Column(name = "is_enabled_virtual_class")
    Integer isEnabledVirtualClass;

    @Column(name = "allow_signup")
    Integer allowSignup;

    @Column(name = "url_access")
    String urlAccess;

    @Column(name = "teacher_code")
    String teacherCode;

    @Column(name = "student_license_required")
    Integer studentLicenseRequired;

    @Column(name = "school_license_auto")
    Integer schoolLicenseAuto;

    @Column(name = "teachers_admin_licenses")
    Integer teachersAdminLicenses;

    @Column(name = "teachers_all_license_schools")
    Integer teachersAllLicenseSchools;

    @Column(name = "users_change_password")
    Integer usersChangePassword;

    @Column(name = "city_guid")
    String cityGuid;

    @Column(name = "zipcode")
    String zipcode;

    @Column(name = "address")
    String address;

    @Column(name = "neighborhood")
    String neighborhood;

    @Column(name = "address_number")
    String addressNumber;

    @Column(name = "address_complement")
    String addressComplement;

    @Column(name = "phone")
    String phone;

    @Column(name = "email")
    String email;

    @Column(name = "document")
    String document;

    @Column(name = "company_name")
    String companyName;

    @Column(name = "legal_representative")
    String legalRepresentative;

    @Column(name = "integration_partner")
    Integer integrationPartner;

    @Column(name = "is_enabled_comments")
    Integer isEnabledComments;

    @Column(name = "created_at")
    ZonedDateTime createdAt;

    @Column(name = "updated_at")
    ZonedDateTime updatedAt;

    @Column(name = "deleted_at")
    ZonedDateTime deletedAt;
}
