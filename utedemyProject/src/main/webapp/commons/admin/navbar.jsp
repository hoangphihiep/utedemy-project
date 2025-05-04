<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<link rel="stylesheet" href="/utedemyProject/views/Css/header.css">            
<div style="background-color: #003580; height: 50px; display: flex; align-items: center; padding: 0 20px; justify-content: space-between;">   
        <!-- Booking.com Text -->
        <div style="color: white; font-size: 18px; font-weight: bold;">
            Booking.com
        </div>
        <!-- Icons Section -->
        <div style="display: flex; align-items: center; gap: 15px;">     
            <!-- Flag Icon -->
			<c:if test="${not empty sessionScope.account}">
				<div style="padding: 5px 10px; background-color: #007bff; border-radius: 20px; display: inline-flex; align-items: center;">
				    <span style="color: white; font-size: 14px; font-weight: 500;">
				        ${sessionScope.account.fullname}
				    </span>
				</div>
				<div class="user-menu-container">
					<div class="dropdown">
						<c:if test="${sessionScope.account.avatarUrl != ''}">
							<c:if
								test="${sessionScope.account.avatarUrl.substring(0,5) != 'https' }">
								<c:url value="/image?fname=${sessionScope.account.avatarUrl}"
									var="imgUrl_avt"></c:url>
							</c:if>
							<c:if
								test="${sessionScope.account.avatarUrl.substring(0,5) == 'https' }">
								<c:url value="${sessionScope.account.avatarUrl}"
									var="imgUrl_avt"></c:url>
							</c:if>
						</c:if>
						<img src="${imgUrl_avt}" alt="User Avatar" class="avatar-img"
							id="avatarDropdown">

						<div class="dropdown-content" id="userDropdown">
							<a href="/learning">Vào học</a> <a href="/member">Hội viên</a> <a
								href="/activate-course">Kích hoạt khóa học</a> <a
								href="/business">Doanh nghiệp</a> <a href="/profile">Cập
								nhật hồ sơ</a> <a href="/wallet">Ví của bạn</a> <a href="/logout">Đăng
								xuất</a>
						</div>
					</div>
				</div>

			</c:if>
	</div>      
    </div>