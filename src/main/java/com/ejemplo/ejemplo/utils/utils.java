package com.ejemplo.ejemplo.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class utils {
    // método que genera un nuevo uuid
    public UUID crearUuid() {

        return UUID.randomUUID();
    }

    //método que valida el formato del correo
    public Boolean validarcorreo(String correo) {
        String regx = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    //método que valida el formato de la contraseña
    public boolean validarPassw(String pass) {
        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        Pattern p = Pattern.compile(regex);
        if (pass == null) {
            return false;
        }
        Matcher m = p.matcher(pass);
        return m.matches();
    }

}
