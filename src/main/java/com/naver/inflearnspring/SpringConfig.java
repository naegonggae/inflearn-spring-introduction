package com.naver.inflearnspring;

import com.naver.inflearnspring.repository.MemberRepository;
import com.naver.inflearnspring.repository.MemoryMemberRepository;
import com.naver.inflearnspring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
// 자바 코드로 직접 스프링 빈을 등록하는 방법
	// 정형화 되어있지 않거나 상황에 따라 구현클래스를 변경해야하는경우 설정을 통해 스프링 빈에 등록한다.

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}

}
