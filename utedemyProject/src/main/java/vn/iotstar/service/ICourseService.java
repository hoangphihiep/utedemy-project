package vn.iotstar.service;

import java.util.List;
import vn.iotstar.entity.Course;
import vn.iotstar.entity.CourseDetail;
import vn.iotstar.entity.CourseType;

public interface ICourseService {
	List<Object[]> getBestSellingCourses(int limit);

	List<Object[]> getTodaySaleCourses(int limit);

	public List<CourseType> listCourseType();
	
	public boolean saveCoure (Course course);
	
	public CourseType findByIDCourseType (int id);
	
	public Course findByIdCourse(int id);
	
	public int maxCourseId();
	
	public boolean saveCourseDetail (CourseDetail courseDetail);
	
	public boolean updateCourse (Course course);
	
	public int maxCourseDetailId();
	
	public CourseDetail findByIdCourseDetail (int id);
}

