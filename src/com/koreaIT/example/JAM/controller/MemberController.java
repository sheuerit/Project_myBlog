package com.koreaIT.example.JAM.controller;

import java.sql.Connection;
import java.util.Scanner;

import com.koreaIT.example.JAM.dto.Member;
import com.koreaIT.example.JAM.service.MemberService;
import com.koreaIT.example.JAM.session.Session;
import com.koreaIT.example.JAM.util.Util;

public class MemberController {
	// MemberController 클래스
	// member 관련 기능 코드 저장
	
	private Scanner sc;
	// Scanner 타입 전역변수 sc 선언 
	
	private MemberService memberService;
	// MemberService 타입 전역변수 memberService 선언
	
	public MemberController(Connection conn, Scanner sc) {
		// MemberController 생성자 객체
		// Connection 타입 변수 conn, Scanner 타입 변수 sc를 매개변수로 전달받아 사용
		
		this.sc = sc;
		// 전역변수 sc에 전달받은 매개변수 sc 저장
		
		this.memberService = new MemberService(conn);
		// 전역변수 memberService에 새 MemberService 객체 생성해서 저장
		// 변수 conn을 인자로 전달
	}

	public void doJoin() {
		// dojoin() 메서드
		
		if (Session.isLogined()) {
			// 만약 Session 클래스 리모컨 누르고 isLogined() 호출해서 return된 데이터가 true와 같다면
			
			System.out.println("로그아웃 후 이용해주세요");
			return; // return으로 메서드 종료
		}
		
		String loginId = null;
		// String 타입 변수 loginId에 null 저장해서 비움
		
		String loginPw = null;
		String name = null;
		
		System.out.println("== 회원가입 ==");

		while (true) {
			// 무한반복문, 아이디 검증

			System.out.printf("아이디 : ");
			loginId = sc.nextLine().trim();
			// 변수 loginId에 변수 sc 리모컨 누르고 nextLine() 메서드 호출해서 return된 데이터(입력한 제목) 저장
			
			if (loginId.length() == 0) {
				// 만약 변수 loginId 리모컨 누르고 length() 메서드 호출해서 길이가 0이면
				System.out.println("아이디를 입력해주세요");
				continue; // 무한반복문 처음으로
			}
			
			boolean isLoginedDup = memberService.isLoginIdDup(loginId);
			// boolean 타입 변수 isLoginedDup에 변수 memberService 리모컨 누르고 isLoginIdDup() 메서드 호출해서 return된 데이터 저장
			// 변수 loginId을 인자로 전달
			
			if (isLoginedDup) {
				// 만약 변수 isLoginedDup가 true라면 
				
				System.out.printf("%s은(는) 이미 사용중인 아이디입니다\n", loginId);
				continue; // 무한반복문 처음으로
			}
			// false라면 조건문 탈출해서 다음 코드로
			
			System.out.printf("%s은(는) 이미 사용 가능한 아이디입니다\n", loginId);
			
			break; // 모든 조건문을 통과했다면 무한반복문 탈출해서 다음 코드로 
		}
		
		while (true) {
			// 무한반복문, 비밀번호 검증
			
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine().trim();
			// 변수 loginPw에 변수 sc 리모컨 누르고 nextLine() 메서드 호출해서 return된 데이터(입력한 내용) 저장
			
			if (loginPw.length() == 0) {
				// 만약 변수 loginPw 리모컨 누르고 length() 메서드 호출해서 길이가 0이면
				
				System.out.println("비밀번호를 입력해주세요");
				continue; // 무한반복문 처음으로
			}
			
			System.out.printf("비밀번호 확인: ");
			String loginPwChk = sc.nextLine().trim();
			// String 타입 변수 loginPw에 변수 sc 리모컨 누르고 nextLine() 메서드 호출해서 return된 데이터(입력한 내용) 저장

			if (loginPw.equals(loginPwChk) == false) {
				// 만약 변수 loginPw 리모컨 누르고 equals() 메서드 호출해서 변수 loginPwChk가 false와 같다면
				
				System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
				continue; // 무한반복문 처음으로
			}
			// false와 같지 않다면 조건문 탈출해서 다음 코드로
			
			break; // 길이가 0이 아니면 무한반복문 탈출해서 다음 코드로
		}
		
		while (true) {
			// 무한반복문, 이름 검증
			
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();
			// 변수 name에 변수 sc 리모컨 누르고 nextLine() 메서드 호출해서 return된 데이터(입력한 이름) 저장
			
			if (name.length() == 0) {
				// 만약 변수 name 리모컨 누르고 length() 메서드 호출해서 길이가 0이면
				
				System.out.println("이름을 입력해주세요");
				continue; // 무한반복문 처음으로
			}
			break; // 길이가 0이 아니면 무한반복문 탈출해서 다음 코드로
		}
		
		memberService.doJoin(loginId, loginPw, name);
		// 변수 memberService 리모컨 누르고 doJoin() 메서드 호출
		// 변수 loginId, loginPw, name을 인자로 전달
		
		System.out.printf("%s회원님이 가입되었습니다\n", name);
	}

