<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>MyBatis Music</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="Keywords" content="MyBatis Music App Test" />
<meta name="Description" content="This is test web app for MyBatis Music Video" />
<style>
html, body {
	margin: 0;
	padding: 0;
	background-color: #FFF;
	font-family: "Liberation Sans", Helvetica, sans-serif;
}
#videos .iframe {
	float: left;
}
#paging {
	width: 640px;
	float: left;
	font-size: 1em;
}
form {
	display: flex;
}
textarea {
	display: inline-block;
	width: 85%;
	resize: none;
}
#submit {
	display: inline-block;
	width: 10%;
	margin-bottom: -10px;
}
</style>
<script src="resources/js/jquery-3.6.1.min.js"></script>
<script>
$(document).ready(function() {
	$("#addForm").submit(function (event) {
		event.preventDefault();
		var $form = $(this);
		var content = $('#addForm-ta').val();
		content = $.trim(content);
		if (content.length === 0) {
			$('#addForm-ta').val('');
			return false;
		}
		var dataToBeSent = $form.serialize();
		var url = $form.attr("action");
		var posting = $.post(url, dataToBeSent);
		posting.done(function () {
			$('#addForm-ta').val('');
		});
	});    

	var originWidth = $('#videos > iframe').width();
	var originHeight = $('#videos > iframe').height();

	var width = $('#paging').width();
	var height = originHeight * width / originWidth;

	$('#videos > iframe').attr('width', width);
	$('#videos > iframe').attr('height', height);

	$('#videos > iframe').attr('allowFullScreen', '');
});
</script>
</head>
<body>
	<div id="videos">
		<c:forEach var="video" items="${list }" varStatus="status">
		${video.content }
		</c:forEach>
	</div>

	<div id="paging">
		<c:if test="${prevBlock > 0}">
		<a href="?page=1" title="1">[First]</a>
		<a href="?page=${prevBlock }" title="${prevBlock }">[Prev]</a>
		</c:if>

		<c:forEach var="i" begin="${firstPage }" end="${lastPage }" varStatus="status">
		<c:choose>
			<c:when test="${param.page == i}">
			<strong>${i }</strong>
			</c:when>
			<c:otherwise>
			<a href="?page=${i }" title="${i }">${i }</a>
			</c:otherwise>
		</c:choose>
		</c:forEach>

		<c:if test="${nextBlock > 0 }">
		<a href="?page=${nextBlock }" title="${nextBlock }">[Next]</a>
		<a href="?page=${totalPage }" title="${totalPage }">[Last]</a>
		</c:if>
		<form id="addForm" action="./">
			<textarea id="addForm-ta" name="content" cols="30" rows="1"></textarea>
			<input id="submit" type="submit" value="Send" /> 
		</form>
	</div>
</body>
</html>
