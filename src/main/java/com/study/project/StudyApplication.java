package com.study.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class StudyApplication extends SpringBootServletInitializer {
	
	// 외부 톰캣(WAS) 배포용 설정
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(StudyApplication.class);
	}
	// 내장 톰캣 실행용 (java -jar or IDE 실행)
	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);
	}

}
