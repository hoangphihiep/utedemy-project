<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Unica - Học trực tuyến</title>
    <link rel="stylesheet" href="styles.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="/utedemyProject/views/Css/cartpage.css">
    <script src="/utedemyProject/views/Script/cartpage.js"></script>  
</head>
<body>
    <header class="header-container">
        <div class="top-bar">
            <div class="logo-section">
                <a href="#" class="unica-logo">
                    <img src="https://unica.vn/media/img/logo-unica.svg" alt="Unica Logo" class="logo-circle" id="logoImg">
                </a>
                <button class="category-btn">
                    <i class="fas fa-th-large"></i> DANH MỤC
                </button>
                <div class="menu-container">
                    <div class="primary-menu">
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-rocket"></i> Kinh Doanh
                            </div>
                            <i class="fas fa-chevron-right"></i>
                            <div class="submenu">
                                <div class="menu-item">Quản Trị Kinh Doanh</div>
                                <div class="menu-item">Kinh Doanh Online</div>
                                <div class="menu-item">Khởi Nghiệp</div>
                                <div class="menu-item">Quản Trị Nhân Sự</div>
                                <div class="menu-item">Thương mại điện tử</div>
                                <div class="menu-item">Bán hàng</div>
                            </div>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-language"></i> Ngoại Ngữ
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-paint-brush"></i> Thiết Kế
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-lightbulb"></i> Kỹ Năng
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-code"></i> Lập Trình & CNTT
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-heartbeat"></i> Sức Khoẻ Và Làm Đẹp
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-bullhorn"></i> Marketing
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-desktop"></i> Tin Học Văn Phòng
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-coffee"></i> Phong Cách Sống
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-heart"></i> Hôn Nhân Và Gia Đình
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-chart-line"></i> Tài Chính Kế Toán
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                        <div class="menu-item">
                            <div>
                                <i class="fas fa-music"></i> Âm Nhạc
                            </div>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="search-box">
                <div class="search-box-inner">
                    <input type="text" placeholder="Tìm khóa học, giảng viên">
                    <button class="search-btn">
                        <i class="fas fa-search"></i>
                    </button>
                </div>
            </div>
            
            <div class="user-actions">
                <span>Doanh nghiệp</span>
                <span>Hội viên</span>
                <i class="fas fa-shopping-cart cart-icon"></i>
                <button class="login-btn">Đăng nhập</button>
                <button class="signup-btn">Đăng ký</button>
            </div>
        </div>
    </header>

    <main>
        <h1 class="cart-title">Giỏ hàng</h1>
        
        <div class="cart-container">
            <c:choose>
                <c:when test="${empty cart.courses}">
                    <div class="empty-cart">
                        <i class="fas fa-shopping-cart fa-4x"></i>
                        <p>Giỏ hàng của bạn đang trống</p>
                        <a href="home" class="continue-shopping-btn">Tiếp tục mua sắm</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="cart-items">
                        <div class="select-all">
                            <div class="select-all-left">
                                <input type="checkbox" id="selectAll" class="select-all-checkbox" checked>
                                <label for="selectAll">CHỌN TẤT CẢ (<c:out value="${fn:length(cart.courses)}" /> SẢN PHẨM)</label>
                            </div>
                            
                            <form action="/utedemyProject/user/cart" method="post">
                                <input type="hidden" name="action" value="deleteAll">
                                <button type="submit" class="delete-btn">
                                    <i class="fas fa-trash delete-icon"></i> XÓA
                                </button>
                            </form>
                        </div>
                        
                        <form action="/utedemyProject/user/cart" method="post" id="deleteForm">
                            <input type="hidden" name="action" value="deleteSelected">
                            <c:forEach var="course" items="${cart.courses}">
                                <div class="cart-item" data-course-id="${course.id}">
                                    <input type="checkbox" class="item-checkbox" checked name="selectedCourses" value="${course.id}" data-price="${course.coursePrice}" data-discount-price="${course.discountPrice}">
                                    <c:choose>
                                        <c:when test="${not empty course.courseDetail.imagePath}">
                                            <c:if test="${course.courseDetail.imagePath.substring(0,5) != 'https'}">
                                                <c:url value="/image?fname=${course.courseDetail.imagePath}" var="imgUrl"></c:url>
                                            </c:if>
                                            <c:if test="${course.courseDetail.imagePath.substring(0,5) == 'https'}">
                                                <c:url value="${course.courseDetail.imagePath}" var="imgUrl"></c:url>
                                            </c:if>
                                            <img src="${imgUrl}" alt="${course.courseName}" class="item-image">
                                        </c:when>
                                        <c:otherwise>
                                            <!-- Placeholder for course without image -->
                                        </c:otherwise>
                                    </c:choose>
                                    
                                    <div class="item-details">
                                        <div class="item-title">${course.courseName}</div>
                                        <div>
                                            <span class="item-rating">
                                                <i class="fas fa-star rating-star"></i>
                                                <c:set var="totalRating" value="0" />
                                                <c:set var="ratingCount" value="0" />
                                                
                                                <c:forEach var="review" items="${course.review}">
                                                    <c:set var="totalRating" value="${totalRating + Integer.parseInt(review.rate)}" />
                                                    <c:set var="ratingCount" value="${ratingCount + 1}" />
                                                </c:forEach>
                                                
                                                <c:choose>
                                                    <c:when test="${ratingCount > 0}">
                                                        <fmt:formatNumber value="${totalRating / ratingCount}" pattern="#.#" /> (${ratingCount} đánh giá)
                                                    </c:when>
                                                    <c:otherwise>
                                                        Chưa có đánh giá
                                                    </c:otherwise>
                                                </c:choose>
                                            </span>
                                        </div>
                                        <div class="item-meta">
                                            <c:set var="totalLessons" value="0" />
                                            <c:set var="totalMinutes" value="0" />
                                            
                                            <c:forEach var="section" items="${course.sections}">
                                                <c:set var="totalLessons" value="${totalLessons + fn:length(section.lessons)}" />
                                                <c:forEach var="lesson" items="${section.lessons}">
                                                    <c:set var="totalMinutes" value="${totalMinutes + lesson.duration}" />
                                                </c:forEach>
                                            </c:forEach>
                                            
                                            ${totalLessons} bài giảng - 
                                            <fmt:formatNumber value="${totalMinutes / 60}" pattern="#" /> giờ 
                                            <fmt:formatNumber value="${totalMinutes % 60}" pattern="#" /> phút
                                        </div>
                                    </div>
                                    
                                    <div class="item-actions">
                                        <div class="item-price">
                                            <c:set var="discountedPrice" value="${course.coursePrice}" />
                                            <c:forEach var="discount" items="${course.discounts}">
                                                <c:if test="${discount.isActive}">
                                                    <c:set var="discountedPrice" value="${course.coursePrice - (course.coursePrice * discount.discountPercentage / 100)}" />
                                                </c:if>
                                            </c:forEach>
                                            
                                            <div class="current-price" data-price="${discountedPrice}">
                                                <fmt:formatNumber value="${discountedPrice}" pattern="#,###" />đ
                                            </div>
                                            
                                            <c:if test="${discountedPrice < course.coursePrice}">
                                                <div class="original-price">
                                                    <fmt:formatNumber value="${course.coursePrice}" pattern="#,###" />đ
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <div style="text-align: right; margin-top: 20px;">
                                <button type="submit" class="delete-selected-button">Xóa các khóa học đã chọn</button>
                            </div>
                        </form>
                    </div>
                    
                    <div class="cart-summary">
                        <div class="order-title">Thông tin đơn hàng</div>
                        
                        <div class="order-row">
                            <span class="order-label">Tạm tính (<span id="selected-count">${fn:length(cart.courses)}</span> sản phẩm)</span>
                            <span class="order-value" id="subtotal">
                                <fmt:formatNumber value="0" pattern="#,###" />đ
                            </span>
                        </div>
                        
                        <div class="total-row">
                            <span class="total-label">Tổng cộng:</span>
                            <span class="total-value" id="totalAmount">
                                <fmt:formatNumber value="0" pattern="#,###" />đ
                            </span>
                        </div>
                        
                        <form action="checkout" method="post" id="checkoutForm">
                            <input type="hidden" name="useXu" id="xuInput" value="false">
                            <div id="selectedCoursesContainer">
                                <!-- Selected courses will be added here by JavaScript -->
                            </div>
                            <input type="hidden" name="totalAmount" id="totalAmountInput" value="0">
                            <button type="submit" class="checkout-btn">Thanh toán</button>
                        </form>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        
        <section class="top-courses" aria-labelledby="today-sale-title">
            <h3 id="today-sale-title">BẠN CÓ THỂ QUAN TÂM</h3>
            <div id="today-sale-courses" class="course-grid" aria-live="polite"></div>
        </section>
    </main>

    <footer class="footer" role="contentinfo">
        <div class="footer-content">
            <div class="footer-section">
                <h4>Về Unica</h4>
                <nav>
                    <ul>
                        <li><a href="#">Giới thiệu về Unica</a></li>
                        <li><a href="#">Hướng dẫn sử dụng</a></li>
                        <li><a href="#">Kích hoạt khóa học</a></li>
                        <li><a href="#">Góc chia sẻ</a></li>
                    </ul>
                </nav>
            </div>
            <div class="footer-section">
                <h4>Hợp tác</h4>
                <nav>
                    <ul>
                        <li><a href="#">Đăng ký giảng viên</a></li>
                        <li><a href="#">Giải pháp e-learning</a></li>
                        <li><a href="#">Đào tạo doanh nghiệp</a></li>
                        <li><a href="#">Đào tạo Inhouse</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </footer>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // Lấy tất cả checkbox khóa học
            const checkboxes = document.querySelectorAll('.item-checkbox');
            const selectAllCheckbox = document.getElementById('selectAll');
            
            // Hàm tính toán lại tổng tiền
            function recalculateTotal() {
                let subtotal = 0;
                let selectedCount = 0;
                
                checkboxes.forEach(checkbox => {
                    if (checkbox.checked) {
                        // Lấy giá của khóa học từ phần tử cha
                        const cartItem = checkbox.closest('.cart-item');
                        const priceElement = cartItem.querySelector('.current-price');
                        
                        // Lấy giá trị từ data-price attribute
                        const price = parseFloat(priceElement.getAttribute('data-price') || 0);
                        
                        subtotal += price;
                        selectedCount++;
                    }
                });
                
                // Cập nhật hiển thị tạm tính
                document.getElementById('selected-count').textContent = selectedCount;
                
                // Cập nhật giá trị tạm tính
                const subtotalElement = document.getElementById('subtotal');
                subtotalElement.textContent = new Intl.NumberFormat('vi-VN').format(subtotal) + 'đ';
                
                // Cập nhật tổng cộng
                const totalValue = document.getElementById('totalAmount');
                totalValue.textContent = new Intl.NumberFormat('vi-VN').format(subtotal) + 'đ';
                
                // Cập nhật giá trị cho form thanh toán
                const totalAmountInput = document.getElementById('totalAmountInput');
                totalAmountInput.value = subtotal;
                
                // Cập nhật danh sách khóa học được chọn cho form thanh toán
                const selectedCoursesContainer = document.getElementById('selectedCoursesContainer');
                selectedCoursesContainer.innerHTML = ''; // Xóa danh sách cũ
                
                checkboxes.forEach(checkbox => {
                    if (checkbox.checked) {
                        const courseId = checkbox.value;
                        const hiddenInput = document.createElement('input');
                        hiddenInput.type = 'hidden';
                        hiddenInput.name = 'selectedCourses';
                        hiddenInput.value = courseId;
                        selectedCoursesContainer.appendChild(hiddenInput);
                    }
                });
            }
            
            // Thêm sự kiện cho tất cả checkbox khóa học
            checkboxes.forEach(checkbox => {
                checkbox.addEventListener('change', function() {
                    // Kiểm tra nếu tất cả đều được chọn thì chọn "Select All"
                    const allChecked = Array.from(checkboxes).every(cb => cb.checked);
                    selectAllCheckbox.checked = allChecked;
                    
                    recalculateTotal();
                });
            });
            
            // Thêm sự kiện cho checkbox "Select All"
            selectAllCheckbox.addEventListener('change', function() {
                const isChecked = this.checked;
                
                // Chọn/bỏ chọn tất cả các checkbox khóa học
                checkboxes.forEach(checkbox => {
                    checkbox.checked = isChecked;
                });
                
                recalculateTotal();
            });
            
            // Tính toán ban đầu khi trang được tải
            recalculateTotal();
            
            // Form checkout validation
            document.getElementById('checkoutForm').addEventListener('submit', function(event) {
                const selectedCoursesInputs = document.querySelectorAll('input[name="selectedCourses"]:checked');
                
                if (selectedCoursesInputs.length === 0) {
                    event.preventDefault();
                    alert('Vui lòng chọn ít nhất một khóa học để thanh toán!');
                }
            });
        });
    </script>
</body>
</html>