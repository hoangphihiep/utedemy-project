package vn.iotstar.strategy;

import java.util.ArrayList;
import java.util.List;

import vn.iotstar.entity.Course;

public class SearchByInstructorName implements SearchStrategy {

	@Override
	public List<Course> search(String keyword, List<Course> allCourses) {
		List<Course> result = new ArrayList<>();

		// Chuẩn hóa từ khóa (bỏ dấu, viết thường)
		String normalizedKeyword = VietnameseNormalizer.normalize(keyword);
		String[] keywordParts = normalizedKeyword.split("\\s+");

		for (Course course : allCourses) {
			if (course.getTeacher() != null && course.getTeacher().getFullname() != null) {
				// Chuẩn hóa tên giảng viên (bỏ dấu, viết thường)
				String normalizedFullname = VietnameseNormalizer.normalize(course.getTeacher().getFullname());

				// So sánh từng phần của từ khóa với tên giảng viên
				for (String part : keywordParts) {
					if (normalizedFullname.contains(part)) {
						result.add(course);
						break;
					}
				}
			}
		}
		return result;
	}
}
