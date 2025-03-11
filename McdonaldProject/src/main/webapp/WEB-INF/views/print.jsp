<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<% //String brand = (String)request.getAttribute("brand"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>으어으어으어으어</title>
</head>
<body>
	${ brand }<br>
	${ bestSeller }
	
	<ul>
		<li>brand	: ${ bestSeller.brand }</li>
		<li>이름 	: ${ bestSeller.name }</li>
		<li>가격 	: ${ bestSeller.price }원</li>
	</ul>
	두 개 이상의 Scope에 같은 키값으로 값을 담은 경우<br>
	<!-- page => request => session => application 순으로 키값을 검색 -->
	
	Scope에 직접 접근하는 방법<br>
	
	requestScope : ${ requestScope.brand }<br>
	sessionScope : ${ sessionScope.brand }<br>
	
	만약에 없는 키값 el구문으로 출력하려고 하면?<br><br>
	없는 값 : ${ sessionScope.abc }
	<hr>
	
	연산은 ?에서 하는게 좋음
	1. SQL문 DB단
	2. Java => Service단(validation/transaction)
	3. View
	<hr>
	
	<p>* EL 구문을 이용한 산술연산<br>
		max + min = ${ max + min }<br>
		max - min = ${ max - min }<br>
		max * min = ${ max * min }<br>
		max / min = ${ max / min } or ${ max div min }<br>
		max % min = ${ max % min } or ${ max mod min }<br>
	</p>
	<h3>2. 논리연산</h3>
	<p>
		AND : ${ true && true } or ${ true and true }<br>
		OR 	: ${ true || true } or ${ true or true }<br>
	</p>
	<h4>비교연산</h4>
	<p>
		max이가 min보다 작니? : ${ max < min } or ${ max lt min }<br>
		max이가 min보다 크니? : ${ max gt min } or ${ max lt min }<br>
		max이가 min보다 작거나 같니? : ${ max le min }<br>
		max이가 min보다 크거나 같니? : ${ max ge min }<br>
	</p>
	<h4>동등비교</h4>
	<p>
		max이가 min과 같니? : ${ max == min } or ${ max eq min }<br>
		max이가 10과 같니? : ${ max eq 10 }<br>
		str이가 히히가 같니? : ${ str == "히히" } or ${ str eq "히히" }<br>
		max이가 10과 같지않니? : ${ max ne 10 }<br>
	</p>
	<h4>비어있는지 체크</h4>
	<p>
		bestSeller이가 null과 일치합니까?<br>
		${ bestSeller == null } or ${ bestSeller eq null } or ${ empty bestSeller }<br>
		
		list이가 비어있습니까?<br>
		${ !empty list }
	</p>
</body>
</html>