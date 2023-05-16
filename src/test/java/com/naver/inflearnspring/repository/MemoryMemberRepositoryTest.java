package com.naver.inflearnspring.repository;

import static org.assertj.core.api.Assertions.*; // static import
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import com.naver.inflearnspring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {
	// 테스트에는 순서가 보장되지 않는다.
	// 의존관계도 없어야한다.
	MemoryMemberRepository repository = new MemoryMemberRepository();

	@AfterEach
	public void afterEach() {
		repository.clearStore();
		// 유닛 테스트가 종료할때마다 데이터를 비워주는 역할
	}

	@Test
	void save() {
		Member member = new Member();
		member.setName("spring");

		repository.save(member);

		Member result = repository.findById(member.getId()).get();
//		Assertions.assertEquals(member, result);
//		Assertions.assertEquals(member, null);
		assertThat(member).isEqualTo(result);
		// static import도 가능
		// 요즘 이렇게 쓴다고함
	}

	@Test
	void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);

		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		// shift + F6 = 변수명 일괄 변경하기

		Member result = repository.findByName("spring1").get();
		assertThat(result).isEqualTo(member1);
//		assertThat(result).isEqualTo(member2);
	}

	@Test
	void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);

		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);

		List<Member> result = repository.findAll();
		// option + cmd + v = 자료형과 변수명 자동 지정

		assertThat(result.size()).isEqualTo(2);
	}
}