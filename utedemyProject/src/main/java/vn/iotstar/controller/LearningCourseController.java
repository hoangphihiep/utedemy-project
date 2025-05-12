package vn.iotstar.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.text.StringEscapeUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.Section;
import vn.iotstar.entity.LessonProgress;
import vn.iotstar.entity.Review;
import vn.iotstar.entity.CourseProgress;
import vn.iotstar.entity.User;
import vn.iotstar.entity.Course;
import vn.iotstar.entity.Discussion;
import vn.iotstar.entity.Lesson;
import vn.iotstar.service.ICourseService;
import vn.iotstar.impl.dao.CourseDao;
import vn.iotstar.impl.service.CourseService;
import java.io.PrintWriter;

@WebServlet("/views/user/Course")
public class LearningCourseController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ICourseService courseService;

    @Override
    public void init() throws ServletException {
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int courseId = 1;
        int userId = 1;
        
        // ======== LẤY DỮ LIỆU BÀI HỌC =========
        List<Object[]> lessonData = courseService.getSectionLessonCourse(courseId);
        String overview = courseService.getOverviewSection(courseId);

        Map<String, List<Map<String, String>>> sections = new LinkedHashMap<>();
        for (Object[] row : lessonData) {
            String sectionTitle = (String) row[0];
            String lessonTitle = (String) row[1];
            String videoUrl = (String) row[2];
            String description = (String) row[3];
            String lessonId = String.valueOf(row[4]);
            String isFreeLesson = String.valueOf(row[5]);

            sections.putIfAbsent(sectionTitle, new ArrayList<>());
            Map<String, String> lesson = new LinkedHashMap<>();
            lesson.put("id", lessonId);
            lesson.put("isFreeLesson", isFreeLesson);
            lesson.put("title", lessonTitle);
            lesson.put("videoUrl", videoUrl);
            lesson.put("description", description);
            sections.get(sectionTitle).add(lesson);
        }

        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<String, List<Map<String, String>>> entry : sections.entrySet()) {
            Map<String, Object> section = new LinkedHashMap<>();
            section.put("title", entry.getKey());
            section.put("lessons", entry.getValue());
            result.add(section);
        }

        // ======== GIẢNG VIÊN =========
        List<Map> teacherInfoList = courseService.getInfoTeacher(courseId);
        Map teacherInfo = teacherInfoList.isEmpty() ? null : teacherInfoList.get(0);

     // ======== GỘP COMMENTS & REPLIES =========
        List<Map> rawCommentData = courseService.getCommentsAndReplys(courseId);
        List<Map<String, Object>> allComments = new ArrayList<>();
        Random random = new Random();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        for (Map<String, Object> row : rawCommentData) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", row.get("id"));
            item.put("parentId", row.get("parentId"));
            item.put("author", row.get("fullname"));
            item.put("avatar", row.get("avatarUrl"));

            String rawContent = (String) row.get("content");
            String decodedContent = StringEscapeUtils.unescapeHtml4(StringEscapeUtils.unescapeJava(rawContent));
            decodedContent = decodedContent.replaceAll("[\\n\\r\\t]+", " ").replaceAll(" +", " ").trim();
            item.put("content", decodedContent);

            LocalDateTime time = (LocalDateTime) row.get("askedAt");
            item.put("time", time.format(formatter));

            item.put("likes", random.nextInt(5) + 1);
            item.put("replies", row.get("repliesCount"));

            allComments.add(item);
        }
        
     // ======== REVIEWSTATS =========
        double averageRating = courseService.getAverageRating(courseId);
        List<Object[]> distributionData = courseService.getRatingDestribution(courseId);

        int totalReviews = distributionData.stream()
                .mapToInt(row -> ((Long) row[1]).intValue())
                .sum();

        Map<Integer, Double> distribution = new LinkedHashMap<>();
        for (int i = 5; i >= 1; i--) {
            distribution.put(i, 0.0);
        }

        for (Object[] row : distributionData) {
            int rate = (int) row[0];
            long count = (long) row[1];
            double percent = (totalReviews > 0) ? (count * 100.0 / totalReviews) : 0.0;
            distribution.put(rate, Math.round(percent * 10.0) / 10.0); 
        }

        Map<String, Object> reviewStats = new LinkedHashMap<>();
        reviewStats.put("average", Math.round(averageRating * 10.0) / 10.0);
        reviewStats.put("distribution", distribution);
        
     // ======== REVIEWS =========
        List<Map> reviews = courseService.getReviews(courseId);
        List<Map<String, Object>> formattedReviews = new ArrayList<>();

        for (Map<String, Object> review : reviews) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("author", review.get("author"));
            item.put("avatar", review.get("avatar"));
            item.put("rating", review.get("rating"));
            item.put("content", review.get("content"));
            formattedReviews.add(item);
        }
              
        // ======== CHUYỂN DỮ LIỆU THÀNH JSON =========
        Gson gson = new Gson();
        String allCommentsJson = gson.toJson(allComments);
        String lessonJson = gson.toJson(result);
        String overviewJson = gson.toJson(overview);
        String teacherJson = gson.toJson(teacherInfo);
        String reviewJson = gson.toJson(reviewStats);
        String reviewsListJson = gson.toJson(formattedReviews);

        // ======== SET ATTRIBUTE CHO JSP =========
        req.setAttribute("lessonJson", lessonJson);
        req.setAttribute("overviewJson", overviewJson);
        req.setAttribute("teacherJson", teacherJson);
        req.setAttribute("reviewJson", reviewJson);
        req.setAttribute("reviewsListJson", reviewsListJson);
        req.setAttribute("allCommentsJson", allCommentsJson);
        req.getRequestDispatcher("/views/user/Course.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String content = request.getParameter("content");
        String parentIdStr = request.getParameter("parentId");
        String lessonIdStr = request.getParameter("lessonId");
        String feedback = request.getParameter("feedback");
        String selectedRating = request.getParameter("selectedRating");
        boolean checkUPE = "true".equalsIgnoreCase(request.getParameter("checkUPE"));
        boolean checkRating = "true".equalsIgnoreCase(request.getParameter("checkRating"));
        boolean takeProgress = "true".equalsIgnoreCase(request.getParameter("takeProgress"));
        boolean takeAvatarURL = "true".equalsIgnoreCase(request.getParameter("takeavatarURL"));
        boolean isCompleted = true;

        int courseId = 1; 
        int userId = 1;
        
        if (takeAvatarURL != false)
        {
            String avatarURL = courseService.getAvatarURL(userId);
            
            System.out.println(""+avatarURL);
            Gson gson = new Gson();
    		String jsonResponse = gson.toJson(avatarURL);
    		PrintWriter out = response.getWriter();
    		out.print(jsonResponse);
    		out.flush();
        }
        
        if (takeProgress != false)
        {
       	 	int courseProgressID = courseService.getCourseProgressId(courseId, userId);

    		int Total = courseService.getTotalLessonInCourse(courseId);
    		int Current = courseService.getCurrentLessonFromUserId(courseProgressID);
    		
    		Map<String, Object> progress = new LinkedHashMap<>();
    		progress.put("total", Total);
    		progress.put("current", Current);

    		Gson gson = new Gson();
    		String jsonResponse = gson.toJson(progress);
    		PrintWriter out = response.getWriter();
    		out.print(jsonResponse);
    		out.flush();
        }
        
        if (checkRating != false)
        {
       	 	if (!courseService.checkUserRating(userId, courseId))
       	 	{
           	 	int courseProgressID = courseService.getCourseProgressId(courseId, userId);
	       	 	if(courseService.checkLessonProgress(courseProgressID))
	       	 	{
	           	 	Gson gson = new Gson();
	                String jsonResponse = gson.toJson(true);
	                PrintWriter out = response.getWriter();
	                out.print(jsonResponse);
	                out.flush();
	       	 	}
	       	 	else
	       	 	{
	       	 	Gson gson = new Gson();
                String jsonResponse = gson.toJson(false);
                PrintWriter out = response.getWriter();
                out.print(jsonResponse);
                out.flush();
	       	 	}
       	 	}
       	 	else {
       	 	Gson gson = new Gson();
            String jsonResponse = gson.toJson(false);
            PrintWriter out = response.getWriter();
            out.print(jsonResponse);
            out.flush();
       	 	}
        }
        
        if (checkUPE != false)
        {
            if(!courseService.checkUserProgressExists(userId,courseId)) {

            	CourseProgress cP = new CourseProgress();
            	cP.setCompleted(false);
            	cP.setProgressPercentage(0);
            	cP.setStartDate(new java.sql.Date(System.currentTimeMillis()));
            	
            	Course course = new Course();
        		course.setId(courseId);
        		cP.setCourse(course);
        		
        		User user = new User();
        		user.setId(userId);
        		cP.setUser(user);
        		
        		courseService.saveCourseProgress(cP);
        		
                Gson gson = new Gson();
                String jsonResponse = gson.toJson(null);
                PrintWriter out = response.getWriter();
                out.print(jsonResponse);
                out.flush();
            } else {        		
                Gson gson = new Gson();
                String jsonResponse = gson.toJson(null);
                PrintWriter out = response.getWriter();
                out.print(jsonResponse);
                out.flush();
            }
        }
        
        if (feedback != null && !feedback.isEmpty() && selectedRating != null && !selectedRating.isEmpty())
        {
        		int rate = Integer.parseInt(selectedRating);
        		
        		Review review = new Review();
        		review.setContent(feedback);
        		review.setRate(rate);
        		review.setCreatedAt(new java.sql.Date(System.currentTimeMillis()));
        		
        		Course course = new Course();
        		course.setId(courseId);
        		review.setCourse(course);
        		
        		User user = new User();
        		user.setId(userId);
        		review.setCreatedBy(user);
        		
        		courseService.saveReview(review);
        		
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(null);
            PrintWriter out = response.getWriter();
            out.print(jsonResponse);
            out.flush();
        }

        if (lessonIdStr != null && !lessonIdStr.isEmpty()) {
       	 	int lessonId = Integer.parseInt(lessonIdStr);
       	 	int courseProgressID = courseService.getCourseProgressId(courseId, userId);
        	if(!courseService.checkLessonProgressExists(courseProgressID, lessonId))
        	{
                LessonProgress lessonProgress = new LessonProgress();
                lessonProgress.setCompleted(isCompleted);
                
                CourseProgress course = new CourseProgress();
                course.setId(courseProgressID);
                lessonProgress.setCourseProgress(course);
                
                Lesson lesson = new Lesson();
                lesson.setId(lessonId);
                lessonProgress.setLesson(lesson);
                
                courseService.markLessonAsCompleted(lessonProgress);
        	}
                
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(null);
            PrintWriter out = response.getWriter();
            out.print(jsonResponse);
            out.flush();
        }

        if (content != null && !content.trim().isEmpty()) {
        Discussion discussion = new Discussion();
        discussion.setContent(content);
        discussion.setAskedAt(LocalDateTime.now());

        Course course = new Course();
        course.setId(courseId);
        discussion.setCourse(course);

        User user = new User();
        user.setId(userId);
        discussion.setAskedBy(user);

        if (parentIdStr != null && !parentIdStr.isEmpty()) {
            try {
                int parentId = Integer.parseInt(parentIdStr);
                Discussion parent = new Discussion();
                parent.setId(parentId);
                discussion.setParent(parent);
            } catch (NumberFormatException ex) {
                discussion.setParent(null);
            }
        } else {
            discussion.setParent(null);
        }

        courseService.saveDiscussion(discussion);
        
        List<Map> rawCommentData = courseService.getCommentsAndReplys(courseId);
        List<Map<String, Object>> allComments = new ArrayList<>();
        Random random = new Random();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        for (Map<String, Object> row : rawCommentData) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", row.get("id"));
            item.put("parentId", row.get("parentId"));
            item.put("author", row.get("fullname"));
            item.put("avatar", row.get("avatarUrl"));

            String rawContent = (String) row.get("content");
            String decodedContent = StringEscapeUtils.unescapeHtml4(StringEscapeUtils.unescapeJava(rawContent));
            decodedContent = decodedContent.replaceAll("[\\n\\r\\t]+", " ").replaceAll(" +", " ").trim();
            item.put("content", decodedContent);

            LocalDateTime time = (LocalDateTime) row.get("askedAt");
            item.put("time", time.format(formatter));

            item.put("likes", random.nextInt(5) + 1); 
            item.put("replies", row.get("repliesCount")); 

            allComments.add(item);
        }

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(allComments);
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
        }
    }
}
