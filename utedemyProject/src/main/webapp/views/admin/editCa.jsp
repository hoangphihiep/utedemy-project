<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

input[type="text"] {
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
		<h3>Cập nhật khóa học</h3>

		<form action="${pageContext.request.contextPath}/admin/edit"
			method="post" class="form-box">
			<input type="hidden" name="id" value="${course.id}" />
			<div>
				<label>Tên khóa học:</label> <input type="text" name="courseName"
					value="${course.courseName}" required />
			</div>
			<button type="submit">Cập nhật</button>
		</form>

		<form action="${pageContext.request.contextPath}/admin/category"
			method="post" class="form-box">
			<button type="submit">Quay về trang hiển thị danh sách</button>
		</form>
	</div>

</body>
</html>
