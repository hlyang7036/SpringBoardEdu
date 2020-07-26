<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list</title>
<style type="text/css">
	li {
			list-style: none;
			float: left;
			padding: 6px;
		}
</style>
</head>
<script type="text/javascript">

	$j(document).ready(function(){
		
		// 전체체크박스 이벤트
		$j("#searchAll").on("click", function(){
			
			// 전체 체크박스 체크시
			if ($j("#searchAll").prop("checked")) {
				// 아래 체크 박스 모두 체크
				$j("input[type=checkbox]").prop("checked", true);
				
				//전체 체크박스 체크 해제시 
			} else {
				// 아래 체크 박스 보두 해제
				$j("input[type=checkbox]").prop("checked", false);
			}
		});
		
 		// type조회
		$j("#typeSearch").on("click", function(){
			var searchKeysArray = new Array();
			var formObj = $j("form[name='searchChkForm']");
			var pageNo = $j("#pageNo").val();
			if ($j("input[name='searchKeys']:checked").length >0) {
				$j("input[name='searchKeys']:checked").each(function(){
					searchKeysArray.push($j(this).val());
					console.log(searchKeysArray);
				
				});
			}
			formObj.attr("action", "/board/boardList.do?pageNo="+pageNo+"&searchKeys="+searchKeysArray);
			formObj.attr("method", "get");
			formObj.submit();
		});
		
	});
	
	// 체크박스 체크 클릭이벤트
	function fn_searchKeys(index){
		// 체크박스 클릭시 전체체크박스를 제외한 체크박스의 갯수와
		//체크된 체크박스의 갯수가 같으면 전체체크박스의 체크를 체크상태로 바뀜
		if($j("input[name='searchKeys']").length == 
			$j("input[name='searchKeys']:checked").length) {
			$j("#searchAll").prop("checked", true);
			// 전체체크박스와 그외  모든 체크박스가 클릭되어 있는 상태에서
			// 이미 체크되어있는 체크박스를 또 클릭하면 전체체크박스의 체크상태가 해제
		} else if($j("input[name='searchKeys']").is(":checked")==true 
				&& $j("#searchAll").is(":checked") == true) {
			$j("#searchAll").prop("checked", false);
			// 클릭 후 해당 체크박스의 체크여부
		} else if ($j("#searchKeys"+index).is(":checked") == true) {
			// 체크가 안되어 있다면 체크
			$j("#searchKeys"+index).prop("checked", true);
		} else {
			// 체크가 되어 있다면 체크해제
			$j("#searchKeys"+index).prop("checked", false);
		}
	}	

</script>
<body>
<table  align="center">
	<tr>
		<c:if test="${user == null}">
			<td align="left">
				<a href="/board/login.do">login</a>
				<a href="/board/signup.do">join</a>
			</td>
		</c:if>
		<c:if test="${user != null}">
			<td align="left">
				<p>${user.userName}님 환영합니다.</p>
			</td>
		</c:if>
		<td align="right">
			total : ${totalCnt}
		</td>
	</tr>
	<tr>
		<td>
			<table id="boardTable" border = "1">
				<tr>
					<td width="80" align="center">
						Type
					</td>
					<td width="40" align="center">
						No
					</td>
					<td width="300" align="center">
						Title
					</td>
				</tr>
				<c:forEach items="${boardList}" var="list">
					<tr>
						<td align="center">
							${list.codeName}
						</td>
						<td>
							${list.boardNum}
						</td>
						<td>
							<a href = "/board/${list.boardType}/${list.boardNum}/boardView.do?">${list.boardTitle}</a>
						</td>
					</tr>	
				</c:forEach>
			</table>
		</td>
	</tr>
	<tr>
		<c:if test="${user != null}">
			<td align="right">
				<a href ="/board/boardWrite.do">글쓰기</a>
				<a href ="/board/logout.do">로그아웃</a>
			</td>
		</c:if>
	</tr>
	<tr>
		<td>
		<form name="searchChkForm">
		<input type="hidden" id="pageNo" name="pageNo" value="${pageMaker.pageVo.pageNo}">
			<label><input type="checkbox" id="searchAll">전체</label>
				<c:forEach items="${codeList}" var="codeList" varStatus="index">
					<label><input name="searchKeys" id="searchKeys${index.count}" type="checkbox" value="${codeList.codeId}" 
					onclick="fn_searchKeys(${index.count})">${codeList.codeName}</label>
				</c:forEach>
			<button type="button" id="typeSearch">조회</button>
		</form>
		</td>
	</tr>
	<tr>
		<td>
			<div>
				<ul>
					<c:if test="${pageMaker.prev}">
						<li><a href="/board/boardList.do${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a></li>
					</c:if>
					
					<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
						<li><a id="pageNoList" href="/board/boardList.do${pageMaker.makeSearch(idx)}">${idx}</a></li>
					</c:forEach>
					
					<c:if test="${pageMaker.next} && pageMaker.endPage > 0">
						<li><a href="/board/boardList.do${pageMaker.makeSearch(pageMaker.endpage + 1)}">다음</a></li>
					</c:if>
				</ul>
				
			</div>
		</td>
	</tr>
</table>

</body>
</html>