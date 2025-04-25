package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Course;
import vn.iotstar.entity.CourseDetail;
import vn.iotstar.entity.Teacher;
import vn.iotstar.entity.CourseType;
import vn.iotstar.entity.Review;
import vn.iotstar.service.ICourseDetailService;
import vn.iotstar.impl.service.CourseDetailService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = {"/course/detail"})
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

        // Lấy courseId từ tham số yêu cầu, mặc định là 1 nếu không hợp lệ
        String courseIdStr = req.getParameter("courseId");
        int courseId = 1; // Mặc định courseId = 1
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

            // Xử lý các trường NULL để tránh lỗi hiển thị trên JSP
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
                : "Hầu hết công dân nào cũng biết mình có quyền và nghĩa vụ phải đóng thuế cho nhà nước, nhưng thuế là gì, vì sao phải đóng thuế, đối tượng phải đóng thuế là ai cũng như cách đóng thuế như thế nào lại rất ít người biết. Điều này đã vô tình làm ảnh hưởng đến quyền và lợi ích của công dân khi chưa ý thức được tầm quan trọng của Thuế như điều chỉnh và ổn định kinh tế, xã hội, phúc lợi xã hội của từng công dân, đảm bảo công bằng xã hội giữa các chủ thể kinh doanh, giữa các tầng lớp xã hội...");
            
            req.setAttribute("courseLearner", courseDetail.getCourseLearner() != null 
                ? courseDetail.getCourseLearner() 
                : "9732 Học viên");

            // Xử lý learner_achievements: nếu NULL thì cung cấp danh sách mặc định
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
                req.setAttribute("coursePrice", course.getCoursePrice());

                // Lấy danh sách Review
                Set<Review> reviews = course.getReview();
                if (reviews != null && !reviews.isEmpty()) {
                    req.setAttribute("reviews", reviews);
                } else {
                    req.setAttribute("reviews", null); // Không có đánh giá
                }
            } else {
                setDefaultTeacherAttributes(req);
                req.setAttribute("courseTypeName", "Tài Chính Kế Toán");
                req.setAttribute("courseName", "Tổng quan về thuế");
                req.setAttribute("coursePrice", 600000);
                req.setAttribute("reviews", null); // Không có đánh giá
            }
        } else {
            // Nếu không tìm thấy khóa học, đặt thông báo lỗi nhưng vẫn hiển thị trang viewCourseDetails.jsp
            req.setAttribute("error", "Không tìm thấy thông tin chi tiết khóa học với ID: " + courseId);

            // Đặt các giá trị mặc định để JSP vẫn hiển thị được
            req.setAttribute("courseIntroduction", "Khóa học giúp bạn có cái nhìn bao quát nhất về Thuế, hiểu được thuế và áp dụng được trong công việc, cuộc sống.");
            req.setAttribute("courseImage", "/api/placeholder/350/200");
            req.setAttribute("courseVideo", "");
            req.setAttribute("description", "Hầu hết công dân nào cũng biết mình có quyền và nghĩa vụ phải đóng thuế cho nhà nước, nhưng thuế là gì, vì sao phải đóng thuế, đối tượng phải đóng thuế là ai cũng như cách đóng thuế như thế nào lại rất ít người biết. Điều này đã vô tình làm ảnh hưởng đến quyền và lợi ích của công dân khi chưa ý thức được tầm quan trọng của Thuế như điều chỉnh và ổn định kinh tế, xã hội, phúc lợi xã hội của từng công dân, đảm bảo công bằng xã hội giữa các chủ thể kinh doanh, giữa các tầng lớp xã hội...");
            req.setAttribute("courseLearner", "9732 Học viên");
            req.setAttribute("learnerAchievements", Arrays.asList(
                "Có hiểu biết và cái nhìn tổng quan bao quát nhất về Thuế",
                "Biết cách cài đặt các ứng dụng khai thuế, các trang website hỗ trợ kê khai thuế",
                "Thành thạo sử dụng các công cụ khai thuế nhanh chóng và chính xác",
                "Biết cách quản lý thuế hiệu quả như giao dịch với cơ quan thuế, cách chuẩn bị và nộp hồ sơ đầy đủ cho tài khoản ngân hàng, cách tính thuế và nộp tiền thuế",
                "Hiểu được lệ phí môn bài trong thuế: cách tính và khai báo lệ phí môn bài, thực hành nộp lệ phí môn bài chi tiết, cụ thể và đơn giản nhất",
                "Biết cách nộp tờ khai, tiền thuế qua mạng một cách an toàn, đảm bảo: kết xuất hồ sơ khai thuế, hướng dẫn nộp tờ khai và tiền thuế qua mạng, cách lập thứ tự soát quá trình nộp thuế..."
            ));

            // Đặt giá trị mặc định cho Teacher, CourseType, Course và Review
            setDefaultTeacherAttributes(req);
            req.setAttribute("courseTypeName", "Tài Chính Kế Toán");
            req.setAttribute("courseName", "Tổng quan về thuế");
            req.setAttribute("coursePrice", 600000);
            req.setAttribute("reviews", null); // Không có đánh giá
        }

        // Chuyển hướng đến JSP để hiển thị
        req.getRequestDispatcher("/views/user/viewCourseDetails.jsp").forward(req, resp);
    }

    // Phương thức để đặt giá trị mặc định cho Teacher
    private void setDefaultTeacherAttributes(HttpServletRequest req) {
        req.setAttribute("teacherName", "Nguyễn Hoàng");
        req.setAttribute("teacherAvatar", "/api/placeholder/100/100");
        req.setAttribute("teacherDescription", "Nơi huấn luyện kế toán thực tế chất lượng, uy tín, tận tâm, trách nhiệm và chuyên nghiệp");
        req.setAttribute("teacherSocialUrl", "#");
    }
}