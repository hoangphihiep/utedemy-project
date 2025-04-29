<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Unica - Thanh Toán</title>
    <link rel="stylesheet" href="/utedemyProject/views/Css/checkout.css">
    <style>
        /* Thêm CSS cho hiển thị loại giảm giá */
        .discount-type {
            font-size: 0.9em;
            font-style: italic;
            color: #f26522;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="left-section">
            <div class="product-list">
                <div class="section-title">Thông tin sản phẩm</div>
                <div id="alert-container"></div>
                
                <c:forEach items="${order.orderItems}" var="item" varStatus="status">
                    <div class="product-item" id="product-item-${item.course.id}">
                        <div class="product-image">
                          <c:if test="${item.course.courseDetail.courseImage != ''}">
                                          <c:if test ="${item.course.courseDetail.courseImage.substring(0,5) != 'https' }">
                                             <c:url value="/image?fname=${item.course.courseDetail.courseImage}" var="imgUrl"></c:url>
                                         </c:if>
                                  <c:if test ="${item.course.courseDetail.courseImage.substring(0,5) == 'https' }">
                                          <c:url value="${item.course.courseDetail.courseImage}" var="imgUrl"></c:url>
                                 </c:if>
                                </c:if>                   
                            <img src="${imgUrl}" alt="${item.course.title}">   
                        </div>
                        <div class="product-details">
                            <div class="product-title">${item.course.courseName}</div>
                           <div class="discount-input">
    <input type="text" placeholder="Nhập mã giảm giá" class="discount-code" data-product-id="${item.course.id}"
        <c:choose>
            <c:when test="${not empty item.discounts}">
                value="${fn:join(item.discounts[0].disCode, ',')}" disabled
            </c:when>
            <c:otherwise>
                value="" 
            </c:otherwise>
        </c:choose>
    >
    <button onclick="applyDiscount(${item.course.id})"
        <c:if test="${not empty item.discountCode}">
            disabled style="background-color: #aaa;"
        </c:if>>
        <c:choose>
            <c:when test="${not empty item.discountCode}">Đã áp dụng</c:when>
            <c:otherwise>Áp dụng</c:otherwise>
        </c:choose>
    </button>
</div>

                           <div class="discount-applied" id="discount-applied-${item.course.id}" 
    <c:if test="${item.discount[0].discountAmount <= 0}">
        style="display: none;"
    </c:if>>
    <c:if test="${item.discount[0].discountAmount > 0}">
        Đã áp dụng mã giảm giá: -<fmt:formatNumber value="${item.discount[0].discountAmount}" type="currency" currencySymbol="đ" maxFractionDigits="0"/>

        <span class="discount-type">
            <c:choose>
                <c:when test="${item.discount[0].isPercentageDiscount()}">
                    (Giảm ${item.discount[0].discountValue}%)
                </c:when>
                <c:otherwise>
                    (Giảm cố định)
                </c:otherwise>
            </c:choose>
        </span>
    </c:if>
</div>
                        </div>
                        <div class="product-price">
                            <div id="price-${item.course.id}">
                                <c:if test="${item.discountAmount > 0}">
                                    <div class="discounted-price">
                                        <fmt:formatNumber value="${item.course.currentPrice - item.discountAmount}" type="currency" currencySymbol="đ" maxFractionDigits="0"/>
                                    </div>
                                </c:if>
                                <c:if test="${item.discountAmount <= 0}">
                                    <fmt:formatNumber value="${item.course.currentPrice}" type="currency" currencySymbol="đ" maxFractionDigits="0"/>
                                </c:if>
                            </div>
                            <div class="strikethrough">
                                <fmt:formatNumber value="${item.course.originalPrice}" type="currency" currencySymbol="đ" maxFractionDigits="0"/>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        
        <div class="right-section">
            <div class="section-title">Phương thức thanh toán</div>
            
            <div class="payment-method">
                <div class="payment-logo paypal-logo">
                    <span class="pay">Pay</span><span class="pal">Pal</span>
                </div>
                <div class="payment-name">PayPal</div>
            </div>
            
            <div class="order-summary">
                <div class="section-title">Thông tin đơn hàng</div>
                <div class="order-row">
                    <div>${cartItems.size()} sản phẩm</div>
                    <div id="subtotal">
                        <fmt:formatNumber value="${orderSummary.subtotal}" type="currency" currencySymbol="đ" maxFractionDigits="0"/>
                    </div>
                </div>
                <div class="order-row" id="discount-row" <c:if test="${orderSummary.totalDiscountAmount <= 0}">style="display: none;"</c:if>>
                    <div>Giảm giá</div>
                    <div id="discount-amount">
                        -<fmt:formatNumber value="${orderSummary.totalDiscountAmount}" type="currency" currencySymbol="đ" maxFractionDigits="0"/>
                    </div>
                </div>
                <div class="order-total">
                    <div>Tổng cộng:</div>
                    <div id="total">
                        <fmt:formatNumber value="${orderSummary.totalAmount}" type="currency" currencySymbol="đ" maxFractionDigits="0"/>
                    </div>
                </div>
            </div>
            
            <form action="${pageContext.request.contextPath}/process-payment" method="post" id="paymentForm">
                <input type="hidden" name="paymentMethod" value="paypal">
                <button type="submit" class="btn btn-payment">TIẾN HÀNH THANH TOÁN</button>
            </form>
        </div>
    </div>

    <script>
 // Áp dụng mã giảm giá thông qua AJAX
    function applyDiscount(productId) {
        const discountInput = document.querySelector('.discount-code[data-product-id="' + productId + '"]');
        const discountCode = discountInput.value.trim();
        
        if (discountCode === '') {
            showAlert('danger', 'Vui lòng nhập mã giảm giá.');
            return;
        }
        
        fetch('${pageContext.request.contextPath}/apply-discount', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: 'productId=' + productId + '&discountCode=' + discountCode
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                // Hiển thị thông báo giảm giá
                const discountNotification = document.getElementById('discount-applied-' + productId);
                
                // Hiển thị loại giảm giá (% hoặc cố định)
                let discountTypeText = '';
                if (data.discountType === 'PERCENTAGE') {
                    discountTypeText = '(Giảm ' + data.discountValue + '%)';
                } else {
                    discountTypeText = '(Giảm cố định)';
                }
                
                discountNotification.innerHTML = 'Đã áp dụng mã giảm giá: -' + data.formattedDiscountAmount + ' <span class="discount-type">' + discountTypeText + '</span>';
                discountNotification.style.display = 'block';
                
                // Cập nhật giá sản phẩm sau khi giảm
                const priceElem = document.getElementById('price-' + productId);
                priceElem.innerHTML = '<div class="discounted-price">' + data.formattedTotalAmount + '</div>';
                
                // Disable input và button giảm giá
                discountInput.disabled = true;
                const applyButton = discountInput.nextElementSibling;
                applyButton.disabled = true;
                applyButton.style.backgroundColor = '#aaa';
                applyButton.textContent = 'Đã áp dụng';
                
                // Cập nhật thông tin đơn hàng
                document.getElementById('subtotal').textContent = data.formattedSubtotal;
                
                if (data.totalDiscountAmount > 0) {
                    document.getElementById('discount-row').style.display = 'flex';
                    document.getElementById('discount-amount').textContent = '-' + data.formattedTotalDiscountAmount;
                } else {
                    document.getElementById('discount-row').style.display = 'none';
                }
                
                document.getElementById('total').textContent = data.formattedTotalAmount;
                
                showAlert('success', 'Áp dụng mã giảm giá thành công!');
            } else {
                showAlert('danger', data.message || 'Mã giảm giá không hợp lệ hoặc đã hết hạn.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            showAlert('danger', 'Có lỗi xảy ra khi áp dụng mã giảm giá.');
        });
    }

    // Hiển thị thông báo
    function showAlert(type, message) {
        const alertContainer = document.getElementById('alert-container');
        const alertDiv = document.createElement('div');
        alertDiv.className = 'alert alert-' + type;
        alertDiv.textContent = message;
        
        // Xóa thông báo cũ nếu có
        while (alertContainer.firstChild) {
            alertContainer.removeChild(alertContainer.firstChild);
        }
        
        alertContainer.appendChild(alertDiv);
        
        // Tự động ẩn thông báo sau 5 giây
        setTimeout(() => {
            alertDiv.style.display = 'none';
        }, 5000);
    }
    </script>
</body>
</html>