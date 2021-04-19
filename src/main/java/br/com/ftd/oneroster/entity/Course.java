package br.com.ftd.oneroster.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name = "lms_courses")
@Data
public class Course implements Serializable {

    @Id
    @Column(name = "guid")
    private String guid;

    @Column(name = "course_template_guid")
    private String courseTemplateGuid;

    @Column(name = "school_group_guid")
    private String schoolGroupGuid;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "school_group_guid", referencedColumnName = "guid", updatable = false, insertable = false)
//    private SchoolGroups schoolGroup;

    @Column(name = "education_year_guid")
    private String educationYearGuid;

    @Column(name = "education_discipline_guid")
    private String educationDisciplineGuid;
    
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "theme_guid")
    private String themeGuid;

    @Column(name = "picture")
    private String picture;

    @Column(name = "picture_header")
    private String pictureHeader;

    @Column(name = "book_guid")
    private String bookGuid;

    @Column(name = "calendar_guid")
    private String calendarGuid;

    @Column(name = "author_guid")
    private String authorGuid;

    @Column(name = "config_messages")
    private String configMessages;

    @Column(name = "number_of_lessons")
    private Integer numberOfLessons;

    @Column(name = "number_of_sessions")
    private Integer numberOfSessions;

    @Column(name = "school_guid")
    private String schoolGuid;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @Column(name = "deleted_at")
    private ZonedDateTime deletedAt;

}
