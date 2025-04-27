package vn.iotstar.controller.Teacher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Answer;
import vn.iotstar.entity.Lesson;
import vn.iotstar.entity.Question;
import vn.iotstar.entity.Quiz;
import vn.iotstar.entity.Section;
import vn.iotstar.impl.service.CourseService;
import vn.iotstar.service.ICourseService;

@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1 MB
	    maxFileSize = 1024 * 1024 * 10,  // 10 MB
	    maxRequestSize = 1024 * 1024 * 15 // 15 MB
	)
@WebServlet(urlPatterns = {"/teacher/editSection","/teacher/editLesson","/teacher/updateTarget","/teacher/updateCourse","/teacher/updateBasicInformation",
		"/teacher/updateSection","/teacher/deleteSection","/teacher/updateLesson","/teacher/deleteLesson","/teacher/editQuiz", "/teacher/updateQuiz", "/teacher/deleteQuiz"})
public class UpdateCourseController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public ICourseService courseService = new CourseService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("/teacher/editSection")) 
		{
			int sectionId = Integer.parseInt(req.getParameter("sectionId"));
			Section section = courseService.findByIdSection(sectionId);
			resp.setContentType("application/json");
		    resp.getWriter().write("{\"title\": \"" + section.getTitle() + "\"}");
		}
		if (url.contains("/teacher/editLesson")) 
		{
			int lessonId = Integer.parseInt(req.getParameter("lessonId"));
			System.out.println ("id lesson: " + lessonId);
			Lesson lesson = courseService.findByIdLesson(lessonId);
			resp.setContentType("application/json");
			String json = "{"
				    + "\"title\": \"" + escapeJson(lesson.getTitle()) + "\","
				    + "\"videoUrl\": \"" + escapeJson(lesson.getVideoUrl()) + "\","
				    + "\"description\": \"" + escapeJson(lesson.getDescription()) + "\","
				    + "\"isFreeLesson\": \"" + escapeJson(String.valueOf(lesson.isFreeLesson())) + "\""
				    + "}";

				resp.getWriter().write(json);
		}
		if (url.contains("/teacher/editQuiz")) {
		    resp.setContentType("application/json");
		    resp.setCharacterEncoding("UTF-8");

		    int quizId = Integer.parseInt(req.getParameter("quizId"));
		    Quiz quiz = courseService.findByIdQuiz(quizId);

		    if (quiz != null) {
		        JsonObject quizJson = new JsonObject();
		        quizJson.addProperty("id", quiz.getId());
		        quizJson.addProperty("title", quiz.getTitle());
		        quizJson.addProperty("description", quiz.getDescription());
		        quizJson.addProperty("duration", quiz.getDuration());

		        JsonArray questionsArray = new JsonArray();
		        for (Question q : quiz.getQuestions()) {
		            JsonObject qJson = new JsonObject();
		            qJson.addProperty("description", q.getDescription());
		            qJson.addProperty("score", q.getScore());

		            JsonArray answersArray = new JsonArray();
		            for (Answer a : q.getAnswers()) {
		                JsonObject aJson = new JsonObject();
		                aJson.addProperty("content", a.getContent());
		                aJson.addProperty("isCorrect", a.isCorrect());
		                answersArray.add(aJson);
		            }

		            qJson.add("answers", answersArray);
		            questionsArray.add(qJson);
		        }

		        quizJson.add("questions", questionsArray);
		        resp.getWriter().write(quizJson.toString());
		    } else {
		        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		        resp.getWriter().write("{\"error\":\"Quiz not found\"}");
		    }
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		//HttpSession session = req.getSession();
		if (url.contains("/teacher/updateSection")) 
		{
			resp.setContentType("application/json");
			String sectionTitle = req.getParameter("sectionTitle");
			String sectionId = req.getParameter("sectionId");

			PrintWriter out = resp.getWriter();
			System.out.println ("id: " + sectionId + " title: " + sectionTitle);

			int sectionIdInt = 0;

			if (sectionId != null && !sectionId.isEmpty()) {
			    try {
			    	sectionIdInt = Integer.parseInt(sectionId);
			    } catch (NumberFormatException e) {
			        e.printStackTrace();
			    }
			}

			try {
				Section section = courseService.findByIdSection(sectionIdInt);
				section.setTitle(sectionTitle);
				boolean checkSection = courseService.updateSection(section);
	        	if (checkSection) {
	        		System.out.println("Lưu thành công");
	        		// Gộp tất cả thông tin trả về vào một object JSON duy nhất
	                String json = "{"
	                            + "\"sectionId\": " + section.getId() + ","
	                            + "\"title\": \"" + section.getTitle() + "\","
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
		if (url.contains("/teacher/updateLesson")) 
		{
			req.setCharacterEncoding("UTF-8");
	        resp.setContentType("application/json");
	        resp.setCharacterEncoding("UTF-8");

	        PrintWriter out = resp.getWriter();

			String title = req.getParameter("title");
			String description = req.getParameter("description");
			boolean isFreeLesson = Boolean.parseBoolean(req.getParameter("isFreeLesson"));
			String videoUrl = req.getParameter("videoUrl");
			String lessonId = req.getParameter("lessonId");


			int lessonIdInt = 0;

			if (lessonId != null && !lessonId.isEmpty()) {
			    try {
			    	lessonIdInt = Integer.parseInt(lessonId);
			    } catch (NumberFormatException e) {
			        e.printStackTrace();
			    }
			}

			try {
				Lesson lesson = courseService.findByIdLesson(lessonIdInt);
				lesson.setDescription(description);
				lesson.setTitle(title);
				lesson.setVideoUrl(videoUrl);
				lesson.setFreeLesson(isFreeLesson);
				boolean checkLesson = courseService.updateLesson(lesson);
	        	if (checkLesson) {
	                String json = "{"
	                            + "\"lessonId\": " + lesson.getId() + ","
	                            + "\"title\": \"" + lesson.getTitle() + "\","
	                            + "\"description\": \"" + lesson.getTitle() + "\","
	                            + "\"video\": \"" + lesson.getTitle() + "\","
	                            + "\"isFreeLesson\": \"" + lesson.isFreeLesson() + "\","
	                            + "\"status\": \"success\","
	                            + "\"message\": \"Đã thêm phần học: " + title + "\""
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
		if (url.contains("/teacher/updateQuiz")) 
		{
			BufferedReader reader = req.getReader();
		    String json = reader.lines().collect(Collectors.joining());

		    ObjectMapper mapper = new ObjectMapper();
		    Quiz quiz = mapper.readValue(json, Quiz.class);

		    // Add validation
		    if (quiz.getId() <= 0) {
		        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		        resp.setContentType("application/json");
		        resp.getWriter().write("{\"error\": \"Invalid quiz ID\"}");
		        return;
		    }
		    System.out.println ("thoi gian làm bài: " + quiz.getDuration());

		    boolean test = courseService.updateQuiz(quiz);
		    if (test) {
		        resp.setContentType("application/json");
		        resp.getWriter().write("{\"success\": true, \"quizId\": " + quiz.getId() + "}");
		    }
		}
		if (url.contains("/teacher/deleteSection")) 
		{
			int sectionId = Integer.parseInt(req.getParameter("sectionId"));
			boolean check;
			try {
				check = courseService.deleteSection(sectionId);
				if (check) {
					resp.setStatus(HttpServletResponse.SC_OK);
			    } else {
			        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			    }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (url.contains("/teacher/deleteLesson")) 
		{
			int lessonId = Integer.parseInt(req.getParameter("lessonId"));
			boolean check;
			try {
				check = courseService.deleteLesson(lessonId);
				if (check) {
					resp.setStatus(HttpServletResponse.SC_OK);
			    } else {
			        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			    }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (url.contains("/teacher/deleteQuiz")) 
		{
			int quizId = Integer.parseInt(req.getParameter("quizId"));
			boolean check;
			try {
				check = courseService.deleteQuiz(quizId);
				if (check) {
					resp.setStatus(HttpServletResponse.SC_OK);
			    } else {
			        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			    }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String escapeJson(String value) {
	    if (value == null) return "";
	    return value.replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
	}

}