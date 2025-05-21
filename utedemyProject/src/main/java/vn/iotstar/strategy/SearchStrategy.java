package vn.iotstar.strategy;

import java.util.List;

import vn.iotstar.entity.Course;

public interface SearchStrategy {
	List<Course> search(String keyword,List<Course> allCourses);
}
