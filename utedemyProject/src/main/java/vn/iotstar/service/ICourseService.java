package vn.iotstar.service;

import java.util.List;
import vn.iotstar.entity.Course;

public interface ICourseService {
	List<Object[]> getBestSellingCourses(int limit);
	List<Object[]> getTodaySaleCourses(int limit);
}