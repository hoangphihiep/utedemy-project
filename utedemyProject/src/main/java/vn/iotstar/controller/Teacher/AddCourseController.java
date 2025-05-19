package vn.iotstar.controller.Teacher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
import observer_pattern.EmailNotifier;
import observer_pattern.NewCourseNotificationManager;
import vn.iotstar.entity.Answer;
import vn.iotstar.entity.Course;
import vn.iotstar.entity.CourseDetail;
import vn.iotstar.entity.CourseType;
import vn.iotstar.entity.Lesson;
import vn.iotstar.entity.Question;
import vn.iotstar.entity.Quiz;
import vn.iotstar.entity.Section;
import vn.iotstar.entity.Teacher;
import vn.iotstar.entity.User;
import vn.iotstar.impl.service.CourseService;
import vn.iotstar.impl.service.UserService;
import vn.iotstar.service.ICourseService;
import vn.iotstar.service.IUserService;
import vn.iotstar.utils.Constant;

@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1 MB
	    maxFileSize = 1024 * 1024 * 10,  // 10 MB
	    maxRequestSize = 1024 * 1024 * 15 // 15 MB
	)
@WebServlet(urlPatterns = {"/teacher/addTarget","/teacher/addCourse","/teacher/add","/teacher/adBasicInformation",
		"/teacher/addSection","/teacher/addLesson", "/teacher/addQuiz","/teacher/submitReviewCourse"})
