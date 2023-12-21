package com.koreaIT.example.JAM.dao;

import java.sql.Connection;
import java.util.Map;

import com.koreaIT.example.JAM.util.DBUtil;
import com.koreaIT.example.JAM.util.SecSql;

public class MemberDao {
	// MemberDao 클래스
	// DB 쿼리 관련 코드 저장
	
	private Connection conn;
	// Connection 타입 전역변수 conn 선언
	
	public MemberDao(Connection conn) {
		// MemberDao 생성자 객체
		// Connection 타입 전역변수 conn을 매개변수로 전달받아 사용
		
		this.conn = conn;
		// 전역변수 conn에 변수 conn 저장
	}

	public boolean isLoginIdDup(String loginId) {
		// boolean 타입 isLoginIdDup() 메서드
		// String 타입 변수 loginId를 매개변수로 전달받아 사용
		
		SecSql sql = SecSql.from("SELECT COUNT(*) > 0 FROM `member`");
		// SecSql 타입 변수 sql에 SecSql 클래스 리모컨 누르고 from() 메서드 호출해서 return된 데이터 저장
		// 작성한 쿼리(SELECT COUNT(*) > 0 FROM article)를 인자로 전달
		
		sql.append("WHERE loginId = ?", loginId);
		// 변수 sql 리모컨 누르고 append() 메서드 호출해서 작성한 쿼리(WHERE loginId = ?) 추가
		// ? 자리에 변수 loginId 치환
		
		return DBUtil.selectRowBooleanValue(conn, sql);
		// DBUtil 클래스 리모컨 누르고 selectRowBooleanValue() 호출해서 return된 데이터를 return
		// 전역변수 conn, 변수 sql을 인자로 전달
	}

	public void doJoin(String loginId, String loginPw, String name) {
		// doJoin() 메서드
		// String 타입 변수 loginId, loginPw, name을 매개변수로 전달받아 사용 
		
		SecSql sql = SecSql.from("INSERT INTO `member`");
		// SecSql 타입 변수 sql에 SecSql 클래스 리모컨 누르고 from() 메서드 호출해서 return된 데이터 저장
		// 작성한 쿼리(INSERT INTO `member`)를 인자로 전달

		sql.append("SET regDate = NOW()");
		// 변수 sql 리모컨 누르고 append() 메서드 호출해서 작성한 쿼리(SET regDate = NOW()) 추가

		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		// ? 자리에 변수 loginId 치환

		sql.append(", loginPw = ?", loginPw);
		sql.append(", `name` = ?", name);

		DBUtil.insert(conn, sql);
		// DBUtil 클래스 리모컨 누르고 insert() 메서드 호출해서 return된 데이터 저장 안함(불필요한 데이터는 버림)
		// 변수 conn, 변수 sql을 인자로 전달
		
	}

	public Map<String, Object> getMemberByLoginId(String loginId) {
		// Map 타입 getMemberByLoginId() 메서드
		// String 타입 변수 loginId를 매개변수로 전달받아 사용
		
		SecSql sql = SecSql.from("SELECT * FROM `member`");
		// SecSql 타입 변수 sql에 SecSql 클래스 리모컨 누르고 from() 메서드 호출해서 return된 데이터 저장
		// 작성한 쿼리(SELECT * FROM `member`)를 인자로 전달
		
		sql.append("WHERE loginId = ?", loginId);
		// 변수 sql 리모컨 누르고 append() 메서드 호출해서 작성한 쿼리(WHERE loginId = ?) 추가
		// ? 자리에 변수 loginId 치환
		
		return DBUtil.selectRow(conn, sql);
		// DBUtil 클래스 리모컨 누르고 selectRow() 메서드 호출해서 return된 데이터 저장 안함(불필요한 데이터는 버림)
		// 변수 conn, 변수 sql을 인자로 전달
	}

}
