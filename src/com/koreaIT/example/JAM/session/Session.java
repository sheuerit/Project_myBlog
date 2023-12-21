package com.koreaIT.example.JAM.session;

import com.koreaIT.example.JAM.dto.Member;

public class Session {
	// Session 클래스
	
	private static int loginedMemeberId;
	// int 타입 변수 loginedMemeberId 선언
	// static으로 데이터를 공유

	private static Member loginedMember;
	// Memeber 타입 변수 loginedMemeber 선언
	
	static {
		// static 블록 (static은 생성자 객체로 초기화를 못하니까 static 블록 내에서 초기화) 
		
		loginedMemeberId = -1;
		// 변수 loginedMemeberId에 -1 저장해서 초기화
		
		loginedMember = null;
		// 변수 loginedMemeber에 null 저장하고 비워서 초기화
	}
	
	public static Member getLoginedMember() {
		// Member 타입 getLoginedMember() 메서드
		
		return loginedMember;
		// 변수 loginedMember를 return
	}
	
	public static int getLoginedMemeberId() {
		// Member 타입 getLoginedMember() 메서드
		
		return loginedMemeberId;
		// 변수 loginedMember를 return
	}

	public static void login(Member member) {
		// login() 메서드
		// Member 타입 변수 member를 매개변수로 전달받아 사용
		
		loginedMemeberId = member.id;
		// 변수 loginedMemeberId에 변수 member 리모컨 누르고 변수 id 호출해서 저장
		
		loginedMember = member;
		// 변수 loginedMemeber에 변수 member 저장
	}
	
	public static boolean isLogined() {
		// boolean 타입 isLogined() 메서드
		
		return loginedMemeberId != -1;
		// 변수 loginedMemeberId가 -1과 같지 않다(true)을 return
	}

	public static void logout() {
		// logout() 메서드
		
		loginedMemeberId = -1;
		// 변수 loginedMemeberId에 -1 저장
		
		loginedMember = null;
		// 변수 loginedMemeber에 null 저장
	}
}
