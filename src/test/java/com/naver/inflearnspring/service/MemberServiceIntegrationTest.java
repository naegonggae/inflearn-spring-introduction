package com.naver.inflearnspring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.naver.inflearnspring.domain.Member;
import com.naver.inflearnspring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
// 통합테스트보다 단위 테스트가 더 좋은 테스트라고 할 수 있다.
// 단위 테스트는 독립적으로 이루어지고 스프링 컨테이너를 거치지않아 빠르다
@SpringBootTest
@Transactional // 테스트 클래스에 있을때만 아래의 기능을 하고 그냥 보통 코드에 있으면 잘 코밋해서 저장해줌
// 테스트는 계속 반복가능해야한다.
// 그래서 트랜잭션을 붙여서 insert를 날려도 나중에 db에 코밋되기전에 롤백해놓는다. 즉, db에 직접 코밋이 되기전에 롤백해 반복가능한 테스트가 된다.
// 테스트를 잘짜자!
public class MemberServiceIntegrationTest {

	// test에서는 직접 DI해줘도 상관없다.
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;

	@Test
	void 회원가입() {
		// given
		Member member = new Member();
		member.setName("hoho");

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

		// then

	}
}
