package com.ejemplo.ejemplo;

import com.ejemplo.ejemplo.dao.phoneDAO;
import com.ejemplo.ejemplo.dao.response_messages;
import com.ejemplo.ejemplo.dao.userDAO;
import com.ejemplo.ejemplo.service.userService;
import com.ejemplo.ejemplo.utils.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(path = "/api")
public class ejemploController {
    @Autowired
    userService userServicemanager;
    @Autowired
    utils utils;

    //Método que devuuelve un usuario dado el correo
    @GetMapping("/user")
    public ResponseEntity getuserByCorreo(@RequestParam(value = "correo", required = true) String correo) {
        response_messages response_messages = new response_messages();
        try {

            if (correo == null || correo == "") {
                response_messages.setMessages("ERROR");
                response_messages.setDescripcion("El correo no puede estar vacio");
                return new ResponseEntity(response_messages, HttpStatus.BAD_REQUEST);
            }
            if (!utils.validarcorreo(correo))
            {
                response_messages.setMessages("ERROR");
                response_messages.setDescripcion("Formato de correo invalido");
                return new ResponseEntity(response_messages, HttpStatus.BAD_REQUEST);
            }
            userDAO byCorreo = userServicemanager.findByCorreo(correo);
            if (byCorreo == null) {
                response_messages.setMessages("Success");
                response_messages.setDescripcion("NO existe el usuario");
                return new ResponseEntity(response_messages, HttpStatus.OK);
            }
            return new ResponseEntity(byCorreo, HttpStatus.OK);
        } catch (Exception e) {
            response_messages.setMessages("ERROR");
            response_messages.setDescripcion(e.toString());
            return new ResponseEntity(response_messages, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //Metodo para insertar usuario
    @PostMapping(path = "/insertUsuario", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity insertUsuario(@RequestBody userDAO requestService) {
        response_messages response_messages = new response_messages();
        try {
            if (requestService == null) {
                response_messages.setMessages("ERROR");
                response_messages.setDescripcion("Mesage de entrada vacio");
                return new ResponseEntity(response_messages, HttpStatus.BAD_REQUEST);
            }
            if (requestService.getCorreo() == null || requestService.getCorreo() == "") {
                response_messages.setMessages("ERROR");
                response_messages.setDescripcion("El correo no puede estar vacio");
                return new ResponseEntity(response_messages, HttpStatus.BAD_REQUEST);
            }
            if (requestService.getPassw() == null || requestService.getPassw() == "") {
                response_messages.setMessages("ERROR");
                response_messages.setDescripcion("La contraseña es obligatoria");
                return new ResponseEntity(response_messages, HttpStatus.BAD_REQUEST);
            }
            if (!utils.validarcorreo(requestService.getCorreo())) {
                response_messages.setMessages("ERROR");
                response_messages.setDescripcion("Formato de correo incorrecto");
                return new ResponseEntity(response_messages, HttpStatus.BAD_REQUEST);
            }

            if (!utils.validarPassw(requestService.getPassw())) {
                response_messages.setMessages("ERROR");
                response_messages.setDescripcion("Formato de la contraseña invalido");
                return new ResponseEntity(response_messages, HttpStatus.BAD_REQUEST);
            }
            if (userServicemanager.findByCorreo(requestService.getCorreo()) != null) {
                response_messages.setMessages("ERROR");
                response_messages.setDescripcion("El correo ya existe");
                return new ResponseEntity(response_messages, HttpStatus.OK);
            }
          return new ResponseEntity(userServicemanager.insertUsuario(requestService),HttpStatus.OK);
        } catch (Exception e) {
            response_messages.setMessages("ERROR");
            response_messages.setDescripcion(e.toString());
            return new ResponseEntity(response_messages, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
