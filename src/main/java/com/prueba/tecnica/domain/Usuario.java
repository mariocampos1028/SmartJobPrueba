package com.prueba.tecnica.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Usuario {

    @Id
    private String id;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private String cityCode;

    private String countryCode;

    private String created;

    private String modified;

    private String last_login;

    private String token;

    private boolean isactive;

}
