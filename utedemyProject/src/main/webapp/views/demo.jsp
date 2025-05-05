<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
body {
	font-family: Arial, sans-serif;
	background: #f5f5f5;
	margin: 0;
	padding: 0;
}
.header {
	background: #fff;
	padding: 20px;
	display: flex;
	justify-content: center;
	align-items: center;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
.search-box {
	display: flex;
	align-items: center;
	gap: 10px;
}
.search-box input[type="text"] {
	padding: 10px;
	width: 300px;
	border: 1px solid #ccc;
	border-radius: 4px;
}
.search-box button {
	padding: 10px 20px;
	background: #4CAF50;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}
.filter-bar {
	display: flex;
	justify-content: center;
	gap: 10px;
	margin: 20px 0;
}
.filter-bar button {
	padding: 8px 16px;
	border: 1px solid #ccc;
	border-radius: 20px;
	background: white;
	cursor: pointer;
}
.course-grid {
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
	gap: 20px;
	padding: 20px;
	max-width: 1200px;
	margin: 0 auto;
}
.course-card {
	background: white;
	border-radius: 8px;
	overflow: hidden;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
	display: flex;
	flex-direction: column;
}
.course-card img {
	width: 100%;
	height: 150px;
	background: #ddd;
}
.course-card .content {
	padding: 15px;
	flex: 1;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
}
.course-card h3 {
	margin: 0 0 10px 0;
	font-size: 18px;
}
.course-card p {
	margin: 0 0 10px 0;
	color: #555;
	font-size: 14px;
}
.course-card .footer {
	display: flex;
	justify-content: space-between;
	align-items: center;
}
.course-card .footer button {
	padding: 6px 12px;
	background: #4CAF50;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}
.chatbox {
	position: fixed;
	bottom: 20px;
	right: 20px;
	background: #fff;
	border: 1px solid #ccc;
	border-radius: 50px;
	padding: 10px 20px;
	display: flex;
	align-items: center;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
}
.progress-circle {
	width: 40px;
	height: 40px;
	border: 2px solid #ccc;
	border-radius: 50%;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 14px;
	font-weight: bold;
	color: #333;
}
</style>
</head>
<body>
	<div class="header">
		<div class="search-box">
			<input type="text" placeholder="Tìm kiếm khóa học...">
			<button>Tìm kiếm</button>
		</div>
	</div>

	<div class="filter-bar">
		<button>Tất cả</button>
		<button>Excel</button>
		<button>Bán hàng</button>
		<button>Thuyết trình</button>
		<button>+ Danh mục</button>
	</div>

	<div class="course-grid">
		<div class="course-card">
			<img alt="">
			<div class="content">
				<h3>Tên khóa học</h3>
				<p>Giảng viên: Tên giảng viên</p>
				<div class="footer">
					<button>Vào học ngay</button>
					<div class="footer">
						<div class="progress-circle">0%</div>
					</div>
				</div>
			</div>
		</div>

		<div class="course-card">
			<img alt="">
			<div class="content">
				<h3>Tên khóa học</h3>
				<p>Giảng viên: Tên giảng viên</p>
				<div class="footer">
					<button>Vào học ngay</button>
					<div class="progress-circle">0%</div>
				</div>
			</div>
		</div>

		<!-- Thêm nhiều thẻ tương tự nếu cần -->
	</div>

	<div class="chatbox">Em có thể giúp gì cho anh chị ạ?</div>
</body>
</html>