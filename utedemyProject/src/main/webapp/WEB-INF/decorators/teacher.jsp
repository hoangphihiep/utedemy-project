<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<title>Bảng điều khiển khóa học trực tuyến</title>
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
            background-color: #003552;
            color: white;
            padding: 60px 200px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        
        .instructor-info {
            display: flex;
            flex-direction: column;
        }
        
        .instructor-name {
            font-size: 36px;
            font-weight: bold;
            margin-bottom: 10px;
        }
        
        .instructor-id {
            font-size: 14px;
            color: #cccccc;
        }
        
        .support-info {
            text-align: left;
            line-height: 1.6;
        }
        
        .support-info p {
            font-size: 14px;
        }
        
        .nav-menu {
            background-color: #003552;
            padding: 0 200px;
            border-top: 1px solid rgba(255, 255, 255, 0.1);
        }
        
        .nav-menu ul {
            list-style: none;
            display: flex;
        }
        
        .nav-menu li {
            padding: 15px 20px;
            cursor: pointer;
            color: white;
            position: relative;
        }
        
        .nav-menu li.active {
            border-bottom: 3px solid white;
            font-weight: bold;
        }
        
        .content {
            padding: 40px 200px;
        }
        
        .content-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
        }
        
        .content-title {
            font-size: 24px;
            font-weight: bold;
        }
        
        .search-bar {
            display: flex;
            margin-right: 300px;
        }
        
        .search-input {
            padding: 10px 15px;
            border: 1px solid #ccc;
            border-radius: 4px 0 0 4px;
            width: 250px;
        }
        
        .search-button {
            background-color: #1a56db;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 0 4px 4px 0;
            cursor: pointer;
        }
        
        .search-button i {
            margin-right: 5px;
        }
        
        .action-buttons {
            display: flex;
        }
        
        .btn {
            padding: 10px 15px;
            border-radius: 4px;
            margin-left: 10px;
            cursor: pointer;
            display: flex;
            align-items: center;
        }
        
        .btn-outline {
            border: 1px solid #1a56db;
            color: #1a56db;
            background-color: white;
        }
        
        .btn-primary {
            background-color: #1a56db;
            color: white;
            border: none;
        }
        
        .course-card {
            background-color: #fff;
            border-radius: 4px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            display: flex;
            overflow: hidden;
        }
        
        .course-image {
            width: 260px;
            height: 150px;
            background-color: #eee;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #999;
        }
        
        .course-info {
            padding: 20px;
            flex-grow: 1;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }
        
        .course-title {
            font-size: 16px;
            font-weight: bold;
            margin-bottom: 5px;
        }
        
        .course-tag {
            display: inline-block;
            background-color: #b91c1c;
            color: white;
            font-size: 12px;
            padding: 4px 8px;
            border-radius: 2px;
            margin-bottom: 20px;
            width: fit-content;
        }
        
        .course-bottom {
            display: flex;
            align-items: center;
            margin-top: auto;
        }
        
        .progress-section {
            display: flex;
            align-items: center;
            flex: 1;
        }
        
        .progress-label {
            font-size: 14px;
            white-space: nowrap;
        }
        
        .progress-bar {
            height: 8px;
            background-color: #e5e7eb;
            width: 300px;
            border-radius: 4px;
            overflow: hidden;
            margin: 0 15px;
        }
        
        .progress-fill {
            height: 100%;
            width: 15%;
            background-color: #1a56db;
        }
        
        .course-actions {
            display: flex;
            align-items: center;
        }
        
        .course-actions a {
            color: #1a56db;
            text-decoration: none;
            margin-left: 20px;
            font-size: 14px;
        }
    </style>
</head>
<body>
	<div class="container">
		<div class="main-content">
			<sitemesh:write property="body" />
		</div>
	</div>
	
	
	<script>
        // JavaScript functionality can be added here
        document.addEventListener('DOMContentLoaded', function() {
            // Menu item activation
            const menuItems = document.querySelectorAll('.nav-menu li');
            menuItems.forEach(item => {
                item.addEventListener('click', function() {
                    menuItems.forEach(i => i.classList.remove('active'));
                    this.classList.add('active');
                });
            });
            
            // Search functionality
            const searchButton = document.querySelector('.search-button');
            const searchInput = document.querySelector('.search-input');
            
            searchButton.addEventListener('click', function() {
                const searchTerm = searchInput.value.trim();
                if (searchTerm) {
                    // Here you would typically perform a search
                    console.log('Searching for:', searchTerm);
                    // You could then update the UI or redirect as needed
                }
            });
        });
    </script>
</body>
</html>