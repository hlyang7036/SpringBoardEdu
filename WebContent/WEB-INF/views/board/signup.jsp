<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$j(document).ready(function(){
		var idEnCheck = RegExp(/[^a-zA-Z0-9]{1,14}$/);
//		var idEnCheck = RegExp(/[^a-z]$/i);
		$j("#userIdInput").keyup(function(){
			$j("#idChk").attr("value", "N");
			if (idEnCheck.test($j("#userIdInput").val())) {
//				alert("아이디는 5-15 영문과 숫자만 사용가능합니다.");
				$j("#userIdInput").val($j("#userIdInput").val().replace(/[^a-z]/gi,''));
			}
		});
		/* ^[a-z]이면 소문자로 시작하는지 [^a-z]면 소문자를 제외하고다 */
		var pwCheck = RegExp(/[^A-Za-z0-9]{6,12}$/);
//		var userPwInput =$j("#userPwInput").val();
//		var userPwChkInput = $j("#userPwChkInput").val();
		$j("#userPwInput").keyup(function(){

			if (pwCheck.test($j("#userPwInput").val())) {
//				alert("패스워드는 영문 대문자와 소문자, 숫자를 사용하여 6-12자리 사용가능합니다.");
				$j("#userPwInput").val($j("#userPwInput").val().replace(/[^a-z]/gi,''));
			} else  {
				var userPwInput =$j("#userPwInput").val();
				var userPwChkInput = $j("#userPwChkInput").val();
				if ($j("#userPwInput").val().length >6 ) {

					if (userPwChkInput.length <1) {
						$j("#pwChkWarning").css("display", "none");
						$j("#pwChkSuccess").css("display","none");
						
					} else {
						if (userPwInput != userPwChkInput) {
							$j("#pwChkWarning").css("display", "");
							$j("#pwChkSuccess").css("display","none");
							$j("#userPwInput").focus();
						} else {
							$j("#pwChkWarning").css("display", "none");
							$j("#pwChkSuccess").css("display","");
						}
					}
				} else {
					$j("#pwChkWarning").css("display", "none");
					$j("#pwChkSuccess").css("display","none");
					
				}
			}
		});
			
//		var idEnCheck = RegExp(/[^a-z]$/i);
		$j("#userPwChkInput").keyup(function(){

			if (pwCheck.test($j("#userPwChkInput").val())) {
//				alert("패스워드는 영문 대문자와 소문자, 숫자를 사용하여 6-12자리 사용가능합니다.");
				$j("#userPwChkInput").val($j("#userPwChkInput").val().replace(/[^a-z]/gi,''));
			} else {
				var userPwInput =$j("#userPwInput").val();
				var userPwChkInput = $j("#userPwChkInput").val();
				if ($j("#userPwChkInput").val().length >6 ) {
					if (userPwInput.length <1) {
						$j("#pwChkWarning").css("display", "none");
						$j("#pwChkSuccess").css("display","none");
					} else {
						if (userPwInput != userPwChkInput) {
							$j("#pwChkWarning").css("display", "");
							$j("#pwChkSuccess").css("display","none");
							$j("#userPwChkInput").focus();
						} else {
							$j("#pwChkWarning").css("display", "none");
							$j("#pwChkSuccess").css("display","");
						}
					}					
				} else {
					$j("#pwChkWarning").css("display", "none");
					$j("#pwChkSuccess").css("display","none");
				}
			}
		});
			
		/* 영어와 숫자가 안들어가게 */
		var nameCheck = RegExp(/^[\u3131-\u318E\uAC00-\uD7A3]*$/); // 한글만 받는다
		$j("#userNameInput").keyup(function(){
			if (!(nameCheck.test($j("#userNameInput").val()))) {
//				alert("이름은 2-7 한글과 영문 사용가능합니다.");
				$j("#userNameInput").val($j("#userNameInput").val().replace(/[a-zA-Z0-9]/gi,''));
			}
		});
			
		/* 숫자만 가능 #userPhoneNumInput2 사용  */
		var phoneNumChk2 = RegExp(/^[0-9]*$/);
		$j("#userPhoneNumInput2").keyup(function(){
			if (phoneNumChk2.test($j("#userPhoneNumInput2").val()) !=true) {
//				alert("4자리 숫자만 입력 가능합니다");
				$j("#userPhoneNumInput2").val($j("#userPhoneNumInput2").val().replace(/[a-z\u3131-\u318E\uAC00-\uD7A3]/gi,''));
			}
		});
		/* 숫자만 가능 #userPhoneNumInput3 사용 */
		var phoneNumChk3 = RegExp(/^[0-9]*$/);
		$j("#userPhoneNumInput3").keyup(function(){
			if (phoneNumChk3.test($j("#userPhoneNumInput3").val()) !=true) {
//				alert("4자리 숫자만 입력 가능합니다");
				$j("#userPhoneNumInput3").val($j("#userPhoneNumInput3").val().replace(/[a-z\u3131-\u318E\uAC00-\uD7A3]/gi,''));
			}
		});

			
		// 우편번호 번호만 입력 가능  #postNoInput 사용
		var chkPostNo1 = RegExp(/^[0-9]|-*$/);	// 숫자만 입력 가능하게
		$j("#postNoInput").keyup(function(){	// 값을 입력 할 때마다 값을 체크 하게된다
			if (!(chkPostNo1.test($j("#postNoInput").val()))) {	// 숫자가 아닌경우
				/* 숫자 이외의것은 삭제 */
//				alert("우편번호는 숫자만 입력 가능합니다");
				// 영어 한글이 들어올경우 삭제, 특문중에 -는 허용하고 다른 특문은 지운다
				// 4번째 자리가 아닐때 -이 들어올경우 -를 지워야한다
				$j("#postNoInput").val($j("#postNoInput").val()
						.replace(/[a-z\u3131-\u318E\uAC00-\uD7A3~!@#$%^&*()_+\=\[\]{};':"\\|,.<>\/?]/gi,''));
			} else {	// 숫자와 -가 들어왔을 경우
				// 현재 입력된 우편번호
				var nowPostNo = $j("#postNoInput").val();
				// 마지막 입력 숫자 저장
				var lastPostNo = nowPostNo.substring(nowPostNo.length);
				// 4번재 자릿수가 아닐때와 마지막값이 -인경우 참일때 -를 지운다 거짓일경우 -를 남겨둔다
				if (nowPostNo.length != 4 && lastPostNo == "-") {	
					$j("#postNoInput").val($j("#postNoInput").val()
							.replace(/[a-z\u3131-\u318E\uAC00-\uD7A3~!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/gi,'')); 
				} else {
					$j("#postNoInput").val($j("#postNoInput").val()
							.replace(/[a-z\u3131-\u318E\uAC00-\uD7A3~!@#$%^&*()_+\=\[\]{};':"\\|,.<>\/?]/gi,''));
				}
				// 숫자가 3개 일때 -를 추가한다
				$j("#postNoInput").val($j("#postNoInput").val()
						.replace(/([0-9]{3})([0-9]{3})/,"$1-$2"));
			}
		
		});
			
		// 회원가입 절차
		$j("#signupAction").on("click", function(){
			var userIdInput = $j("#userIdInput").val();
			var userPwInput =$j("#userPwInput").val();
			var userPwChkInput = $j("#userPwChkInput").val();
			if ($j("#userIdInput").val() == "") {
				alert("아이디를 입력해주세요");
				$j("#userIdInput").focus();
				return false;
			}
			if ($j("#userPwInput").val() == "") {
				alert("비밀번호를 입력해주세요");
				$j("#userPwInput").focus();
				return false;
			}
			if ($j("#userPwChkInput").val() == "") {
				$j("#pwChkWarning").append(pwChkWarning)
				alert("비밀번호 확인을 입력해주세요");
				$j("#userPwChkInput").focus();
				return false;
			}
			if ($j("#userNameInput").val() == "") {
				alert("이름을 입력해주세요");
				$j("#userNameInput").focus();
				return false;
			}
			if ($j("#userPhoneNumInput2").val() == "" || $j("#userPhoneNumInput2").val().length < 4) {
				alert("폰번호 4자리를  입력해주세요");
//				alert($j("#userPhoneNumInput2").val().length)
				$j("#userPhoneNumInput2").focus();
				return false;
			}
			if ($j("#userPhoneNumInput3").val() == "" || $j("#userPhoneNumInput3").val().length < 4) {
				alert("폰번호 4자리를  입력해주세요");
				$j("#userPhoneNumInput3").focus();
				return false;
			}
			if ($j("#postNoInput").val().length > 1 && $j("#postNoInput").val().length <6) {
				alert("우편번호를 확인해주세요");
				$j("#postNoInput").focus();
				return false;
			}
//			else ($j("#postNoInput").val() == "") {
//				alert("1");
//				return true;
//			} 
			
			if (userPwInput != userPwChkInput) {
//				var pwChkWarning = "<p id='pwChkContext'>비밀번호와 비밀번호 확인의 입력이 다릅니다</p>";
				alert("비밀번호와 비밀번호 확인의 입력이 다릅니다");
//				$j("#pwChkWarning").attr("style", "visibility: none");
				$j("#pwChkWarning").css("display","");
				$j("#pwChkSuccess").css("display","none");
				$j("#userPwInput2").focus();
				return false;
			} else {
				var idChk = $j("#idChk").val();
				$j("#pwChkWarning").css("display","none");
				if (idChk == "N") {
					alert("아이디 중복을 확인하세요.");
					$j("#idChk").focus();
					return false;
				} else if (idChk == "Y") {
//					var $frm = $j(".signup :input");
					
					var param = $j(".signup").serialize();
					$j.ajax({
						url : "/board/signupAction.do",
						data : param,
						dataType : "json",
						type : "POST",
						success : function(data){
							if (data != 1) {
								alert("회원가입 오류");
							} else {
								alert("가입을 환영합니다");
								location.href = "/board/boardList.do";
							}
						},
						error: function (jqXHR, textStatus, errorThrown)
					    {
					    	alert("실패");
					    }
					})
//					var formObj = $j("form[name='signup']");
//					formObj.attr("action", "/board/signupAction.do");
//					formObj.attr("method", "POST");
//					formObj.submit();
//					alert("가입을 환영합니다");
				}
			}
		});
		
	});
	
	function fn_idChk(){
		if ($j("#userIdInput").val() == "") {
			alert("아이디를 입력해주세요");
		} else
		$j.ajax({
			url : "/board/idChkAction.do",
			type : "post",
			dataType : "json",
			data : { 'userId' : $j("#userIdInput").val()
				
			},
			success : function(data){
				if (data == 1) {
					alert("중복된 아이디입니다");
				} else if(data == 0) {
					$j("#idChk").attr("value", "Y");
					alert("사용 가능한 아이디입니다");
				}
			},
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		    	alert("실패");
		    }
		});
	}
</script>
<title>list</title>
</head>
<body>
	<form name="signup" class="signup">
		<table align="center">
			<tr>
				<td  align="left">
					<a href="/board/boardList.do">list</a>
				</td>	
			</tr>
			<tr>
				<td>
					<table align="center" border="1">
						<tr>
							<td>
								id
							</td>
							<td>
								<input id="userIdInput" name="userId" type="text" maxlength="15">
								<button type="button" id="idChk" value="N" onclick="fn_idChk()">중복확인</button>
							</td>
						</tr>
						<tr>
							<td>
								pw
							</td>
							<td>
								<input id="userPwInput" name="userPw" type="password" maxlength="7" >
								<div id="pwChkWarning" style="display:none;">비밀번호가 일치하지 않습니다.</div>
								<div id="pwChkSuccess" style="display:none;">비밀번호가 일치 합니다</div>
							</td>
						</tr>
						<tr>
							<td>
								pw check
							</td>
							<td>
								<input id="userPwChkInput"  type="password" maxlength="7">
							</td>
						</tr>		
						<tr>
							<td>
								name
							</td>
							<td>
								<input id="userNameInput" name="userName" type="text" maxlength="7">
							</td>
						</tr>
						<tr>
							<td>
								phone
							</td>
							<td>
								<select name="userPhone1">
									<c:forEach items="${codeListPhone}" var="codeListPhone">
										<option value="${codeListPhone.codeId}">${codeListPhone.codeName}</option>
									</c:forEach>
								</select>
								<input id="userPhoneNumInput2" name="userPhone2" type="text" maxlength="4" size="4">&nbsp;-
								<input id="userPhoneNumInput3" name="userPhone3" type="text" maxlength="4" size="4">
							</td>
						</tr>		
						<tr>
							<td>
								postNo
							</td>
							<td>
								<input id="postNoInput" name="userAddr1" type="text" maxlength="7" size="7">
							</td>
						</tr>		
						<tr>
							<td>
								address
							</td>
							<td>
								<input id="address" name="userAddr2" maxlength="75">
							</td>
						</tr>		
						<tr>
							<td>
								company
							</td>
							<td>
								<input id="company" name="userCompany" maxlength="30">
							</td>
						</tr>		
					</table>
					<p id="signupAction" align="right">join</p>
				</td>
			</tr>
		</table>
	</form>	
</body>
</html>