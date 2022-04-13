package com.ejemplo.ejemplo.service;

import com.ejemplo.ejemplo.dao.phoneDAO;
import com.ejemplo.ejemplo.dao.userDAO;
import com.ejemplo.ejemplo.entity.phoneEntity;
import com.ejemplo.ejemplo.entity.userEntity;
import com.ejemplo.ejemplo.repository.IuserRepository;
import com.ejemplo.ejemplo.utils.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class userService {
    @Autowired
    IuserRepository iuserRepository;
    @Autowired
    utils utils;


    public userDAO findByCorreo(String correo) {
        userEntity byCorreo = iuserRepository.findByCorreo(correo);
        if (byCorreo == null)
            return null;
        return convertuserEntity_To_userDAO(byCorreo);
    }

@Transactional
    public userDAO insertUsuario(userDAO userDAO) {
       return convertuserEntity_To_userDAO( iuserRepository.save(convert_userDAO_To_userEntity(userDAO)));

    }

    //convierte de entidad de bd a clases DAO
    private userDAO convertuserEntity_To_userDAO(userEntity userEntity) {
        userDAO userDAO = new userDAO();
        userDAO.setId(userEntity.getId());
        userDAO.setCorreo(userEntity.getCorreo());
        userDAO.setCreated(userEntity.getCreated());
        userDAO.setToken(userEntity.getToken());
        if (userEntity.getIsactive() == 1)
            userDAO.setIsactive(true);
        else
            userDAO.setIsactive(false);
        userDAO.setLast_login(userEntity.getLast_login());
        userDAO.setModified(userEntity.getModified());
        if (userEntity.getPhoneList() != null || userEntity.getPhoneList().size() > 0) {
            List<phoneDAO> phoneList = new ArrayList<>();

            for (phoneEntity currentPhoneEntity : userEntity.getPhoneList()) {
                phoneDAO phoneDAO = new phoneDAO();
                phoneDAO.setId(currentPhoneEntity.getId());
                phoneDAO.setCitycode(currentPhoneEntity.getCitycode());
                phoneDAO.setContrycode(currentPhoneEntity.getContrycode());
                phoneDAO.setNumber(currentPhoneEntity.getNumber());

                phoneList.add(phoneDAO);
            }
            userDAO.setPhoneList(phoneList);
        }
        return userDAO;
    }

    //convertir de DAO a entity
    private userEntity convert_userDAO_To_userEntity(userDAO userDAO) {

        Date fechaactual = new Date();
        userEntity userEntity = new userEntity();
        userEntity.setCorreo(userDAO.getCorreo());
        userEntity.setCreated(fechaactual);
        userEntity.setIsactive(1);
        userEntity.setModified(fechaactual);
        userEntity.setPassw(userDAO.getPassw());
        userEntity.setLast_login(fechaactual);
        userEntity.setId(utils.crearUuid().toString());
        userEntity.setToken(utils.crearUuid().toString());
        if (userDAO.getPhoneList() != null || userDAO.getPhoneList().size() > 0) {
            List<phoneEntity> phoneList = new ArrayList<>();
            for (phoneDAO currentPhoneDAO : userDAO.getPhoneList()) {
                phoneEntity phoneEntity = new phoneEntity();
                phoneEntity.setCitycode(currentPhoneDAO.getCitycode());
                phoneEntity.setContrycode(currentPhoneDAO.getContrycode());
                phoneEntity.setNumber(currentPhoneDAO.getNumber());
                phoneList.add(phoneEntity);

            }
            userEntity.setPhoneList(phoneList);
        }
        return userEntity;
    }
}
