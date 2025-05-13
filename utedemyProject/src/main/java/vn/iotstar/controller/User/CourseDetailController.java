package vn.iotstar.controller.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.*;
import vn.iotstar.service.ICourseDetailService;
import vn.iotstar.impl.service.CourseDetailService;
import vn.iotstar.impl.service.FavoriteCourseService;
import vn.iotstar.impl.service.OrderService;
import vn.iotstar.service.IFavoriteCourseService;
import vn.iotstar.service.IOrderService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = {"/course/courseDetail"})
public class CourseDetailController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ICourseDetailService courseDetailService;
    private IFavoriteCourseService favoriteCourseService;
    private IOrderService orderService;

    public CourseDetailController() {
        this.courseDetailService = new CourseDetailService();
        this.favoriteCourseService = new FavoriteCourseService();
        this.orderService = new OrderService();
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
        Course course = courseDetail != null ? courseDetail.getCourse() : null;

        if (courseDetail != null && course != null) {
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
            
            int totalLessons = 0;
            if (course.getSections() != null) {
                for (Section section : course.getSections()) {
                    if (section.getLessons() != null) {
                        totalLessons += section.getLessons().size();
                    }
                }
            }

            req.setAttribute("courseLessions", totalLessons);

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
            if (course != null) {
                // Lấy danh sách Section
                Set<Section> sectionsSet = course.getSections();
                List<Section> sections = new ArrayList<>(sectionsSet);
                sections.sort(Comparator.comparingInt(Section::getId));

                // Sắp xếp Lessons và Quizs trong mỗi Section
                for (Section section : sections) {
                    List<Lesson> lessons = new ArrayList<>(section.getLessons());
                    lessons.sort(Comparator.comparingInt(Lesson::getNumberItem));
                    section.setLessons(new LinkedHashSet<>(lessons));

                    List<Quiz> quizs = new ArrayList<>(section.getQuizs());
                    quizs.sort(Comparator.comparingInt(Quiz::getId));
                    section.setQuizs(new LinkedHashSet<>(quizs));
                }
                req.setAttribute("sections", sections);

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
                    req.setAttribute("teacherAddress", teacher.getAddress() != null 
                        ? teacher.getAddress() 
                        : "Không có thông tin");
                    req.setAttribute("teacherEmail", teacher.getEmail() != null 
                        ? teacher.getEmail() 
                        : "Không có thông tin");
                    req.setAttribute("teacherPhoneNumber", teacher.getPhoneNumber() != null 
                        ? teacher.getPhoneNumber() 
                        : "Không có thông tin");
                } else {
                    setDefaultTeacherAttributes(req);
                }

                // Lấy thông tin CourseType
                CourseType courseType = course.getCourseType();
                req.setAttribute("courseTypeName", courseType != null && courseType.getCourseTypeName() != null 
                    ? courseType.getCourseTypeName() 
                    : "Tài Chính Kế Toán");
                // Lấy thông tin Course (tên khóa học, giá)
                req.setAttribute("courseName", course.getCourseName() != null 
                    ? course.getCourseName() 
                    : "Tổng quan về thuế");

                // Tính giá hiện tại và giá gốc
                double coursePrice = course.getCoursePrice();
                double originalPrice = coursePrice * 1.5;
                double currentPrice = coursePrice;
                double discountPercentage = 33;

                req.setAttribute("currentPrice", currentPrice);
                req.setAttribute("originalPrice", originalPrice);
                req.setAttribute("discountPercentage", discountPercentage);

                // Lấy danh sách Review
                Set<Review> reviews = course.getReview();
                req.setAttribute("reviews", reviews != null && !reviews.isEmpty() ? reviews : null);

                // Kiểm tra trạng thái yêu thích
                User user = (User) req.getSession().getAttribute("account");
                System.out.println("Session user: " + (user != null ? user.getId() : "null"));
                if (user != null && course != null) {
                    boolean isFavorite = favoriteCourseService.isCourseInFavorite(user, course);
                    req.setAttribute("isFavorite", isFavorite);
                }

                // Kiểm tra trạng thái đơn hàng
                if (user != null) {
                    int userId = user.getId();
                    List<Orders> userOrders = orderService.getOrdersByUserId(userId);
                    // Tạo một biến final để sử dụng trong lambda
                    final int finalCourseId = courseId; // Biến này không thay đổi, thỏa mãn "effectively final"
                    Orders completedOrder = userOrders.stream()
                            .filter(o -> "COMPLETED".equals(o.getOrderStatus()) && o.getOrderItems().stream()
                                    .anyMatch(oi -> oi.getCourse().getId() == finalCourseId))
                            .findFirst()
                            .orElse(null);
                    String orderStatus = (completedOrder != null) ? "COMPLETED" : "PROCESSING";
                    req.setAttribute("orderStatus", orderStatus);
                    System.out.println("Order status for course " + courseId + ": " + orderStatus);
                } else {
                    req.setAttribute("orderStatus", "PROCESSING");
                }
            } else {
                setDefaultCourseAttributes(req);
            }
        } else {
            setDefaultCourseAttributes(req);
        }

        req.getRequestDispatcher("/views/user/viewCourseDetails.jsp").forward(req, resp);
    }

    private void setDefaultTeacherAttributes(HttpServletRequest req) {
        req.setAttribute("teacherName", "Nguyễn Hoàng");
        req.setAttribute("teacherAvatar", "/api/placeholder/100/100");
        req.setAttribute("teacherDescription", "Nơi huấn luyện kế toán thực tế chất lượng, uy tín, tận tâm, trách nhiệm và chuyên nghiệp");
        req.setAttribute("teacherSocialUrl", "#");
        req.setAttribute("teacherAddress", "Không có thông tin");
        req.setAttribute("teacherEmail", "Không có thông tin");
        req.setAttribute("teacherPhoneNumber", "Không có thông tin");
    }
    
    private void setDefaultCourseAttributes(HttpServletRequest req) {
        req.setAttribute("error", "Không tìm thấy thông tin chi tiết khóa học với ID: " + req.getParameter("courseId"));
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
        req.setAttribute("sections", null);
        req.setAttribute("orderStatus", "PROCESSING");
    }
}