package vn.iotstar.strategy;

import java.util.ArrayList;
import java.util.List;

import vn.iotstar.entity.Course;

public class SearchByDescription implements SearchStrategy {

	@Override
	public List<Course> search(String keyword, List<Course> allCourses) {
		List<Course> result=new ArrayList<>();
		for(Course course: allCourses) {
			if(course.getCourseDetail()!=null&course.getCourseDetail().getDescription().toLowerCase().contains(keyword.toLowerCase())) {
				result.add(course);
			}
		}
		return result;
	}

}
