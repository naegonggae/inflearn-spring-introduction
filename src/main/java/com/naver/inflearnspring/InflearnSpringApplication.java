package com.naver.inflearnspring;
// spring이 자동으로 컴포넌트 스캔하는 범위는 위의 패키지경로이다.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InflearnSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(InflearnSpringApplication.class, args);
	}

}
