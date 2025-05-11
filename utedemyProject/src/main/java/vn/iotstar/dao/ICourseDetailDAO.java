package vn.iotstar.dao;

import vn.iotstar.entity.CourseDetail;
import java.util.List;
import vn.iotstar.entity.CourseType;

import vn.iotstar.entity.CourseProgress;

public interface ICourseDetailDAO {
    CourseDetail findById(int id);

	CourseDetail findByCourseId(int courseId); 
	
	public List<CourseProgress> getAllCourseProgress();
	
	public List<CourseType> getAllCourseTypes();

	public CourseType getCourseTypeById(int id);
}