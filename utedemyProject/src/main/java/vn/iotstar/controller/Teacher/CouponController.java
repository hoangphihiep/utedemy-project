package vn.iotstar.controller.Teacher;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.Course;
import vn.iotstar.entity.Discount;
import vn.iotstar.entity.Notification;
import vn.iotstar.entity.User;
import vn.iotstar.impl.service.CourseService;
import vn.iotstar.impl.service.DiscountService;
import vn.iotstar.impl.service.NotificationService;
import vn.iotstar.service.ICourseService;
import vn.iotstar.service.IDiscountService;
import vn.iotstar.service.INotificationService;

@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1 MB
	    maxFileSize = 1024 * 1024 * 10,  // 10 MB
	    maxRequestSize = 1024 * 1024 * 15 // 15 MB
	)
@WebServlet(urlPatterns = {"/teacher/addCoupon", "/teacher/editCoupon"})
public class CouponController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public ICourseService courseService = new CourseService();
	public INotificationService notificationService = new NotificationService();
	public IDiscountService discountService = new DiscountService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		if (url.contains("/teacher/addCoupon")) 
		{
			User user = (User)session.getAttribute("account");
		    req.setAttribute("fullname", user.getFullname());
		    
		    // Lấy danh sách khóa học của giáo viên hiện tại
		    List<Course> teacherCourses = courseService.findByIdTeacher(user);
		    
		    List<Notification> listNotification = notificationService.findByIdUser(user.getId());
		    int soLuongThongBao = listNotification.size();
		    
		    req.setAttribute("teacherCourses", teacherCourses);
		    req.setAttribute("slthongbao", soLuongThongBao);
		    req.setAttribute("listthongbao", listNotification);
		    req.getRequestDispatcher("/views/teacher/addDiscount.jsp").forward(req, resp);
		}
		if (url.contains("/teacher/editCoupon")) 
		{
			User user = (User)session.getAttribute("account");
		    req.setAttribute("fullname", user.getFullname());
		    
		    int discountId = Integer.parseInt(req.getParameter("id"));
		    Discount discount = discountService.findById(discountId);
		    
		    if (discount == null) {
	            resp.sendRedirect(req.getContextPath() + "/teacher/coupon");
	            return;
	        }
		    
		 // Kiểm tra xem discount có thuộc về các khóa học của giáo viên này không
	        boolean belongsToTeacher = false;
	        for (Course course : discount.getAppliedCourses()) {
	            if (course.getTeacher().getId() == user.getId()) {
	                belongsToTeacher = true;
	                break;
	            }
	        }
	        
	        if (!belongsToTeacher) {
	            resp.sendRedirect(req.getContextPath() + "/teacher/coupon");
	            return;
	        }
	        
	        // Lấy danh sách khóa học của giáo viên hiện tại
	        List<Course> teacherCourses = courseService.findByIdTeacher(user);
	        
	        List<Notification> listNotification = notificationService.findByIdUser(user.getId());
	        int soLuongThongBao = listNotification.size();
	        
	        req.setAttribute("discount", discount);
	        req.setAttribute("teacherCourses", teacherCourses);
	        req.setAttribute("slthongbao", soLuongThongBao);
	        req.setAttribute("listthongbao", listNotification);
	        req.getRequestDispatcher("/views/teacher/editDiscount.jsp").forward(req, resp);
		    
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		if (url.contains("/teacher/addCoupon")) 
		{
			User user = (User)session.getAttribute("account");
			
		    String disCode = req.getParameter("disCode");
		    String decreasedFee = req.getParameter("decreasedFee");
		    String type = req.getParameter("type");
		    String startTime = req.getParameter("startTime");
		    String endTime = req.getParameter("endTime");
		    String codeAmount = req.getParameter("codeAmount");
		    String[] appliedCourseIds = req.getParameterValues("appliedCourses");
		    
		    Discount discount = new Discount();
		    discount.setDisCode(disCode);
		    discount.setDecreasedFee(decreasedFee);
		    discount.setType(type);
		    discount.setStartTime(startTime);
		    discount.setEndTime(endTime);
		    discount.setCodeAmount(codeAmount);
		    
		    Set<Course> appliedCourses = new HashSet<>();
		    if (appliedCourseIds != null) {
		        for (String courseId : appliedCourseIds) {
		            Course course = courseService.findByIdCourse(Integer.parseInt(courseId));
		            if (course != null && course.getTeacher().getId() == user.getId()) {
		                appliedCourses.add(course);
		            }
		        }
		    }
		    discount.setAppliedCourses(appliedCourses);
		    
		    if (discountService.insert(discount)) {
		    	System.out.println("Thêm thành công");
		    	resp.sendRedirect(req.getContextPath() + "/teacher/coupon");
		    }
		}
		if (url.contains("/teacher/editCoupon")) 
		{
			User user = (User)session.getAttribute("account");
	        
	        // Lấy dữ liệu từ form
	        int discountId = Integer.parseInt(req.getParameter("id"));
	        String disCode = req.getParameter("disCode");
	        String decreasedFee = req.getParameter("decreasedFee");
	        String type = req.getParameter("type");
	        String startTime = req.getParameter("startTime");
	        String endTime = req.getParameter("endTime");
	        String codeAmount = req.getParameter("codeAmount");
	        String[] appliedCourseIds = req.getParameterValues("appliedCourses");
	        
	        Discount discount = discountService.findById(discountId);
	        
	        boolean belongsToTeacher = false;
	        for (Course course : discount.getAppliedCourses()) {
	            if (course.getTeacher().getId() == user.getId()) {
	                belongsToTeacher = true;
	                break;
	            }
	        }
	        
	        if (!belongsToTeacher) {
	            resp.sendRedirect(req.getContextPath() + "/teacher/coupon");
	            return;
	        }
	        
	     // Cập nhật thông tin discount
	        discount.setDisCode(disCode);
	        discount.setDecreasedFee(decreasedFee);
	        discount.setType(type);
	        discount.setStartTime(startTime);
	        discount.setEndTime(endTime);
	        discount.setCodeAmount(codeAmount);
	        
	        // Cập nhật mối quan hệ với khóa học
	        Set<Course> appliedCourses = new HashSet<>();
	        if (appliedCourseIds != null) {
	            for (String courseId : appliedCourseIds) {
	                Course course = courseService.findByIdCourse( Integer.parseInt(courseId));
	                if (course != null && course.getTeacher().getId() == user.getId()) {
	                    appliedCourses.add(course);
	                }
	            }
	        }
	        discount.getAppliedCourses().clear();
	        discount.getAppliedCourses().addAll(appliedCourses);
	        
	        if (discountService.update(discount)) {
	        	resp.sendRedirect(req.getContextPath() + "/teacher/coupon");
	        }
		}
	}
}
