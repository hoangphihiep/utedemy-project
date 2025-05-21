package vn.iotstar.strategy;

import java.util.ArrayList;
import java.util.List;

import vn.iotstar.entity.Course;

public class SearchByType implements SearchStrategy {

	@Override
	public List<Course> search(String keyword, List<Course> allCourses) {
		List<Course> result= new ArrayList<>();
		String normalizedKeyword= VietnameseNormalizer.normalize(keyword.toLowerCase());
		for(Course course:allCourses) {
			if(course.getCourseType()!=null&&course.getCourseType().getCourseTypeName()!=null) {
				String typename=VietnameseNormalizer.normalize(course.getCourseType().getCourseTypeName().toLowerCase());	
				if(typename.contains(normalizedKeyword)) {
					result.add(course);
				}
			}
		}
		return result;
	}

}