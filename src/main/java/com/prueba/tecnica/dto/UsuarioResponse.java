package com.prueba.tecnica.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phones")
    private List<Telefono> phones;

    @JsonProperty("created")
    private String created;

    @JsonProperty("modified")
    private String modified;

    @JsonProperty("last_login")
    private String last_login;

    @JsonProperty("token")
    private String token;

    @JsonProperty("isActive")
    private boolean isActive;




}
