package com.naver.inflearnspring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect// Aop 사용할때는 이거 적어줘야함
@Component // 이렇게 스프링 빈에 등록해줘도 됨 하지만 스프링 컨피그에 넣어서 해주는게 좋음
public class TimeTraceAop {

	// 이렇게 시간을 찍으면 어디서 병목이 있는지 파악할 수 있다.
	// 공통관심사항을 어디에 적용시킬것인가
	@Around("execution(* com.naver.inflearnspring..*(..))") // 처음에 명시한 패키지 내부는 모두 적용, 클래스단위까지 세부적으로 적용시키기도 가능
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		System.out.println("START : " + joinPoint.toString());

		try {
			return joinPoint.proceed();
		} finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
		}
	}
}
