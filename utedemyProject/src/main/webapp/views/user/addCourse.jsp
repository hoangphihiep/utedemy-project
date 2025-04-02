<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thành Thạo Excel Từ Cơ Bản Đến Nâng Cao</title>
    <style>
        * {
		    margin: 0;
		    padding: 0;
		    box-sizing: border-box;
		    font-family: Arial, sans-serif;
		}
        
        body {
            background-color: #f5f5f5;
        }
        
        .header {
            background-color: #0c3b4f;
            color: white;
            padding: 15px 20px;
            display: flex;
            align-items: center;
        }
        
        .back-button {
            color: white;
            margin-right: 15px;
            text-decoration: none;
            font-size: 18px;
        }
        
        .header-title {
            font-size: 16px;
            flex-grow: 1;
        }
        
        .status-tags {
            display: flex;
            gap: 10px;
        }
        
        .status-tag {
            padding: 5px 10px;
            border-radius: 4px;
            font-size: 14px;
        }
        
        .new-tag {
            background-color: #648de5;
        }
        
        .updating-tag {
            background-color: #479e6d;
        }
        
        .container {
            display: flex;
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        
        .sidebar {
            width: 250px;
            margin-right: 20px;
        }
        
        .sidebar-menu {
            background-color: white;
            border-radius: 5px;
            overflow: hidden;
        }
        
        .sidebar-item {
            padding: 15px;
            border-left: 3px solid transparent;
            cursor: pointer;
        }
        
        .sidebar-item:hover {
            background-color: #f0f0f0;
        }
        
        .sidebar-item.active {
            border-left-color: #4285f4;
            background-color: #e8f0fe;
        }
        
        .submit-button {
            background-color: #1a73e8;
            color: white;
            border: none;
            border-radius: 4px;
            padding: 15px;
            width: 100%;
            margin-top: 20px;
            cursor: pointer;
            font-weight: bold;
        }
        
        .submit-button:hover {
            background-color: #1765cc;
        }
        
        .main-content {
            flex-grow: 1;
        }
        
        .content-header {
            margin-bottom: 20px;
        }
        
        .content-title {
            font-size: 20px;
            font-weight: bold;
            margin-bottom: 20px;
        }
        
        .course-section {
            background-color: white;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 1px 3px rgba(0,0,0,0.1);
        }
        
        .section-header {
            display: flex;
            justify-content: space-between;
            padding-bottom: 15px;
            border-bottom: 1px solid #e0e0e0;
            margin-bottom: 15px;
        }
        
        .section-title {
            font-weight: bold;
            display: flex;
            align-items: center;
        }
        
        .section-title span::before {
            content: "❖";
            margin-right: 10px;
            color: #666;
        }
        
        .section-columns {
            display: grid;
            grid-template-columns: 2fr 1fr 1fr 1fr;
            text-align: center;
        }
        
        .section-columns div:first-child {
            text-align: left;
        }
        
        .section-actions {
            display: flex;
            gap: 10px;
        }
        
        .action-icon {
            color: #4285f4;
            cursor: pointer;
        }
        
        .lesson-actions {
            padding: 15px 0;
        }
        
        .lesson-button {
            background-color: #f1f3f4;
            border: 1px solid #dadce0;
            border-radius: 4px;
            padding: 8px 15px;
            margin-right: 10px;
            cursor: pointer;
            display: inline-flex;
            align-items: center;
        }
        
        .lesson-button::before {
            content: "+";
            margin-right: 5px;
        }
        
        .lesson-info {
            color: #666;
            font-size: 14px;
            margin-top: 5px;
        }
        
        .add-section-button {
            background-color: #f1f3f4;
            border: 1px solid #dadce0;
            border-radius: 4px;
            padding: 8px 15px;
            cursor: pointer;
            display: inline-flex;
            align-items: center;
        }
        
        .add-section-button::before {
            content: "+";
            margin-right: 5px;
        }
    </style>
</head>
<body>
    <div class="header">
        <div class="header-left">
            <a href="#" class="back-button">&#9664;</a>
            <div class="header-title">Quay lại danh sách khóa học &nbsp;|&nbsp; Thành Thạo Excel Từ Cơ Bản Đến Nâng Cao</div>
        </div>
        <div class="header-status">
            <div class="status-tag new-tag">Mới</div>
            <div class="status-tag updating-tag">Đang cập nhật</div>
            <button class="save-button">Lưu</button>
        </div>
    </div>
    
    <div class="container">
        <div class="sidebar">
            <div class="sidebar-menu">
                <div class="sidebar-item active">Mục tiêu khóa học</div>
                <div class="sidebar-item">Thông tin cơ bản</div>
                <div class="sidebar-item">Danh sách bài học</div>
                <div class="sidebar-item">Tài liệu và quà tặng</div>
                <div class="sidebar-item">Giá khóa học</div>
            </div>
            <button class="submit-button">Gửi duyệt</button>
        </div>
        
        <div class="main-content">
            <div class="content-header">
                <h1 class="content-title">Danh sách bài học</h1>
            </div>
            
            <div class="course-section">
                <div class="section-header">
                    <div class="section-title">
                        <span>Phần 1: Tổng Quan Về Excel</span>
                    </div>
                    <div class="section-columns">
                        <div>Học thử</div>
                        <div>Trạng thái</div>
                        <div>Thời lượng</div>
                    </div>
                    <div class="section-actions">
                        <div class="action-icon">✎</div>
                        <div class="action-icon">🗑</div>
                    </div>
                </div>
                
                <div class="lesson-actions">
                    <button class="lesson-button">Bài học mới</button>
                    <button class="lesson-button">Bài trắc nghiệm</button>
                    <button class="lesson-button">Thêm nhanh bài học</button>
                </div>
                
                <div class="lesson-info">
                    Mỗi bài học nên có một bài trắc nghiệm để cung cố kiến thức
                </div>
            </div>     
            
            <button class="add-section-button">Phần học mới</button>
        </div>
    </div>
</body>
</html>