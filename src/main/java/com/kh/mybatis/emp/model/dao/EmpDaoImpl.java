package com.kh.mybatis.emp.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class EmpDaoImpl implements EmpDao {

	@Override
	public List<Map<String, Object>> selectAllEmp(SqlSession session) {
	
		return session.selectList("emp.selectAllEmp");
	}

	@Override
	public List<Map<String, Object>> search1(SqlSession session, Map<String, Object> param) {
		
		return session.selectList("emp.search1", param);
	}

	@Override
	public List<Map<String, Object>> search2(SqlSession session, Map<String, Object> param) {
		// TODO Auto-generated method stub
		return session.selectList("emp.search2", param);
	}
	
}
