package com.naver.inflearnspring.repository;

import com.naver.inflearnspring.domain.Member;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

	// jpa 쓸려면 이거 주입받아야한다.
	private final EntityManager em;

	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member save(Member member) {
		em.persist(member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member);
	}

	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result = em.createQuery("select m from Member m where m.name = :name",
						Member.class)
				.setParameter("name", name)
				.getResultList();

		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		// jpql 문임 테이블대상이 아닌 객체대상으로 쿼리를 날림
		return em.createQuery("select m from Member m", Member.class)
				.getResultList();
	}
}
