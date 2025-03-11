<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	a { text-decoration: none; }
</style>
<title>JSP 문법</title>
</head>
<body>
	<h1>* EL(Expression Language)표현 언어</h1>
	<pre>
		기존 JSP상에 사용했던 &lt;%= %>(출력식)같은 경우 사용이 복잡하고,
		컴파일시 문제가 발생할 수 있기 때문에
		이 문제를 대체 할 수 있는 문법
	</pre>
	<h3>1. EL구문 기본학습</h3>
	<!--
		절대 경로방식 : /bm/sc
		상대 경로방식 : sc
	-->
	<a href="sc">I WANT TO GO HOME</a>
	<hr>
	<a href="include">포함하다</a><br>
	<a href="forward">forward</a>
	<hr>
	<a href="jstl">실과 바늘?</a>
</body>
</html>