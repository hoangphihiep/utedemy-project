package vn.iotstar.controller.Teacher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.iotstar.entity.Course;
import vn.iotstar.entity.CourseDetail;
import vn.iotstar.entity.CourseType;
import vn.iotstar.impl.service.CourseService;
import vn.iotstar.service.ICourseService;
import vn.iotstar.utils.Constant;

@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1 MB
	    maxFileSize = 1024 * 1024 * 10,  // 10 MB
	    maxRequestSize = 1024 * 1024 * 15 // 15 MB
	)
@WebServlet(urlPatterns = {"/teacher/homePage","/teacher/addTarget","/teacher/addCourse","/teacher/add","/teacher/adBasicInformation","/teacher/addLessonQuiz"})
public class HomeController extends HttpServlet {

	public ICourseService courseService = new CourseService();
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		if (url.contains("/teacher/homePage")) 
		{
			req.getRequestDispatcher("/views/teacher/homePage.jsp").forward(req, resp);
		}
		else if (url.contains("/teacher/addCourse")) 
		{
			List<CourseType> listCourseType = courseService.listCourseType();
			req.setAttribute("listCourseType", listCourseType);
			req.getRequestDispatcher("/views/teacher/addCourse.jsp").forward(req, resp);
		}
		else if (url.contains("/teacher/addTarget")) 
		{
			req.getRequestDispatcher("/views/teacher/addTarget.jsp").forward(req, resp);
		}
		else if (url.contains("/teacher/add")) 
		{
			req.getRequestDispatcher("/views/teacher/add.jsp").forward(req, resp);
		}
		else if (url.contains("/teacher/adBasicInformation")) 
		{
			int id = (Integer)session.getAttribute("courseId");
			
			System.out.println ("id của course: " + id);
			Course course = courseService.findByIdCourse(id);
			System.out.println ("Ten khoa học: " + course.getCourseName());
			CourseType courseType = courseService.findByIDCourseType(course.getCourseType().getId());
			List<CourseType> listCourseType = courseService.listCourseType();
			req.setAttribute("listCourseType", listCourseType);
			req.setAttribute("course", course);
			req.setAttribute("courseType", courseType);
			req.getRequestDispatcher("/views/teacher/adBasicInformation.jsp").forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		if (url.contains("/teacher/addCourse")) 
		{
			String courseName = req.getParameter("courseName");
			String courseTypeId = req.getParameter("courseCategory");
			int courseTypeIdInt = 0;

			if (courseTypeId != null && !courseTypeId.isEmpty()) {
			    try {
			        courseTypeIdInt = Integer.parseInt(courseTypeId);
			    } catch (NumberFormatException e) {
			        e.printStackTrace();
			    }
			}
			CourseType courseType = courseService.findByIDCourseType(courseTypeIdInt);
			
			Course course = new Course();
			course.setCourseName(courseName);
			course.setCourseType(courseType);
			
			boolean check = courseService.saveCoure(course);
			
			int idCourse = courseService.maxCourseId();
			session.setAttribute("courseId", idCourse);
			if (check) {
				resp.sendRedirect(req.getContextPath() + "/teacher/adBasicInformation");
			}else {
				System.out.println("Tạo không thành công");
			}
		}
		else if (url.contains("/teacher/adBasicInformation")) 
		{
			// Set response type
	        resp.setContentType("application/json");
	        
	        try {

	            // Extract values from JSON
	        	String courseTitle = req.getParameter("courseTitle");
	            String shortDescription = req.getParameter("shortDescription");
	            String courseTypeId = req.getParameter("courseTypeId");
	            String coursePrice = req.getParameter("coursePrice");
	            String courseIntroduction = req.getParameter("courseIntroduction");
	            String videoLink = req.getParameter("videoLink");
	            // Get course ID from session
	            int id = (Integer)session.getAttribute("courseId");
	            
	            Course course = courseService.findByIdCourse(id);
	            course.setCourseName(courseTitle);
	            int coursePriceInt = 0;

				if (coursePrice != null && !coursePrice.isEmpty()) {
				    try {
				    	coursePriceInt = Integer.parseInt(coursePrice);
				    } catch (NumberFormatException e) {
				        e.printStackTrace();
				    }
				}
				course.setCoursePrice(coursePriceInt);
				
				int courseTypeIdInt = 0;

				if (courseTypeId != null && !courseTypeId.isEmpty()) {
				    try {
				        courseTypeIdInt = Integer.parseInt(courseTypeId);
				    } catch (NumberFormatException e) {
				        e.printStackTrace();
				    }
				}
				CourseType courseType = courseService.findByIDCourseType(courseTypeIdInt);
				course.setCourseType(courseType);
				
	            CourseDetail courseDetail = new CourseDetail();
	            courseDetail.setDescription(shortDescription);
	            courseDetail.setCourseIntroduction(courseIntroduction);
	            courseDetail.setCourseVideo(videoLink);
	            
	            String fname = "";
				String uploadPath = Constant.DIR;
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists())
					uploadDir.mkdir();
				
				try {
					Part part = req.getPart("courseImage");
					if (part.getSize() > 0) {
						String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
						int index = filename.lastIndexOf(".");
						System.out.println (filename);
						String ext = filename.substring(index + 1);
						fname = System.currentTimeMillis() + "." + ext;

						part.write(uploadPath + "/" + fname);

						courseDetail.setCourseImage(fname);
					} else {
						courseDetail.setCourseImage("avatar.png");
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				boolean check = courseService.saveCourseDetail(courseDetail);
				if (check) {
					int idCourseDetail = courseService.maxCourseDetailId();
					CourseDetail courseDetail1 = courseService.findByIdCourseDetail(idCourseDetail);
					course.setCourseDetail(courseDetail1);
					boolean checkUpdate = courseService.updateCourse(course);
					if (checkUpdate) {
						System.out.println ("Lưu thông tin thành công");
					}
				}
	            
	        } catch (Exception e) {
	            // Log the error
	            e.printStackTrace();
	        }
		}
		else if (url.contains("/teacher/addTarget")) 
		{
			// Đọc dữ liệu JSON từ request
	        StringBuilder buffer = new StringBuilder();
	        BufferedReader reader = req.getReader();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            buffer.append(line);
	        }
	        
	        String data = buffer.toString();
	        
	        // Parse JSON
	        Gson gson = new Gson();
	        JsonObject jsonObject = gson.fromJson(data, JsonObject.class);
	        
	        try {
	            // Xử lý dữ liệu mục tiêu
	            JsonArray objectivesArray = jsonObject.getAsJsonArray("objectives");
	            List<String> objectives = new ArrayList<>();
	            
	            for (JsonElement element : objectivesArray) {
	                objectives.add(element.getAsString());
	                System.out.println ("Mục tiêu: " + element.getAsString());
	            }
	            
	            // Lấy đối tượng và lời chào
	            String courseLearner = jsonObject.get("targetAudience").getAsString();
	            String welcomeMessage = jsonObject.get("welcomeMessage").getAsString();
	            
	            System.out.println ("Đối tượng: " + courseLearner);
	            System.out.println ("Lời chào: " + welcomeMessage);        
	        } catch (Exception e) {
	            e.printStackTrace();
	            
	            // Trả về lỗi
	            resp.setContentType("application/json");
	            resp.setCharacterEncoding("UTF-8");
	            
	            JsonObject responseObj = new JsonObject();
	            responseObj.addProperty("success", false);
	            responseObj.addProperty("message", "Lỗi: " + e.getMessage());
	            
	            resp.getWriter().write(gson.toJson(responseObj));
	        }
	    }
	}
}
