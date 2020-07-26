<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript">
	$j(document).ready(function(){
		$j("#loginAction").on("click", function(){
			var loginIdInput = $j("#loginIdInput").val();
			var loginPwInput = $j("#loginPwInput").val();
			if ($j("#loginIdInput").val() == "") {
				alert("아이디를 입력 해주세요");
				$j("#loginIdInput").focus();
				return false;
			} else if ($j("#loginPwInput").val() == "") {
				alert("비밀번호를 입력 해주세요");
				$j("#loginPwInput").focus();
				return false;
			} else{
				$j.ajax({
					url : "/board.loginChkAction.do",
					type : "POST",
					data : {
						'userId' : loginIdInput,
						'userPw' : loginPwInput
					},
					dataType : "json",
					success : function(data){
						if (data == "0") {
							alert("존재하지 않는 아이디 입니다.");
							return;
						} else if (data == "1") {
							alert("비밀번호가 틀렸습니다.");
							return;
						} else {
							alert("방문을 환영합니다.");
							var formObj = $j("form[name=loginForm]");
							formObj.attr("action", "/board/loginAction.do");
							formObj.attr("method", "POST")
							formObj.submit();
						}
					}
					
				})
				
			}
		});
	});
</script>

<title>list</title>
</head>
	<body>
		<form name="loginForm">
		<table align="center">
			<tr>
				<td>
					<table align="center" border="1">
						<tr>
							<td>ID
							</td>
							<td>
								<input id="loginIdInput" name="userId" type="text" maxlength="15">
							</td>
						</tr>
						<tr>
							<td>PASSWORD
							</td>
							<td>
								<input id="loginPwInput"name="userPw" type="password" maxlength="16">
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table align="right">
						<tr>
							<td>
								<p id="loginAction">login
								</p>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<table>
			
		</table>
	</form>
</body>
</html>