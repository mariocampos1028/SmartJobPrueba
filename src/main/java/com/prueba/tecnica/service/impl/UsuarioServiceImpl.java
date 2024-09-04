package com.prueba.tecnica.service.impl;

import com.prueba.tecnica.domain.Usuario;
import com.prueba.tecnica.dto.ErrorMessage;
import com.prueba.tecnica.dto.Telefono;
import com.prueba.tecnica.dto.UsuarioRequest;
import com.prueba.tecnica.dto.UsuarioResponse;
import com.prueba.tecnica.repository.UsuarioRepository;
import com.prueba.tecnica.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private int length;

    private boolean upperPassword;

    private boolean digitsPassword;

    private boolean characterPassword;

    public Optional<Object> createUser(UsuarioRequest body){
        Optional<Usuario> finduser = usuarioRepository.findByEmail(body.getEmail());
        if(finduser.isPresent()){
            ErrorMessage resp = new ErrorMessage("El correo ya registrado");
            return Optional.of(resp);
        }else{
            if(!isValidEmail(body.getEmail())){
                ErrorMessage resp = new ErrorMessage("El correo '"+body.getEmail()+"' tiene un formato no válido (usuariocorreo@dominio.xx)");
                return Optional.of(resp);
            } else if (!isValidPassword(body.getPassword() ) ){
                ErrorMessage resp = new ErrorMessage("El password '"+body.getPassword()+"' no cumple con el formato correcto");
                return Optional.of(resp);
            }
            // Implementación para crear un usuario
            Usuario user = mapResquest(body);
            Optional<UsuarioResponse> response = Optional.of(mapResponse(usuarioRepository.save(user)));
            return Optional.of(response);
        }

    }

    public boolean isValidEmail(String email){
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+\\.[A-Za-z]{2}$";
        if (email.matches(regex))
            return true;
        else
            return false;
    }

    public Usuario mapResquest(UsuarioRequest user){
        Usuario resp = new Usuario();
        resp.setId(generarUUID());
        resp.setName(user.getName());
        resp.setEmail(user.getEmail());
        resp.setPassword(user.getPassword());
        for (Telefono phone : user.getPhones()) {
            resp.setPhoneNumber(phone.getNumber());
            resp.setCityCode(phone.getCityCode());
            resp.setCountryCode(phone.getCountryCode());
        }
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        resp.setCreated(now.format(formatter));
        resp.setModified(now.format(formatter));
        resp.setLast_login(now.format(formatter));
        resp.setToken(generarUUID());
        resp.setIsactive(true);
        return resp;
    }

    public UsuarioResponse mapResponse(Usuario user){
        UsuarioResponse res = new UsuarioResponse();
        res.setId(user.getId());
        res.setName(user.getName());
        res.setEmail(user.getEmail());
        Telefono tel = new Telefono(user.getPhoneNumber(), user.getCityCode(), user.getCountryCode());
        List<Telefono> listPhones = new ArrayList<>();
        listPhones.add(tel);
        res.setPhones(listPhones);
        res.setCreated(user.getCreated());
        res.setModified(user.getModified());
        res.setLast_login(user.getLast_login());
        res.setActive(user.isIsactive());
        res.setToken(user.getToken());

        return res;

    }

    public String generarUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public boolean isValidPassword(String password){
        customPassword();
        String UPPER_CASE_REGEX = ".*[A-Z].*"; // Al menos una letra mayúscula
        String LOWER_CASE_REGEX = ".*[a-z].*"; // Al menos una letra minúscula
        String DIGIT_REGEX = ".*[0-9].*"; // Al menos un dígito
        String SPECIAL_CHAR_REGEX = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*"; // Al menos un carácter especial
        if (password.length() < this.length || password.isEmpty()) {
            return false;
        } else if (this.upperPassword && !password.matches(UPPER_CASE_REGEX)) {
            return false;
        }else if (this.digitsPassword && !password.matches(DIGIT_REGEX)){
            return false;
        }else if (this.characterPassword && !password.matches(SPECIAL_CHAR_REGEX)){
            return false;
        }
        return true;
    }

    public void customPassword(){
        this.length = 8;
        this.upperPassword = true;
        this.digitsPassword = true;
        this.characterPassword = true;
    }



}
