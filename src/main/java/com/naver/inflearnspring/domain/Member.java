package com.naver.inflearnspring.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // 이건 jpa가 관리하는 엔티티다.라는 표시
public class Member {

	@Id // pk mapping
	@GeneratedValue(strategy = GenerationType.IDENTITY) // id 자동 번호 매기기
	private Long id;
//	@Column(name = "name") // 컬럼명 설정
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
