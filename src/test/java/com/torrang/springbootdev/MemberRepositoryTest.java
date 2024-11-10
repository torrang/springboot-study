package com.torrang.springbootdev;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Sql("/insert.sql")
    @Test
    void getAllMembers() {
        // when
        List<Member> members = memberRepository.findAll();

        // then
        assertThat(members.size()).isEqualTo(3);
    }

    @Sql("/insert.sql")
    @Test
    void getMemberById() {
        // when
        Member member = memberRepository.findById(2L).get();

        // then
        assertThat(member.getId()).isEqualTo(2L);
    }

    @Sql("/insert.sql")
    @Test
    void getMemberByName() {
        // when
        Member member = memberRepository.findByName("A").get();

        // then
        assertThat(member.getName()).isEqualTo("A");
    }

    @Test
    void saveMember() {
        // given
        Member member = new Member(1L, "A");

        // when
        memberRepository.save(member);

        // then
        assertThat(memberRepository.findById(1L).get().getName()).isEqualTo("A");
    }

    @Test
    void saveMembers() {
        // given
        List<Member> members = List.of(
            new Member(1L, "A"),
            new Member(2L, "B")
        );

        // when
        memberRepository.saveAll(members);

        // then
        assertThat(memberRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    @Sql("/insert.sql")
    void deleteMember() {
        // when
        memberRepository.deleteById(1L);

        // then
        assertThat(memberRepository.findById(1L).isEmpty()).isTrue();
    }

    @Test
    @Sql("/insert.sql")
    void deleteAllMembers() {
        // when
        memberRepository.deleteAll();

        // then
        assertThat(memberRepository.findAll().size()).isEqualTo(0);
    }
}