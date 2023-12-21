package com.koreaIT.example.JAM.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.koreaIT.example.JAM.exception.SQLErrorException;

public class DBUtil {
	// 작성한 쿼리를 DB에 전송
	
	public static Map<String, Object> selectRow(Connection dbConn, SecSql sql) {
		// Map 타입 selectRow() 메서드
		// 제너릭 String은 키(key)의 타입(컬럼은 문자열이기 때문), 제너릭 Object는 값(value)의 타입(모든 타입의 데이터를 수용해야 하기 때문)
		// Connection 타입 변수 dbConn, SecSql 타입 변수 sql을 매개변수로 전달받음
		// 한 줄의 데이터를 조회
		
		List<Map<String, Object>> rows = selectRows(dbConn, sql);

		if (rows.size() == 0) {
			return new HashMap<>();
		}

		return rows.get(0);
	}

	public static List<Map<String, Object>> selectRows(Connection dbConn, SecSql sql) throws SQLErrorException {
		// 리스트 형태의 Map 타입 selectRows() 메서드
		// 제너릭 String은 키(key)의 타입(컬럼은 문자열이기 때문), 제너릭 Object는 값(value)의 타입(모든 타입의 데이터를 수용해야 하기 때문)
		// Connection 타입 변수 dbConn, SecSql 타입 변수 sql을 매개변수로 전달받음
		// 여러 줄의 데이터를 조회
		
		List<Map<String, Object>> rows = new ArrayList<>();

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = sql.getPreparedStatement(dbConn);
			rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnSize = metaData.getColumnCount();

			while (rs.next()) {
				
				Map<String, Object> row = new HashMap<>();
				// 자바에서 편하게 관리할 수 있도록 DB에서 받아온 데이터를 Map 형태로 변환

				for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
					String columnName = metaData.getColumnName(columnIndex + 1);
					Object value = rs.getObject(columnName);

					if (value instanceof Long) {
						int numValue = (int) (long) value;
						row.put(columnName, numValue);
					} else if (value instanceof Timestamp) {
						String dateValue = value.toString();
						dateValue = dateValue.substring(0, dateValue.length() - 2);
						row.put(columnName, dateValue);
					} else {
						row.put(columnName, value);
					}
				}

				rows.add(row);
			}
		} catch (SQLException e) {
			throw new SQLErrorException("SQL 예외, SQL : " + sql, e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new SQLErrorException("SQL 예외, rs 닫기, SQL : " + sql, e);
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new SQLErrorException("SQL 예외, stmt 닫기, SQL : " + sql, e);
				}
			}
		}

		return rows;
	}

	public static int selectRowIntValue(Connection dbConn, SecSql sql) {
		// selectRowIntValue() 메서드
		// Connection타입 변수 dbConn, SecSql타입 변수 sql를 매개변수로 전달받음
		// DB에서 정수 데이터를 전달받을 때 사용
		
		Map<String, Object> row = selectRow(dbConn, sql);
		// Map 타입 변수 row에 selectRow() 메서드에서 리턴된 한 줄의 데이터 저장
		// 변수 dbConn, sql 인자로 전달
		
		for (String key : row.keySet()) {
			// 향상된 for문
			// String 타입 변수 key에 변수 row 리모컨 누르고 keySet() 메서드 호출해서 리턴된 키(컬럼) 데이터들을 순회해서 반복문이 돌 때 마다 저장
			
			return (int) row.get(key);
			// int 타입으로 형변환된 변수 row 리모컨 누르고 get() 메서드 호출, 변수 key 인자로 전달해서 리턴된 데이터를 리턴
		}

		return -1;
	}

	public static String selectRowStringValue(Connection dbConn, SecSql sql) {
		// selectRowStringValue() 메서드
		// DB에서 문자 데이터를 전달받을 때 사용
		
		Map<String, Object> row = selectRow(dbConn, sql);

		for (String key : row.keySet()) {
			return (String) row.get(key);
		}

		return "";
	}

	public static boolean selectRowBooleanValue(Connection dbConn, SecSql sql) {
		// selectRowBooleanValue() 메서드
		// DB에서 논리(true, false) 데이터를 전달받을 때 사용 
		
		Map<String, Object> row = selectRow(dbConn, sql);

		for (String key : row.keySet()) {
			return ((int) row.get(key)) == 1;
			// int 타입으로 형변환된 변수 row 리모컨 누르고 get() 메서드 호출, 변수 key 인자로 전달해서 리턴된 데이터가 1과 같은 경우인 true를 리턴
		}

		return false;
		// 0인 경우인 false 리턴
	}

	public static int insert(Connection dbConn, SecSql sql) {
		// insert() 메서드
		// INSERT 쿼리 전송할 때 사용
		
		int id = -1;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = sql.getPreparedStatement(dbConn);
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (SQLException e) {
			throw new SQLErrorException("SQL 예외, SQL : " + sql, e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new SQLErrorException("SQL 예외, rs 닫기, SQL : " + sql, e);
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new SQLErrorException("SQL 예외, stmt 닫기, SQL : " + sql, e);
				}
			}

		}

		return id;
	}

	public static int update(Connection dbConn, SecSql sql) {
		// update() 메서드
		// UPDATE 쿼리 전송할 때 사용
		
		int affectedRows = 0;

		PreparedStatement stmt = null;

		try {
			stmt = sql.getPreparedStatement(dbConn);
			affectedRows = stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLErrorException("SQL 예외, SQL : " + sql, e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new SQLErrorException("SQL 예외, stmt 닫기, SQL : " + sql, e);
				}
			}
		}

		return affectedRows;
	}

	public static int delete(Connection dbConn, SecSql sql) {
		return update(dbConn, sql);
	}
}