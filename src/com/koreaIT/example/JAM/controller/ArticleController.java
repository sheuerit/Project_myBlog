package com.koreaIT.example.JAM.controller;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.example.JAM.dto.Article;
import com.koreaIT.example.JAM.service.ArticleService;
import com.koreaIT.example.JAM.session.Session;
import com.koreaIT.example.JAM.util.Util;

public class ArticleController {
	// ArticleController 클래스
	// article 관련 기능 코드 저장

	private Scanner sc;
	// Scanner 타입 전역변수 sc 선언 
	
	private ArticleService articleService;
	// ArticleService 타입 전역변수 articleService 선언
	
	public ArticleController(Connection conn, Scanner sc) {
		// ArticleController 생성자 객체
		// Connection 타입 변수 conn, Scanner 타입 변수 sc를 매개변수로 전달받아 사용
		
		this.sc = sc;
		// 전역변수 sc에 전달받은 매개변수 sc 저장
		
		this.articleService = new ArticleService(conn);
		// 전역변수 articleService에 새 ArticleService 객체 생성해서 저장
		// 변수 conn을 인자로 전달
	}

	public void doWrite() {
		// doWrite() 메서드
		
		if (Session.isLogined() == false) {
			// 만약 Session 클래스 리모컨 누르고 isLogined() 호출해서 return된 데이터가 false와 같다면
			
			System.out.println("로그인 후 이용해주세요");
			return; // return으로 메서드 종료
		}
		// false와 같지 않다면 조건문 탈출해서 다음 코드로
		
		System.out.println("== 게시물 작성 ==");

		System.out.printf("제목 : ");
		String title = sc.nextLine().trim();
		// String 타입 변수 title에 변수 sc 리모컨 누르고 nextLine() 메서드 호출해서 return된 데이터(입력한 제목) 저장

		System.out.printf("내용 : ");
		String body = sc.nextLine().trim();
		// String 타입 변수 body에 변수 sc 리모컨 누르고 nextLine() 메서드 호출해서 return된 데이터(입력한 내용) 저장

		int id = articleService.doWrite(Session.getLoginedMemeberId(), title, body);
		// 변수 id에 변수 articleDao 리모컨 누르고 doWrite() 메서드 호출해서 return된 데이터를 저장
		// Session 클래스 리모컨 누르고 getLoginedMemeberId() 메서드 호출해서 return된 데이터, 변수 title, body를 인자로 전달
		
		System.out.printf("%d번 게시물이 생성되었습니다\n", id);
	}

	public void showList(String cmd) {
		// showList() 메서드
		// String 타입 변수 cmd를 매개변수로 전달받아 사용
		
		System.out.println("== 게시물 목록 ==");
		
		String searchKeyward = cmd.substring("article list".length()).trim();
		// String 타입 변수 searchKeyward에 변수 cmd 리모컨 누르고 substring() 메서드 호출해서 return된 데이터 저장
		// 명령어 article list의 리모컨 누르고 length() 메서드 호출해서 길이를 제외한 명령어를 인자로 전달

		List<Article> articles = articleService.showList(searchKeyward);
		// 리스트 형태의 Article 타입 변수 articles에 변수 articleService 리모컨 누르고 showList() 메서드 호출해서 return된 데이터 저장

		if (articles.size() == 0) {
			// 만약 변수 articles 리모컨 누르고 size() 메서드 호출해서 크기가 0이면 (게시글이 없으면)

			System.out.println("존재하는 게시물이 없습니다");
			return;
			// return으로 메서드 종료
		}
		// 크기가 0이 아니면 조건문 탈출해서 다음 코드로

		System.out.println("번호	|	제목	|	작성자	|		날짜");
		for (Article article : articles) {
			// 향상된 for문
			// articles를 순회해서 반복문이 한번 돌 때 마다 리스트 내부 각 index의 내 데이터를 변수 article에 저장

			System.out.printf("%d	|	%s	|	%s	|	%s\n", article.id, article.title, article.writerName, Util.datetimeFormat(article.regDate));
		}
		
	}

