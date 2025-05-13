<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cập nhật khóa học</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f7f7f7;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: flex-start;
	min-height: 100vh;
}

.container {
	margin-top: 60px;
	text-align: center;
}

h3 {
	color: #2c3e50;
	margin-bottom: 30px;
}

.form-box {
	background-color: #fff;
	padding: 20px 30px;
	border-radius: 12px;
	width: 400px;
	margin-bottom: 20px;
	box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
	text-align: left;
}

label {
	display: block;
	margin-bottom: 8px;
	font-weight: bold;
}

input[type="text"], input[type="number"], select {
	width: 100%;
	padding: 10px;
	margin-bottom: 16px;
	border: 1px solid #ccc;
	border-radius: 6px;
}

button {
	padding: 10px 20px;
	background-color: #3498db;
	color: white;
	border: none;
	border-radius: 6px;
	cursor: pointer;
	font-size: 14px;
}

button:hover {
	background-color: #2980b9;
}
</style>
</head>
<body>
	<div class="container">
		<h3>Chỉnh sửa khóa học</h3>

		<!-- Form chỉnh sửa khóa học -->
		<form action="${pageContext.request.contextPath}/admin/edit2"
			method="post" class="form-box">
			<!-- Giữ id của loại khóa học không thay đổi -->
			<input type="hidden" name="id" value="${courseType.id}" />

			<div>
				<label for="courseTypeName">Tên loại khóa học:</label>
				<!-- Hiển thị tên loại khóa học cũ, người dùng có thể chỉnh sửa -->
				<input type="text" id="courseTypeName" name="courseTypeName"
					placeholder="Nhập tên loại khóa học"
					value="${courseType.courseTypeName}" required />
			</div>
			<button type="submit">Cập nhật khóa học</button>
		</form>

		<!-- Hiển thị thông báo lỗi hoặc thành công -->
		<c:if test="${not empty message}">
			<p style="color: red; font-weight: bold;">${message}</p>
		</c:if>

		<!-- Form quay lại trang danh sách -->
		<form action="${pageContext.request.contextPath}/admin/category2"
			method="post" class="form-box">
			<button type="submit">Quay về danh sách</button>
		</form>
	</div>
</body>

</html>