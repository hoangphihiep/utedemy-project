package vn.iotstar.dao;

import java.util.List;
import vn.iotstar.entity.Course;

public interface ICourseDao {

	List<Object[]> findBestSellingCourseDetails(int limit);
	List<Object[]> findTodaySaleCourses(int limit);
}