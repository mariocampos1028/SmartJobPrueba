package com.prueba.tecnica.service;

import com.prueba.tecnica.dto.UsuarioRequest;
import com.prueba.tecnica.dto.UsuarioResponse;

import java.util.Optional;

public interface IUsuarioService{

    Optional<Object> createUser(UsuarioRequest request);
}
