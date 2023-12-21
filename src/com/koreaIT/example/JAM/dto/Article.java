package com.koreaIT.example.JAM.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class Article {
	// Article 클래스
	
	public int id; // int 타입 변수 id 선언
	public LocalDateTime regDate; // LocalDateTime 타입 변수 regDate 선언
	public LocalDateTime updateDate; 
	public int memberId;
	public String title; // String 타입 변수 title 선언
	public String body;
	public String writerName;

	public Article(Map<String, Object> articleMap) {
		// Article 생성자 객체
		// 생성자는 클래스의 객체(인스턴스)를 만들 때 호출되는 메서드, 주로 객체의 초기화를 담당하며, 객체가 생성될 때 실행되어 초기 상태를 설정
		// Map 타입 변수 articleMap을 매개변수로 전달받음
		
		this.id = (int) articleMap.get("id");
		// 전역변수 id에 매개변수 articleMap를 Object 타입에서 int 타입으로 수동 형변환 후 리모컨 누르고 get() 메서드 호출해서 return된 데이터 저장
		// id(컬럼)의 값(value)을 인자로 전달
		
		this.regDate = (LocalDateTime) articleMap.get("regDate");
		// 전역변수 regDate에 매개변수 articleMap Object 타입에서 LocalDateTime 타입으로 수동 형변환 후 리모컨 누르고 get() 메서드 호출해서 return된 데이터 저장
		// regDate(컬럼)의 값(value)을 인자로 전달
		
		this.updateDate = (LocalDateTime) articleMap.get("updateDate");
		this.memberId = (int) articleMap.get("memberId");
		this.title = (String) articleMap.get("title");
		// 전역변수 title에 매개변수 articleMap를 Object 타입에서 String 타입으로 수동 형변환 후 리모컨 누르고 get() 메서드 호출해서 return된 데이터 저장
		// title(컬럼)의 값(value)을 인자로 전달
		
		this.body = (String) articleMap.get("body");
		this.writerName = (String) articleMap.get("writerName");
	}
}


