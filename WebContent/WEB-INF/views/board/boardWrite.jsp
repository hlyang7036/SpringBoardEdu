<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>boardWrite</title>
</head>
<script type="text/javascript">

	$j(document).ready(function(){
		var tableIndex = 0;
		
		$j("#submit").on("click",function(){
			var $frm = $j('.boardWrite :input');
			var param = $frm.serialize();
			
// 			var boardTitle = $j("#boardTitle").val();
// 			var boardComment = $j("#boardComment").val();
// 			var codeId = $j("#selectCode").val();
// //			console.log(param);
// 			console.log(boardTitle);
// 			console.log(boardComment);
			console.log(param);
			
			if ($j("#boardTitle").val() == "") {
				alert("제목을 입력해주세요");
				$j("#boardTitle").focus();
				return false;
			}
			if ($j("#userName").text() == "") {
				alert("글작성은 로그인이 필요합니다.");
				return false;
			}
			$j.ajax({
			    url : "/board/boardWriteAction.do",
			    dataType: "json",
			    type: "POST",
			    data : param
// 			    {
// 			    		'boardTitle' : boardTitle,
// 			    		'boardComment' : boardComment,
// 			    		'codeId' : codeId,
// 			    		'pageNo' : pageNo
// 			    			param
// 			    		}
			    ,
			    success: function(data, textStatus, jqXHR)
			    {
			    	var pageNo = $j("#pageNo").val();
					alert("작성완료");
					
					alert("메세지:"+data.success);
					
					location.href = "/board/boardList.do?pageNo="+pageNo;
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    }
			});
		});
		
		$j("#addComment").on("click", function(){
			$j("#deleteComment").css("display", "");
			
			
			var userName = $j("#userName").text();
			console.log(userName);
			var addTable = "<p><table border='1' id='addTable"+tableIndex+"'>";
			addTable += "<tr>";
			addTable += "<td>";
			addTable += "<input type='checkbox' name='selectedTable' class='selectTable' id='selectTable"+tableIndex+"' value="+tableIndex+">선택";
			addTable += "</td>";
			addTable += "</tr>";
			addTable += "<tr>";
			addTable += "<td width='120' align='center'>type</td>";
			addTable += "<td width='409'>";
			addTable += "<select id='selectCode' name = 'codeId'>"
			$j.each(${codeListJson}, function(index, item){
				addTable += "<option value='"+item.codeId+"'>"+item.codeName+"</option>";
			});	
			addTable += "</select>";
			addTable += "</td>";
			addTable += "</tr>";
			addTable += "<tr>";
			addTable += "<td width='120' align='center'>";
			addTable += "Title</td>";
			addTable += "<td width='400'>";
			addTable += "<input id='boardTitle' name='boardTitle' type='text' size='50'>";
			addTable += "</td>";
			addTable += "</tr>";
			addTable += "<tr>";
			addTable += "<td height='300' align='center'>"; 
			addTable += "Comment";
			addTable += "</td>";
			addTable += "<td valign='top'>";
			addTable+= "<textarea id='boardComment' name='boardComment'  rows='20' cols='55'></textarea>";
			addTable += "</td>";
			addTable += "</tr>"; 
			addTable += "<tr>";
			addTable += "<td align='center'>";
			addTable += "Writer";
			addTable += "</td>";
			addTable += "<td>"+userName+"</td>";
			addTable += "</tr>";
			addTable += "</table></p>";
			
			tableIndex ++;
			$j("#tablePlace").append(addTable);
			
		
		});
		
		$j("#deleteComment").on("click", function(){
			// 선택된 폼의 인덱스 값을 담을 배열
			var checkBox = new Array();
			// 체크된 체크박스의 value값을 모두 배열에 담는다
			$j("input[name='selectedTable']:checked").each(function(){
				checkBox.push($j(this).val());
			});
			// 체크된 체크박스가 없다면 
			if (checkBox.length < 1) {
				alert("삭제할 행을 선택해주세요");
				return false;
			} else {	// 체크된 체크박스가 있다면
				// 체크된 체크박스의 인덱스값을 꺼내서 선택된 폼의 id를 찾아 폼삭제   
				for (var i = 0; i < checkBox.length; i++) {
					var index = checkBox[i];
					// 행추가된 테이블의 인덱스값을 찾아서 삭제
					$j("#addTable"+index).remove();
					
				}
				// 만약 행추가로 만든어진 폼이 없다면 행삭제버튼 숨김
				if ($j("input[name='selectedTable']").length < 1) {
					$j("#deleteComment").css("display","none");
				}

			}

		});
		
	});
	

</script>
<body>
<form class="boardWrite">
<input type="hidden" id="pageNo" name="pageNo" value="${pageNo}">
<c:if test="${user.userName != null}">
	<input type="hidden" name="creator" value="${user.userName}">
</c:if>
	
	<table align="center">
		<tr>
			<td align="right">
				<input id="submit" type="button" value="작성">
				<input id="addComment" type="button" value="행 추가">
				<input id="deleteComment" type="button" value="행 삭제" style="display: none">
			</td>
		</tr>

		<tr>
			<td>
			<div id="tablePlace">
				<table border ="1"> 
					<tr>
						<td width="120" align="center">
						Type
						</td>
						<td width="400">
							<select id="selectCode" name = "codeId">
								<c:forEach items="${codeList}" var="code">
									<option id="codeList" value="${code.codeId}">${code.codeName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>					
					<tr>
						<td width="120" align="center">
						Title
						</td>
						<td width="400">
						<input id="boardTitle" name="boardTitle" type="text" size="25" value="${board.boardTitle}"> 
						</td>
					</tr>
					<tr>
						<td height="300" align="center">
						Comment
						</td>
						<td valign="top">
						<textarea id="boardComment" name="boardComment"  rows="20" cols="55">${board.boardComment}</textarea>
						</td>
					</tr>
					<tr>
						<td align="center">
						Writer
						</td>
						<td id="userName">${user.userName}
						</td>
					</tr>
				</table>
			</div>
			</td>
		</tr>
		<tr>
			<td align="right">
				<a href="/board/boardList.do">List</a>
			</td>
		</tr>
	</table>
</form>	
</body>
</html>