	public void doLogin() {
		// doLogin() 메서드
		
		if (Session.isLogined()) {
			// 만약 Session 클래스 리모컨 누르고 isLogined() 호출해서 return된 데이터가 true이면
			
			System.out.println("로그아웃 후 이용해주세요");
			return; // return으로 메서드 종료
		}
		
		String loginId = null;
		// String 타입 변수 loginId에 null 저장해서 비움
		
		String loginPw = null;
		
		System.out.println("== 로그인 ==");
		
		while (true) {
			// 무한반복문
			
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine().trim();
			// 변수 loginId에 변수 sc 리모컨 누르고 nextLine() 메서드 호출해서 return된 데이터(입력한 아이디) 저장
			
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine().trim();
			// 변수 loginPw에 변수 sc 리모컨 누르고 nextLine() 메서드 호출해서 return된 데이터(입력한 비밀번호) 저장
			
			if (loginId.length() == 0) {
				// 만약 변수 loginId 리모컨 누르고 length() 메서드 호출해서 길이가 0이면
				
				System.out.println("아이디를 입력해주세요");
				continue; // 무한반복문 처음으로
			}
			
			if (loginPw.length() == 0) {
				// 만약 변수 loginPw 리모컨 누르고 length() 메서드 호출해서 길이가 0이면
				
				System.out.println("비밀번호를 입력해주세요");
				continue; // 무한반복문 처음으로
			}
			// 길이가 0이 아니라면 조건문 탈출해서 다음 코드로
			
			Member member = memberService.getMemberByLoginId(loginId);
			// Member 타입 변수 member에 변수 memberService 리모컨 누르고 getMemberByLoginId() 메서드 호출해서 return된 데이터 저장 
			// 변수 loginId를 인자로 전달
			
			if (member == null) {
				// 만약 변수 member가 null과 같다면
				
				System.out.printf("%s은(는) 존재하지 않는 아이디입니다\n", loginId);
				continue; // 무한반복문 처음으로
			}
			// null과 같지 않다면 조건문 탈출해서 다음 코드로
			
			if (member.loginId.equals(loginPw) == false) {
				// 만약 변수 member 리모컨 누르고 변수 loginId 리모컨 누르고 equals() 메서드 호출해서 false와 같다면
				
				System.out.println("비밀번호가 일치하지 않습니다");
				continue; // 무한반복문 처음으로
			}
			// false와 같지 않다면 조건문 탈출해서 다음 코드로
			
			Session.login(member);
			// Session 클래스 리모컨 누르고 login() 메서드 호출
			// 변수 member를 인자로 전달
			
			System.out.printf("%s님 환영합니다~\n", member.name);
			break; // 조건문 전부 통과했다면 무한반복문 탈출해서 다음 코드로
		}
	}

	public void doLogout() {
		// doLogout() 메서드
		
		if (Session.isLogined() == false) {
			// 만약 Session 클래스 리모컨 누르고 isLogined() 호출해서 return된 데이터가 false와 같다면
			
			System.out.println("로그인 후 이용해주세요");
			return; // return으로 메서드 종료
		}
		// false와 같지 않다면 조건문 탈출해서 다음 코드로
		
		Session.logout();
		// Session 클래스 리모컨 누르고 logout() 메서드 호출
		
		System.out.println("로그아웃 되었습니다");
	}

	public void showProfile() {
		// showProfile() 메서드
		
		if (Session.isLogined() == false) {
			// 만약 Session 클래스 리모컨 누르고 isLogined() 호출해서 return된 데이터가 false와 같다면
			
			System.out.println("로그인 후 이용해주세요");
			return; // return으로 메서드 종료
		}
		// false와 같지 않다면 조건문 탈출해서 다음 코드로
		
		System.out.println("== 마이 페이지 ==");
		System.out.printf("가입일 : %s", Util.datetimeFormat(Session.getLoginedMember().regDate));
		System.out.printf("수정일 : %s", Util.datetimeFormat(Session.getLoginedMember().updateDate));
		System.out.printf("로그인 아이디 : %s", Session.getLoginedMember().loginId);
		System.out.printf("이름 : %s", Session.getLoginedMember().name);
	}
}
