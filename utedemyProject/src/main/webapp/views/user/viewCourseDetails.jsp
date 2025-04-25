<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${courseName} - Unica</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/Css/viewCourseDetails.css">
    <script src="${pageContext.request.contextPath}/views/Script/viewCourseDetails.js"></script>
</head>
<body>

    <c:if test="${not empty error}">
        <div class="error-message" style="color: red; text-align: center; margin: 10px 0;">
            ${error}
        </div>
    </c:if>
    <c:if test="${not empty warning}">
        <div class="warning-message" style="color: orange; text-align: center; margin: 10px 0;">
            ${warning}
        </div>
    </c:if>
    
    <header class="header">
        <div class="logo">
            <img src="/api/placeholder/120/30" alt="Unica Logo">
            <div class="category-menu">
                <span class="icon">≡</span>
                <span>DANH MỤC</span>
            </div>
        </div>
        
        <div class="search-bar">
            <input type="text" placeholder="Tìm khóa học, giảng viên">
            <span class="search-icon">🔍</span>
        </div>
        
        <div class="user-actions">
            <div class="user-menu-item">Doanh nghiệp</div>
            <div class="user-menu-item">Hội viên</div>
            <div class="login-button">
                <span>Vào học</span>
            </div>
            <div class="user-menu-item">❤️</div>
            <div class="user-menu-item">🛒</div>
            <div class="user-menu-item">👤</div>
        </div>
    </header>
    
    <div class="breadcrumb">
        <a href="#">🏠 Unica</a>
        <span class="separator">></span>
        <a href="#">${courseTypeName}</a>
        <span class="separator">></span>
        <a href="#">Kế Toán</a>
    </div>
    
    <section class="course-banner">
        <div class="course-info">
            <h1>${courseName}</h1>
            <p class="course-description">${courseIntroduction}</p>
            
            <div class="course-meta">
                <div class="rating">
                    <div class="number">4.9</div>
                    <div class="stars">★★★★★</div>
                    <div class="reviews">(258 đánh giá)</div>
                </div>
                <div class="students">${courseLearner}</div>
            </div>
            
            <div class="instructor">
                Giảng viên:1
                <a href="#">${teacherName}</a>
            </div>
        </div>
        
        	<div class="video-preview">
		        <c:choose>
		            <c:when test="${not empty courseVideo}">
		                <!-- Chuyển đổi URL YouTube sang định dạng nhúng -->
		                <c:set var="embedUrl" value="${fn:replace(courseVideo, 'watch?v=', 'embed/')}" />
		                <iframe width="100%" height="200" 
		                        src="${embedUrl}" 
		                        title="YouTube video player" 
		                        frameborder="0" 
		                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
		                        allowfullscreen>
		                </iframe>
		            </c:when>
		            <c:otherwise>
		                <!-- Hiển thị hình ảnh mặc định nếu không có video -->
		                <img src="${courseImage}" alt="Video Preview">
		                <div class="play-button"></div>
		            </c:otherwise>
		        </c:choose>
		    </div>
    </section>
    
    <!-- Main content area with sidebar -->
    <div class="main-content-container">
        <!-- Left side: Main course content -->
        <div class="main-content">
            <div class="content-section">
                <h2 class="section-title">Bạn sẽ học được</h2>
                <div class="learning-points">
                    <c:forEach var="achievement" items="${learnerAchievements}">
                        <div class="learning-point">
                            <span class="check-icon">✓</span>
                            <div class="learning-point-text">${achievement}</div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            
            <div class="content-section">
                <h2 class="section-title">Giới thiệu khóa học</h2>
                <div class="description-content">
                    <p><strong>Bạn có biết:</strong></p>
                    <p>${description}</p>
                </div>
            </div>
            
            <div class="content-section">
                <h2 class="section-title">Nội dung khóa học</h2>
                <div class="course-modules">
                    <!-- Module 1 -->
                    <div class="module">
                        <div class="module-header">
                            <div class="module-title">
                                <span class="dropdown-icon"></span>
                                <span class="title">Phần 1: Tổng quan và cài đặt các ứng dụng khai thuế</span>
                            </div>
                            <div class="module-info">8 bài giảng - 1 giờ 12 phút</div>
                        </div>
                        
                        <div class="module-content collapsed">
                            <div class="lesson">
                                <div class="lesson-icon">▶</div>
                                <div class="lesson-title">Bài 1: Giới thiệu khóa học</div>
                                <div class="lesson-duration">00:04:47</div>
                            </div>
                            
                            <div class="lesson">
                                <div class="lesson-icon">▶</div>
                                <div class="lesson-title">Bài 2: Cài đặt phần mềm hỗ trợ kê khai thuế</div>
                                <div class="lesson-duration">00:11:48</div>
                            </div>
                            
                            <div class="lesson">
                                <div class="lesson-icon">▶</div>
                                <div class="lesson-title">Bài 3: Sửa lỗi thoát phần mềm hỗ trợ kê khai thuế</div>
                                <div class="lesson-duration">00:05:26</div>
                            </div>
                            
                            <div class="lesson">
                                <div class="lesson-icon">▶</div>
                                <div class="lesson-title">Bài 4: Khai báo thông tin cho phần mềm HTKK</div>
                                <div class="lesson-duration">00:16:00</div>
                            </div>
                            
                            <div class="lesson">
                                <div class="lesson-icon">▶</div>
                                <div class="lesson-title">Bài 5: Tìm hiểu về năm tài chính</div>
                                <div class="lesson-duration">00:11:51</div>
                            </div>
                            
                            <div class="lesson">
                                <div class="lesson-icon">▶</div>
                                <div class="lesson-title">Bài 6: Cài đặt các công cụ hỗ trợ</div>
                                <div class="lesson-duration">00:07:07</div>
                            </div>
                            
                            <div class="lesson">
                                <div class="lesson-icon">▶</div>
                                <div class="lesson-title">Bài 7: Định dạng lại ngày, tháng, năm cho máy tính</div>
                                <div class="lesson-duration">00:10:15</div>
                            </div>
                            
                            <div class="lesson">
                                <div class="lesson-icon">▶</div>
                                <div class="lesson-title">Bài 8: Sao lưu phục hồi dữ liệu cho phần mềm Hỗ trợ kê khai</div>
                                <div class="lesson-duration">00:04:58</div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Module 2 -->
                    <div class="module">
                        <div class="module-header">
                            <div class="module-title">
                                <span class="dropdown-icon"></span>
                                <span class="title">Phần 2: Tổng quan về quản lý thuế</span>
                            </div>
                            <div class="module-info">36 bài giảng - 5 giờ 55 phút</div>
                        </div>
                        
                        <div class="module-content collapsed">
                            <!-- Nội dung bài giảng sẽ hiển thị khi được mở rộng -->
                        </div>
                    </div>
                    
                    <!-- Module 3 -->
                    <div class="module">
                        <div class="module-header">
                            <div class="module-title">
                                <span class="dropdown-icon"></span>
                                <span class="title">Phần 3: Lệ phí môn bài</span>
                            </div>
                            <div class="module-info">7 bài giảng - 1 giờ 13 phút</div>
                        </div>
                        
                        <div class="module-content collapsed">
                            <!-- Nội dung bài giảng sẽ hiển thị khi được mở rộng -->
                        </div>
                    </div>
                    
                    <!-- Module 4 -->
                    <div class="module">
                        <div class="module-header">
                            <div class="module-title">
                                <span class="dropdown-icon"></span>
                                <span class="title">Phần 4: Hướng dẫn nộp tờ khai, tiền thuế qua mạng</span>
                            </div>
                            <div class="module-info">8 bài giảng - 1 giờ 28 phút</div>
                        </div>
                        
                        <div class="module-content collapsed">
                            <!-- Nội dung bài giảng sẽ hiển thị khi được mở rộng -->
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- Các phần khác như Tags, Khóa học liên quan, Giảng viên, Đánh giá giữ nguyên -->
            <div class="content-section">
                <div class="tags">
                    <span class="tag-label">Tags:</span>
                    <span class="tag">Kế Toán</span>
                    <span class="tag">Kế toán thuế</span>
                </div>
            </div>

            <div class="content-section">
                <h2 class="section-title">Khóa học liên quan</h2>
                <div class="related-courses">
                    <div class="related-course">
                        <div class="course-thumbnail">
                            <img src="/api/placeholder/250/150" alt="Khóa học liên quan">
                        </div>
                        <div class="course-info">
                            <h3 class="course-title">Kiểm tra, đối chiếu, nhận diện RỦI RO VỀ THUẾ thông qua tài khoản trên Báo cáo tài chính</h3>
                            <div class="course-meta">
                                <div class="rating">
                                    <span class="rating-number">4.9</span>
                                    <span class="rating-stars">★★★★★</span>
                                    <span class="students">140 Học viên</span>
                                </div>
                                <div class="price">
                                    <span class="current-price">1.299.000đ</span>
                                    <span class="original-price">5.000.000đ</span>
                                </div>
                            </div>
                        </div>
                        <button class="wishlist-button">❤️</button>
                    </div>
                    
                    <!-- Các khóa học liên quan khác -->
                </div>
            </div>

            <div class="content-section">
                <h2 class="section-title">Giảng viên</h2>
                <div class="instructor-profile">
                    <div class="instructor-header">
                        <div class="instructor-avatar">
                            <img src="${teacherAvatar}" alt="${teacherName}">
                        </div>
                        <div class="instructor-info">
                            <h3 class="instructor-name">${teacherName}</h3>
                            <div class="instructor-meta">
                                <div class="meta-item">
                                    <span class="icon">🏢</span>
                                    <span>Viện Đào Tạo Kế Toán Online</span>
                                </div>
                                <div class="meta-item">
                                    <span class="icon">⭐</span>
                                    <span>4.3 xếp hạng</span>
                                </div>
                                <div class="meta-item">
                                    <span class="icon">💬</span>
                                    <span>750/50 đánh giá</span>
                                </div>
                                <div class="meta-item">
                                    <span class="icon">👥</span>
                                    <span>1,075/8 học viên</span>
                                </div>
                                <div class="meta-item">
                                    <span class="icon">📚</span>
                                    <span>5 khóa học</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="instructor-bio">
                        <p><strong>${teacherName} </strong></p>
                        <p>${teacherDescription}</p>
                        
                        <ul class="instructor-experience">
                    <!--        <li>Hơn 15 năm kinh nghiệm làm kế toán và huấn luyện kế toán thực tế</li>
                            <li>Trực tiếp huấn luyện hàng ngàn học viên học kế toán mỗi năm, cả trực tiếp và online</li>
                            <li>Trực tiếp tư vấn thuế, kế toán cho hàng trăm doanh nghiệp</li>
                            <li>Tác giả phần mềm kế toán Excel VBA - cung cấp miễn phí cho doanh nghiệp</li>
                            <li>Tác giả mobile app kế toán - chia sẻ miễn phí cho doanh nghiệp</li>
                            <li>Chia sẻ và là Giảng viên chính thức của các khóa học Kế toán Online trên nền tảng Unica</li>
                            <li>Là người thân thiện, vui vẻ, dễ gần... được nhiều người yêu mến</li>
                            <li>Với phương pháp giảng bài đơn giản, chi tiết, dễ hiểu - Cam kết CHẤT LƯỢNG và SỰ HÀI LÒNG</li>
                            <li>Phương châm: Cầm tay hướng việc chứ không Cầm tay chỉ việc</li>
                            <li>Chia sẻ kiến thức thanh tra về học kế toán thuế thực tế có 1 không 2 được nhiều người đánh giá cao</li> -->
                        </ul>
                        <p><a href="${teacherSocialUrl}" target="_blank">Liên kết mạng xã hội</a></p>
                    </div>
                </div>
            </div>

            <div class="content-section">
			    <h2 class="section-title">Đánh giá của học viên</h2>
			    <div class="reviews-container">
			        <c:choose>
			            <c:when test="${not empty reviews}">
			                <c:forEach var="review" items="${reviews}">
			                    <div class="review">
			                        <div class="review-header">
			                            <div class="reviewer-name">${review.createdBy.fullname}</div>
			                            <div class="review-date">
			                                <fmt:formatDate value="${review.createdAt}" pattern="dd/MM/yyyy"/>
			                            </div>
			                        </div>
			                        <div class="review-rating">
			                            <span class="stars">
			                                <fmt:parseNumber var="rateDouble" value="${review.rate}" type="number" />
			                                <c:forEach begin="1" end="5" var="i">
			                                    <c:choose>
			                                        <c:when test="${i <= rateDouble}">
			                                            ★
			                                        </c:when>
			                                        <c:otherwise>
			                                            ☆
			                                        </c:otherwise>
			                                    </c:choose>
			                                </c:forEach>
			                            </span>
			                            <span class="rating-number">${review.rate}</span>
			                        </div>
			                        <div class="review-comment">
			                            ${review.content}
			                        </div>
			                    </div>
			                </c:forEach>
			            </c:when>
			            <c:otherwise>
			                <div class="review">
			                    <div class="review-comment">
			                        Chưa có đánh giá nào cho khóa học này.
			                    </div>
			                </div>
			            </c:otherwise>
			        </c:choose>
			    </div>
			</div>
        </div>
        
        <!-- Right side: Sticky sidebar -->
        <div class="sticky-sidebar">
            <div class="price-info">
                <div class="current-price">
                    0đ
                    <span class="original-price"><fmt:formatNumber value="${coursePrice}" type="currency" currencySymbol="đ" groupingUsed="true"/></span>
                    <span class="discount">Giảm 100%</span>
                </div>
                
                <div class="action-buttons">
                    <button class="primary-button">VÀO HỌC NGAY</button>
                    <button class="secondary-button">MUA NGAY</button>
                </div>
                
                <div class="wishlist-button">❤️</div>
                
                <div class="course-features">
                    <div class="feature-item">
                        <span class="feature-icon">⌚</span>
                        <span>Thời lượng: 09 giờ 49 phút</span>
                    </div>
                    
                    <div class="feature-item">
                        <span class="feature-icon">📚</span>
                        <span>Giáo trình: 59 Bài giảng</span>
                    </div>
                    
                    <div class="feature-item">
                        <span class="feature-icon">🔄</span>
                        <span>Sở hữu khóa học trọn đời</span>
                    </div>
                    
                    <div class="feature-item">
                        <span class="feature-icon">🏆</span>
                        <span>Cấp chứng nhận hoàn thành</span>
                    </div>
                </div>
                
                <div class="coupon-form">
                    <input type="text" class="coupon-input" placeholder="Nhập mã giảm giá">
                    <button class="coupon-button">Áp dụng</button>
                </div>
                
                <button class="enterprise-button">Đăng ký cho doanh nghiệp</button>
                
                <div class="subscription-note">
                    Học không giới hạn 600 khóa học, chỉ với 199K / tháng
                </div>
            </div>
            
            <div class="content-section">
                <div class="registration-note">
                    <span class="feature-icon">🎯</span>
                    Trần Hoàng Hương vừa đăng ký
                </div>
            </div>
        </div>
    </div>
</body>
</html>