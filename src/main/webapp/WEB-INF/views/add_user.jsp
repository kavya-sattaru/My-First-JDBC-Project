<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				<h1 class="text-center mb-3 mt-3 text-warning">Fill the User Details</h1>
				<form action="handle-user" method="post">

					<div class="form-group">
						<label for="name">User Name </label> <input type="text"
							class="form-control" id="name" name="name"
							placeholder="Enter Your User Name">
					</div>

					<div class="form-group">
						<label for="Mobile">Mobile Number</label>
						 <input type="text"
							class="form-control" name="mobile" id="mobile"
							placeholder="Enter the User Mobile">
					</div>

					<div class="form-group">
						<label for="Email">Email </label> <input type="text"
							class="form-control" id="email" name="email"
							placeholder="Enter Your User Email">
					</div>
					
					<div class="form-group">
						<label for="Address">Address </label> <input type="text"
							class="form-control" id="profile.address" name="profile.address"
							placeholder="Enter Your Address">
					</div>
					
					<div class="form-group">
						<label for="Pincode">Pincode </label> <input type="text"
							class="form-control" id="profile.pincode" name="profile.pincode"
							placeholder="Enter Pincode">
					</div>

					<div class="container text-center mb-4">
						<a href="${pageContext.request.contextPath}/" class="btn btn-outline-danger"> Back </a>
						<button type="submit" class="btn btn-primary">Add User</button>
					</div>

				</form>
			</div>
		</div>
	</div>

</body>
</html>