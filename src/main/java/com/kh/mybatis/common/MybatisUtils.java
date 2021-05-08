package com.kh.mybatis.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtils {
	
	/**
	 * mybatis-config.xml 설정파일 기반으로 SqlSession객체를 생성반환
	 * build-path(target/classes)로 배포된 설정파일을 읽어들임.
	 * 
	 * 공장짓는이 - 공장 - SqlSession
	 * @return
	 */
	public static SqlSession getSqslSession() {
		SqlSession session = null;
		//target밑의 mybatis-config를 가져오는 것
		String resource ="/mybatis-config.xml";
		//1. FactoryBuilder
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		//2. Factory생성 - 설정파일
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory factory = builder.build(is);
		
		//3. SqlSession
		//오토커밋 false처리
		session = factory.openSession(false);
		
		return session;
	}
}
