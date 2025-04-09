<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi Tiết Khoá Học</title>
    <link rel="stylesheet" href="CourseDetail.css">
</head>
<body>
    <header>
        <h1>Unica</h1>
        <input type="text" placeholder="Tìm khoá học, giảng viên...">
        <button>Tìm kiếm</button>
    </header>
    
    <main>
        <section class="course-detail">
            <h2><%= request.getAttribute("courseTitle") %></h2>
            <p><%= request.getAttribute("courseDescription") %></p>
            <p><strong><%= request.getAttribute("courseRating") %> ⭐ (<%= request.getAttribute("courseReviews") %> đánh giá) - <%= request.getAttribute("courseStudents") %> học viên</strong></p>
            <p>Giảng viên: <a href="#"><%= request.getAttribute("instructor") %></a></p>
        </section>
        
        <section class="course-benefits">
            <h3>Bạn sẽ học được</h3>
            <ul>
                <li>Mở rộng vốn từ vựng tiếng Hàn</li>
                <li>Hiểu và nắm vững ngữ pháp trong mỗi bài</li>
                <li>Có kỹ năng tự đặt câu</li>
                <li>Luyện phát âm chuẩn giọng Seoul</li>
                <li>Phản ứng nhanh khi giao tiếp</li>
            </ul>
        </section>
        
        <aside class="course-purchase">
            <h3><%= request.getAttribute("coursePrice") %>đ <del><%= request.getAttribute("courseOldPrice") %>đ</del> (Giảm <%= request.getAttribute("courseDiscount") %>%)</h3>
            <button>Thêm vào giỏ hàng</button>
            <button>Mua ngay</button>
            <p>Thời lượng: <%= request.getAttribute("courseDuration") %> - <%= request.getAttribute("courseLessons") %> bài giảng</p>
            <p>Sở hữu khoá học trọn đời</p>
            <p>Cấp chứng nhận hoàn thành</p>
            <input type="text" placeholder="Nhập mã giảm giá">
            <button>Áp dụng</button>
        </aside>
    </main>
</body>
</html>
