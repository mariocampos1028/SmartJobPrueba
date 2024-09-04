package com.prueba.tecnica.controller;

import com.prueba.tecnica.dto.ErrorMessage;
import com.prueba.tecnica.dto.UsuarioRequest;
import com.prueba.tecnica.dto.UsuarioResponse;
import com.prueba.tecnica.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("/createUser")
    public Object createUser(@RequestBody UsuarioRequest request){
        try {
            Optional<Object> obj = usuarioService.createUser(request);
            Object result = obj.get();
            if (result instanceof Optional<?>) {
                return new ResponseEntity<>(result, HttpStatus.CREATED); // 201 Created
            } else if (result instanceof ErrorMessage) {
                return new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE); // 400 Bad Request
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorMessage("Error interno del servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
