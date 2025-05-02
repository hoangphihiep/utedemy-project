<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="vi">
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
                            <img src="${imgUrl}" alt="${item.course.courseName}">   
                        </div>
                        <div class="product-details">
                            <div class="product-title">${item.course.courseName}</div>
                          <div class="discount-input">
    <input type="text" placeholder="Nhập mã giảm giá" class="discount-code" data-product-id="${item.course.id}"
        value="${not empty item.discount ? item.discount.disCode : ''}"
        <c:if test="${not empty item.discount}">
            disabled
        </c:if>
    >

    <div class="discount-buttons">
        <button class="apply-btn" onclick="applyDiscount(${item.course.id},${item.id})"
            <c:if test="${not empty item.discount}">
                disabled style="background-color: #aaa;"
            </c:if>>
            <c:choose>
                <c:when test="${not empty item.discount}">Đã áp dụng</c:when>
                <c:otherwise>Áp dụng</c:otherwise>
            </c:choose>
        </button>
        
        <button class="remove-btn" onclick="removeDiscount(${item.course.id},${item.id})"
            <c:if test="${empty item.discount}">
                disabled style="background-color: #aaa;"
            </c:if>>
            Hủy giảm giá
        </button>
    </div>
</div>


                       <div class="discount-applied" id="discount-applied-${item.course.id}"
    <c:if test="${empty item.discount or empty item.discount.decreasedFee}">
        style="display: none;"
    </c:if>>
    
    <c:if test="${not empty item.discount and not empty item.discount.decreasedFee}">
        Đã áp dụng mã giảm giá: 

        <c:choose>
            <c:when test="${item.discount.type eq 'AMOUNTMONEY'}">
                -<fmt:formatNumber value="${item.discount.decreasedFee}" type="currency" currencySymbol="đ" maxFractionDigits="0"/>
                (Giảm cố định)
            </c:when>

            <c:when test="${item.discount.type eq 'PERCENTAGE'}">
                -${item.discount.decreasedFee}%
                (Giảm theo phần trăm)
            </c:when>

            <c:otherwise>
                (Không xác định loại giảm giá)
            </c:otherwise>
        </c:choose>
    </c:if>
</div>


                        </div>
                    <div class="product-price1" id="price-${item.course.id}">
    <c:choose>
        <c:when test="${item.discount != null}">
            <div class="discounted-price">
                <fmt:formatNumber value="${item.finishedFee}" type="currency" currencySymbol="đ" maxFractionDigits="0"/>
            </div>
            <div class="strikethrough">
                <fmt:formatNumber value="${item.course.coursePrice}" type="currency" currencySymbol="đ" maxFractionDigits="0"/>
            </div>
        </c:when>
        <c:otherwise>
            <div class="regular-price">
                <fmt:formatNumber value="${item.finishedFee}" type="currency" currencySymbol="đ" maxFractionDigits="0"/>
            </div>
        </c:otherwise>
    </c:choose>
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
                    <div>${order.orderItems.size()} sản phẩm</div>
                    <div id="subtotal">
                        <fmt:formatNumber value="${subtotalAmount}" type="currency" currencySymbol="đ" maxFractionDigits="0"/>
                    </div>
                </div>
                <div class="order-row" id="discount-row" <c:if test="0">style="display: none;"</c:if>>
                    <div>Giảm giá</div>
                    <div id="discount-amount">
                        -<fmt:formatNumber value="0" type="currency" currencySymbol="đ" maxFractionDigits="0"/>
                    </div>
                </div>
                <div class="order-total">
                    <div>Tổng cộng:</div>
                    <div id="total">
                        <fmt:formatNumber value="${subtotalAmount}" type="currency" currencySymbol="đ" maxFractionDigits="0"/>
                    </div>
                </div>
            </div>
            
         <form action="${pageContext.request.contextPath}/process-payment" method="post" id="paymentForm">
    <input type="hidden" name="paymentMethod" value="paypal">
    <input type="hidden" name="totalAmount" id="totalAmountField" value="${subtotalAmount}">
    
    <c:forEach items="${order.orderItems}" var="item">
    <input type="hidden" name="orderItemIds" value="${item.id}" />
    <input type="hidden" id="finishedFee-${item.id}" name="finishedFees" value="${item.finishedFee}">
</c:forEach>


    <button type="submit" class="btn btn-payment">TIẾN HÀNH THANH TOÁN</button>
