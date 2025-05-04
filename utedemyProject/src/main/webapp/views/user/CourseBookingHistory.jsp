<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lịch sử đặt hàng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/Css/CourseBookingHistory.css">

</head>
<body>
    <div class="container">
        <h1>Lịch sử đặt hàng</h1>
        <table>
            <thead>
                <tr>
                    <th class="description-column">Mô tả</th>
                    <th>Ngày</th>
                    <th>Tên khóa học</th>
                    <th>Giá khóa học</th>
                    <th>Trạng thái</th>
                </tr>
            </thead>
            <tbody id="orderHistory">
                <!-- Lặp qua danh sách các đơn hàng -->
                <c:forEach var="order" items="${orders}">
                    <c:forEach var="item" items="${order.orderItems}">
                        <tr>
                            <td class="description-column"><span class="cart-icon">🛒</span>${item.course.courseName}</td>
                            <td>${order.orderDate}</td>
                            <td>${item.course.courseName}</td>
                            <td>${item.finishedFee} đ</td>
                            <td>
                                <span class="${order.orderStatus == 'Đã hủy' ? 'status-pending' : 'status-delivered'}">
                                    ${order.orderStatus}
                                </span>
                            </td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
