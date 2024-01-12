package com.example.basic.repository;

import com.example.basic.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Member save(Member member){
        entityManager.persist(member);
        return member;
    }

//    JPQL에서는 엔티티명과 필드명은 대소문자를 구분하지만 키워드는 대소문자를 구분하지 않는다.
//    테이블명 자리에는 테이블명이 아닌 엔티티명을 작성하고 반드시 알리아스를 작성해야 한다.
    public List<String> findAll(){
        return entityManager
                .createQuery("select m.memberEmail from Member m", String.class)
                .getResultList();
    }

    public List<Member> findByMemberName(String memberName){
        return entityManager.createQuery("select m from Member m where m.memberName = :memberName")
                .setParameter("memberName", memberName)
                .getResultList();
    }

    public Member findById(Long memberId){
        return entityManager.find(Member.class, memberId);
    }

//    벌크 연산
//    영속성 컨텍스트와 관련 없이 바로 SQL문이 실행된다.
//    기존에 저장되었던 영속성 컨텍스트의 정보와 RDB의 정보가 다르게 될 수 있다.
    public void updateByMemberAgeLoe(int age){
        entityManager.flush();
        entityManager.clear();

        entityManager
                .createQuery("update Member m set m.memberName = '이순신' where m.memberAge <= :age")
                .setParameter("age", age)
                .executeUpdate();
    }
}





















