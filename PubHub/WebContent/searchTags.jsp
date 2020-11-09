	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<style type="text/css"> 
	
	
</style> 	

	
	<header>
	
	<div class="container">
	  
	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
	
	<h1>PUBHUB <small>Tag Search</small></h1>
	<hr class="book-primary">
	
	<form class="form-inline" action="SearchTag" method="post">
		<div class="form-group">
			<input type="text" class="form-control" name="tag" placeholder="Search for a tag..." required>
		</div>
		<input type="hidden" name="tag" value="${tag }">
		<button type="submit" class="btn btn-info">Search</button>
	</form>
	
	</div>
	
	<div class="container" style="padding-top:5%">
	
	<c:choose>
	<c:when test="${not empty books }">
	
	<table class="table table-striped table-hover table-responsive pubhub-datatable">
	
			<thead>
				<tr>
					<td>ISBN-13:</td>
					<td>Title:</td>
					<td>Author:</td>
					<td>Publish Date:</td>
					<td>Price:</td>
					<td></td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${books}">
					<tr>
						<td><c:out value="${book.isbn13}" /></td>
						
						<td><c:out value="${book.title}" /></td>
						<td><c:out value="${book.author}" /></td>
						<td><c:out value="${book.publishDate}" /></td>
						<td><fmt:formatNumber value="${book.price }" type="CURRENCY"/></td>
						<td><form action="DownloadBook" method="get">
								<input type="hidden" name="isbn13" value="${book.isbn13}">
								<button class="btn btn-success">Download</button>
							</form></td>
						<td><form action="ViewBookDetails?isbn=${book.isbn13}" method="get">
								<input type="hidden" name="isbn13" value="${book.isbn13}">
								<button class="btn btn-primary">Details</button>
							</form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</c:when>
	</c:choose>
	<c:remove var="books" />
	</div>
	</header>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />