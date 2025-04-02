<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
            justify-content: space-between;
        }
        
        .header-left {
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
        }
        
        .header-status {
            display: flex;
            gap: 10px;
            align-items: center;
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
        
        .save-button {
            background-color: white;
            color: #333;
            border: none;
            border-radius: 4px;
            padding: 6px 15px;
            cursor: pointer;
            font-weight: bold;
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
        
        .content-box {
            background-color: white;
            border-radius: 5px;
            box-shadow: 0 1px 2px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }
        
        .content-header {
            padding: 15px 20px;
            border-bottom: 1px solid #e0e0e0;
            font-size: 18px;
            font-weight: bold;
        }
        
        .content-body {
            padding: 20px;
        }
        
        .objective-intro {
            margin-bottom: 20px;
        }
        
        .objective-hint {
            color: #666;
            font-size: 14px;
            margin-bottom: 20px;
        }
        
        .objective-item {
            display: flex;
            align-items: center;
            border: 1px solid #e0e0e0;
            border-radius: 4px;
            padding: 10px 15px;
            margin-bottom: 10px;
            justify-content: space-between;
        }
        
        .objective-text {
            flex-grow: 1;
            color: #666;
        }
        
        .counter {
            color: #999;
            margin-right: 10px;
        }
        
        .delete-button {
            color: #999;
            cursor: pointer;
            font-size: 16px;
        }
        
        .add-button {
            display: flex;
            align-items: center;
            color: #4285f4;
            cursor: pointer;
            font-size: 14px;
            margin-top: 10px;
        }
        
        .add-icon {
            margin-right: 5px;
            font-size: 18px;
        }
        
        .section-title {
            font-size: 16px;
            font-weight: bold;
            margin-bottom: 15px;
            margin-top: 30px;
        }
        
        .text-editor {
            border: 1px solid #e0e0e0;
            border-radius: 4px;
        }
        
        .editor-toolbar {
            padding: 10px;
            border-bottom: 1px solid #e0e0e0;
            display: flex;
        }
        
        .toolbar-button {
            background: none;
            border: none;
            font-size: 16px;
            margin-right: 15px;
            cursor: pointer;
        }
        
        .editor-content {
            padding: 15px;
            min-height: 100px;
            color: #999;
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
        
        .add-lesson-button {
            display: inline-flex;
            align-items: center;
            background-color: #f0f0f0;
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 8px 15px;
            font-size: 14px;
            cursor: pointer;
            margin-top: 20px;
            margin-left: 20px;
        }
        .lesson-list-container {
            margin-top: 20px;
            min-height: 300px;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        
        .empty-state {
            color: #999;
            font-style: italic;
            text-align: center;
        }
        
        /* Modal styles */
        .modal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 1000;
            justify-content: center;
            align-items: center;
        }
        
        .modal.show {
            display: flex;
        }
        
        .modal-content {
            background-color: white;
            border-radius: 8px;
            width: 100%;
            max-width: 600px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        
        .modal-header {
            padding: 15px 20px;
            border-bottom: 1px solid #eee;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        
        .modal-title {
            font-size: 18px;
            font-weight: 500;
        }
        
        .close-button {
            background: none;
            border: none;
            font-size: 22px;
            cursor: pointer;
            color: #999;
        }
        
        .modal-body {
            padding: 20px;
        }
        
       	.form-group {
            margin-bottom: 20px;
        }
        
        .form-label {
            display: block;
            margin-bottom: 8px;
            font-weight: 500;
        }
        
        .required {
            color: red;
            margin-left: 3px;
        }
        
        .form-input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 15px;
        }
        
        .form-input:focus {
            outline: none;
            border-color: #2271b1;
        }
        
        .char-count {
            text-align: right;
            color: #999;
            font-size: 14px;
            margin-top: 5px;
        }
        
        .modal-footer {
            padding: 15px 20px;
            display: flex;
            justify-content: flex-end;
            gap: 10px;
        }
        
        .btn-cancel {
            background-color: #f0f0f0;
            border: 1px solid #ddd;
            color: #333;
            padding: 8px 15px;
            border-radius: 4px;
            cursor: pointer;
        }
        
        .btn-save {
            background-color: #2271b1;
            color: white;
            border: none;
            padding: 8px 20px;
            border-radius: 4px;
            cursor: pointer;
        }
        
        .info-text {
		    color: #666;
		    line-height: 1.6;
		    margin-bottom: 20px;
		    display: block; /* Đảm bảo nó là block element */
		    width: 100%; /* Đảm bảo nó không bị co lại */
		    text-align: left; /* Điều chỉnh nếu bị lệch */
		}
        
        .info-text a {
            color: #2271b1;
            text-decoration: none;
        }
        
        .form-hint {
            font-size: 14px;
            color: #777;
            margin-top: 5px;
        }
        .dropdown {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
            background-color: white;
            cursor: pointer;
        }
        
        /* Additional styles for the new sections */
	    .image-upload-container, .video-upload-container {
	        display: flex;
	        gap: 20px;
	        margin-top: 10px;
	    }
	    
	    .image-preview, .video-preview {
	        width: 320px;
	        height: 180px;
	        background-color: #f0f0f0;
	        border: 1px solid #ddd;
	        display: flex;
	        align-items: center;
	        justify-content: center;
	        overflow: hidden;
	    }
	    
	    .image-preview img {
	        max-width: 100%;
	        max-height: 100%;
	    }
	    
	    .video-placeholder {
	        width: 100%;
	        height: 100%;
	        background-color: #e0e0e0;
	    }
	    
	    .image-upload-info, .video-upload-info {
	        flex: 1;
	    }
	    
	    .image-upload-info p, .video-upload-info p {
	        margin-bottom: 15px;
	        font-size: 14px;
	        color: #666;
	        line-height: 1.5;
	    }
	    
	    .upload-controls {
	        display: flex;
	        align-items: center;
	        gap: 10px;
	    }
	    
	    .upload-button {
	        background-color: #2271b1;
	        color: white;
	        border: none;
	        padding: 8px 15px;
	        border-radius: 4px;
	        cursor: pointer;
	    }
        .file-status {
	        font-size: 14px;
	        color: #666;
	    }
	    
	    .form-input-container {
	        width: 100%;
	    }
	    
	    .form-row {
            display: flex;
            gap: 20px;
            margin-bottom: 20px;
        }
        
        .form-column {
            flex: 1;
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
            	<a href="/utedemyProject/user/addTarget" class="sidebar-item" style="display: inline-block; text-decoration: none;">
			        <span>Mục tiêu khóa học</span>
			    </a>
			    <a href="/utedemyProject/user/adBasicInformation" class="sidebar-item" style="display: inline-block; text-decoration: none;">
			        <span>Thông tin cơ bản</span>
			    </a>
			    <a href="/utedemyProject/user/add" class="sidebar-item" style="display: inline-block; text-decoration: none;">
			        <span>Danh sách bài học</span>
			    </a>
			    <a href="/utedemyProject/user/addTarget" class="sidebar-item" style="display: inline-block; text-decoration: none;">
			        <span>Tài liệu và quà tặng</span>
			    </a>
			    <a href="/utedemyProject/user/addTarget" class="sidebar-item" style="display: inline-block; text-decoration: none;">
			        <span>Giá khóa học</span>
			    </a>
            </div>
            <button class="submit-button">Gửi duyệt</button>
        </div>
        <div class="main-content">
        	<sitemesh:write property = "body" />
        </div>
    </div>
</body>
</html>