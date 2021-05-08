package com.kh.mybatis.student.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.common.AbstractController;
import com.kh.mybatis.student.model.service.StudentService;
import com.kh.mybatis.student.model.service.StudentServiceImpl;

public class InsertStudentMapController extends AbstractController {

	private StudentService studentService = new StudentServiceImpl();

	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 1. 사용자 입력값
			String name = request.getParameter("name");
			String tel = request.getParameter("tel");

			Map<String, Object> student = new HashMap<String, Object>();
			student.put("name", name);
			student.put("tel", tel);
			// 바티스 사용할 경우 한번에 보내야함 따로 못보낸다

			System.out.println("studentMap@controller = " + student);
			// 2. 업무로직
			int result = studentService.insertStudentMap(student);

			// 3. 사용자 피드백 & 리다이렉트
			request.getSession().setAttribute("msg", "학생 정보 등록 성공!");

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "redirect:/student/insertStudent.do";
	}

}
