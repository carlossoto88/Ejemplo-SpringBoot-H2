package com.ejemplo.ejemplo.repository;

import com.ejemplo.ejemplo.entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IuserRepository extends JpaRepository<userEntity,Long> {
    userEntity findByCorreo(String correo);
}