	public void showDetail(String cmd) {
		// showDetail() 메서드
		// String 타입 변수 cmd를 매개변수로 전달받아 사용
		
		int id = articleService.getNumInCmd(cmd);
		// int 타입 변수 id에 변수 articleService 리모컨 누르고 getNumInCmd() 메서드 호출해서 return된 데이터 저장
		// 변수 cmd 인자로 전달

		Article article = articleService.showDetail(id);
		// 변수 articleService 리모컨 누르고 showDetail() 메서드 호출
		// 변수 id 인자로 전달
		
		if (article == null) {
			// 만약 변수 article에 저장된 데이터가 null과 같다면
			
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return; // return으로 메서드 종료
		}
		// null과 같지 않다면 조건문 탈출해서 다음 코드로

		System.out.printf("== %d번 게시물 상세보기 ==\n", id);
		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("작성일 : %s\n", Util.datetimeFormat(article.regDate));
		System.out.printf("수정일 : %s\n", Util.datetimeFormat(article.updateDate));
		System.out.printf("작성자 : %s\n", article.writerName);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);
		
	}

	public void doModify(String cmd) {
		// doModify() 메서드
		// String 타입 변수 cmd를 매개변수로 전달받아 사용
		
		if (Session.isLogined() == false) {
			// 만약 Session 클래스 리모컨 누르고 isLogined() 호출해서 return된 데이터가 false와 같다면
			
			System.out.println("로그인 후 이용해주세요");
			return; // return으로 메서드 종료
		}
		// false와 같지 않다면 조건문 탈출해서 다음 코드로
		
		int id = articleService.getNumInCmd(cmd);
		// int 타입 변수 id에 변수 articleService 리모컨 누르고 getNumInCmd() 메서드 호출해서 return된 데이터 저장
		// 변수 cmd 인자로 전달

		Article article = articleService.getArticleById(id);
		// Article 타입 변수 article에 변수 articleService 리모컨 누르고 getArticleById() 메서드 호출해서 return된 데이터 저장
		// 변수 id를 인자로 전달
		
		if (article == null) {
			// 만약 변수 article에 저장된 데이터가 null과 같다면

			System.out.printf("%d번 게시물은 존재하지 않습니다", id);
			return;
			// return으로 메서드 종료
		}
		// null과 같지 않다면 조건문 탈출해서 다음 코드로
		
		if (article.memberId != Session.getLoginedMemeberId()) {
			// 만약 변수 article 리모컨 누르고 호출한 변수 memberId와 Sesstion 클래스 리모컨 누르고 호출한 getLoginedMemeberId() 메서드에서 return된 데이터가 같지 않다면
			
			System.out.printf("%d번 게시물에 대한 권한이 없습니다\n", id);
			return;
			// return으로 메서드 종료
		}
		// 같다면 조건문 탈출해서 다음 코드로

		System.out.println("== 게시물 수정 ==");

		System.out.printf("수정할 제목 : ");
		String title = sc.nextLine().trim();
		// String 타입 변수 title에 변수 sc 리모컨 누르고 nextLine() 메서드 호출해서 return된 데이터(수정한 제목) 저장

		System.out.printf("수정할 내용 : ");
		String body = sc.nextLine().trim();
		// String 타입 변수 body에 변수 sc 리모컨 누르고 nextLine() 메서드 호출해서 return된 데이터(수정한 내용) 저장

		articleService.doModify(title, body, id);
		// 변수 articleService 리모컨 누르고 doModify() 메서드 호출
		// 변수 title, body, id를 인자로 전달

		System.out.printf("%d번 게시물이 수정되었습니다\n", id);
		
	}

	public void doDelete(String cmd) {
		// doDelete() 메서드
		// String 타입 변수 cmd를 매개변수로 전달받아 사용
		
		if (Session.isLogined() == false) {
			// 만약 Session 클래스 리모컨 누르고 isLogined() 호출해서 return된 데이터가 false와 같다면
			
			System.out.println("로그인 후 이용해주세요");
			return; // return으로 메서드 종료
		}
		// false와 같지 않다면 조건문 탈출해서 다음 코드로
		
		int id = articleService.getNumInCmd(cmd);
		// int 타입 변수 id에 변수 articleService 리모컨 누르고 getNumInCmd() 메서드 호출해서 return된 데이터 저장
		// 변수 cmd 인자로 전달

		Article article = articleService.getArticleById(id);
		// Article 타입 변수 article에 변수 articleService 리모컨 누르고 getArticleById() 메서드 호출해서 return된 데이터 저장
		// 변수 id를 인자로 전달

		if (article == null) {
			// 만약 변수 article에 저장된 데이터가 null과 같다면

			System.out.printf("%d번 게시물은 존재하지 않습니다", id);
			return;
			// return으로 메서드 종료
		}
		// null과 같지 않다면 조건문 탈출해서 다음 코드로

		if (article.memberId != Session.getLoginedMemeberId()) {
			// 만약 변수 article 리모컨 누르고 호출한 변수 memberId와 Sesstion 클래스 리모컨 누르고 호출한 getLoginedMemeberId() 메서드에서 return된 데이터가 같지 않다면
			
			System.out.printf("%d번 게시물에 대한 권한이 없습니다\n", id);
			return;
			// return으로 메서드 종료
		}
		// 같다면 조건문 탈출해서 다음 코드로
		
		System.out.println("== 게시물 삭제 ==");

		articleService.doDelete(id);
		// 변수 articleService 리모컨 누르고 doDelete() 메서드 호출
		// 변수 id를 인자로 전달

		System.out.printf("%d번 게시물이 삭제되었습니다\n", id);
	}

}
