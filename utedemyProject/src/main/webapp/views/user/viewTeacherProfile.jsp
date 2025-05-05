<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Unica - ${teacher.fullname} Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/Css/viewTeacherProfile.css">
</head>
<body>
    <!-- Profile Header -->
    <div class="header-banner">
        <div class="profile-info">
            <c:choose>
                <c:when test="${not empty teacher.avatarUrl}">
                    <img src="${teacher.avatarUrl}" alt="${teacher.fullname}" class="profile-avatar">
                </c:when>
                <c:otherwise>
                    <img src="${pageContext.request.contextPath}/views/images/default-avatar.jpg" alt="${teacher.fullname}" class="profile-avatar">
                </c:otherwise>
            </c:choose>
            <div class="profile-details">
                <h1 class="profile-name">${teacher.fullname}</h1>
                <p class="profile-title">${teacher.description}</p>
                <div class="action-buttons">
                    <button class="btn-outline">
                        <span class="btn-icon">👨‍👦‍👦</span> Follow mình
                    </button>
                    <button class="btn-outline">
                        <span class="btn-icon">✉️</span> Nhắn cho mình
                    </button>
                </div>
                <div class="contact-info">
                    <p><strong>Email:</strong> ${teacher.email}</p>
                    <p><strong>Số điện thoại:</strong> ${teacher.phoneNumber}</p>
                </div>
            </div>
        </div>

        <div class="stats">
            <div class="stat-item">
                <span class="stat-number">${teacher.course != null ? teacher.course.size() : 0}</span>
                <span class="stat-label">khóa học</span>
            </div>
            <div class="stat-item">
                <span class="stat-number">14725</span> <!-- Giả sử số học viên, cần lấy từ database nếu có -->
                <span class="stat-label">học viên</span>
            </div>
            <div class="stat-item">
                <span class="stat-number">5 <span class="star-icon">★</span></span> <!-- Giả sử đánh giá, cần lấy từ database -->
                <span class="stat-label">đánh giá</span>
            </div>
        </div>
    </div>

    <!-- Main Content -->
    <div class="main-content">
        <div class="left-column">
            <!-- Bio Section -->
            <div class="content-box">
                <h2 class="section-title">Giới thiệu</h2>
                <div class="bio-content">
                    <p class="bio-item">${teacher.description}</p>
                    <c:if test="${not empty teacher.socialUrl}">
                        <p class="bio-item"><strong>Link mạng xã hội:</strong> <a href="${teacher.socialUrl}" target="_blank">${teacher.socialUrl}</a></p>
                    </c:if>
                </div>
            </div>

            <!-- Course Section -->
            <div class="content-box">
                <h2 class="section-title">Khóa học của giảng viên ${teacher.fullname}</h2>
                <c:if test="${not empty teacher.course}">
                    <c:forEach var="course" items="${teacher.course}">
                        <div class="course-card">
                            <c:choose>
                                <c:when test="${not empty course.courseDetail.courseImage}">
                                    <img src="${course.courseDetail.courseImage}" alt="Course" class="course-image">
                                </c:when>
                                <c:otherwise>
                                    <img src="${pageContext.request.contextPath}/views/images/default-course.jpg" alt="Course" class="course-image">
                                </c:otherwise>
                            </c:choose>
                            <div class="course-details">
                                <h3 class="course-title">${course.courseName}</h3>
                                <div class="course-meta">
                                    <span class="meta-item"><span class="meta-icon">📝</span> ${course.sections != null ? course.sections.size() : 0} bài giảng</span>
                                    <span class="meta-item"><span class="meta-icon">⏱️</span> 13 giờ 22 phút</span> <!-- Giả sử thời lượng, cần tính toán -->
                                </div>
                                <p class="course-description">${course.courseDetail.courseIntroduction}</p>
                            </div>
                            <div class="price-box">
                                <div class="current-price"><fmt:formatNumber value="${course.coursePrice}" type="number" groupingUsed="true"/>₫</div>
                                <div class="original-price"><fmt:formatNumber value="${course.coursePrice * 1.5}" type="number" groupingUsed="true"/>₫</div>
                                <div class="discount">OFF 33%</div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${empty teacher.course}">
                    <p>Giảng viên này chưa có khóa học nào.</p>
                </c:if>
            </div>
        </div>

        <div class="right-column">
            <!-- Video Section đã bị xóa -->
        </div>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            console.log('Page loaded successfully');
            const followButton = document.querySelector('.action-buttons .btn-outline:first-child');
            followButton.addEventListener('click', function() {
                alert('Đã theo dõi giảng viên ${teacher.fullname}!');
            });
            
            const messageButton = document.querySelector('.action-buttons .btn-outline:last-child');
            messageButton.addEventListener('click', function() {
                alert('Chức năng nhắn tin đang được phát triển!');
            });
        });
    </script>
</body>
</html>