package com.example.basic.repository;

import com.example.basic.entity.Member;
import com.example.basic.type.MemberType;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class RepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void saveTest(){
        Member hds = new Member();
        Member hgd = new Member();

        hds.setMemberName("한동석");
        hds.setMemberAge(20);
        hds.setMemberEmail("tedhan1204@gmail.com");
        hds.setMemberPassword("1234");
        hds.setMemberType(MemberType.MEMBER);
        assertThat(memberRepository.save(hds)).isEqualTo(hds);

        hgd.setMemberName("홍길동");
        hgd.setMemberAge(15);
        hgd.setMemberEmail("hgd1204@gmail.com");
        hgd.setMemberPassword("5555");
        hgd.setMemberType(MemberType.ADMIN);
        assertThat(memberRepository.save(hgd)).isEqualTo(hgd);
    }

    @Test
    public void findAllTest(){
        memberRepository.findAll().forEach(log::info);
    }

    @Test
    public void findByNameTest(){
        assertThat(memberRepository.findByMemberName("홍길동").get(0).getMemberAge()).isEqualTo(15);
    }

    @Test
    public void updateTest(){
        Member hgd = new Member();
        hgd.setMemberName("홍길동");
        hgd.setMemberAge(19);
        hgd.setMemberEmail("hgd1234@gmail.com");
        hgd.setMemberPassword("9999");
        hgd.setMemberType(MemberType.MEMBER);
        assertThat(memberRepository.save(hgd)).isEqualTo(hgd);

//        entityManager.flush();
//        entityManager.clear();

        memberRepository.updateByMemberAgeLoe(20);

        assertThat(memberRepository.findById(3L).getMemberName()).isEqualTo("이순신");
    }
}














