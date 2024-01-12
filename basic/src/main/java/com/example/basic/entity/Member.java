package com.example.basic.entity;

import com.example.basic.type.MemberType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MEMBER")
//@Data
@Getter @Setter @NoArgsConstructor
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long memberId;
    private String memberName;
    @Column(unique = true)
    private String memberEmail;
    private String memberPassword;
    private Integer memberAge;
    @Enumerated(EnumType.STRING)
    private MemberType memberType;

}
