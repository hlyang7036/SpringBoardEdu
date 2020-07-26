<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8R">
<title>boardView</title>
</head>
<script type="text/javascript">
	$j(document).ready(function(){
		
		$j("#boardModifyAction_btn").on("click", function(){
//			var $frm = $j('.boardModify :input');
//			var param = $frm.serialize();
//			console.log(param);
			
			var boardType = $j("#boardType").val();
			var boardNum = $j("#boardNum").val();
			var boardTitle = $j("#boardTitle").val();
			var boardComment = $j("#boardComment").val();
			$j.ajax({
				url : "/board/boardModifyAction.do",
				dataType : "json",
				type : "post",
				data : {
					'boardType' : boardType,
					'boardNum' : boardNum,
					'boardTitle' : boardTitle,
					'boardComment' : boardComment
				},
				success : function(data)
				{
					var pageNo = $j("#pageNo").val();

					alert("수정완료");
//					alert("메세지:"+data.success);

					location.href = "/board/"+boardType+"/"+boardNum+"/boardView.do?pageNo="+pageNo;
					
				},
				error: function (jqXHR, textStatus, errorThrown)
				{
					alert("실패");
				}
			});
			
		});
		
	});
</script>

<body>

<table align="center">
	<tr>
		<td>
		<form name="modifyForm" class="boardModify">
			<table border ="1">
				<tr>
					<td width="120" align="center">
					Title
					</td>
					<td width="400">
					<input name="boardTitle" id="boardTitle" value="${board.boardTitle}"> 
					</td>
				</tr>
				<tr>
					<td height="300" align="center">
					Comment
					</td>
					<td>
					<textarea name="boardComment" rows="20" cols="55" id="boardComment">${board.boardComment}
					</textarea> 
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
			<input type="hidden" id="boardType" name="boardType" value="${boardType}">
			<input type="hidden" id="boardNum" name="boardNum" value="${boardNum}">
			<input type="hidden" id="pageNo" name="pageNo" value="${pageNo}">
			<p>
					<button type="button" id="boardModifyAction_btn">수정 완료</button>&nbsp;&nbsp;
					<button type="button" id="boardCancel_btn">취소</button>
			</p>
			</form>
		</td>
	</tr>
	<tr>
		<td align="right">
			<a href="/board/boardList.do">List</a>
		</td>
	</tr>
</table>
	
</body>
</html>