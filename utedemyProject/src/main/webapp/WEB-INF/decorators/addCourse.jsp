<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/utedemyProject/views/Css/addCourse.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
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
				<a href="/utedemyProject/teacher/adBasicInformation"
					class="sidebar-item"
					style="display: inline-block; text-decoration: none;"> <span>Thông
						tin cơ bản</span>
				</a>
				<a href="/utedemyProject/teacher/addTarget" class="sidebar-item"
					style="display: inline-block; text-decoration: none;"> <span>Mục
						tiêu khóa học</span>
				</a>  <a href="/utedemyProject/teacher/add" class="sidebar-item"
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
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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
	            
	            // Kiểm tra và lấy nội dung từ các editor-content
	            if (editorContents.length >= 1) {
	                targetAudience = editorContents[0].textContent || "";
	            }

	            // Kiểm tra và ghi log để debug
	            console.log("Objectives:", objectives);
	            console.log("Target Audience:", targetAudience);
		        
		        // Tạo object chứa dữ liệu
		        const targetData = {
		            objectives: objectives,
		            targetAudience: targetAudience,
		        };
		        
		        // Gửi dữ liệu đến server qua AJAX
		        sendDataToServer('/utedemyProject/teacher/addTarget', targetData);
		    }
		 	// Nếu đang ở trang thông tin cơ bản khóa học
			if (currentPath.includes('/teacher/adBasicInformation')) {
				// Create FormData object to handle file uploads and form data together
				  const formData = new FormData();
				  
				  // Add text fields to FormData
				  formData.append("courseTitle", document.querySelector('input[name="courseTitle"]').value);
				  formData.append("shortDescription", document.querySelector('textarea.form-input').value);
				  formData.append("courseTypeId", document.querySelector('select[name="courseTypeId"]').value);
				  formData.append("coursePrice", document.querySelector('input[name="courseName"]').value);
				  formData.append("courseIntroduction", CKEDITOR.instances.courseIntroduction.getData());
				  formData.append("videoLink", document.querySelector('.video-upload-container .form-input').value || "");
				  
				  // Get the file input and append file if one was selected
				  const fileInput = document.getElementById('imageUpload');
				  formData.append("courseImage", fileInput.files[0]);
				  
				  // Log data for debugging (note: can't easily log FormData contents)
				  console.log("Form data created with all fields including possible file upload");
				  
				  // Send FormData to server (need to modify the sendDataToServer function)
				  sendDataToServer('/utedemyProject/teacher/adBasicInformation', formData);
			}
		}
		
	
		function sendDataToServer(url, data) {
			  // Tạo request
			  const xhr = new XMLHttpRequest();
			  xhr.open('POST', url, true);
			  
			  // Không đặt header nếu data là FormData
			  if (!(data instanceof FormData)) {
			    xhr.setRequestHeader('Content-Type', 'application/json');
			  }
			  
			  // Xử lý phản hồi
			  xhr.onload = function() {
				    if (xhr.status >= 200 && xhr.status < 300) {
				        Swal.fire({
				            title: 'Đã lưu thành công!',
				            text: 'Bạn có muốn tiếp tục chỉnh sửa hay quay lại trang chính?',
				            icon: 'success',
				            showCancelButton: true,
				            confirmButtonText: 'Tiếp tục',
				            cancelButtonText: 'Về trang chủ'
				        }).then((result) => {
				            if (result.isConfirmed) {
				                // Ở lại trang
				            } else {
				                // Chuyển về trang chủ
				                window.location.href = "/utedemyProject/teacher/homePage";
				            }
				        });
				    } else {
				        Swal.fire({
				            title: 'Lỗi khi lưu!',
				            text: xhr.statusText,
				            icon: 'error'
				        });
				    }
				};
			  
			  // Xử lý lỗi mạng
			  xhr.onerror = function() {
				  Swal.fire({
				    title: 'Lỗi kết nối!',
				    text: 'Không thể kết nối tới máy chủ. Vui lòng kiểm tra mạng hoặc thử lại sau.',
				    icon: 'error'
				  });
				};
			  
			  // Gửi dữ liệu (nếu là FormData thì gửi trực tiếp, nếu không thì chuyển sang JSON)
			  if (data instanceof FormData) {
				Swal.showLoading();
			    xhr.send(data);
			  } else {
			    xhr.send(JSON.stringify(data));
			  }
			}
	</script>
</body>
</html>