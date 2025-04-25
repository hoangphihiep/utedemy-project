<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Unica - Học trực tuyến</title>
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
                <c:choose>
                    <c:when test="${empty sessionScope.account}">
                        <button class="login-btn">Đăng nhập</button>
                        <button class="signup-btn">Đăng ký</button>
                    </c:when>
                    <c:otherwise>
                        <span>Xin chào, ${sessionScope.account.fullname}</span>
                        <a href="/utedemyProject/user/logout">Đăng xuất</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </header> 
    <main>
        <h1 class="cart-title">Giỏ hàng</h1>
        
        <div class="cart-container">
            <c:choose>
                <c:when test="${not empty cart.courses}">
                    <!-- TRƯỜNG HỢP 1: GIỎ HÀNG KHÔNG RỖNG -->
                    <div class="cart-items">
                        <div class="select-all">
                            <div class="select-all-left">
                                <input type="checkbox" id="selectAll" class="select-all-checkbox" checked>
                                <label for="selectAll">CHỌN TẤT CẢ (<span id="item-count">${cart.courses.size()}</span> SẢN PHẨM)</label>
                            </div>
                            
                            <form action="/utedemyProject/user/deletecart" method="post">
                                <input type="hidden" name="action" value="deleteAll">
                                <button type="submit" class="delete-btn">
                                    <i class="fas fa-trash delete-icon"></i> XÓA
                                </button>
                            </form>
                        </div>
                        
                        <form action="/utedemyProject/user/deletecart" method="post" id="deleteForm">
                            <input type="hidden" name="action" value="deleteSelected">
                            
                            <!-- Danh sách khóa học trong giỏ hàng -->
                            <c:forEach var="item" items="${cart.courses}">
                                <div class="cart-item" data-course-id="${item.id}">
                                    <input type="checkbox" class="item-checkbox" checked name="selectedCourses" value="${item.id}" data-price="${item.coursePrice}">
                                    <c:if test="${item.courseDetail.courseImage != ''}">
                                          <c:if test ="${item.courseDetail.courseImage.substring(0,5) != 'https' }">
                                             <c:url value="/image?fname=${item.courseDetail.courseImage}" var="imgUrl"></c:url>
                                         </c:if>
                                  <c:if test ="${item.courseDetail.courseImage.substring(0,5) == 'https' }">
                                          <c:url value="${item.courseDetail.courseImage}" var="imgUrl"></c:url>
                                 </c:if>
                                </c:if>                   
                                    <img src="${imgUrl}" alt="${item.courseName}" class="item-image">
                                    
                                    <div class="item-details">
                                        <div class="item-title">${item.courseName}</div>
                                       <c:set var="totalReview" value="${fn:length(item.review)}" />
<c:set var="totalRate" value="0" />

<c:forEach var="rv" items="${item.review}">
    <c:set var="totalRate" value="${totalRate + rv.rate}" />
</c:forEach>

<c:choose>
    <c:when test="${totalReview > 0}">
        <c:set var="averageRate" value="${totalRate / totalReview}" />
    </c:when>
    <c:otherwise>
        <c:set var="averageRate" value="0" />
    </c:otherwise>
</c:choose>

<fmt:formatNumber var="averageRateFormatted" value="${averageRate}" type="number" maxFractionDigits="1" />

<div>
    <span class="item-rating">
        <i class="fas fa-star rating-star"></i>
        ${averageRateFormatted} (${totalReview} đánh giá)
    </span>
</div>
                    
                                        <div class="item-meta">
                                            ${item.sections.size()} bài giảng
                                        </div>
                                 
                                    </div>
                                    
                                    <div class="item-actions">
                                        <div class="item-price">
                                            <div class="current-price">
                                                <fmt:formatNumber value="${item.coursePrice}" type="currency" currencySymbol="" maxFractionDigits="0" />đ
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            
                 <div style="display: flex; justify-content: flex-end; margin: 20px 0;">
  <button type="submit" class="delete-selected-button" style="
      background-color: #ff4d4d;
      color: white;
      border: none;
      padding: 10px 20px;
      border-radius: 6px;
      cursor: pointer;
      font-size: 14px;
  ">
    Xóa các khóa học đã chọn
  </button>
