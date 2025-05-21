<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Khóa học yêu thích</title>


</head>
<body>
    <!-- Main Content -->
    <div class="container my-5">
        <h2 class="mb-4">Khóa học yêu thích</h2>

        <c:if test="${empty favoriteCourses}">
            <div class="alert alert-info">
                Bạn chưa có khóa học yêu thích nào. Hãy khám phá các khóa học của chúng tôi!
            </div>
            <div class="text-center mt-4">
                <a href="${pageContext.request.contextPath}/user/homepage" class="btn btn-primary">Khám phá khóa học</a>
            </div>
        </c:if>

        <!-- Explicitly define container and row -->
        <c:if test="${empty favoriteCourses}">
		    <div class="container-fluid p-0">
		        <div class="row">
		            <div class="col-12 text-center py-4">
		                <p class="text-muted">Bạn chưa có khóa học yêu thích nào.</p>
		            </div>
		        </div>
		    </div>
		</c:if>
		<c:if test="${not empty favoriteCourses}">
		    <div class="container-fluid p-0">
	            <div class="row">
	                <c:forEach var="course" items="${favoriteCourses}">
	                    <div class="col-lg-4 col-md-6 col-sm-12 course-container">
	                        <div class="card course-card">
	                            <div class="position-relative">
	                                <c:if test="${course.courseDetail.courseImage.substring(0,5) != 'https' }">
	                                    <c:url value="/image?fname=${course.courseDetail.courseImage}" var="imgUrl"></c:url>
	                                </c:if>
	                                <c:if test="${course.courseDetail.courseImage.substring(0,5) == 'https' }">
	                                    <c:url value="${course.courseDetail.courseImage}" var="imgUrl"></c:url>
	                                </c:if>
	                                <img src="${imgUrl}" class="card-img-top course-image" alt="${course.courseName}">
	                                <button class="favorite-btn remove-favorite" data-course-id="${course.id}">
	                                    <i class="fas fa-heart active"></i>
	                                </button>
	                            </div>
	                            <div class="card-body">
	                                <h5 class="card-title">${course.courseName}</h5>
	                                <p class="card-text text-muted">${course.teacher.fullname}</p>
	                                <div class="rating mb-2">
	                                    <c:choose>
	                                        <c:when test="${not empty course.review}">
	                                            <c:set var="totalRating" value="0" />
	                                            <c:set var="reviewCount" value="0" />
	                                            <c:forEach var="review" items="${course.review}">
	                                                <c:set var="totalRating" value="${totalRating + review.rate}" />
	                                                <c:set var="reviewCount" value="${reviewCount + 1}" />
	                                            </c:forEach>
	                                            <c:set var="averageRating" value="${totalRating / reviewCount}" />
	                                            <fmt:formatNumber var="roundedRating" value="${averageRating}" pattern="#.#" />
	
	                                            <span>${roundedRating}</span>
	                                            <c:forEach begin="1" end="5" var="i">
	                                                <c:choose>
	                                                    <c:when test="${i <= averageRating}">
	                                                        <i class="fas fa-star"></i>
	                                                    </c:when>
	                                                    <c:when test="${i > averageRating && i < averageRating + 1}">
	                                                        <i class="fas fa-star-half-alt"></i>
	                                                    </c:when>
	                                                    <c:otherwise>
	                                                        <i class="far fa-star"></i>
	                                                    </c:otherwise>
	                                                </c:choose>
	                                            </c:forEach>
	                                            <span class="rating-count">(${reviewCount})</span>
	                                        </c:when>
	                                        <c:otherwise>
	                                            <span>Chưa có đánh giá</span>
	                                        </c:otherwise>
	                                    </c:choose>
	                                </div>
	                                <div class="price-section">
	                                    <span class="price">
	                                        <fmt:formatNumber value="${course.coursePrice}" type="currency" currencySymbol="đ" maxFractionDigits="0"/>
	                                    </span>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </c:forEach>
	            </div>
	        </div>
		</c:if>
        
    </div>    
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <script>
        $(document).ready(function() {
            // Remove course from favorites
            $('.remove-favorite').click(function() {
                const courseId = $(this).data('course-id');
                const card = $(this).closest('.course-container');
                
                $.ajax({
                    url: '${pageContext.request.contextPath}/user/removeFavoriteCourse',
                    type: 'POST',
                    data: {
                        action: 'removeFromFavorite',
                        courseId: courseId
                    },
                    success: function(response) {
                        if (response.success) {
                            // Remove the card with animation
                            card.fadeOut(300, function() {
                                $(this).remove();
                                
                                // Check if there are no more courses
                                if ($('.course-card').length === 0) {
                                    $('.row').html(`
                                        <div class="alert alert-info col-12">
                                            Bạn chưa có khóa học yêu thích nào. Hãy khám phá các khóa học của chúng tôi!
                                        </div>
                                        <div class="text-center mt-4 col-12">
                                            <a href="${pageContext.request.contextPath}/user/homepage" class="btn btn-primary">Khám phá khóa học</a>
                                        </div>
                                    `);
                                }
                            });
                            
                            // Show success message
                            showToast(response.message, 'success');
                        } else {
                            showToast(response.message, 'danger');
                        }
                    },
                    error: function() {
                        showToast('Đã xảy ra lỗi khi xử lý yêu cầu', 'danger');
                    }
                });
            });
            
            // Function to show toast messages
            function showToast(message, type) {
                const toastHTML = `
                    <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 9999">
                        <div class="toast align-items-center text-white bg-${type} border-0" role="alert" aria-live="assertive" aria-atomic="true">
                            <div class="d-flex">
                                <div class="toast-body">
                                    ${message}
                                </div>
                                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
                            </div>
                        </div>
                    </div>
                `;
                
                $('body').append(toastHTML);
                const toast = new bootstrap.Toast($('.toast'));
                toast.show();
                
                // Remove the toast after it's hidden
                $('.toast').on('hidden.bs.toast', function() {
                    $(this).parent().remove();
                });
            }
        });
    </script>
</body>
</html>