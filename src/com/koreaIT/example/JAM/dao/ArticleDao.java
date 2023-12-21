package com.koreaIT.example.JAM.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.koreaIT.example.JAM.util.DBUtil;
import com.koreaIT.example.JAM.util.SecSql;

public class ArticleDao {
	// ArticleDao 클래스
	// DB 쿼리 관련 코드 저장

	private Connection conn;
	// Connection 타입 전역변수 conn 선언

	public ArticleDao(Connection conn) {
		// ArticleDao 생성자 객체
		// Connection 타입 전역변수 conn을 매개변수로 전달받아 사용

		this.conn = conn;
		// 전역변수 conn에 변수 conn 저장
	}

	public int doWrite(int memberId, String title, String body) {
		// doWrite() 메서드
		// int 타입 변수 memberId, String 타입 변수 title, body를 매개변수로 전달받아 사용

		SecSql sql = SecSql.from("INSERT INTO article");
		// SecSql 타입 변수 sql에 SecSql 클래스 내 from() 메서드 호출해서 return된 데이터 저장
		// 작성한 쿼리(INSERT INTO article)를 인자로 전달

		sql.append("SET regDate = NOW()");
		// 변수 sql 리모컨 누르고 append() 메서드 호출해서 작성한 쿼리(SET regDate = NOW()) 추가

		sql.append(", updateDate = NOW()");
		sql.append(", memberId = ?", memberId);
		// ? 자리에 변수 memberId 치환

		sql.append(", title = ?", title);
		// ? 자리에 변수 title 치환

		sql.append(", `body` = ?", body);
		// ? 자리에 변수 body 치환

		return DBUtil.insert(conn, sql);
		// DBUtil 클래스 내 insert() 메서드 호출해서 return된 데이터를 return
		// 변수 conn, 변수 sql을 인자로 전달
	}

	public List<Map<String, Object>> showList(String searchKeyward) {
		// 리스트 형태의 Map 타입 showList() 메서드
		// String 타입 변수 searchKeyward를 매개변수로 전달받아 사용

		SecSql sql = SecSql.from("SELECT a.*, m.name AS `writerName`");
		// SecSql 타입 변수 sql에 SecSql 클래스 내 from() 메서드 호출해서 return된 데이터 저장
		// 작성한 쿼리(SELECT a.*, m.name AS `writerName`)를 인자로 전달

		sql.append("FROM article AS a");
		// 변수 sql 리모컨 누르고 append() 메서드 호출해서 작성한 쿼리(FROM article AS a) 추가
		
		sql.append("INNER JOIN `member` AS m");
		sql.append("ON a.memberId = m.id");
		
		if (searchKeyward.length() > 0) {
			// 만약 변수 searchKeyward의 리모컨 누르고 length() 메서드 호출해서 길이가 0보다 크다면
			
			sql.append("WHERE a.title LIKE CONCAT('%', ?, '%')", searchKeyward);
			// 변수 sql 리모컨 누르고 append() 메서드 호출해서 작성한 쿼리(WHERE a.title LIKE CONCAT('%', ?,'%')) 추가
			// ? 자리에 변수 searchKeyward 치환
		}
		// 길이가 0과 같거나 작다면 조건문 탈출해서 다음 코드로
		
		sql.append("ORDER BY a.id DESC");

		return DBUtil.selectRows(conn, sql);
		// DBUtil 클래스 내 selectRows() 메서드 호출해서 return된 데이터를 return
		// 변수 conn, 변수 sql을 인자로 전달
	}

