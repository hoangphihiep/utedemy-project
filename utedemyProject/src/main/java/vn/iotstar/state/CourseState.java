package vn.iotstar.state;

import vn.iotstar.entity.Course;

public interface CourseState {
	void review(Course course);
	void cancel(Course course);
	void restore(Course course);
	void edit(Course course);
}
