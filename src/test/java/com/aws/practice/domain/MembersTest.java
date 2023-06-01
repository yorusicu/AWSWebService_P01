package com.aws.practice.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@Rollback(value = false)
class MembersTest {
    @PersistenceContext
    private EntityManager em;

    @Test
    @Transactional
    void MemberTest() throws Exception{
        // given
        Members members1 = Members.builder()
                .name("영일")
                .birth("20000101")
                .phone("01012345678")
                .email("memberTest1@email.com")
                .build();
        Members members2 = Members.builder()
                .name("영이")
                .birth("20000202")
                .phone("01087654321")
                .email("memberTest2@email.com")
                .build();
        Members members3 = Members.builder()
                .name("영삼")
                .birth("20000303")
                .phone("01012344321")
                .email("memberTest3@email.com")
                .build();
        Members members4 = Members.builder()
                .name("영사")
                .birth("20000404")
                .phone("01056781234")
                .email("memberTest4@email.com")
                .build();

        em.persist(members1);
        em.persist(members2);
        em.persist(members3);
        em.persist(members4);

        em.flush();
        em.clear();

        // when
        List<Members> findMembers = em.createQuery("select m from Members m", Members.class).getResultList();

        // then
        for (Members members : findMembers) {
            System.out.println("members = " + members);
            System.out.println("members = " + members.getPost());
        }

        assertThat(members4.getMemberId()).isEqualTo(4);

    }
}