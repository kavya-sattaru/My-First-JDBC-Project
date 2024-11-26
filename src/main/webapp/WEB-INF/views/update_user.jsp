<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
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
				<h1 class="text-center mb-3 mt-3 text-warning">Update User
					Details</h1>
				<form action="${pageContext.request.contextPath}/handle-user"
					method="post">
					<div class="form-group">
						<input type="hidden" value="${user.id}" name="id" class="form-control">
					</div>
					<div class="form-group">
						<label for="name">Name </label> <input type="text"
							class="form-control" id="name" name="name"
							placeholder="Enter Your Name" value="${user.name}">
					</div>

					<div class="form-group">
						<label for="Mobile">Mobile Number</label> <input type="text"
							class="form-control" name="mobile" id="mobile"
							placeholder="Enter the Mobile" value="${user.mobile}">
					</div>
					
					<div class="form-group">
						<label for="email">Email </label> <input type="text"
							class="form-control" id="email" name="email"
							placeholder="Enter Your Email" value="${user.email}">
					</div>

					<div class="form-group">
						<label for="email">Address </label> <input type="text"
							class="form-control" id="profile.address" name="profile.address"
							placeholder="Enter Your Address" value="${user.profile.address}">
					</div>
					
					<div class="form-group">
						<label for="pincode">Pincode</label> <input type="text"
							class="form-control" id="profile.pincode" name="profile.pincode"
							placeholder="Enter Your Pincode" value="${user.profile.pincode}">
					</div>
					
					
					<div class="container text-center mb-4">
						<a href="${pageContext.request.contextPath}/"
							class="btn btn-outline-danger"> Back </a>
						<button type="submit" class="btn btn-danger">Update
							User</button>
					</div>

				</form>
			</div>
		</div>
	</div>

</body>
</html>