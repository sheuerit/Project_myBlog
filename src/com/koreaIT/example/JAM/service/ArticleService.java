package com.koreaIT.example.JAM.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.koreaIT.example.JAM.dao.ArticleDao;
import com.koreaIT.example.JAM.dto.Article;

public class ArticleService {
	// ArticleService 클래스
	// ArticleController 클래스와 ArticleDao 클래스 간 통신 관련 코드 저장
	
	private ArticleDao articleDao;
	// ArticleDao 타입 전역변수 articleDao 선언

	public ArticleService(Connection conn) {
		// ArticleService 생성자 객체
		// Connection 타입 변수 conn을 매개변수로 전달받아 사용
		
		this.articleDao = new ArticleDao(conn);
		// 전역변수 articleDao에 변수 conn을 인자로 넘겨서 생성된 새 ArticleDao 객체를 저장
	}

	public int doWrite(int memberId, String title, String body) {
		// doWrite() 메서드
		// int 타입 변수 memberId, String 타입 변수 title, body를 매개변수로 전달받아 사용
		
		return articleDao.doWrite(memberId, title, body);
		// 변수 articleDao 리모컨 누르고 doWrite() 메서드 호출해서 return된 데이터를 return
		// 변수 memberId, title, body를 인자로 전달
	}

	public List<Article> showList(String searchKeyward) {
		// 리스트 형태 Article 타입 showList() 메서드
		// String 타입 변수 searchKeyward를 매개변수로 전달받아 사용
		
		List<Map<String, Object>> articleListMap = articleDao.showList(searchKeyward);
		// 리스트 형태의 Map 타입 변수 articleListMap에 변수 articleDao 리모컨 누르고 showList() 메서드 호출해서 return된 데이터 저장
		// 변수 searchKeyward를 인자로 전달
		
		List<Article> articles = new ArrayList<>();
		// 리스트 형태의 Article 타입 변수 articles에 새 리스트 저장
		
		for (Map<String, Object> articleMap : articleListMap) {
			// 향상된 for문
			// 변수 articleMap에 변수 articleListMap를 순회해서 반복문이 한번 돌 때 마다 각 index 내 데이터를 저장

			articles.add(new Article(articleMap));
			// 변수 articles 리모컨 누르고 add() 메서드 호출해서 return된 데이터 추가
			// 변수 articleMap을 인자로 전달해서 생성한 Article 객체를 인자로 전달
		}
		
		return articles;
		// 변수 articles를 return
	}

	public Article showDetail(int id) {
		// Article 타입 showDatil() 메서드
		// int 타입 변수 id를 매개변수로 전달받아 사용
		
		Map<String, Object> articleMap = articleDao.showDatil(id);
		// Map 타입 변수 articleMap에 변수 articleDao 리모컨 누르고 showDatil() 메서드 호출
		// 변수 id를 인자로 전달
		
		if (articleMap.isEmpty()) {
			// 만약 변수 articleMap 리모컨 누르고 isEmpty() 메서드 호출해서 비어있으면
			
			return null; // null을 return
		}
		// 비어있지 않다면 조건문 탈출해서 다음 코드로
		
		return new Article(articleMap);
		// 변수 articleMap 인자로 전달해서 생성한 새 Article 객체를 return
	}

	public int getNumInCmd(String cmd) {
		// getNumInCmd() 메서드
		// String 타입 변수 cmd를 매개변수로 전달받아 사용
		
		return Integer.parseInt(cmd.split(" ")[2]);
		// Integer 클래스 내 parseInt() 메서드를 호출해서 return된 데이터를 int 타입으로 변환한 데이터를 return
		// 변수 cmd 리모컨 누르고 split() 메서드 호출, 공백 기준으로 나눈 문자열을 리스트로 저장하고 2번 index에 저장된 데이터를 인자로 전달
	}

	public int getArticleCnt(int id) {
		// getArticleCnt() 메서드
		// int 타입 변수 id를 매개변수로 전달받아 사용
		
		return articleDao.getArticleCnt(id);
		// 변수 articleDao 리모컨 누르고 getArticleCnt() 메서드 호출해서 return된 데이터를 return
		// 변수 id를 인자로 전달
	}

	public void doModify(String title, String body, int id) {
		// doModify() 메서드
		// String 타입 변수 title, body, int 타입 변수 id를 매개변수로 전달받아 사용
		
		articleDao.doModify(title, body, id);
		// 변수 articleDao 리모컨 누르고 doModify() 메서드 호출
		// 변수 title, body, id를 인자로 전달
	}

	public void doDelete(int id) {
		// doDelete() 메서드
		// int 타입 변수 id를 매개변수로 전달받아 사용
		
		articleDao.doDelete(id);
		// 변수 articleDao 리모컨 누르고 doModify() 메서드 호출
		// 변수 title, body, id를 인자로 전달
	}

	public Article getArticleById(int id) {
		// Article 타입 getArticleById() 메서드
		// int 타입 변수 id를 매개변수로 전달받아 사용
		
		Map<String, Object> articleMap = articleDao.getArticleById(id);
		// Map 타입 변수 articleMap에 변수 articleDao 리모컨 누르고 showDatil() 메서드 호출
		// 변수 id를 인자로 전달
		
		if (articleMap.isEmpty()) {
			// 만약 변수 articleMap 리모컨 누르고 isEmpty() 메서드 호출해서 비어있으면
			
			return null; // null을 return
		}
		// 비어있지 않다면 조건문 탈출해서 다음 코드로
		
		return new Article(articleMap);
		// 변수 articleMap 인자로 전달해서 생성한 새 Article 객체를 return
	}
}
