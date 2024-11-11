<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="./base.jsp"%>
</head>
<body>
	<div class="container mt-5 bg-light">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<h1 class="text-center mb-3 mt-3 text-warning">Update Student Details</h1>
				<form action="${pageContext.request.contextPath}/handle-student" method="post" >
				
                  <div class="form-group">
                  
                    <input type="hidden" value="${student.id}" name="id"class="form-control" >
                   </div> 
                   
					<div class="form-group">
						<label for="name">Student Name </label> <input type="text"
							class="form-control" id="name" name="name"
							placeholder="Enter Student Name" value="${student.name}">
					</div>
					
					<div class="form-group">
						<label for="branch">Student Branch</label> <input type="text"
							class="form-control" id="branch" name="branch"
							placeholder="Enter Branch" value="${student.branch}">
					</div>
					
					<div class="form-group">
						<label for="collegeName"> Student College Name </label>
						<input type="text" class="form-control" name="collegeName" id="collegeName"
							 placeholder="Enter College Name" value="${student.collegeName}">
					</div>
					
					<div class="form-group">
						<label for="state">State </label> <input type="text"
							class="form-control" id="state" name="state"
							placeholder="Enter state" value="${student.state}">
					</div>
					
					<div class="form-group">
						<label for="mobileno">Student MobileNo </label> <input type="text"
							class="form-control" id="mobileno" name="mobileno"
							placeholder="Enter MobileNo" value="${student.mobileno}">
					</div>
					
					<div class="container text-center mb-4">
						<a href="${pageContext.request.contextPath}/" class="btn btn-outline-danger"> Back </a>
						<button type="submit" class="btn btn-danger">Update Student</button>
					</div>

				</form>
			</div>
		</div>
	</div>

</body>
</html>