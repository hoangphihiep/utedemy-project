<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/utedemyProject/views/Css/addCourse.css">
</head>
<body>
	<div class="header">
		<div class="header-left">
			<a href="#" class="back-button">&#9664;</a>
			<div class="header-title">Quay lại danh sách khóa học
				&nbsp;|&nbsp; Thành Thạo Excel Từ Cơ Bản Đến Nâng Cao</div>
		</div>
		<div class="header-status">
			<div class="status-tag new-tag">Mới</div>
			<div class="status-tag updating-tag">Đang cập nhật</div>
			<button class="save-button">Lưu</button>
		</div>
	</div>
	<div class="container">
		<div class="sidebar">
			<div class="sidebar-menu">
				<a href="/utedemyProject/teacher/addTarget" class="sidebar-item"
					style="display: inline-block; text-decoration: none;"> <span>Mục
						tiêu khóa học</span>
				</a> <a href="/utedemyProject/teacher/adBasicInformation"
					class="sidebar-item"
					style="display: inline-block; text-decoration: none;"> <span>Thông
						tin cơ bản</span>
				</a> <a href="/utedemyProject/teacher/add" class="sidebar-item"
					style="display: inline-block; text-decoration: none;"> <span>Danh
						sách bài học</span>
				</a>
			</div>
			<button class="submit-button">Gửi duyệt</button>
		</div>
		<div class="main-content">
			<sitemesh:write property="body" />
		</div>
	</div>
	<script type="text/javascript">
		// Thêm event listener cho nút lưu
		document.querySelector('.save-button').addEventListener('click', function() {
		    saveTargetData();
		});
	
		// Hàm thu thập và gửi dữ liệu
		function saveTargetData() {
		    // Kiểm tra URL hiện tại để biết đang ở trang nào
		    const currentPath = window.location.pathname;
		    
		    // Nếu đang ở trang mục tiêu khóa học
		    if (currentPath.includes('/teacher/addTarget')) {
		        // Thu thập dữ liệu từ các mục tiêu
		        const objectives = [];
		        document.querySelectorAll('.objective-item').forEach(item => {
		            const objectiveText = item.querySelector('.objective-text').textContent;
		            objectives.push(objectiveText);
		        });
		        
		     // Thu thập dữ liệu từ phần đối tượng khóa học và chào mừng
	            // Tìm các editor-content theo thứ tự xuất hiện
	            const editorContents = document.querySelectorAll('.editor-content');
	            
	            let targetAudience = "";
	            let welcomeMessage = "";
	            
	            // Kiểm tra và lấy nội dung từ các editor-content
	            if (editorContents.length >= 1) {
	                targetAudience = editorContents[0].textContent || "";
	            }
	            
	            if (editorContents.length >= 2) {
	                welcomeMessage = editorContents[1].textContent || "";
	            }
	            
	            // Kiểm tra và ghi log để debug
	            console.log("Objectives:", objectives);
	            console.log("Target Audience:", targetAudience);
	            console.log("Welcome Message:", welcomeMessage);
		        
		        // Tạo object chứa dữ liệu
		        const targetData = {
		            objectives: objectives,
		            targetAudience: targetAudience,
		            welcomeMessage: welcomeMessage
		        };
		        
		        // Gửi dữ liệu đến server qua AJAX
		        sendDataToServer('/utedemyProject/teacher/addTarget', targetData);
		    }
		    // Có thể thêm các điều kiện cho các trang khác ở đây
		}
	
		// Hàm gửi dữ liệu đến server
		function sendDataToServer(url, data) {
		    // Tạo request
		    const xhr = new XMLHttpRequest();
		    xhr.open('POST', url, true);
		    xhr.setRequestHeader('Content-Type', 'application/json');
		    
		    // Xử lý phản hồi
		    xhr.onload = function() {
		        if (xhr.status >= 200 && xhr.status < 300) {
		            // Thành công
		            alert('Đã lưu thành công!');
		        } else {
		            // Lỗi
		            alert('Lỗi khi lưu: ' + xhr.statusText);
		        }
		    };
		    
		    // Xử lý lỗi mạng
		    xhr.onerror = function() {
		        alert('Lỗi kết nối đến máy chủ!');
		    };
		    
		    // Gửi dữ liệu
		    xhr.send(JSON.stringify(data));
		}
	</script>
</body>
</html>