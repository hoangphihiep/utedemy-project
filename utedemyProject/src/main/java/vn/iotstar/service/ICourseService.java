package vn.iotstar.service;

import java.util.List;

import vn.iotstar.entity.Course;
import vn.iotstar.entity.CourseType;

public interface ICourseService {
	public List<CourseType> listCourseType();
	
	public boolean saveCoure (Course course);
	
	public CourseType findByIDCourseType (int id);
	
	public Course findByIdCourse(int id);
	
	public int maxCourseId();
}
