package com.example.basic.repository;

import com.example.basic.entity.SuperCar;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class SuperCarRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public SuperCar save(SuperCar superCar){
        entityManager.persist(superCar);
        return superCar;
    }

    public Optional<SuperCar> findById(Long superCarId){
        return Optional.ofNullable(entityManager.find(SuperCar.class, superCarId));
    }

    public List<SuperCar> findAll(){
        return entityManager.createQuery("select s from SuperCar s").getResultList();
    }

    public List<SuperCar> findAllBySuperCarReleaseDate(String superCarReleaseDate){
        return entityManager
                .createQuery("select s from SuperCar s where function('to_char', s.superCarReleaseDate, 'yyyyMMdd') = :superCarReleaseDate")
                .setParameter("superCarReleaseDate", superCarReleaseDate)
                .getResultList();
    }

    public List<SuperCar> findAllBySuperCarColorContaining(String superCarColor){
        return entityManager.createQuery("select s from SuperCar s where s.superCarColor like :superCarColor")
                .setParameter("superCarColor", "%" + superCarColor + "%")
                .getResultList();
    }

//    실습
//    시작 날짜와 종료 날짜를 전달받은 뒤 해당 기간 내에 출시된 슈퍼카 목록 전체 조회
    public List<SuperCar> findAllBySuperCarReleaseDateBetween(LocalDateTime startDate, LocalDateTime endDate){
        return entitiyManager.createQuery("se")
    }
}

























