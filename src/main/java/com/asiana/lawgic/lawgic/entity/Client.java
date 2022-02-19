package com.asiana.lawgic.lawgic.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class Client {

    @Id
    private String clientId;

    private String password;

    private String name;

    private Date birthday;

    private String email;

    private String address;

    private String phone;

    private String carType;

    private int gender;


}