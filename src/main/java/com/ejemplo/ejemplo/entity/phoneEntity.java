package com.ejemplo.ejemplo.entity;

import javax.persistence.*;

@Entity
@Table(name = "phone")
public class phoneEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "number")
    private String number;
    @Column(name = "citycode")
    private String citycode;
    @Column(name = "contrycode")
    private String contrycode;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getContrycode() {
        return contrycode;
    }

    public void setContrycode(String contrycode) {
        this.contrycode = contrycode;
    }
}
