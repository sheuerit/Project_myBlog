package com.koreaIT.example.JAM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.koreaIT.example.JAM.controller.ArticleController;
import com.koreaIT.example.JAM.controller.MemberController;

public class App {
	// App 클래스
	
	void run() {
		// run() 메서드
		
		Scanner sc = new Scanner(System.in);
		// Scanner 타입 변수 sc에 새 Scanner 객체 저장
		// 입력 기능(System.in)을 인자로 전달

		System.out.println("== 프로그램 시작 ==");

		Connection conn = null;
		// Connection 타입 변수 conn에 null 저장해서 비움
		// Connection 타입은 연결할 때 사용할 수 있는 접속데이터를 저장하는 타입
		
		try {
			// try 블록, try 블록, Exception이 발생해서 문제가 생길만한 코드를 작성하면 try/catch 블록으로 예외처리를 해야함
			// DB와 접속을 시도
			// 내부 코드에 문제가 없으면 finally로 감

			Class.forName("com.mysql.jdbc.Driver");
			// Class 클래스 리모컨 누르고 forName() 메서드 호출
			// 인자로 com.mysql.jdbc.Driver 전달

			String url = "jdbc:mysql://127.0.0.1:3306/JAM?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
			// String 타입 변수 url에 jdbc:mysql://127.0.0.1:3306/JAM?... 저장
			// 127.0.0.1:3306/JAM >> 로컬호스트의 3306 포트 / 접속할 DB 이름 (이 부분이 중요)

			conn = DriverManager.getConnection(url, "root", "");
			// 변수 conn에 DriverManager 클래스 내 getConnection() 메서드 호출
			// 인자로 변수 url, root(DBMS 사용자이름), 공백(비밀번호)
			// DB 접속 시도
			
			MemberController memberController = new MemberController(conn, sc); 
			// MemberController 타입 변수 memberController에 새 MemberController() 객체 생성해서 저장
			// 변수 conn, sc 인자로 전달
			
			ArticleController articleController = new ArticleController(conn, sc); 
			// ArticleController 타입 변수 articleController에 새 ArticleController() 객체 생성해서 저장
			// 변수 conn, sc 인자로 전달

			while (true) {
				// 무한반복문, 명령어 검증

				System.out.printf("명령어) ");
				String cmd = sc.nextLine().trim();
				// String 타입 변수 cmd에 변수 sc 리모컨 누르고 nextLine() 메서드 호출해서 return된 데이터(명령어) 저장
				// trim() 메서드는 단어 사이의 단일 공백을 제외하고 텍스트에서 모든 공백을 제거

				if (cmd.equals("exit")) {
					// 만약 변수 cmd 리모컨 누르고 equals() 메서드 호출해서 exit과 같다면 블록 내부 코드 실행

					break;
					// 무한반복문 탈출하고 다음 코드로
				}

				if (cmd.equals("member join")) {
					// 만약 변수 cmd에 리모컨 누르고 equals() 메서드 호출해서 저장된 명령어가 member join과 같다면 블록 내부 코드 실행
					
					memberController.doJoin();
					// 변수 memberController 리모컨 누르고 doJoin() 메서드 호출
					
				} else if (cmd.equals("member login")) {
					// 만약 변수 cmd에 리모컨 누르고 equals() 메서드 호출해서 저장된 명령어가 member login과 같다면 블록 내부 코드 실행
					
					memberController.doLogin();
					// 변수 memberController 리모컨 누르고 doLogin() 메서드 호출
					
				} else if (cmd.equals("member logout")) {
					// 만약 변수 cmd에 리모컨 누르고 equals() 메서드 호출해서 저장된 명령어가 member login과 같다면 블록 내부 코드 실행
					
					memberController.doLogout();
					// 변수 memberController 리모컨 누르고 doLogin() 메서드 호출
					
				} else if (cmd.equals("member profile")) {
					// 만약 변수 cmd에 리모컨 누르고 equals() 메서드 호출해서 저장된 명령어가 member profile과 같다면 블록 내부 코드 실행
					
					memberController.showProfile();
					// 변수 memberController 리모컨 누르고 doLogin() 메서드 호출
					
				} else if (cmd.equals("article write")) {
					// 만약 변수 cmd에 리모컨 누르고 equals() 메서드 호출해서 저장된 명령어가 article write와 같다면 블록 내부 코드 실행

					articleController.doWrite();
					// 변수 articleController 리모컨 누르고 doWrite() 메서드 호출

				} else if (cmd.startsWith("article list")) {
					// 만약 변수 cmd에 리모컨 누르고 equals() 메서드 호출해서 저장된 명령어가 article list로 시작한다면 블록 내부 코드 실행

					articleController.showList(cmd);
					// 변수 articleController 리모컨 누르고 showList() 메서드 호출
					// 변수 cmd를 인자로 전달
					
				} else if (cmd.startsWith("article detail ")) {
					// 만약 변수 cmd에 리모컨 누르고 startsWith() 메서드 호출해서 저장된 명령어가 article detail로 시작한다면 블록 내부 코드 실행

					articleController.showDetail(cmd);
					// 변수 articleController 리모컨 누르고 showDetail() 메서드 호출
					// 변수 cmd를 인자로 전달
					
				} else if (cmd.startsWith("article modify ")) {
					// 만약 변수 cmd에 리모컨 누르고 startsWith() 메서드 호출해서 article modify로 시작한다면 블록 내부 코드 실행
					
					articleController.doModify(cmd);
					// 변수 articleController 리모컨 누르고 doModify() 메서드 호출
					// 변수 cmd를 인자로 전달
					
				} else if (cmd.startsWith("article delete ")) {
					// 만약 변수 cmd가 리모컨 누르고 startsWith() 메서드 호출해서 article modify로 시작한다면 블록 내부 코드 실행

					articleController.doDelete(cmd);
					// 변수 articleController 리모컨 누르고 doDelete() 메서드 호출
					// 변수 cmd를 인자로 전달

				} else {
					// 조건문에 해당하는 명령어가 없다면 블록 내부 코드 실행

					System.out.println("존재하지 않는 명령어 입니다");
					continue;
					// 무한반복문 처음으로
				}
			} // 무한반복문 끝
			
		} catch (ClassNotFoundException e) {
			// catch 블록
			// try 블록 내부 코드로 DB 접속을 시도하는 중에 ClassNotFoundException 클래스에서 발생하는 문제를 잡아내면 실행

			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			// try 블록 내부 코드를 실행 중에 SQLException 클래스에서 발생하는 문제를 잡아내면 실행

			System.out.println("에러: " + e);
		} finally {
			// finally 블록
			// try 블록에 문제가 있든 없든 무조건 실행

			try {
				// 변수 conn(DB 접속)을 종료하는 코드를 가지고 있음

				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				// 변수 conn 종료를 시도하는 중에 SQLException 클래스에서 발생하는 문제를 잡아내면 실행

				e.printStackTrace();
			}
		}

		sc.close();
		// 변수 sc 리모컨 누르고 close() 메서드 호출해서 종료

		System.out.println("== 프로그램 종료 ==");

	}
}
