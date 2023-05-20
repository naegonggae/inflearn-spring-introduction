package com.naver.inflearnspring;

import com.naver.inflearnspring.repository.JdbcMemberRepository;
import com.naver.inflearnspring.repository.JdbcTemplateMemberRepository;
import com.naver.inflearnspring.repository.JpaMemberRepository;
import com.naver.inflearnspring.repository.MemberRepository;
import com.naver.inflearnspring.repository.MemoryMemberRepository;
import com.naver.inflearnspring.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
// 자바 코드로 직접 스프링 빈을 등록하는 방법
	// 정형화 되어있지 않거나 상황에 따라 구현클래스를 변경해야하는경우 설정을 통해 스프링 빈에 등록한다.

//	private final DataSource dataSource;
//	@Autowired
//	public SpringConfig(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}

	// jpa할때 DI
//	@PersistenceContext
	private final EntityManager em;
	@Autowired
	public SpringConfig(EntityManager em) {
		this.em = em;
	}


	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
//		return new MemoryMemberRepository();
//		return new JdbcMemberRepository(dataSource);
//		return new JdbcTemplateMemberRepository(dataSource);
		return new JpaMemberRepository(em);
	}
	// spring의 장점
	// 메모리사용하는 로직 쓰다가 h2 db 쓰도록 로직을 바꿨는데 인터페이스도 같은걸 사용해서 구현했고(다형성)
	// DI 설정도 조립만 하니까 객체 지향적이다.
	// 개방폐쇄 원칙이 지켜진 부분 OCP 확장에는 열려있고 수정, 변경에는 닫혀있다.
	// 스프링의 DI (Dependencies Injection)을 사용하면 기존 코드를 전혀 손대지 않고, 설정만으로 구현 클래스를 변경할 수 있다.

}
