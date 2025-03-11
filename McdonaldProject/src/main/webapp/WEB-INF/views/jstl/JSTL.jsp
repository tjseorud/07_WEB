<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<style>
		#g { color: gold; }
		#d {
			color: white;
			background-color: pink;
		}
	</style>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<title>JSTL</title>
</head>
<body>
	<h1>JSTL이란?</h1>
	<p>
		JSP Standard Tag Library의 약어 JSP에서 사용할 수 있는 커스텀 액션태크<br>
	 	공통적으로 사용해야하는 코드들을 보다 쉽게 사용할 수 있도록 태그화해서 표준으로 제공하는 라이브러리<br>
	</p>
	<hr>
	<p>
		JSTL을 사용하기 위해선 먼저 라이브러리를 추가해준 후<br>
		JSTL을 사용하고자 하는 JSP페이지 상단에 선언을 해줘야함<br>
	</p>
	<% if(true) { %>
		ABCDEF 
	<% } %>
	<h2>Core 라이브러리 요약</h2>
	<p>
		if라는 태그를 작성하여 조전문을 만들어 낼 수 있음<br>
		- Jaca에서 단일 if문과 똑같은 역할을 할수있는 태그<br>
		- 조건식은 test라는 속성에 작성
		 (조건식을 만들때 반드시 EL구문으로 작성해야..)
	</p>
	<c:if test="${ 1 lt 2 }">
		<strong>1이가 2보다 작습니다.</strong>
	</c:if>
	<c:if test="${ 1 gt 2 }">
		<strong>1이가 2보다 큼니다.</strong>
	</c:if>
	<c:if test="${ '원두' ne '모카' }">
		<mark>원두와 모카는 같지 않습니다.</mark>
	</c:if>
	<hr>
	<h3>2) choose, when, otherwise</h3>
	<!-- point 키값으로 Value가 넘어옴 -->
	<!--
		포인트 사용량이 100이하라면 일반회원으로 출력 
		포인트 사용량이 300이하라면 우수회원으로 출력
		두 케이스에 걸리지 않을 시 최우수회원으로 출력하려함 
	 --><!-- choose 안에는 주석 불가 -->
	 <c:choose>
	 	<c:when test="${ point le 100 }"> 
	 		<label>일반회원</label><br>
 		</c:when>
	 	<c:when test="${ point le 100 }"> 
	 		<label id="g">우수회원</label><br>
 		</c:when>
	 	<c:otherwise>
	 		<mark id="d">최우수회원</mark><br>
	 	</c:otherwise>
	 </c:choose>
	<hr>
	<h3>3) 반복문 - forEach</h3>
	<pre>
		[표현법]
		for loop문
		&lt;c:forEach var="속성명" begin ="초기값" end="종료값" step="증가값(생략가능)"&gt;
			반복할 내용
		&lt;/c:forEach&gt;
		
		&lt;c:forEach var="속성명" items="순차적으로 접근할 배열 또는 컬렉션"&gt;
			반복할 내용
		&lt;/c:forEach&gt;
	</pre>
	<c:forEach var="i" begin="0" end="9">
		i ${ i }
	</c:forEach>
	<br>
	<c:forEach var="i" begin="1" end="6">
		<h${i}> 2025.03.11 </h${i}]>
	</c:forEach>
	<hr>
	<ul>
		<c:forEach var="c" items="${ colors }">
			<li style="color: ${c}">${c}</li>
		</c:forEach>
	</ul>
	<hr>
	<h3>회원목록</h3>
	<!-- caption, thead, tbody, tfoot, tr, th, td -->
	<table class="table table-striped">
		<thead>
			<tr>
				<th>NO</th>
				<th>ID</th>
				<th>NAME</th>
				<th>DATE</th>
			</tr>
		</thead>
		<tbody>
		<!-- 조회된 결과가 있는가 없는가? -->			
			<c:choose>
				<c:when test="${ empty requestScope.users }">
					<tr><td colspan="4">조회결과가 존재하지 않습니다..</td></tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="user" items="${ users }">
						<tr>
							<td>${ user.userNo }</td>
							<td>${ user.userId }</td>
							<td>${ user.userName }</td>
							<td>${ user.enrollDate }</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>				
		</tbody>
	</table>
</body>
</html>