package com.kh.mybatis.student.model.service;

import org.apache.ibatis.session.SqlSession;

import static com.kh.mybatis.common.MybatisUtils.*;

import java.util.Map;

import com.kh.mybatis.student.model.dao.StudentDao;
import com.kh.mybatis.student.model.dao.StudentDaoImpl;
import com.kh.mybatis.student.model.vo.Student;

public class StudentServiceImpl implements StudentService {

	private StudentDao studentDao = new StudentDaoImpl();

	@Override
	public int insertStudent(Student student) {
		// 1. SqlSession 생성
		SqlSession session = getSqslSession();
		int result = 0;
		try {
			// 2. dao 업무위임
			result = studentDao.insertStudent(session, student);
			// 3. transaction 처리 : SqlSession에 대해서 commit|rollback
			session.commit();

		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			// 4. SqlSession 자원반납
			session.close();
		}
		return result;
	}

	@Override
	public int insertStudentMap(Map<String, Object> student) {

		SqlSession session = getSqslSession();
		int result = 0;
		try {
			result = studentDao.insertStudentMap(session, student);

			session.commit();

		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public int selectStudentCount() {
		SqlSession session = getSqslSession();
		int total = studentDao.selectStudentCount(session);
		session.close();
		return total;
	}

	@Override
	public Student selectOneStudent(int no) {
		SqlSession session = getSqslSession();
		Student student = studentDao.selectOneStudent(session, no);
		session.close();
		return student;
	}

	@Override
	public Map<String, Object> selectOneStudentMap(int no) {
		SqlSession session = getSqslSession();
		Map<String, Object> map = studentDao.selectOneStudentMap(session, no);
		session.close();
		return map;
	}

	@Override
	public int updateStudentMap(Map<String, Object> student) {
		SqlSession session = getSqslSession();
		int result = 0;
		System.out.println("studentServiceimple = " + student);
		try {
			result = studentDao.updateStudentMap(session, student);

			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public int deleteStudentMap(Map<String, Object> student) {
		SqlSession session = getSqslSession();
		int result = 0;
		System.out.println("deletemap" + student);
		try {
			result = studentDao.deleteStudentMap(session, student);

			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}

}
