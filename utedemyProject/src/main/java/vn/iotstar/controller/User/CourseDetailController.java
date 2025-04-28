package vn.iotstar.controller.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.*;
import vn.iotstar.service.ICourseDetailService;
import vn.iotstar.impl.service.CourseDetailService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = {"/course/courseDetail"})
public class CourseDetailController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ICourseDetailService courseDetailService;

    public CourseDetailController() {
        this.courseDetailService = new CourseDetailService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // Lấy courseId từ tham số yêu cầu
        String courseIdStr = req.getParameter("courseId");
        int courseId = 1; // Mặc định courseId = 1 nếu không có giá trị hoặc không hợp lệ
        if (courseIdStr != null && !courseIdStr.isEmpty()) {
            try {
                courseId = Integer.parseInt(courseIdStr);
            } catch (NumberFormatException e) {
                req.setAttribute("warning", "ID khóa học không hợp lệ, sử dụng ID mặc định: 1.");
            }
        }

        // Lấy thông tin chi tiết khóa học từ Service
        CourseDetail courseDetail = courseDetailService.getCourseDetailById(courseId);

        if (courseDetail != null) {
            // Đặt dữ liệu vào request để JSP sử dụng
            req.setAttribute("courseDetail", courseDetail);

            req.setAttribute("courseIntroduction", courseDetail.getCourseIntroduction() != null 
                ? courseDetail.getCourseIntroduction() 
                : "Khóa học giúp bạn có cái nhìn bao quát nhất về Thuế, hiểu được thuế và áp dụng được trong công việc, cuộc sống.");

            req.setAttribute("courseImage", courseDetail.getCourseImage() != null 
                ? courseDetail.getCourseImage() 
                : "/api/placeholder/350/200");

            req.setAttribute("courseVideo", courseDetail.getCourseVideo() != null 
                ? courseDetail.getCourseVideo() 
                : "https://www.youtube.com/watch?v=Gq6AMfLFSD4");

            req.setAttribute("description", courseDetail.getDescription() != null 
                ? courseDetail.getDescription() 
                : "Hầu hết công dân nào cũng biết mình có quyền và nghĩa vụ phải đóng thuế cho nhà nước, nhưng thuế là gì, vì sao phải đóng thuế, đối tượng phải đóng thuế là ai cũng như cách đóng thuế như thế nào lại rất ít người biết...");

            req.setAttribute("courseLearner", courseDetail.getCourseLearner() != null 
                ? courseDetail.getCourseLearner() 
                : "9732 Học viên");

            String learnerAchievementsStr = courseDetail.getLearnerAchievements();
            List<String> learnerAchievements;
            if (learnerAchievementsStr != null && !learnerAchievementsStr.isEmpty()) {
                learnerAchievements = Arrays.asList(learnerAchievementsStr.split(";"));
            } else {
                learnerAchievements = Arrays.asList(
                    "Có hiểu biết và cái nhìn tổng quan bao quát nhất về Thuế",
                    "Biết cách cài đặt các ứng dụng khai thuế, các trang website hỗ trợ kê khai thuế",
                    "Thành thạo sử dụng các công cụ khai thuế nhanh chóng và chính xác",
                    "Biết cách quản lý thuế hiệu quả như giao dịch với cơ quan thuế, cách chuẩn bị và nộp hồ sơ đầy đủ cho tài khoản ngân hàng, cách tính thuế và nộp tiền thuế",
                    "Hiểu được lệ phí môn bài trong thuế: cách tính và khai báo lệ phí môn bài, thực hành nộp lệ phí môn bài chi tiết, cụ thể và đơn giản nhất",
                    "Biết cách nộp tờ khai, tiền thuế qua mạng một cách an toàn, đảm bảo: kết xuất hồ sơ khai thuế, hướng dẫn nộp tờ khai và tiền thuế qua mạng, cách lập thứ tự soát quá trình nộp thuế..."
                );
            }
            req.setAttribute("learnerAchievements", learnerAchievements);

            // Lấy thông tin từ Course
            Course course = courseDetail.getCourse();
            if (course != null) {
                // Lấy thông tin Teacher
                Teacher teacher = course.getTeacher();
                if (teacher != null) {
                    req.setAttribute("teacher", teacher);
                    req.setAttribute("teacherName", teacher.getFullname() != null 
                        ? teacher.getFullname() 
                        : "Nguyễn Hoàng");
                    req.setAttribute("teacherAvatar", teacher.getAvatarUrl() != null 
                        ? teacher.getAvatarUrl() 
                        : "/api/placeholder/100/100");
                    req.setAttribute("teacherDescription", teacher.getDescription() != null 
                        ? teacher.getDescription() 
                        : "Nơi huấn luyện kế toán thực tế chất lượng, uy tín, tận tâm, trách nhiệm và chuyên nghiệp");
                    req.setAttribute("teacherSocialUrl", teacher.getSocialUrl() != null 
                        ? teacher.getSocialUrl() 
                        : "#");
                } else {
                    setDefaultTeacherAttributes(req);
                }

                // Lấy thông tin CourseType
                CourseType courseType = course.getCourseType();
                if (courseType != null) {
                    req.setAttribute("courseTypeName", courseType.getCourseTypeName() != null 
                        ? courseType.getCourseTypeName() 
                        : "Tài Chính Kế Toán");
                } else {
                    req.setAttribute("courseTypeName", "Tài Chính Kế Toán");
                }

                // Lấy thông tin Course (tên khóa học, giá)
                req.setAttribute("courseName", course.getCourseName() != null 
                    ? course.getCourseName() 
                    : "Tổng quan về thuế");

                // Tính giá hiện tại và giá gốc dựa trên logic của "Siêu Ưu Đãi Hôm Nay"
                double coursePrice = course.getCoursePrice();
                double originalPrice = coursePrice * 1.5; // Giá gốc hiển thị (nhân 1.5)
                double currentPrice = coursePrice; // Giá hiện tại (giả sử không có giảm giá mặc định)
                double discountPercentage = 0;

                // Giả sử logic giảm giá: Nếu khóa học thuộc "Siêu Ưu Đãi Hôm Nay", giảm 33% (giá hiện tại = 2/3 giá gốc thực tế)
                // Ở đây, ta giả sử coursePrice đã là giá sau khi giảm (300.000đ), nên tính ngược lại giá gốc thực tế
                double realOriginalPrice = coursePrice * 1.5; // 300.000 * 1.5 = 450.000
                discountPercentage = 33; // Giảm 33% (tương ứng với 300.000/450.000)
                currentPrice = coursePrice; // 300.000đ

                req.setAttribute("currentPrice", currentPrice);
                req.setAttribute("originalPrice", realOriginalPrice);
                req.setAttribute("discountPercentage", discountPercentage);

                // Lấy danh sách Review
                Set<Review> reviews = course.getReview();
                if (reviews != null && !reviews.isEmpty()) {
                    req.setAttribute("reviews", reviews);
                } else {
                    req.setAttribute("reviews", null);
                }
            } else {
                setDefaultTeacherAttributes(req);
                req.setAttribute("courseTypeName", "Tài Chính Kế Toán");
                req.setAttribute("courseName", "Tổng quan về thuế");
                req.setAttribute("currentPrice", 400000); // Giả sử giá hiện tại mặc định
                req.setAttribute("originalPrice", 600000); // Giả sử giá gốc mặc định
                req.setAttribute("discountPercentage", 33);
                req.setAttribute("reviews", null);
            }
        } else {
            req.setAttribute("error", "Không tìm thấy thông tin chi tiết khóa học với ID: " + courseId);
            req.setAttribute("courseIntroduction", "Khóa học giúp bạn có cái nhìn bao quát nhất về Thuế, hiểu được thuế và áp dụng được trong công việc, cuộc sống.");
            req.setAttribute("courseImage", "/api/placeholder/350/200");
            req.setAttribute("courseVideo", "");
            req.setAttribute("description", "Hầu hết công dân nào cũng biết mình có quyền và nghĩa vụ phải đóng thuế cho nhà nước, nhưng thuế là gì, vì sao phải đóng thuế, đối tượng phải đóng thuế là ai cũng như cách đóng thuế như thế nào lại rất ít người biết...");
            req.setAttribute("courseLearner", "9732 Học viên");
            req.setAttribute("learnerAchievements", Arrays.asList(
                "Có hiểu biết và cái nhìn tổng quan bao quát nhất về Thuế",
                "Biết cách cài đặt các ứng dụng khai thuế, các trang website hỗ trợ kê khai thuế",
                "Thành thạo sử dụng các công cụ khai thuế nhanh chóng và chính xác",
                "Biết cách quản lý thuế hiệu quả như giao dịch với cơ quan thuế, cách chuẩn bị và nộp hồ sơ đầy đủ cho tài khoản ngân hàng, cách tính thuế và nộp tiền thuế",
                "Hiểu được lệ phí môn bài trong thuế: cách tính và khai báo lệ phí môn bài, thực hành nộp lệ phí môn bài chi tiết, cụ thể và đơn giản nhất",
                "Biết cách nộp tờ khai, tiền thuế qua mạng một cách an toàn, đảm bảo: kết xuất hồ sơ khai thuế, hướng dẫn nộp tờ khai và tiền thuế qua mạng, cách lập thứ tự soát quá trình nộp thuế..."
            ));

            setDefaultTeacherAttributes(req);
            req.setAttribute("courseTypeName", "Tài Chính Kế Toán");
            req.setAttribute("courseName", "Tổng quan về thuế");
            req.setAttribute("currentPrice", 400000);
            req.setAttribute("originalPrice", 600000);
            req.setAttribute("discountPercentage", 33);
            req.setAttribute("reviews", null);
        }

        req.getRequestDispatcher("/views/user/viewCourseDetails.jsp").forward(req, resp);
    }

    private void setDefaultTeacherAttributes(HttpServletRequest req) {
        req.setAttribute("teacherName", "Nguyễn Hoàng");
        req.setAttribute("teacherAvatar", "/api/placeholder/100/100");
        req.setAttribute("teacherDescription", "Nơi huấn luyện kế toán thực tế chất lượng, uy tín, tận tâm, trách nhiệm và chuyên nghiệp");
        req.setAttribute("teacherSocialUrl", "#");
    }
}