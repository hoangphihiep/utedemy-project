<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>

<!-- Di chuyển CSS và script vào decorator hoặc để lại nếu cần -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/views/Css/viewCourseDetails.css">
<script src="${pageContext.request.contextPath}/views/Script/viewCourseDetails.js"></script>

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
            <!-- Thêm phần "Người mới bắt đầu" -->
            <div class="level"></div>
            <div class="students">${courseLearner}</div>
        </div>
        <div class="instructor">Giảng viên: <a href="#">${teacherName}</a> </div>


        <div class="course-pricing">
            <span class="current-price">
                <fmt:formatNumber value="${currentPrice}" type="currency" currencySymbol="đ" groupingUsed="true" pattern="#,###"/>đ
            </span>
            <span class="original-price">
                <fmt:formatNumber value="${originalPrice}" type="currency" currencySymbol="đ" groupingUsed="true" pattern="#,###"/>đ
            </span>
        </div>
    </div>

    <div class="video-preview">
        <c:choose>
            <c:when test="${not empty courseVideo}">
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
                <img src="${courseImage}" alt="Video Preview">
                <div class="play-button"></div>
            </c:otherwise>
        </c:choose>
    </div>
</section>

		<div class="main-content-container">
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
        <c:choose>
            <c:when test="${not empty sections}">
                <c:forEach var="section" items="${sections}">
                    <div class="module">
                        <div class="module-header">
                            <div class="module-title">
                                <span class="dropdown-icon"></span>
                                <span class="title">${section.title}</span>
                            </div>
                            
                        </div>
                        <div class="module-content collapsed">
                            <!-- Hiển thị danh sách Lesson -->
                            <c:forEach var="lesson" items="${section.lessons}">
                                <div class="lesson">
                                    <div class="lesson-icon">▶</div>
                                    <div class="lesson-title">${lesson.title}</div>
                                    
                                </div>
                            </c:forEach>
                            <!-- Hiển thị danh sách Quiz -->
                            <c:forEach var="quiz" items="${section.quizs}">
                                <div class="lesson">
                                    <div class="lesson-icon">📝</div>
                                    <div class="lesson-title">${quiz.title}</div>

                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="module">
                    <div class="module-header">
                        <div class="module-title">
                            <span class="dropdown-icon"></span>
                            <span class="title"></span>
                        </div>
                        <div class="module-info"></div>
                    </div>
                    <div class="module-content collapsed">
                        <div class="lesson">
                            <div class="lesson-icon">▶</div>
                            <div class="lesson-title">c</div>
                            <div class="lesson-duration"></div>
                        </div>
                        <div class="lesson">
                            <div class="lesson-icon">▶</div>
                            <div class="lesson-title"></div>
                            <div class="lesson-duration"></div>
                        </div>
                        <div class="lesson">
                            <div class="lesson-icon">▶</div>
                            <div class="lesson-title"></div>
                            <div class="lesson-duration"></div>
                        </div>
                        <div class="lesson">
                            <div class="lesson-icon">▶</div>
                            <div class="lesson-title"></div>
                            <div class="lesson-duration"></div>
                        </div>
                        <div class="lesson">
                            <div class="lesson-icon">▶</div>
                            <div class="lesson-title"></div>
                            <div class="lesson-duration"></div>
                        </div>
                        <div class="lesson">
                            <div class="lesson-icon">▶</div>
                            <div class="lesson-title"></div>
                            <div class="lesson-duration"></div>
                        </div>
                        <div class="lesson">
                            <div class="lesson-icon">▶</div>
                            <div class="lesson-title"></div>
                            <div class="lesson-duration"></div>
                        </div>
                        <div class="lesson">
                            <div class="lesson-icon">▶</div>
                            <div class="lesson-title"></div>
                            <div class="lesson-duration"></div>
                        </div>
                    </div>
                </div>
                <div class="module">
                    <div class="module-header">
                        <div class="module-title">
                            <span class="dropdown-icon"></span>
                            <span class="title"></span>
                        </div>
                        <div class="module-info"></div>
                    </div>
                    <div class="module-content collapsed"></div>
                </div>
                <div class="module">
                    <div class="module-header">
                        <div class="module-title">
                            <span class="dropdown-icon"></span>
                            <span class="title"></span>
                        </div>
                        <div class="module-info"></div>
                    </div>
                    <div class="module-content collapsed"></div>
                </div>
                <div class="module">
                    <div class="module-header">
                        <div class="module-title">
                            <span class="dropdown-icon"></span>
                            <span class="title"></span>
                        </div>
                        <div class="module-info"></div>
                    </div>
                    <div class="module-content collapsed"></div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

        
        <div class="content-section">
    <h2 class="section-title">Giảng viên</h2>
    <div>
        <div class="instructor-header">
            <div class="instructor-avatar">
                
                <c:if test="${teacherAvatar.substring(0,5) != 'https' }">
					<c:url value="/image?fname=${teacherAvatar}" var="imgUrl"></c:url>
				</c:if>
				<c:if test="${teacherAvatar.substring(0,5) == 'https' }">
					<c:url value="${teacherAvatar}" var="imgUrl"></c:url>
				</c:if>
				

				<div class="image-upload-container">
					<div class="image-preview">
						<img id="previewImage" src="${imgUrl}" alt="${teacherName}" />
					</div>
				</div>
				

            </div>
           
            
            
            
            <div class="instructor-info">
                <!-- Bỏ phần instructor-meta cũ -->
            </div>
        </div>

        <div class="instructor-bio">
    <!-- Debug để kiểm tra giá trị teacher.id -->

    <c:choose>
        <c:when test="${not empty teacher and not empty teacher.id}">
            <p><strong class="teacher-name"><a href="${pageContext.request.contextPath}/viewTeacherProfile?id=${teacher.id}">${teacher.fullname}</a></strong></p>
        </c:when>
        <c:otherwise>
            <p><strong class="teacher-name">${teacher.fullname}</strong></p>
        </c:otherwise>
    </c:choose>
    <p><i class="fas fa-graduation-cap"></i> Giảng viên về khoa học máy tính</p>
    <p><i class="fas fa-map-marker-alt"></i> Địa chỉ: ${teacher.address}</p>
    <p><i class="fas fa-envelope"></i> Email: <a href="mailto:${teacher.email}">${teacher.email}</a></p>
    <p><i class="fas fa-phone-alt"></i> Số điện thoại: ${teacher.phoneNumber}</p>
    <p><i class="fas fa-info-circle"></i> ${teacher.description}</p>
    <p><a href="${teacher.socialUrl}" target="_blank"><i class="fas fa-link"></i> Liên kết mạng xã hội</a></p>
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

    <div class="sticky-sidebar">
        <div class="price-info">
            <span class="new-current-price">
    			<fmt:formatNumber value="${currentPrice}" type="currency" currencySymbol="đ" groupingUsed="true" pattern="#,###"/>đ
			</span>

            <span class="original-price">
                <fmt:formatNumber value="${originalPrice}" type="currency" currencySymbol="đ" groupingUsed="true" pattern="#,###"/>đ
            </span>
            

            <div class="action-buttons">
                <button class="primary-button">MUA NGAY</button>
                <button class="secondary-button">VÀO HỌC NGAY</button>
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

            <div class="subscription-note">
                Học không giới hạn 600 khóa học, chỉ với 199K / tháng
            </div>
        </div>
    </div>
    
    
</div>
<script type="text/javascript">
//Function to preview images
function previewImage(event, previewId, placeholderId) {
  const placeholderText = document.getElementById(placeholderId);
  const imagePreview = document.getElementById(previewId);
  const file = event.target.files[0];

  if (file) {
    const reader = new FileReader();
    reader.onload = function(e) {
      imagePreview.src = e.target.result; // Gán đường dẫn ảnh cho img
      imagePreview.style.display = 'block'; // Hiển thị ảnh xem trước
    };
    reader.readAsDataURL(file);
  }
}
</script>



