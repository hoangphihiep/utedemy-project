<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>	
<!-- BEGIN SIDEBAR -->
<div class="container-flex">
        <div class="sidebar">
            <div class="sidebar-header">
                <h3>Admin Dashboard</h3>
                <p>Quản lý hệ thống</p>
            </div>
            
            <div class="sidebar-category">Quản lý chung</div>
            
            <a href="#" class="sidebar-item active">
                <i class="fas fa-user"></i>
                <span>Tài khoản</span>
            </a>
            
            <a href="#" class="sidebar-item">
                <i class="fas fa-graduation-cap"></i>
                <span>Khóa học</span>
            </a>
            
            <a href="#" class="sidebar-item">
                <i class="fas fa-th-list"></i>
                <span>Danh mục</span>
            </a>
            
            <div class="sidebar-divider"></div>
            
            <div class="sidebar-category">Khuyến mãi & Tài chính</div>
            
            <a href="#" class="sidebar-item">
                <i class="fas fa-gift"></i>
                <span>Khuyến mãi</span>
                <span class="badge">5</span>
            </a>
            
            <a href="#" class="sidebar-item">
                <i class="fas fa-chart-line"></i>
                <span>Doanh thu</span>
            </a>
        </div>
        
        <div class="main-section">
            <!-- Nội dung chính sẽ được đặt ở đây -->
            <div style="padding: 20px; background-color: white; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.05);">
                <h2 style="margin-bottom: 20px; color: #333;">Bảng điều khiển quản trị</h2>
                <p style="color: #666;">Chọn một mục từ sidebar để xem nội dung</p>
            </div>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.min.js"></script>