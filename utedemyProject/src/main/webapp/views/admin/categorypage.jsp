<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard - Tài khoản</title>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto&display=swap"
	rel="stylesheet">
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f2f5;
	margin: 0;
	padding: 0;
}

.sidebar {
	width: 220px;
	height: 100vh;
	background-color: #2c3e50;
	color: white;
	position: fixed;
	top: 0;
	left: 0;
	padding: 20px 0;
}

.sidebar h2 {
	text-align: center;
	margin-bottom: 30px;
}

.sidebar a {
	display: block;
	color: white;
	padding: 10px 20px;
	text-decoration: none;
	margin-left: 50px;
}

.sidebar a:hover {
	background-color: #34495e;
}

.main {
	margin-left: 240px;
	padding: 20px;
}

.table-container {
	background-color: white;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

table {
	width: 100%;
	border-collapse: collapse;
}

table th, table td {
	padding: 12px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

.status-active {
	color: green;
	font-weight: bold;
}

.status-locked {
	color: red;
	font-weight: bold;
}

.role {
	display: inline-block;
	padding: 5px 10px;
	background-color: #3498db;
	color: white;
	border-radius: 4px;
	font-size: 12px;
}

.btn-lock, .btn-unlock {
	padding: 5px 10px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	color: white;
}

.btn-lock {
	background-color: #e74c3c;
}

.btn-unlock {
	background-color: #3498db;
}

.pagination {
	margin-top: 15px;
	text-align: center;
}

.pagination button {
	padding: 5px 10px;
	margin: 0 2px;
	border: 1px solid #ccc;
	background-color: white;
	cursor: pointer;
}

.pagination button:hover {
	background-color: #eee;
}

/* Styling for Add Course button */
.add-course-button {
	display: block;
	width: 200px;
	margin: 20px auto;
	padding: 12px 20px;
	text-align: center;
	background-color: #3498db;
	color: white;
	border: none;
	border-radius: 6px;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.add-course-button:hover {
	background-color: #2980b9;
}
</style>
</head>
<body>
	<div class="sidebar">
		<h2>Admin</h2>
		<a href="#">Trang chủ</a> 
		<a href="#">Tài khoản</a> 
		<a href="#">Khóa học</a>
	</div>

	<div class="main">
		<h2>Danh mục</h2>
		<div class="table-container">
			<table>
				<thead>
					<tr>
						<th>STT</th>
						<th>Tên Khóa Học</th>
						<th>Hành động</th>
					</tr>
				</thead>
				<c:forEach items="${List}" var="c" varStatus="status">
					<tbody>
						<tr>
							<td>${status.index + 1}</td>
							<td>${c.courseName}</td>
							<td><a href="edit?id=${c.id}">Chỉnh sửa</a></td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
		</div>

		<!-- Styled button to add course -->
		<form action="${pageContext.request.contextPath}/admin/add"
			method="get">
			<button type="submit" class="add-course-button">Thêm khóa
				học</button>
		</form>
	</div>
</body>
</html>
