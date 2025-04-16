package vn.iotstar.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.Course;
import vn.iotstar.service.ICourseService;
import vn.iotstar.impl.service.CourseService;
import java.io.PrintWriter;

@WebServlet("/views/user/homepage")
public class CourseController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ICourseService courseService;

    @Override
    public void init() throws ServletException {
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    	List<Object[]> bestSellerCourses = courseService.getBestSellingCourses(5);
    	req.setAttribute("bestSellerCourses", bestSellerCourses);

    	List<Object[]> todaySaleCourses = courseService.getTodaySaleCourses(10);
    	req.setAttribute("todaySaleCourses", todaySaleCourses);

    	req.getRequestDispatcher("/views/user/homepage.jsp").forward(req, resp);

    }
}