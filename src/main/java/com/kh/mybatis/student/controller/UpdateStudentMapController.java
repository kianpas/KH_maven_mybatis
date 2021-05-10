package com.kh.mybatis.student.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.common.AbstractController;
import com.kh.mybatis.common.exception.NoMatchingStudentException;
import com.kh.mybatis.student.model.service.StudentService;
import com.kh.mybatis.student.model.service.StudentServiceImpl;

public class UpdateStudentMapController extends AbstractController {

	private StudentService studentService = new StudentServiceImpl();

	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int no = 0;
		try {
			no = Integer.parseInt(request.getParameter("no"));
			String name = request.getParameter("name");
			String tel = request.getParameter("tel");

			Map<String, Object> student = new HashMap<String, Object>();
			student.put("no", no);
			student.put("name", name);
			student.put("tel", tel);
			System.out.println("updateStudentMapController = " + student);
			int result = studentService.updateStudentMap(student);

			if (result == 0) {
				throw new NoMatchingStudentException(String.valueOf(no));
			}

			System.out.println(result);
			String msg = result > 0 ? "학생 정보 수정 성공" : "학생 정보 수정 실패";
			System.out.println(msg);
			// 3. 사용자 피드백
			request.getSession().setAttribute("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "redirect:/student/selectOne.do?no=" + no;
	}

}
