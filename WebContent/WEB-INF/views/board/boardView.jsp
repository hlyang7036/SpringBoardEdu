<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardView</title>
</head>
<script type="text/javascript">
	$j(document).ready(function(){
		
		$j("#boardDeleteAction_btn").on("click",function(){

			
			var boardType = $j("#boardType").val();
			var boardNum = $j("#boardNum").val();
			
			$j.ajax({
			    url : "/board/boardDeleteAction.do",
			    dataType: "json",
			    type: "POST",
			    data : {
			    		'boardType': boardType,
			    		'boardNum' : boardNum,
			    	},
			    success: function(data, textStatus, jqXHR)
			    {
			    	var pageNo = $j("#pageNo").val();
					alert("삭제완료");
					
//					alert("메세지:"+data.success);
					
					location.href = "/board/boardList.do?pageNo="+pageNo;
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    }
			});
		});
		
		// 수정페이지 이동
		$j("#boardModify_btn").on("click", function(){

			var boardType = $j("#boardType").val();
			var boardNum= $j("#boardNum").val();
			var pageNo = $j("#pageNo").val();
			
			location.href = "/board/boardModify.do?pageNo="+pageNo
							+"&boardType="+boardType
							+"&boardNum="+boardNum;
		});
		
	
		
	})
</script>

<body>

<table align="center">
	<tr>
		<td>
		<form name="viewForm" class="boardView">
			<input type="hidden" id="boardType" name="boardType" value="${boardType}">
			<input type="hidden" id="boardNum" name="boardNum" value="${boardNum}">
			<input type="hidden" id="pageNo" name="pageNo" value="${pageNo}">
			<table border ="1">
				<tr>
					<td width="120" align="center">
					Type
					</td>
					<td width="400">
						${boardType}
					</td>
				</tr>
				<tr>
					<td width="120" align="center">
					Title
					</td>
					<td width="400">
						${board.boardTitle}
					</td>
				</tr>
				<tr>
					<td height="300" align="center">
					Comment
					</td>
					<td>
						${board.boardComment}
					</td>
				</tr>
				<tr>
					<td align="center">
					Writer
					</td>
					<td>
					</td>
				</tr>
			</table>
			<p>
					<button type="button" id="boardModify_btn">수정</button>&nbsp;&nbsp;
					<button type="button" id="boardDeleteAction_btn">삭제</button>
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