package com.koreaIT.example.JAM.service;

import java.sql.Connection;
import java.util.Map;

import com.koreaIT.example.JAM.dao.MemberDao;
import com.koreaIT.example.JAM.dto.Member;

public class MemberService {
	// MemberService 클래스
	// MemberController 클래스와 MemberDao 클래스 간 통신 관련 코드 저장
	
	private MemberDao memberDao;
	// MemberDao 타입 전역변수 memberDao 선언

	public MemberService(Connection conn) {
		// MemberService 생성자 객체
		// Connection 타입 변수 conn을 매개변수로 전달받아 사용
		
		this.memberDao = new MemberDao(conn);
		// 전역변수 memberDao에 변수 conn을 인자로 넘겨서 생성된 새 MemberDao 객체를 저장
	}

	public boolean isLoginIdDup(String loginId) {
		// boolean 타입 isLoginIdDup() 메서드
		// String 타입 변수 loginId를 매개변수로 전달받아 사용
		
		return memberDao.isLoginIdDup(loginId);
		// 전역변수 memberDao 리모컨 누르고 isLoginIdDup() 호출해서 return된 데이터를 return
		// 변수 loginId를 인자로 전달
	}

	public void doJoin(String loginId, String loginPw, String name) {
		// doJoin() 메서드
		// String 타입 변수 loginId, loginPw, name을 매개변수로 전달받아 사용
		
		memberDao.doJoin(loginId, loginPw, name);
		// 변수 memberDao 리모컨 눌고 doJoin() 메서드 호출
		// 변수 loginId, loginPw, name을 인자로 전달
	}

	public Member getMemberByLoginId(String loginId) {
		// Member 타입 getMemberByLoginId() 메서드
		// String 타입 변수 loginId를 매개변수로 전달받아 사용
		
		Map<String, Object> memberMap = memberDao.getMemberByLoginId(loginId);
		// Map 타입 변수 memberMap에 변수 memberDao 리모컨 누르고 getMemberByLoginId() 메서드 호출해서 return된 데이터 저장
		// 변수 loginId를 인자로 전달
		
		if (memberMap.isEmpty()) {
			// 만약 변수 memberMap 리모컨 누르고 isEmpty() 메서드 호출해서 비어있다면
			
			return null; // null을 return
		}
		// 비어있지 않다면 조건문 탈출해서 다음 코드로
		
		return new Member(memberMap);
		// 변수 memberMap을 인자로 전달해서 생성한 새 Member 객체를 return
	}
}
