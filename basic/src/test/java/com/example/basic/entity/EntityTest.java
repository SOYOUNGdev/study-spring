package com.example.basic.entity;

import com.example.basic.type.Brand;
import com.example.basic.type.MemberType;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class EntityTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void memberTest(){
        Member member = new Member();
        member.setMemberName("한동석");
        member.setMemberAge(20);
        member.setMemberEmail("tedhan1204@gmail.com");
        member.setMemberPassword("1234");
        member.setMemberType(MemberType.MEMBER);

//        영속성 컨텍스트에 등록, 1차 캐시에 저장
        entityManager.persist(member);

//        쓰기 지연 저장소의 SQL문을 모두 즉시 실행
//        entityManager.flush();
//        영속성 컨텍스트 모두 비우기, 1차 캐시도 비워진다.
//        entityManager.clear();

//        1차 캐시 검사 후 동일한 주소가 있을 때에는 SQL문이 실행되지 않지만,
//        동일한 주소가 없을 경우 SQL문이 실행된다.
        Member foundMember = entityManager.find(Member.class, 1L);

        log.info("member: " + member);
        log.info("foundMember: " + foundMember);

        assertThat(member).isEqualTo(foundMember);
    }

    @Test
    public void updateTest(){
        Member foundMember = entityManager.find(Member.class, 1L);
//        변경 감지(더티 체킹)
        foundMember.setMemberAge(25);
    }

    @Test
    public void deleteTest(){
//        삭제할 때에는 영속 상태인 객체만 가능하다.
        entityManager.remove(entityManager.find(Member.class, 1L));
    }

    @Test
    public void persistTest(){
        SuperCar ferrari = new SuperCar();
        SuperCar lamborghini = new SuperCar();

        ferrari.setSuperCarBrand(Brand.Ferrari);
        ferrari.setSuperCarName("488");
        ferrari.setSuperCarColor("RED");
        ferrari.setSuperCarPrice(380_000_000L);
        ferrari.setSuperCarReleaseDate(LocalDateTime.now());

        lamborghini.setSuperCarBrand(Brand.Lamborghini);
        lamborghini.setSuperCarName("우라칸");
        lamborghini.setSuperCarColor("Black");
        lamborghini.setSuperCarPrice(580_000_000L);
        lamborghini.setSuperCarReleaseDate(LocalDateTime.of(2022, 11, 28, 0, 0));

//        날짜 형식을 사용해서 문자열 타입으로 변경하기
//        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        entityManager.persist(ferrari);
        entityManager.persist(lamborghini);
    }

    @Test
    public void findTest(){
        SuperCar superCar = entityManager.find(SuperCar.class, 1L);
        assertThat(superCar.getSuperCarBrand().name()).isEqualTo(Brand.Ferrari.name());
    }

    @Test
    public void updateSuperCarTest(){
        SuperCar superCar = entityManager.find(SuperCar.class, 1L);
        superCar.setSuperCarBrand(Brand.Lamborghini);
        superCar.setSuperCarName("우라칸");
    }

    @Test
    public void removeSuperCarTest(){
        entityManager.remove(entityManager.find(SuperCar.class, 1L));
    }

    @Test
    public void findAllTest(){
        List resultList = entityManager.createQuery("select s.superCarName from SuperCar s").getResultList();

        for (Object o : resultList) {
            log.info(String.valueOf(o));
        }
    }
}


















