package com.kh.mybatis.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.common.exception.ControllerNotFoundException;
import com.kh.mybatis.common.exception.MethodNotAllowedException;

/**
 * Servlet implementation class DispatcherServlet
 * 
 * Servlet의 lifeCycle 객체생성 - init - service - doGet/doPost - destroy
 */
@WebServlet(asyncSupported = true, urlPatterns = { "*.do" })
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 컨트롤러의 값이 들어갈 맵 선언
	private Map<String, AbstractController> urlControllerMap = new HashMap<>();

	/**
	 * Servlet 생성시 최초 1회 실행 사용자 요청주소 - controller객체를 binding할 수 있는 정보를 가진 map객체 생성
	 * 
	 * /student/selectOneStudent.do -> SelectOneStudentController
	 * /student/insertStudent.do -> InsertStudentController
	 */
	public void init(ServletConfig config) throws ServletException {
		Properties prop = new Properties();
		String fileName = DispatcherServlet.class.getResource("/url-command.properties").getPath();
		try {
			// 프로퍼티 읽어오기
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// url-pattern : String - Controller 객체 : AbstractController
		/// student/insertStudent.do =
		// com.kh.mybatis.student.controller.InsertStudentController

		// 프로퍼티의 키만 리턴하는 코드
		Set<String> keys = prop.stringPropertyNames();
		// 키 반복
		for (String key : keys) {
			// 키가 반복되며 프로퍼티 값을 value 저장
			String value = prop.getProperty(key); // 클래스명
			// Controller객체화 : new SomeController();
			try {
				// 클래스객체 : 해당클래스의 구성정보(필드, 메소드, 접근제한자), 이것을 통해 객체 생성 가능
				// value를 Class로 객체 생성
				Class clazz = Class.forName(value);
				// 이것을 통해 객체 생성 가능
				AbstractController controller = (AbstractController) clazz.newInstance();
				// urlControllerMap에 추가
				urlControllerMap.put(key, controller);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		System.out.println("urlControllerMap@init = " + urlControllerMap);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 요청주소 가져오기
		String uri = request.getRequestURI(); // maven-mybatis/student/insertStudent.do
		int beginIndex = request.getContextPath().length();
		System.out.println(uri);
		// maven-mybatis만큼 지움
		String url = uri.substring(beginIndex); // /student/insertStudent.do
		
		System.out.println(url);
		// 2. controller 객체 호출
		AbstractController controller = urlControllerMap.get(url);
		if (controller == null) {
			throw new ControllerNotFoundException(url + " controller가 존재하지 않습니다.");
		}

		String method = request.getMethod();
		String viewName = null;
		switch (method) {
		case "GET":
			viewName = controller.doGet(request, response);
			break;
		case "POST":
			viewName = controller.doPost(request, response);
			break;
		default:
			throw new MethodNotAllowedException(method);
		}

		// 3. jsp forwarding 또는 redirect 처리
		// 컨트롤러에 작성된 리다이렉트, 포워드 판단하여 처리
		if (viewName != null) {
			// 컨트롤러에 리턴값이 redirect: ~~
			if (viewName.startsWith("redirect:")) {
				// redirect
				// 리다이렉트 판단을 위해 작성 redirect를 지우고 사용
				String location = request.getContextPath() + viewName.replace("redirect:", "");
				response.sendRedirect(location);
			} else {
				// jsp forwarding
				// 컨트롤러의 리턴 값이 student/insertStudent
				final String prefix = "/WEB-INF/views/";
				final String suffix = ".jsp";
				// 리턴값을 포워드 위해 문자열 더해줌
				request.getRequestDispatcher(prefix + viewName + suffix).forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
