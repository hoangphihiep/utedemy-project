package vn.iotstar.strategy;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.kerberos.KerberosTicket;

import vn.iotstar.entity.Course;

public class SearchByCourseName implements SearchStrategy {

	@Override
	public List<Course> search(String keyword, List<Course> allCourses) {
		List<Course> result= new ArrayList<>();
		String[] keywordParts=keyword.toLowerCase().split("\\s+");



		for(Course course : allCourses) {
			String name=course.getCourseName().toLowerCase();
			for(String part:keywordParts) {
				if(name.contains(part)) {
					result.add(course);
					break;
				}
			}
		}
		return result;
	}

}
