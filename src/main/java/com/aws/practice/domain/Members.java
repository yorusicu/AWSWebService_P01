package com.aws.practice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Table(name = "members")
public class Members extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;
    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "phone", length = 15)
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "birth", length = 10)
    private String birth;

//    @OneToMany(mappedBy = "member")
//    private List<Posts> post = new ArrayList<>();

    @Builder
    public Members(String name, String phone, String email, String birth) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birth = birth;
    }
}
