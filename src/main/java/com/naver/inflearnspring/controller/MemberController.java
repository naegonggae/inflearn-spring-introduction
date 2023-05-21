package com.naver.inflearnspring.controller;

import com.naver.inflearnspring.domain.Member;
import com.naver.inflearnspring.service.MemberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
// Controller, service, repository 어노테이션들은 다 component를 포함하고 있다.
// component가 붙은 곳은 컴포넌트 스캔으로 스프링 컨테이너에 저장이되고 Autowired로 자동 의존관계가 설정된다.
// 스프링은 스프링 컨테이너에 스프링 빈을 등록할때 기본적으로 싱글톤으로 등록한다.
public class MemberController {
	// 컨트롤러 어노테이션이 붙은 클래스가 있으면 스프링 컨테이너에 멤버 컨트롤러 객체를 생성하고 관리한다.
	// = 스프링 컨테이너에서 bean이 관리 된다.

	private  MemberService memberService;
//	@Autowired private final MemberService memberService; 1. 필드 주입 방법

//	@Autowired // 2. setter 주입 방법
//	public void setMemberService(MemberService memberService) {
//		this.memberService = memberService;
//		변경될수 있어서 권장하지 않음
//	}

	// 3. 생성자 주입 방법
	@Autowired // 컨테이너에서 memberService를 찾아오는 역할
	public MemberController(MemberService memberService) {

		this.memberService = memberService;
		System.out.println("memberService " + memberService.getClass());
	}
	// MemberController가 생성이 될때 스프링 빈에 등록되어있는 멤버 서비스 객체를 가져다 넣어줌 = DI = 의존관계 주입

	@GetMapping("/members/new")
	public String createForm() {
		return "members/createMemberForm";
	}
	// cmd + option + L = 코드 정렬

	@PostMapping("/members/new")
	public String create(MemberForm memberForm) {
		Member member = new Member();
		member.setName(memberForm.getName());

		System.out.println("membre = "+ member.getName());

		memberService.join(member);

		return "redirect:/"; // home으로 보내기
	}

	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);

		return "members/memberList";
		// cmd + E = 가장 최근에 봤던 파일 리스트
		// 메모리에 저장한경우 동작을 멈출경우 데이터가 비워진다.
	}
}
