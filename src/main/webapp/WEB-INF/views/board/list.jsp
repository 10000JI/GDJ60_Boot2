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
					<h1 class="fw-bolder">${board}List</h1>
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
									<td><a href="./detail?num=${boardVO.num}">${boardVO.title}</a></td>
									<td>${boardVO.writer}</td>
									<td>${boardVO.regDate}</td>
									<td>${boardVO.hit}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
	<%-- 				<c:if test="${pager.pre}">
						<a href="./list?page=${pager.startNum-1}">이전</a>
					</c:if>
					
					<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
     		 			<a href="./list?page=${i}">${i}</a>
     		 		</c:forEach>
     		 		
     		 		<c:if test="${pager.next}">
						<a href="./list?page=${pager.lastNum+1}">다음</a>
					</c:if> --%>

					<div class="row">
						<nav aria-label="Page navigation example">
							<ul class="pagination ">
								<li class="page-item "><a class="page-link"
									href="./list?page=1" aria-label="Previous"
									data-board-page="1"> <span aria-hidden="true">&laquo;</span>
								</a></li>
								<li class="page-item ${pager.pre?'disabled':''}"><a
									class="page-link" href="./list?page=${pager.startNum-1}&kind=${pager.kind}&search=${pager.search}" 
									aria-label="Previous" data-board-page="${pager.startNum-1}">
										<span aria-hidden="true">&lsaquo;</span>
								</a></li>

								<c:forEach begin="${pager.startNum}" end="${pager.lastNum}"
									var="i">
									<li class="page-item"><a class="page-link"
										href="./list?page=${i}&kind=${pager.kind}&search=${pager.search}" data-board-page="${i}">${i}</a></li>
								</c:forEach>

								<li class="page-item ${pager.next eq false ? 'disabled' : ''}">
									<%--${pager.after eq false ? 'disabled' : ''} --%> <a
									class="page-link" href="./list?page=${pager.lastNum+1}&kind=${pager.kind}&search=${pager.search}" aria-label="Next"
									data-board-page="${pager.lastNum+1}"> <span
										aria-hidden="true">&rsaquo;</span>
								</a>
								</li>
								<li class="page-item ">
									<%--${pager.after eq false ? 'disabled' : ''} --%> <a
									class="page-link" href="./list?page=${pager.totalPage}&kind=${pager.kind}&search=${pager.search}" aria-label="Next"
									data-board-page="${pager.totalPage}"> <span
										aria-hidden="true">&raquo;</span>
								</a>
								</li>
							</ul>
						</nav>
					</div>

					<!-- <form action="./list">
						<select name="kind">
							<option value="title">Title</option>
							<option value="contents">Contents</option>
							<option value="writer">Writer</option>
						</select>
						
						<input type="text" name="search">
						<button type="submit">Search</button>
					</form> -->
					<div class="row">
						<form class="row g-3" action="./list" method="get" id="searchForm">
							<input type="hidden" name="page" value="1" id="page">
							<div class="col-auto">
								<label for="kind" class="visually-hidden">Kind</label> <select
									class="form-select" name="kind" id="kind"
									aria-label="Default select example">
									<option value="title" ${pager.kind eq 'title'?'selected':''}>Title</option>
									<option value="contents"
										${pager.kind eq 'contents'?'selected':''}>Contents</option>
									<option value="writer" ${pager.kind eq 'writer'?'selected':''}>Writer</option>
								</select>
							</div>
							<div class="col-auto">
								<label for="search" class="visually-hidden">Search</label> <input
									type="text" class="form-control" value="${pager.search}"
									name="search" id="search" placeholder="검색어를 입력하세요.">
							</div>
							<div class="col-auto">
								<button type="submit" class="btn btn-primary mb-3">검색</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</section>
		<a href="./add">WRITER</a>
	</main>
	<!-- Footer 적용 -->
   	<c:import url="../temp/footer.jsp"></c:import>
   	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
   	<!-- Footer 끝 -->
   	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
   	<script>
   	setData('${pager.search}')
   	const pl = document.getElementsByClassName('page-link');
   	const searchForm = document.getElementById('searchForm');
   	const page = document.getElementById('page');

   	//for
   	//for of --for(꺼낸타입명 변수명: Collection)

   	for(let p of pl){
   	    p.addEventListener('click',function(e){
   	        let v = p.getAttribute('data-board-page');
   	        page.value=v;
   	        searchForm.submit();
   	    })
   	}

   	let data='';
   	function setData(d){
   	    data=d;
   	}
   	</script>
</body>
</html>