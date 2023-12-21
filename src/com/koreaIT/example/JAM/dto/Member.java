package com.koreaIT.example.JAM.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class Member {
	// Member 클래스
	
	public int id; // int 타입 변수 id 선언
	public LocalDateTime regDate; // LocalDateTime 타입 변수 regDate 선언
	public LocalDateTime updateDate; 
	public String loginId; // String 타입 변수 title 선언
	public String loginPw;
	public String name;
	
	public Member(Map<String, Object> memberMap) {
		// Memeber 생성자 객체
		// Map 타입 변수 articleMap을 매개변수로 전달받음
		
		this.id = (int) memberMap.get("id");
		// 변수 id에 매개변수 memberMap를 Object 타입에서 int 타입으로 수동 형변환 후 리모컨 누르고 get() 메서드 호출해서 return된 데이터 저장
		// id(컬럼)의 값(value)을 인자로 전달
		
		this.regDate = (LocalDateTime) memberMap.get("regDate");
		// 변수 regDate에 매개변수 memberMap Object 타입에서 LocalDateTime 타입으로 수동 형변환 후 리모컨 누르고 get() 메서드 호출해서 return된 데이터 저장
		// regDate(컬럼)의 값(value)을 인자로 전달
		
		this.updateDate = (LocalDateTime) memberMap.get("updateDate");
		this.loginId = (String) memberMap.get("loginId");
		// 변수 title에 매개변수 memberMap를 Object 타입에서 String 타입으로 수동 형변환 후 리모컨 누르고 get() 메서드 호출해서 return된 데이터 저장
		// title(컬럼)의 값(value)을 인자로 전달
		
		this.loginPw = (String) memberMap.get("loginPw");
		this.name = (String) memberMap.get("name");
	}
}