	public Map<String, Object> showDatil(int id) {
		// Map 타입 showDatil() 메서드
		// int 타입 변수 id를 매개변수로 전달받아 사용

		SecSql sql = SecSql.from("SELECT a.*, m.name AS `writerName`");
		// SecSql 타입 변수 sql에 SecSql 클래스 내 from() 메서드 호출해서 return된 데이터 저장
		// 작성한 쿼리(SELECT a.*, m.name)를 인자로 전달

		sql.append("FROM article AS a");
		// 변수 sql 리모컨 누르고 append() 메서드 호출해서 작성한 쿼리(FROM article AS a) 추가
		
		sql.append("INNER JOIN `member` AS m");
		sql.append("ON a.memberId = m.id");

		sql.append("WHERE a.id = ?", id);
		// ? 자리에 변수 id 치환

		return DBUtil.selectRow(conn, sql);
		// DBUtil 클래스 내 selectRow() 메서드 호출해서 return된 데이터를 return
		// 변수 conn, sql 인자로 전달
	}

	public int getArticleCnt(int id) {
		// getArticleCnt() 메서드
		// int 타입 변수 id를 매개변수로 전달받아 사용

		SecSql sql = SecSql.from("SELECT COUNT(*) FROM article");
		// SecSql 타입 변수 sql에 SecSql 클래스 내 from() 메서드 호출해서 return된 데이터 저장
		// 작성한 쿼리(SELECT COUNT(*) FROM article)를 인자로 전달

		sql.append("WHERE id = ?", id);
		// 변수 sql 리모컨 누르고 append() 메서드 호출해서 작성한 쿼리(WHERE id = ?) 추가
		// ? 자리에 변수 id 치환

		return DBUtil.selectRowIntValue(conn, sql);
		// DBUtil 클래스 내 selectRowIntValue() 메서드 호출해서 return된 데이터를 return
		// 변수 conn, sql을 인자로 전달
	}

	public void doModify(String title, String body, int id) {
		// doModify() 메서드
		// String 타입 변수 title, body, int 타입 변수 id를 매개변수로 전달받아 사용

		SecSql sql = SecSql.from("UPDATE article");
		// SecSql 타입 변수 sql에 SecSql 클래스 내 from() 메서드 호출해서 return된 데이터 저장
		// 작성한 쿼리(UPDATE article)를 인자로 전달

		sql.append("SET updateDate = NOW()");
		// 변수 sql 리모컨 누르고 append() 메서드 호출해서 작성한 쿼리(SET uadateDate = NOW()) 추가

		sql.append(", title = ?", title);
		// ? 자리에 변수 title 치환

		sql.append(", `body` = ?", body);
		// ? 자리에 변수 body 치환

		sql.append("WHERE id = ?", id);
		// ? 자리에 변수 id 치환

		DBUtil.update(conn, sql);
		// DBUtil 클래스 내 update() 메서드 호출해서 return된 데이터 저장 안함(불필요한 데이터는 버림)
		// 변수 conn, sql 인자로 전달
	}

	public void doDelete(int id) {
		// doDelete() 메서드
		// int 타입 변수 id를 매개변수로 전달받아 사용

		SecSql sql = SecSql.from("DELETE FROM article");
		// SecSql 타입 변수 sql에 SecSql 클래스 내 from() 메서드 호출해서 return된 데이터 저장
		// 작성한 쿼리(DELETE FROM article)를 인자로 전달

		sql.append("WHERE id = ?", id);
		// ? 자리에 변수 id 치환

		DBUtil.delete(conn, sql);
		// DBUtil 클래스 내 delete() 메서드 호출해서 return된 데이터 저장 안함(불필요한 데이터는 버림)
		// 변수 conn, sql 인자로 전달
	}

	public Map<String, Object> getArticleById(int id) {
		// Map 타입 getArticleById() 메서드
		// int 타입 변수 id를 매개변수로 전달받아 사용
		
		SecSql sql = SecSql.from("SELECT * FROM article");
		// SecSql 타입 변수 sql에 SecSql 클래스 내 from() 메서드 호출해서 return된 데이터 저장
		// 작성한 쿼리(SELECT COUNT(*) FROM article)를 인자로 전달

		sql.append("WHERE id = ?", id);
		// 변수 sql 리모컨 누르고 append() 메서드 호출해서 작성한 쿼리(WHERE id = ?) 추가
		// ? 자리에 변수 id 치환
		
		return DBUtil.selectRow(conn, sql);
	}
}
