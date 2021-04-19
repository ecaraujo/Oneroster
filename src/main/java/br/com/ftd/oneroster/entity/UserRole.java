package br.com.ftd.oneroster.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_roles")
@Data
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "guid")
    private String guid;
    private String role;
    private String description;

}
