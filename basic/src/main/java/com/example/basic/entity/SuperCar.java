package com.example.basic.entity;

import com.example.basic.type.Brand;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_SUPER_CAR")
@Data
public class SuperCar {
    @Id @GeneratedValue
    private Long superCarId;
    @Enumerated(EnumType.STRING)
    private Brand superCarBrand;
    private String superCarName;
    private String superCarColor;
    private Long superCarPrice;
    private LocalDateTime superCarReleaseDate;
}