public class AddCourseController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public ICourseService courseService = new CourseService();
	public IUserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		if (url.contains("/teacher/addCourse")) 
		{
			List<CourseType> listCourseType = courseService.listCourseType();
			req.setAttribute("listCourseType", listCourseType);
			req.getRequestDispatcher("/views/teacher/addCourse.jsp").forward(req, resp);
		}
		else if (url.contains("/teacher/addTarget")) 
		{
			Course course = (Course)session.getAttribute("courseSession");
			req.setAttribute("course", course);
			req.getRequestDispatcher("/views/teacher/addTarget.jsp").forward(req, resp);
		}
		else if (url.contains("/teacher/add")) 
		{
			Course course = (Course)session.getAttribute("courseSession");
			req.setAttribute("course", course);
			req.getRequestDispatcher("/views/teacher/add.jsp").forward(req, resp);
		}
		else if (url.contains("/teacher/adBasicInformation")) 
		{
			Course course = (Course)session.getAttribute("courseSession");
			System.out.println ("Ten khoa học: " + course.getCourseName());
			CourseType courseType = courseService.findByIDCourseType(course.getCourseType().getId());
			List<CourseType> listCourseType = courseService.listCourseType();
			req.setAttribute("listCourseType", listCourseType);
			req.setAttribute("course", course);
			req.setAttribute("courseType", courseType);
			req.getRequestDispatcher("/views/teacher/adBasicInformation.jsp").forward(req, resp);
		}
		else if (url.contains("/teacher/submitReviewCourse")) 
		{
			String idCourseStr = req.getParameter("id");
			if (idCourseStr != null) {
				int idCourse = Integer.parseInt(idCourseStr);
				Course course = courseService.findByIdCourse(idCourse); 
				course.setStatus(2);
				boolean check = courseService.updateCourse(course);
				if (check) {
					resp.sendRedirect(req.getContextPath() + "/teacher/course");
				}
			}
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
			course.setStatus(3);
			
			User user = (User) session.getAttribute("account");
			Teacher teacher = (Teacher) userService.findById(user.getId());
			course.setTeacher(teacher);
			boolean check = courseService.saveCoure(course);

			
			if (check) {
				int idCourse = courseService.maxCourseId();
				Course course1 = courseService.findByIdCourse(idCourse);
				session.setAttribute("courseSession", course1);
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

	            Course course = (Course)session.getAttribute("courseSession");
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
						resp.setContentType("application/json");
					    resp.setCharacterEncoding("UTF-8");

					    JsonObject responseObj = new JsonObject();
					    responseObj.addProperty("success", true);

					    PrintWriter out = resp.getWriter();
					    out.print(responseObj.toString());
					    out.flush();
					}else {
						resp.setContentType("application/json");
					    resp.setCharacterEncoding("UTF-8");

					    JsonObject responseObj = new JsonObject();
					    responseObj.addProperty("success", false);
					    responseObj.addProperty("message", "Lỗi khi cập nhật khóa học");

					    PrintWriter out = resp.getWriter();
					    out.print(responseObj.toString());
					    out.flush();
					}
				} else {
					resp.setContentType("application/json");
				    resp.setCharacterEncoding("UTF-8");

				    JsonObject responseObj = new JsonObject();
				    responseObj.addProperty("success", false);
				    responseObj.addProperty("message", "Lỗi khi lưu thông tin căn bản");

				    PrintWriter out = resp.getWriter();
				    out.print(responseObj.toString());
				    out.flush();
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
	            String target = "";
	            for (JsonElement element : objectivesArray) {
	                objectives.add(element.getAsString());
	                target = target + element.getAsString() + ", ";
	                System.out.println ("Mục tiêu: " + element.getAsString());
	            }

	            // Lấy đối tượng và lời chào
	            String courseLearner = jsonObject.get("targetAudience").getAsString();

	            int idCourseDetail = courseService.maxCourseDetailId();
	            CourseDetail courseDetail = courseService.findByIdCourseDetail(idCourseDetail);
	            courseDetail.setCourseLearner(courseLearner);
	            courseDetail.setLearnerAchievements(target);
	            boolean check = courseService.updateCourseDetail(courseDetail);
	            if (check) {
	            	resp.setContentType("application/json");
				    resp.setCharacterEncoding("UTF-8");

				    JsonObject responseObj = new JsonObject();
				    responseObj.addProperty("success", true);

				    PrintWriter out = resp.getWriter();
				    out.print(responseObj.toString());
				    out.flush();
	            }else {
	            	resp.setContentType("application/json");
				    resp.setCharacterEncoding("UTF-8");

				    JsonObject responseObj = new JsonObject();
				    responseObj.addProperty("success", false);
				    responseObj.addProperty("message", "Lỗi khi lưu dữ liệu");

				    PrintWriter out = resp.getWriter();
				    out.print(responseObj.toString());
				    out.flush();
	            }

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
		else if (url.contains("/teacher/addSection")) 
		{
			// Đặt encoding để xử lý đúng tiếng Việt
	        req.setCharacterEncoding("UTF-8");
	        resp.setContentType("application/json");
	        resp.setCharacterEncoding("UTF-8");

	        // Lấy dữ liệu từ request
	        String sectionTitle = req.getParameter("sectionTitle");
	        PrintWriter out = resp.getWriter();

	        try {
	        	Course course = (Course)session.getAttribute("courseSession");
	        	Section section = new Section();
	        	section.setTitle(sectionTitle);
	        	section.setCourse(course);

	        	boolean check = courseService.saveSection(section);
	        	if (check) {
	        		int maxSectionId = courseService.maxSectionId();
	        		Section section1 = courseService.findByIdSection(maxSectionId);
	        		session.setAttribute("sectionSession", section1);
	                String json = "{"
	                            + "\"sectionId\": " + section1.getId() + ","
	                            + "\"title\": \"" + section1.getTitle() + "\","
	                            + "\"status\": \"success\","
	                            + "\"message\": \"Đã thêm phần học: " + sectionTitle + "\""
	                            + "}";
	                resp.getWriter().write(json);
	        	}
	        } catch (Exception e) {
	            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            out.print("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}");
	        } finally {
	            out.flush();
	        }

		}
		else if (url.contains("/teacher/addLesson")) 
		{
			req.setCharacterEncoding("UTF-8");
	        resp.setContentType("application/json");
	        resp.setCharacterEncoding("UTF-8");

			String title = req.getParameter("title");
			String description = req.getParameter("description");
			boolean isFreeLesson = Boolean.parseBoolean(req.getParameter("isFreeLesson"));
			String videoUrl = req.getParameter("videoUrl");
			String numberItem = req.getParameter("numerical");

			int numberItemInt = 0;

			if (numberItem != null && !numberItem.isEmpty()) {
			    try {
			    	numberItemInt = Integer.parseInt(numberItem);
			    } catch (NumberFormatException e) {
			        e.printStackTrace();
			    }
			}

			Section section = (Section)session.getAttribute("sectionSession");

			Lesson lesson = new Lesson();
			lesson.setDescription(description);
			lesson.setNumberItem(numberItemInt);
			lesson.setTitle(title);
			lesson.setVideoUrl(videoUrl);
			lesson.setIsFreeLesson(isFreeLesson);
			lesson.setSection(section);

			boolean check = courseService.saveLesson(lesson);
			if (check) {
				String json = "{"
                        + "\"lessonId\": " + lesson.getId() + ","
                        + "\"title\": \"" + lesson.getTitle() + "\","
                        + "\"description\": \"" + lesson.getDescription() + "\","
                        + "\"video\": \"" + lesson.getVideoUrl() + "\","
                        + "\"isFreeLesson\": \"" + lesson.getIsFreeLesson() + "\","
                        + "\"status\": \"success\","
                        + "\"message\": \"Đã thêm bài học: " + title + "\""
                        + "}";
				resp.getWriter().write(json);
			}
		}
		else if (url.contains("/teacher/addQuiz")) 
		{
			req.setCharacterEncoding("UTF-8");
	        resp.setContentType("application/json");
	        resp.setCharacterEncoding("UTF-8");

			BufferedReader reader = req.getReader();
		    StringBuilder jsonBuilder = new StringBuilder();
		    String line;

		    while ((line = reader.readLine()) != null) {
		            jsonBuilder.append(line);
		    }

		    String jsonData = jsonBuilder.toString();
		    Gson gson = new Gson();
		    Quiz quiz = gson.fromJson(jsonData, Quiz.class);

		    Section section = (Section)session.getAttribute("sectionSession");

		    Quiz quizNew = new Quiz();
		    quizNew.setTitle(quiz.getTitle());
		    quizNew.setNumberItem(quiz.getNumberItem());
		    quizNew.setDescription(quiz.getDescription());
		    quizNew.setDuration(quiz.getDuration());
		    quizNew.setSection(section);

		    boolean allSaved = true;

			boolean checkQuiz = courseService.saveQuiz(quizNew);
			if (checkQuiz) {
				int idQuiz = courseService.maxQuizId();
				Quiz quiz1 = courseService.findByIdQuiz(idQuiz);
				for (Question q : quiz.getQuestions()) {
					Question question = new Question();
					question.setDescription(q.getDescription());
					question.setScore(q.getScore());
					question.setQuiz(quiz1);

					if (!courseService.saveQuestion(question)) {
						allSaved = false;
						break;
					}

					int idQuestion = courseService.maxQuestionId();
					Question question1 = courseService.findByIdQuestion(idQuestion);

					for (Answer a : q.getAnswers()) {
						a.setQuestion(question1);
						if (!courseService.saveAnswer(a)) {
							allSaved = false;
							break;
						}
					}
					if (!allSaved) break;			        
			    }

				if (allSaved) {
					JsonObject json = new JsonObject();
					json.addProperty("quizId", quiz1.getId());
					resp.getWriter().write(json.toString());
				}
			}
		}
	}
}