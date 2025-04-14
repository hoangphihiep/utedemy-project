<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Roboto', sans-serif;
        }

        body {
            background-color: #f5f7fa;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        .container-flex {
            display: flex;
            width: 100%;
            min-height: 100vh;
        }

        /* Sidebar */
         .sidebar {
            width: 250px;
            background: linear-gradient(180deg, #2c3e50 0%, #1a2a38 100%);
            height: 100vh;
            color: #fff;
            position: fixed;
            left: 0;
            top: 0;
            overflow-y: auto;
            box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
            transition: all 0.3s ease;
            z-index: 100;
        }


        .sidebar-header {
            padding: 20px;
            text-align: center;
            border-bottom: 1px solid rgba(255, 255, 255, 0.1);
        }

        .sidebar-header h3 {
            color: #fff;
            font-size: 18px;
            margin-bottom: 5px;
        }

        .sidebar-header p {
            color: #6c8aab;
            font-size: 12px;
        }

        /* Sidebar item styling */
        .sidebar-item {
            padding: 15px 20px;
            display: flex;
            align-items: center;
            color: #a5b9cc;
            text-decoration: none;
            font-size: 14px;
            transition: all 0.3s;
            border-left: 3px solid transparent;
            margin: 5px 0;
        }

        .sidebar-item i {
            width: 20px;
            height: 20px;
            margin-right: 15px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 16px;
            transition: all 0.3s;
        }

        .sidebar-item span {
            transition: all 0.3s;
        }

        .sidebar-item:hover {
            background-color: rgba(255, 255, 255, 0.05);
            color: #fff;
            border-left: 3px solid #3498db;
            transform: translateX(5px);
        }

        .sidebar-item:hover i {
            color: #3498db;
            transform: scale(1.1);
        }

        .sidebar-item.active {
            background-color: rgba(52, 152, 219, 0.2);
            color: #fff;
            border-left: 3px solid #3498db;
        }
        
        .sidebar-item.active i {
            color: #3498db;
        }

        /* Divider for sidebar categories */
        .sidebar-divider {
            height: 1px;
            background-color: rgba(255, 255, 255, 0.1);
            margin: 10px 15px;
        }

        .sidebar-category {
            padding: 10px 20px;
            font-size: 12px;
            text-transform: uppercase;
            color: #6c8aab;
            letter-spacing: 1px;
        }

        /* Badge for notification or counts */
        .badge {
            background-color: #3498db;
            color: white;
            font-size: 10px;
            padding: 2px 6px;
            border-radius: 10px;
            margin-left: auto;
        }

        /* Main content area */
        .main-section {
            flex: 1;
            margin-left: 250px;
            padding: 20px;
            transition: all 0.3s ease;
        }

        /* Top navbar */
        .navbar {
            background-color: #1a4f8e;
            color: white;
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .navbar-brand {
            font-size: 20px;
            font-weight: bold;
            display: flex;
            align-items: center;
        }

        .navbar-brand i {
            margin-right: 10px;
        }

        .navbar-menu {
            display: flex;
            align-items: center;
        }

        .navbar-item {
            margin-left: 20px;
            position: relative;
            cursor: pointer;
        }

        /* Dashboard content */
        .dashboard-content {
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
            margin-top: 20px;
        }

        .dashboard-title {
            font-size: 24px;
            margin-bottom: 20px;
            color: #333;
        }

        .dashboard-instruction {
            color: #666;
            font-size: 16px;
            line-height: 1.5;
        }

        /* Responsive */
        @media (max-width: 768px) {
            .sidebar {
                width: 70px;
            }
            
            .sidebar-item span, 
            .sidebar-header h3, 
            .sidebar-header p, 
            .sidebar-category {
                display: none;
            }
            
            .sidebar-item {
                padding: 15px;
                justify-content: center;
            }
            
            .sidebar-item i {
                margin-right: 0;
            }
            
            .main-section {
                margin-left: 70px;
            }
        }
    </style>
</head>
<body>
    <%@ include file="/commons/admin/navbar.jsp"%>
	<%@ include file="/commons/admin/sidebar.jsp" %>
        
        <!-- Main Content Area -->
        <div class="main-section">
         
        </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.min.js"></script>
    <script>
        function toggleDropdown(event) {
            event.preventDefault();
            const dropdown = document.getElementById('notificationDropdown');
            // Toggle visibility
            if (dropdown.style.display === 'none' || dropdown.style.display === '') {
                dropdown.style.display = 'block';
            } else {
                dropdown.style.display = 'none';
            }
        }

        // Đóng dropdown nếu click ra ngoài
        document.addEventListener('click', function(event) {
            const dropdown = document.getElementById('notificationDropdown');
            if (dropdown && !event.target.closest('.dropdown')) {
                dropdown.style.display = 'none';
            }
        });
    </script>
</body>
</html>