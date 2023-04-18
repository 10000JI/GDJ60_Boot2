<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Modern Business - Start Bootstrap Template</title>
        <!-- css favicon  -->
        <c:import url="../temp/style.jsp"></c:import>
        <!-- css favicon -->
    </head>
<body class="d-flex flex-column h-100">
	<main class="flex-shrink-0">
		<!-- Navigation-->
        <c:import url="../temp/header.jsp"></c:import>
    	<!-- Header-->
    	<section class="bg-light py-5">
        	<div class="container px-5 my-5">
            	<div class="text-center mb-5">
              		<h1 class="fw-bolder">${board} List</h1>
           			<p class="lead fw-normal text-muted mb-0">공지사항 게시판 입니다.</p>
     		 	</div>
     		 	
     		 	<div>
     		 		<table class="table table-hover">
     		 			<thead>
     		 				<tr>
     		 					<th>Num</th>
     		 					<th>Title</th>
     		 					<th>Writer</th>
     		 					<th>Date</th>
     		 					<th>Hit</th>
     		 				</tr>
     		 			</thead>
     		 			<tbody>
     		 				<c:forEach items="${list}" var="boardVO">
     		 					<tr>
     		 						<td>${boardVO.num}</td>
     		 						<td>${boardVO.title}</td>
     		 						<td>${boardVO.writer}</td>
     		 						<td>${boardVO.regDate}</td>
     		 						<td>${boardVO.hit}</td>
     		 					</tr>
     		 				</c:forEach>
     		 			</tbody>
     		 		</table>
     		 		<c:forEach begin="${pager.startNum}" end="${pager.lastNum }" var="i">
     		 			<a>${i}</a>
     		 		</c:forEach>
     		 	</div>
     		 </div>

		</section>
	</main>
	<!-- Footer 적용 -->
   	<c:import url="../temp/footer.jsp"></c:import>
   	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
   	<!-- Footer 끝 -->
</body>
</html>