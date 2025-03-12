<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<link href=resources/css/header.css" rel="stylesheet">
<title>SDK</title>
</head>
<body>
	<div>
		<ul>
			<li class="top-item">
				<a href="#">HOME</a>
			</li>
			<li class="top-item">
				<a href="#">공지사항</a>
			</li>
			<li class="top-item">
				<a href="#">1</a>
			</li>
			<li class="top-item">
				<a href="#">2</a>
			</li>

			<c:choose>
				<c:when test="${ empty loginUser }">
					<li class="top-item">
						<a data-toggle="modal" data-target="#login-form">로그인</a>
					</li>
					<li class="top-item">
						<a href="signup-form">회원가입</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="top-item">
						<a href="myPage">내정보</a>
					</li>
					<li class="top-item">
						<a href="logout" onclick="return confirm('로그아웃?')">로그아웃</a>
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>

	<!-- 로그인 Modal-->
	<div class="modal fade" id="#login-form">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">
						<span></span>로그인
					</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form action="/sdk/login" name="login" method="post" id="loginForm" style="margin-bottom: 0;">
						<table style="cellpadding: 0; cellspacing: 0; margin: 0 auto; width: 100%">
							<tr>
								<td style="text-align: left">
									<p>
										<strong>아이디를 입력해주세요.</strong>&nbsp;&nbsp;&nbsp;
										<span id="idCheck"></span>
									</p>
								</td>
							</tr>
							<tr>
								<td><input type="text" name="userId" id="loginId"
									class="form-control tooltipstered" maxlength="10"
									required="required" aria-required="true" placeholder="">
								</td>
							</tr>
							<tr>
								<td style="text-align: left">
									<p>
										<strong>비밀번호를 입력해주세요.</strong>&nbsp;&nbsp;&nbsp;
										<span id="pwCheck"></span>
									</p>
								</td>
							</tr>
							<tr>
								<td><input type="password" name="userPw" id="loginPw"
									class="form-control tooltipstered" size="17" maxlength="20"
									required="required" aria-required="true" placeholder="">
								</td>
							</tr>
							<tr>
								<td style="width: 100%; text-align: center; colspan: 2;">
									<input type="submit" value="로그인" id="login-btn" class="btn form-control tooltipstered">
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>