package vn.iotstar.impl.service;


import java.util.List;
import vn.iotstar.dao.ICourseDao;
import vn.iotstar.impl.dao.CourseDao;
import vn.iotstar.entity.Course;
import vn.iotstar.service.ICourseService;


public class CourseService implements ICourseService {


    private ICourseDao courseDAO;


    public CourseService() {
    	courseDAO = new CourseDao();
    }


    @Override
    public List<Object[]> getBestSellingCourses(int limit) {
        return courseDAO.findBestSellingCourseDetails(limit);
    }


    @Override
    public List<Object[]> getTodaySaleCourses(int limit) {
        return courseDAO.findTodaySaleCourses(limit);
    }
}