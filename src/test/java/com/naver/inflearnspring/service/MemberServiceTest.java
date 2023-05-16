package com.naver.inflearnspring.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.naver.inflearnspring.domain.Member;
import com.naver.inflearnspring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

	MemberService memberService;
	MemoryMemberRepository memberRepository;
	// 서비스에서 new 한 인스턴스랑 다른 객체다.

	@BeforeEach
	void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
		// dependency injection
	}

	@AfterEach
	void afterEach() {
		memberRepository.clearStore();
	}
	// control + r = 이전에 실행했던거 실행
	// 테스트코드 명은 한글로 적어도 무방
	@Test
	void 회원가입() {
		// given
		Member member = new Member();
		member.setName("spring");

		// when
		Long savedId = memberService.join(member);

		// then
		Member findMember = memberService.findOne(savedId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}

	@Test
	void 회원가입할때_중복체크() {
		// given
		Member member1 = new Member();
		member1.setName("spring");

		Member member2 = new Member();
		member2.setName("spring");

		// when
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class,
				() -> memberService.join(member2));
		// 예외가 터지는지 확인
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//		try {
//			memberService.join(member2);
//			fail();
//		} catch (IllegalStateException e) {
//			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//		}
		// 위와 같이 try catch로도 할수있긴함

		// then

	}
	@Test
	void findMembers() {
	}

	@Test
	void findOne() {
	}
}