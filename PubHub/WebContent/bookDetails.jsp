	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
<style type="text/css"> 
	table tr {
		padding: 10%;
	}
	
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
	
		<h1>PUBHUB <small>Book Details - ${book.isbn13 }</small></h1>
		
		<hr class="book-primary">
		
		<form action="UpdateBook" method="post" class="form-horizontal">
		  
		  <input type="hidden" class="form-control" id="isbn13" name="isbn13" required="required" value="${book.isbn13 }" />
		  
		  <div class="form-group">
		    <label for="title" class="col-sm-4 control-label">Title</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="title" name="title" placeholder="Title" required="required" value="${book.title }" />
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="author" class="col-sm-4 control-label">Author</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="author" name="author" placeholder="Author" required="required" value="${book.author }" />
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="price" class="col-sm-4 control-label">Price</label>
		    <div class="col-sm-5">
		      <input type="number" step="0.01" class="form-control" id="price" name="price" placeholder="Price" required="required" value="${book.price }" />
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button type="submit" class="btn btn-info">Update</button>
		    </div>
		  </div>
		</form>
		<br/>
		<!-- BEGIN NEW ROW. Add Bootstrap List group -->
		<h2><small>Tags</small></h2>
		<div class="form-group" style="margin:auto">
		<label for="tag" class="col-sm-4 control-label"></label>
		    <div class="col-sm-5">
		    	<table class="table">
		    	<tbody>
		    		<c:forEach var="tag" items="${tags }">
		    		<tr>
		    			<td><ul class="list-group">
		    				<li class="list-group-item" ><c:out value = "${tag }" /></li></ul>
		    			</td>
		    			<td>
		    				<form action="DeleteTag" method="get">
		    				<input type="hidden" name="tag" value="${tag }" />
		    				<input type="hidden" name="isbn13" value="${book.isbn13 }" />
		    				<button type="submit" class="btn btn-secondary">x</button>
		    				</form>
		    			</td>
		    		</tr>
		    		</c:forEach>
		    		
		    		<form action="AddTag" method="get">
		    		<tr>
		    		<td>
		    		<ul class="list-group">
		    			<li class="list-group-item" ><input type="text" class="form-control"
		    				placeholder="Add new tag" name="newTag"></li></ul>
		    		</td>
		    		<td>
		    			<input type="hidden" name="newTag" value="${newTag }" />
		    			<input type="hidden" name="isbn13" value="${book.isbn13 }" />
		    			<button type="submit" class="btn btn-secondary">
		    			<svg width="1em" height="2.7em" viewBox="0 0 16 16" class="bi bi-check" fill="currentColor" 
		    			xmlns="http://www.w3.org/2000/svg">
  						<path fill-rule="evenodd" d="M10.97 4.97a.75.75 0 0 1 1.071 
  						1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 
  						1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z"/>
						</svg></button>
		    		</td>
		    		</tr>
		    	</form>
		    	</tbody>
		    	</table>
			</div>
		  </div>
		  <!-- END NEW ROW -->

	  </div>
	</header>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
