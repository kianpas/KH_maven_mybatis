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

public class DeleteStudentMapController extends AbstractController {

	private StudentService studentService = new StudentServiceImpl();

	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int no = 0;
			try {
				no = Integer.parseInt(request.getParameter("no"));
			} catch (NumberFormatException e) {

			}
			System.out.println(no);
			Map<String, Object> student = new HashMap<>();
			student.put("no", no);

			int result = studentService.deleteStudentMap(student);
			if (result == 0) {
				throw new NoMatchingStudentException(String.valueOf(no));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "redirect:/student/selectOne.do";
	}

}
