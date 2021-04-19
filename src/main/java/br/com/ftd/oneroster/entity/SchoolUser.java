package br.com.ftd.oneroster.entity;

import br.com.ftd.oneroster.entity.pk.SchoolUserPk;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "school_users")

public class SchoolUser implements Serializable {

    @Column(name = "guid")
    private String guid;

    @EmbeddedId
    private SchoolUserPk id = new SchoolUserPk();

    @Column(name = "is_active")
    private String isActive;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @Column(name = "deleted_at")
    private ZonedDateTime deletedAt;

    public SchoolUser(){
    }

    public SchoolUser(String guid, UserPerson userPerson, School school, String isActive, ZonedDateTime createdAt, ZonedDateTime updatedAt, ZonedDateTime deletedAt) {
        this.guid = guid;
        this.id.setSchool(school);
        this.id.setUserPerson(userPerson);
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    @JsonIgnore
    public UserPerson getUserPerson(){
        return this.id.getUserPerson();
    }

    public void setUserPerson(UserPerson userPerson){
        this.id.setUserPerson(userPerson);
    }

    public School getSchool(){
        return this.id.getSchool();
    }

    public void setSchool(School school){
        this.id.setSchool(school);
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ZonedDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(ZonedDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolUser that = (SchoolUser) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
