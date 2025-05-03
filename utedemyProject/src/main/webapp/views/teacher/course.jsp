<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giảng viên Dashboard</title>
    
</head>
<body>
    <main class="content">
        <div class="content-header">
            <h1 class="content-title">Bài giảng của tôi</h1>
        </div>
        
        <!-- Stats cards -->
        <div class="course-stats">
            <div class="stat-card">
                <span class="stat-title">Tổng số khóa học</span>
                <span class="stat-value">${listCourse.size()}</span>
                <span class="stat-change positive">
                    <i class="fas fa-arrow-up"></i> 10% so với tháng trước
                </span>
            </div>
            <div class="stat-card">
                <span class="stat-title">Khóa học đang hoạt động</span>
                <span class="stat-value">${activeCoursesCount}</span>
                <span class="stat-change positive">
                    <i class="fas fa-arrow-up"></i> 5% so với tháng trước
                </span>
            </div>
            <div class="stat-card">
                <span class="stat-title">Tổng học viên</span>
                <span class="stat-value">${totalStudents}</span>
                <span class="stat-change positive">
                    <i class="fas fa-arrow-up"></i> 15% so với tháng trước
                </span>
            </div>
            <div class="stat-card">
                <span class="stat-title">Đánh giá trung bình</span>
                <span class="stat-value">4.8 <small style="font-size: 16px; color: #6b7280;">/5</small></span>
                <span class="stat-change positive">
                    <i class="fas fa-arrow-up"></i> 0.2 so với tháng trước
                </span>
            </div>
        </div>

        <div class="search-filter-container">
            <div class="search-bar">
                <input type="text" class="search-input" placeholder="Nhập tên khóa học">
                <button class="search-button">
                    <i class="fas fa-search"></i> Tìm kiếm
                </button>
            </div>
            
            <div class="filter-container">
                <select class="filter-select">
                    <option value="">Tất cả trạng thái</option>
                    <option value="1">Đang hoạt động</option>
                    <option value="0">Tạm ngưng</option>
                    <option value="2">Đang kiểm duyệt</option>
                    <option value="3">Đang tạo</option>
                </select>
                
                <select class="filter-select">
                    <option value="">Sắp xếp theo</option>
                    <option value="newest">Mới nhất</option>
                    <option value="oldest">Cũ nhất</option>
                    <option value="name_asc">Tên A-Z</option>
                    <option value="name_desc">Tên Z-A</option>
                    <option value="students">Số học viên</option>
                    <option value="rating">Đánh giá</option>
                </select>
            </div>
            
            <div class="action-buttons">
                <a href="/utedemyProject/teacher/addCourse" class="btn btn-primary">
                    <i class="fas fa-plus"></i> Tạo khóa học
                </a>
            </div>
        </div>

        <div class="course-list">
            <c:choose>
                <c:when test="${not empty listCourse}">
                    <c:forEach var="course" items="${listCourse}">
                        <div class="course-card">
                            <div class="course-image">
                                <c:if test="${course.courseDetail.courseImage.substring(0,5) != 'https' }">
                                    <c:url value="/image?fname=${course.courseDetail.courseImage}" var="imgUrl"></c:url>
                                </c:if>
                                <c:if test="${course.courseDetail.courseImage.substring(0,5) == 'https' }">
                                    <c:url value="${course.courseDetail.courseImage}" var="imgUrl"></c:url>
                                </c:if>
                                <img id="courseImage${course.id}" src="${imgUrl}" alt="${course.courseName}" />
                            </div>
                            <div class="course-info">
                                <div class="course-options">
                                    <button class="options-btn" onclick="toggleOptionsMenu(${course.id})">
                                        <i class="fas fa-ellipsis-vertical"></i>
                                    </button>
                                    <div id="optionsMenu${course.id}" class="options-menu">
                                        <a href="<c:url value='/teacher/editBasicInformation?id=${course.id}'/>" class="options-item">
                                            <i class="fas fa-edit"></i> Chỉnh sửa
                                        </a>
                                        <a href="<c:url value='/teacher/manageLessons?id=${course.id}'/>" class="options-item">
                                            <i class="fas fa-list-check"></i> Quản lý bài học
                                        </a>
                                        <a href="<c:url value='/teacher/viewStudents?id=${course.id}'/>" class="options-item">
                                            <i class="fas fa-users"></i> Học viên đăng ký
                                        </a>
                                        <a href="<c:url value='/teacher/viewAnalytics?id=${course.id}'/>" class="options-item">
                                            <i class="fas fa-chart-line"></i> Thống kê
                                        </a>
                                        <c:if test="${course.status == 1}">
                                            <a href="<c:url value='/teacher/pauseCourse?id=${course.id}'/>" class="options-item">
                                                <i class="fas fa-pause"></i> Tạm ngưng
                                            </a>
                                        </c:if>
                                        <c:if test="${course.status == 0}">
                                            <a href="<c:url value='/teacher/activateCourse?id=${course.id}'/>" class="options-item">
                                                <i class="fas fa-play"></i> Kích hoạt
                                            </a>
                                        </c:if>
                                        <a href="#" onclick="confirmDelete(${course.id})" class="options-item danger">
                                            <i class="fas fa-trash"></i> Xóa
                                        </a>
                                    </div>
                                </div>
                                
                                <div>
                                    <h3 class="course-title">${course.courseName}</h3>
                                    
                                    <c:if test="${course.status == 3}">
                                        <span class="course-tag">Khóa học mới</span>
                                    </c:if>
                                    
                                    <div class="course-status">
                                        <c:choose>
                                            <c:when test="${course.status == 0}">
                                                <span class="status-dot dot-stopped"></span>
                                                <span class="status-stopped">Trạng thái: Tạm ngưng</span>
                                            </c:when>
                                            <c:when test="${course.status == 1}">
                                                <span class="status-dot dot-active"></span>
                                                <span class="status-active">Trạng thái: Hoạt động</span>
                                            </c:when>
                                            <c:when test="${course.status == 2}">
                                                <span class="status-dot dot-pending"></span>
                                                <span class="status-pending">Trạng thái: Đang kiểm duyệt</span>
                                            </c:when>
                                            <c:when test="${course.status == 3}">
                                                <span class="status-dot dot-draft"></span>
                                                <span class="status-draft">Trạng thái: Đang tạo</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="status-dot"></span>
                                                <span>Trạng thái: Không xác định</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    
                                   
                                </div>
                                
                                <div class="course-bottom">
                                    
                                    <div class="course-actions">
                                        <a href="<c:url value='/teacher/editBasicInformation?id=${course.id}'/>" class="action-btn btn-edit">
                                            <i class="fas fa-edit"></i> Sửa
                                        </a>
                                        <a href="<c:url value='/teacher/previewCourse?id=${course.id}'/>" class="action-btn btn-view">
                                            <i class="fas fa-eye"></i> Xem
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="no-courses">
                        <div class="no-courses-icon">
                            <i class="fas fa-book"></i>
                        </div>
                        <h3 class="no-courses-text">Bạn chưa có khóa học nào</h3>
                        <a href="/utedemyProject/teacher/addCourse" class="btn btn-primary">
                            <i class="fas fa-plus"></i> Tạo khóa học đầu tiên
                        </a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        
        <!-- Phân trang -->
        <div class="pagination">
            <a class="page-item disabled">
                <i class="fas fa-chevron-left"></i>
            </a>
            <a class="page-item active">1</a>
            <a class="page-item">2</a>
            <a class="page-item">3</a>
            <a class="page-item">
                <i class="fas fa-chevron-right"></i>
            </a>
        </div>
    </main>
    
    <!-- Toast Notification -->
    <div id="toast" class="toast">
        <div class="toast-icon">
            <i class="fas fa-check-circle"></i>
        </div>
        <div class="toast-content">
            <div class="toast-title">Thành công!</div>
            <div class="toast-message">Thao tác đã được thực hiện thành công.</div>
        </div>
        <button class="toast-close" onclick="closeToast()">
            <i class="fas fa-times"></i>
        </button>
    </div>
        <script>
	     
		     // Preview hình ảnh khi upload
		        function previewFile(courseId) {
		            const preview = document.getElementById(`courseImage${courseId}`);
		            const file = document.getElementById(`courseImage${courseId}Upload`).files[0];
		            const reader = new FileReader();
		            
		            reader.onloadend = function() {
		                preview.src = reader.result;
		            }
		            
		            if (file) {
		                reader.readAsDataURL(file);
		            }
		        }
		 
  		</script>
</body>
</html>