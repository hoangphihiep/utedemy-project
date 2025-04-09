<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Phần học Excel</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }
        
        body {
            background-color: #f5f5f5;
            padding: 20px;
        }
        
        .course-section {
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 1px 3px rgba(0,0,0,0.1);
            overflow: hidden;
            max-width: 1200px;
            margin: 0 auto;
        }
        
        .section-header {
            display: flex;
            justify-content: space-between;
            padding: 15px 20px;
            border-bottom: 1px solid #e0e0e0;
            align-items: center;
        }
        
        .section-title {
            display: flex;
            align-items: center;
            font-weight: 600;
            font-size: 16px;
            color: #333;
        }
        
        .section-title::before {
            content: "❖";
            margin-right: 10px;
            color: #333;
        }
        
        .section-columns {
            display: flex;
            align-items: center;
        }
        
        .column-label {
            margin-left: 40px;
            color: #555;
            font-size: 14px;
        }
        
        .action-buttons {
            display: flex;
            gap: 10px;
        }
        
        .edit-button, .delete-button {
            border: none;
            background: none;
            font-size: 16px;
            color: #4285f4;
            cursor: pointer;
        }
        
        .delete-button {
            color: #4285f4;
        }
        
        .section-content {
            padding: 20px;
            background-color: #f9f9f9;
            border-bottom-left-radius: 10px;
            border-bottom-right-radius: 10px;
        }
        
        .action-buttons-row {
            display: flex;
            gap: 10px;
            margin-bottom: 15px;
        }
        
        .action-button {
            background-color: white;
            border: 1px solid #dadce0;
            border-radius: 4px;
            padding: 8px 15px;
            cursor: pointer;
            display: flex;
            align-items: center;
            font-size: 14px;
            color: #555;
        }
        
        .action-button::before {
            content: "+";
            margin-right: 5px;
            font-weight: bold;
        }
        
        .help-text {
            color: #666;
            font-size: 13px;
        }
    </style>
</head>
<body>
    <div class="course-section">
        <div class="section-header">
            <div class="section-title">Phần 2: Hướng Dẫn Các Phím Tắt</div>
            <div class="section-columns">
                <div class="column-label">Học thử</div>
                <div class="column-label">Trạng thái</div>
                <div class="column-label">Thời lượng</div>
            </div>
            <div class="action-buttons">
                <button class="edit-button">✏️</button>
                <button class="delete-button">🗑️</button>
            </div>
        </div>
        <div class="section-content">
            <div class="action-buttons-row">
                <button class="action-button">Bài học mới</button>
                <button class="action-button">Bài trắc nghiệm</button>
                <button class="action-button" style="padding-left: 10px; padding-right: 10px;">Thêm nhanh bài học</button>
            </div>
            <div class="help-text">
                Mỗi bài học nên có một bài trắc nghiệm để củng cố kiến thức
            </div>
        </div>
    </div>

    <script>
        // JavaScript để xử lý các sự kiện nếu cần
        document.querySelectorAll('.action-button').forEach(button => {
            button.addEventListener('click', function() {
                alert('Bạn đã nhấn: ' + this.textContent.trim());
                // Xử lý logic thêm bài học, trắc nghiệm tại đây
            });
        });
        
        document.querySelector('.edit-button').addEventListener('click', function() {
            alert('Chỉnh sửa phần học');
        });
        
        document.querySelector('.delete-button').addEventListener('click', function() {
            if(confirm('Bạn có chắc muốn xóa phần học này?')) {
                alert('Đã xóa phần học');
            }
        });
    </script>
</body>
</html>