</form>

        </div>
    </div>

    <script>
 // Áp dụng mã giảm giá thông qua AJAX
    function applyDiscount(productId, orderId) {
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
                    discountTypeText = '(Giảm theo phần trăm)';
                } else if (data.discountType === 'AMOUNTMONEY') {
                    discountTypeText = '(Giảm cố định)';
                }
                
                // Hiển thị thông tin giảm giá cho sản phẩm
                if (data.discountType === 'PERCENTAGE') {
                    discountNotification.innerHTML = 'Đã áp dụng mã giảm giá: -' + data.discountValue + '% <span class="discount-type">' + discountTypeText + '</span>';
                } else {
                    discountNotification.innerHTML = 'Đã áp dụng mã giảm giá: -' + data.formattedDiscountAmount + ' <span class="discount-type">' + discountTypeText + '</span>';
                }
                discountNotification.style.display = 'block';
                
                // Cập nhật giá sản phẩm sau khi giảm
                const priceElem = document.getElementById('price-' + productId);
                priceElem.innerHTML = '<div class="discounted-price">' + data.formattedFinishedFee + '</div>' +
                                      '<div class="strikethrough">' + data.formattedOriginalPrice + '</div>';
                document.getElementById('finishedFee-' + orderId).value = data.formattedFinishedFee;

                
                // Disable input và button áp dụng, enable button hủy
                discountInput.disabled = true;
                const applyButton = document.querySelector('.discount-input .apply-btn[onclick*="' + productId + '"]');
                const removeButton = document.querySelector('.discount-input .remove-btn[onclick*="' + productId + '"]');
                
                applyButton.disabled = true;
                applyButton.style.backgroundColor = '#aaa';
                applyButton.textContent = 'Đã áp dụng';
                
                removeButton.disabled = false;
                removeButton.style.backgroundColor = '';
                
                // Cập nhật thông tin đơn hàng
                document.getElementById('subtotal').textContent = data.formattedSubtotal;
                
                if (data.totalDiscountAmount > 0) {
                    document.getElementById('discount-row').style.display = 'flex';
                    document.getElementById('discount-amount').textContent = '-' + data.formattedTotalDiscountAmount;
                } else {
                    document.getElementById('discount-row').style.display = 'none';
                }
                
                document.getElementById('total').textContent = data.formattedTotalOrderAmount;
                
                // CẬP NHẬT GIÁ TRỊ CHO FORM THANH TOÁN
                document.getElementById('totalAmountField').value = data.totalOrderAmount;
                
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
    
    // Hủy áp dụng mã giảm giá
    function removeDiscount(productId, orderId) {
        fetch('${pageContext.request.contextPath}/remove-discount', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: 'productId=' + productId + '&orderId=' + orderId
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                // Ẩn thông báo giảm giá
                const discountNotification = document.getElementById('discount-applied-' + productId);
                discountNotification.style.display = 'none';
                
                // Cập nhật lại giá sản phẩm về giá gốc
                const priceElem = document.getElementById('price-' + productId);
                priceElem.innerHTML = '<div class="regular-price">' + data.formattedOriginalPrice + '</div>';
                document.getElementById('finishedFee-' + orderId).value = data.originalPrice;
                
                // Reset input và button
                const discountInput = document.querySelector('.discount-code[data-product-id="' + productId + '"]');
                discountInput.value = '';
                discountInput.disabled = false;
                
                const applyButton = document.querySelector('.discount-input .apply-btn[onclick*="' + productId + '"]');
                const removeButton = document.querySelector('.discount-input .remove-btn[onclick*="' + productId + '"]');
                
                applyButton.disabled = false;
                applyButton.style.backgroundColor = '';
                applyButton.textContent = 'Áp dụng';
                
                removeButton.disabled = true;
                removeButton.style.backgroundColor = '#aaa';
                
                // Cập nhật thông tin đơn hàng
                document.getElementById('subtotal').textContent = data.formattedSubtotal;
                
                if (data.totalDiscountAmount > 0) {
                    document.getElementById('discount-row').style.display = 'flex';
                    document.getElementById('discount-amount').textContent = '-' + data.formattedTotalDiscountAmount;
                } else {
                    document.getElementById('discount-row').style.display = 'none';
                }
                
                document.getElementById('total').textContent = data.formattedTotalOrderAmount;
                
                // CẬP NHẬT GIÁ TRỊ CHO FORM THANH TOÁN
                document.getElementById('totalAmountField').value = data.totalOrderAmount;
                
                showAlert('success', 'Đã hủy mã giảm giá thành công!');
            } else {
                showAlert('danger', data.message || 'Có lỗi xảy ra khi hủy mã giảm giá.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            showAlert('danger', 'Có lỗi xảy ra khi hủy mã giảm giá.');
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