</div>

                        </form>
                    </div>
                    
                    <div class="cart-summary">
                        <div class="order-title">Thông tin đơn hàng</div>
                        
                        <div class="order-row">
                            <span class="order-label">Tạm tính (<span id="selected-count">${cart.courses.size()}</span> sản phẩm)</span>
                            <span class="order-value" id="subtotal">
                                <fmt:formatNumber value="${totalAmount}" type="currency" currencySymbol="" maxFractionDigits="0" />đ
                            </span>
                        </div>
                        
                        <div class="total-row">
                            <span class="total-label">Tổng cộng:</span>
                            <span class="total-value" id="totalAmount">
                                <fmt:formatNumber value="${totalAmount}" type="currency" currencySymbol="" maxFractionDigits="0" />đ
                            </span>
                        </div>
                        
                        <form action="/utedemyProject/user/checkout" method="post" id="checkoutForm">
                            <div id="selectedCoursesContainer">
                                <c:forEach var="item" items="${cart.courses}">
                                    <input type="hidden" name="selectedCourses" value="${item.id}">
                                </c:forEach>
                            </div>
                       <input type="hidden" name="totalAmount" id="totalAmountInput" value="${totalAmount}"> 
                           <button type="submit" 
    style="width: 100%; background-color: #e65c00; color: white; border: none; border-radius: 4px; padding: 15px; font-size: 16px; font-weight: bold; margin-top: 15px; cursor: pointer; text-transform: uppercase;">
    Thanh toán
</button>

                        </form>
                    </div>
                </c:when>
                <c:otherwise>
                    <!-- TRƯỜNG HỢP 2: GIỎ HÀNG RỖNG -->
                    <div class="empty-cart">
                        <p>Giỏ hàng của bạn đang trống</p>
                        <i class="fas fa-shopping-cart empty-cart-icon"></i>
                        <a href="/utedemyProject/home" class="continue-shopping-btn">Tiếp tục mua sắm</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        
     <section class="top-courses" aria-labelledby="today-sale-title">
            <h3 id="today-sale-title">BẠN CÓ THỂ QUAN TÂM</h3>
            <div id="today-sale-courses" class="course-grid" aria-live="polite">
                <c:if test="${not empty recommendedCourses}">
                    <c:forEach var="course" items="${recommendedCourses}">
                        <div class="course-card">
                        
                        <c:if test="${course.courseDetail.courseImage != ''}">
                                          <c:if test ="${course.courseDetail.courseImage.substring(0,5) != 'https' }">
                                             <c:url value="/image?fname=${course.courseDetail.courseImage}" var="imgUrl_rcm"></c:url>
                                         </c:if>
                                  <c:if test ="${course.courseDetail.courseImage.substring(0,5) == 'https' }">
                                          <c:url value="${course.courseDetail.courseImage}" var="imgUrl_rcm"></c:url>
                                 </c:if>
                         </c:if>                   
                            <img src="${imgUrl_rcm}" alt="${course.courseName}" class="course-image">
                            <div class="course-details">
                                <h4>${course.courseName}</h4>
                                <div class="course-instructor" style="text-align: left;">${course.teacher.fullname}</div>
                                <div class="course-rating">
                                 <c:set var="totalReview_rcm" value="${fn:length(course.review)}" />
<c:set var="totalRate_rcm" value="0" />

<c:forEach var="rv" items="${course.review}">
    <c:set var="totalRate_rcm" value="${totalRate_rcm + rv.rate}" />
</c:forEach>

<c:choose>
    <c:when test="${totalReview > 0}">
        <c:set var="averageRate_rcm" value="${totalRate_rcm / totalReview_rcm}" />
    </c:when>
    <c:otherwise>
        <c:set var="averageRate_rcm" value="0" />
    </c:otherwise>
</c:choose>

<fmt:formatNumber var="averageRateFormatted_rcm" value="${averageRate_rcm}" type="number" maxFractionDigits="1" />

                                    <i class="fas fa-star rating-star"></i> ${totalRate_rcm} (${totalReview_rcm} đánh giá)
                                </div>
                               <div class="course-price" style="text-align: left;">
                                    <fmt:formatNumber value="${course.coursePrice}" type="currency" currencySymbol="" maxFractionDigits="0" />đ
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
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
                    // Tìm phần tử cha chứa khóa học
                    const cartItem = checkbox.closest('.cart-item');
                    
                    // Lấy giá hiện tại (current price) của khóa học
                    const priceElement = cartItem.querySelector('.current-price');
                    
                    // Chuyển đổi giá từ định dạng "499,000đ" sang số
                    const priceText = priceElement.textContent.trim();
                    const price = parseFloat(priceText.replace(/[,.đ]/g, ''));
                    
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
        document.getElementById('checkoutForm')?.addEventListener('submit', function(event) {
            const selectedCount = document.querySelectorAll('.item-checkbox:checked').length;
            
            if (selectedCount === 0) {
                event.preventDefault();
                alert('Vui lòng chọn ít nhất một khóa học để thanh toán!');
            }
        });
    });
    </script>
</body>
</html>