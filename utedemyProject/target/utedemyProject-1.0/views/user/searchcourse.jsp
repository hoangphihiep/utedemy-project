<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="vi">
<body>

    <div class="search-header">
        <h1>"<c:out value='${searchAmount}'/>" kết quả cho "<c:out value='${keyWord}'/>"</h1>

        <div class="filter-container">
            <button class="filter-btn">
                <span>Bộ lọc</span>
            </button>

            <div class="sort-dropdown">
                <button class="sort-btn">
                    <span>Sắp xếp theo</span> 
                    <span>Học nhiều nhất</span> 
                    <span>▼</span>
                </button>
            </div>
        </div>
    </div>
    
    <!-- Main Content -->
    <div class="container">

        <!-- Nút hiển thị lại bộ lọc -->
        <button class="show-filter-btn">Hiển thị bộ lọc</button>

        <form action="${pageContext.request.contextPath}/user/filter" 
              method="post" 
              id="filterForm"
              onsubmit="return validateForm()">
              
            <!-- CRITICAL: Giới hạn độ dài csrfToken -->
            <input type="hidden" 
                   name="csrfToken" 
                   value="<c:out value='${csrfToken}'/>" 
                   maxlength="100" 
                   id="csrfTokenInput"/>
            
            <div class="content-wrapper">
                <!-- Sidebar Filters -->
                <div class="sidebar">
                    <div class="filter-section">
                        <h3 class="filter-title">Đánh giá</h3>
                        <div class="rating-filter">
                            <label class="rating-item">
                                <input type="radio" 
                                       name="rating" 
                                       value="4.5"
                                       data-valid="true">
                                <span class="star-rating">★ ★ ★ ★ ★</span>
                                <span>Từ 4.5 trở lên</span>
                            </label>
                            
                            <label class="rating-item">
                                <input type="radio" 
                                       name="rating" 
                                       value="4"
                                       data-valid="true">
                                <span class="star-rating">★ ★ ★ ★ ☆</span>
                                <span>Từ 4.0 trở lên</span>
                            </label>
                            
                            <label class="rating-item">
                                <input type="radio" 
                                       name="rating" 
                                       value="3.5"
                                       data-valid="true">
                                <span class="star-rating">★ ★ ★ ☆ ☆</span>
                                <span>Từ 3.5 trở lên</span>
                            </label>
                            
                            <label class="rating-item">
                                <input type="radio" 
                                       name="rating" 
                                       value="3.0"
                                       data-valid="true">
                                <span class="star-rating">★ ★ ★ ☆ ☆</span>
                                <span>Từ 3.0 trở lên</span>
                            </label>
                        </div>
                    </div>
                    
                    <div class="filter-section">
                        <button type="submit"
                            style="padding: 10px 20px; font-size: 16px; color: white; 
                                   background-color: #007bff; border: none; border-radius: 15px; 
                                   cursor: pointer; transition: background-color 0.3s, transform 0.2s;">
                            Lọc khóa học
                        </button>
                    </div>
                </div>
            </div>
        </form>
        
        <div class="course-list">
            <c:forEach items="${CourseList}" var="i">
                <div style="display: flex; background-color: white; border-radius: 5px; 
                            overflow: hidden; margin-bottom: 15px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);">
                    <div style="padding: 15px; flex: 1; display: flex; flex-direction: column; 
                                justify-content: space-between;">
                        <div>
                            <h3 id="courseName"  
                                style="font-size: 16px; font-weight: bold; margin-bottom: 5px; 
                                       color: #333; cursor: pointer;"
                                onclick="window.location.href='/utedemyProject/course/courseDetail?courseId=<c:out value='${i.id}'/>'">
                                <c:out value="${i.courseName}"/>
                            </h3>

                            <p class="instructor"><c:out value="${i.teacher.fullname}"/></p>
                            
                            <c:set var="reviewCount" value="0" scope="page" />
                            <div class="rating">
                                <c:set var="sum" value="0" />
                                <c:set var="reviewCount" value="0" />

                                <!-- Lặp qua Review để tính tổng điểm và đếm số lượng -->
                                <c:forEach items="${Review}" var="r">
                                    <c:if test="${r.course.id == i.id}">
                                        <c:set var="sum" value="${sum + r.rate}" />
                                        <c:set var="reviewCount" value="${reviewCount + 1}" />
                                    </c:if>
                                </c:forEach>

                                <!-- Kiểm tra nếu có review -->
                                <c:choose>
                                    <c:when test="${reviewCount > 0}">
                                        <!-- Tính trung bình -->
                                        <c:set var="average" value="${sum / reviewCount}" />

                                        <!-- Làm tròn: ví dụ 3.4 => 3; 3.5 => 4 -->
                                        <c:set var="roundedRating"
                                            value="${average - (average % 1) + (average % 1 ge 0.5 ? 1 : 0)}" />
                                        <c:if test="${roundedRating > 5}">
                                            <c:set var="roundedRating" value="5" />
                                        </c:if>
                                        <c:if test="${roundedRating < 0}">
                                            <c:set var="roundedRating" value="0" />
                                        </c:if>
                                        
                                        <span class="stars" style="color: gold; font-size: 20px; margin-left: -130px;">
                                            <c:forEach begin="1" end="${roundedRating}" var="star">★</c:forEach>
                                            <c:forEach begin="1" end="${5 - roundedRating}" var="star">☆</c:forEach>
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <span>No reviews yet</span>
                                    </c:otherwise>
                                </c:choose>

                                <!-- Hiển thị tổng số đánh giá -->
                                <span class="rating-count">(<c:out value="${reviewCount}"/>)</span>
                            </div>

                            <c:if test="${i.courseDetail.courseImage.substring(0,5) != 'https' }">
                                <c:url value="/image?fname=${i.courseDetail.courseImage}" var="imgUrl"></c:url>
                            </c:if>
                            <c:if test="${i.courseDetail.courseImage.substring(0,5) == 'https' }">
                                <c:url value="${i.courseDetail.courseImage}" var="imgUrl"></c:url>
                            </c:if>

                            <img src="<c:out value='${imgUrl}'/>" 
                                 alt="Course Image"
                                 style="width: 200px; height: auto;">
                        </div>
                    </div>

                    <div class="price-container">
                        <span class="price"><c:out value="${i.coursePrice}"/></span>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- Chat Bubble -->
        <div class="chat-bubble">
            💬 <span class="notification">1</span>
        </div>
    </div>

    <script nonce="${requestScope.nonce}">
    // Client-side validation để ngăn chặn buffer overflow
    function validateForm() {
        // Validate CSRF Token length
        var csrfToken = document.getElementById('csrfTokenInput').value;
        if (csrfToken && csrfToken.length > 100) {
            alert('CSRF token không hợp lệ');
            return false;
        }
        
        // Validate Rating
        var ratingInputs = document.querySelectorAll('input[name="rating"]:checked');
        if (ratingInputs.length > 0) {
            var ratingValue = ratingInputs[0].value;
            
            // Check length
            if (ratingValue.length > 10) {
                alert('Rating không hợp lệ');
                return false;
            }
            
            // Check if it's a valid number
            var rating = parseFloat(ratingValue);
            if (isNaN(rating) || rating < 0 || rating > 5) {
                alert('Rating phải từ 0 đến 5');
                return false;
            }
            
            // Check if it's one of the allowed values
            var allowedRatings = ['3.0', '3.5', '4', '4.5'];
            if (!allowedRatings.includes(ratingValue)) {
                alert('Rating không hợp lệ');
                return false;
            }
        }
        
        return true;
    }
    
    // Prevent manipulation of radio button values
    document.addEventListener('DOMContentLoaded', function() {
        var ratingInputs = document.querySelectorAll('input[name="rating"]');
        var validRatings = ['3.0', '3.5', '4', '4.5'];
        
        ratingInputs.forEach(function(input) {
            // Prevent value changes
            Object.defineProperty(input, 'value', {
                writable: false,
                configurable: false
            });
            
            // Monitor for DOM manipulation
            var observer = new MutationObserver(function(mutations) {
                mutations.forEach(function(mutation) {
                    if (mutation.type === 'attributes' && mutation.attributeName === 'value') {
                        var newValue = input.getAttribute('value');
                        if (!validRatings.includes(newValue)) {
                            console.warn('Detected rating manipulation attempt');
                            input.setAttribute('value', '4'); // Reset to default
                        }
                    }
                });
            });
            
            observer.observe(input, {
                attributes: true,
                attributeFilter: ['value']
            });
        });
        
        // Validate CSRF token on page load
        var csrfInput = document.getElementById('csrfTokenInput');
        if (csrfInput && csrfInput.value.length > 100) {
            console.error('CSRF token too long, clearing...');
            csrfInput.value = '';
        }
    });
    </script>
</body>
</html>