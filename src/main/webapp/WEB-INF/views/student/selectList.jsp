<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis실습 - selectList</title>
<style>
div#student-container {
	text-align: center;
}

table.tbl-student {
	margin: 0 auto;
	border: 1px solid;
	border-collapse: collapse;
}

table.tbl-student th, table.tbl-student td {
	border: 1px solid;
	padding: 5px;
}
</style>
</head>
<body>
	<div id="student-container">
		<h2>selectList</h2>
		<p>SqlSession의 selectList메소드를 호출해서 List&lt;Student>를 리턴받음.</p>
		<table class="tbl-student">
			<tr>
				<th>학번</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>이메일</th>
				<th>주소</th>
				<th>등록일</th>
			</tr>
			<c:if test="${empty list}">
				<tr>
					<td colspan="4">등록된 학생이 존재하지 않습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty list}">
				<c:forEach items="${list}" var="s">
					<tr>
						<td>${s.no}</td>
						<td>${s.name}</td>
						<td>${s.tel}</td>
						<td></td>
						<td></td>
						<td><fmt:formatDate value="${s.reg_date}"
								pattern="yy/MM/dd(E) hh:mm:ss" /></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<hr />
		<p>SqlSession의 selectList메소드를 호출해서 List&lt;Map&lt;String,
			Object>>을 리턴받음.</p>
		<table class="tbl-student">
			<tr>
				<th>학번</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>등록일</th>
			</tr>
			<c:if test="${empty mapList}">
				<tr>
					<td colspan="4">등록된 학생이 존재하지 않습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty mapList}">
				<c:forEach items="${mapList}" var="ms">
					<tr>
						<td>${ms.no}</td>
						<td>${ms.name}</td>
						<td>${ms.tel}</td>
						<td><fmt:formatDate value="${ms.reg_date}"
								pattern="yy/MM/dd(E) hh:mm:ss" /></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	</div>


</body>
</html>
