package com.naver.inflearnspring.repository;

import com.naver.inflearnspring.domain.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository{

	private static Map<Long, Member> store = new HashMap<>();
	// key와 value로 접근
	private static long sequence = 0L;

	@Override
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		return Optional.ofNullable(store.get(id));
		// null일경우를 대비해서 Optional로 감싸준다.
	}

	@Override
	public Optional<Member> findByName(String name) {
		return store.values().stream()
				.filter(member -> member.getName().equals(name))
				// 루프를 돌고 같은 이름이 있으면 반환
				.findAny();
		// 끝까지 돌았는데 없으면 Optional에 null이 포함되서 반환
	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
		// .values values만 모두 출력 list형태임
		// store에 value가 member이니까
	}

	public void clearStore() {
		store.clear();
	}
}
