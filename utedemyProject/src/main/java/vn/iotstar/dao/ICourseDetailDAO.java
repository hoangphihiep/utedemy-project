package vn.iotstar.dao;

import vn.iotstar.entity.CourseDetail;
import java.util.List;

import vn.iotstar.entity.CourseProgress;

public interface ICourseDetailDAO {
    CourseDetail findById(int id);

	CourseDetail findByCourseId(int courseId); 
	
	public List<CourseProgress> getAllCourseProgress();
}