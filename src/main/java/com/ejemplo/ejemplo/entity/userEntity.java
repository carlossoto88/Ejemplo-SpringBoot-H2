package com.ejemplo.ejemplo.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class userEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "correo",nullable = false)
    private String correo;
    @Column(name = "passw",nullable = false)
    private String passw;
    @Column(name = "created")
    private Date created;
    @Column(name = "modified")
    private Date modified;
    @Column(name = "last_login")
    private Date last_login;
    @Column(name = "token")
    private String token;
    @Column(name = "isactive")
    private int isactive;
    @OneToMany(targetEntity = phoneEntity.class, cascade = { CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id")
    private List<phoneEntity>phoneList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }

    public List<phoneEntity> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<phoneEntity> phoneList) {
        this.phoneList = phoneList;
    }
}
