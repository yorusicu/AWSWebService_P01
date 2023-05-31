package com.aws.practice.repository;

import com.aws.practice.domain.Members;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class MembersRepositoryTest {
    @Autowired
    MemberRepository repository;

    @Test
    @Transactional
    void MemberRepositoryTest() throws Exception{
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

        repository.save(members1);
        repository.save(members2);
        repository.save(members3);
        repository.save(members4);

        // when
        List<Members> all = repository.findAll();

        // then


    }
}