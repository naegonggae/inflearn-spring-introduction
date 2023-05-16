package com.naver.inflearnspring.service;

import com.naver.inflearnspring.domain.Member;
import com.naver.inflearnspring.repository.MemoryMemberRepository;
import java.util.List;
import java.util.Optional;

public class MemberService {

	private final MemoryMemberRepository memberRepository = new MemoryMemberRepository();

	/** 회원가입 **/
	public Long join(Member member) {
		// 같은 이름이 있는 중복 회원 X
//		Optional<Member> result = memberRepository.findByName(member.getName());
////		Member member1 = result.get();
////		Optional 안쓰고 위와 같이 꺼낼 수도 있다.
//		result.ifPresent(m -> {
//			throw new IllegalStateException("이미 존재하는 회원입니다.");
//		});
		// 값이 있으면 진행

		validateDuplicateMember(member);
		// option + cmd + m = 드래그한 로직을 메서드로 만들기

		memberRepository.save(member);
		return member.getId();


	}

	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
						.ifPresent(m2 -> {
							throw new IllegalStateException("이미 존재하는 회원입니다.");
						});
		// 이렇게 간결하게 쓸 수도 있음 어짜피 optional로 묶이는걸 아니까
	}

	/** 전체 회원 조회 **/
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	/** 회원 한명 조회 **/
	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}


}
