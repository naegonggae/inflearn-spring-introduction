package com.naver.inflearnspring.repository;

import com.naver.inflearnspring.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
	Member save(Member member);
	Optional<Member> findById(Long id);
	Optional<Member> findByName(String name);
	// optional은 null처리를 하기위해 쓴다고 생각
	List<Member> findAll();

}
