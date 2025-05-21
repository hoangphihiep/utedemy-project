package vn.iotstar.state;

import jakarta.persistence.criteria.CriteriaBuilder.Case;
import vn.iotstar.entity.Course;

public class CourseContext {
	private CourseState state;
	public CourseContext(Course course) {
		switch (course.getStatus()) {
		case 3 -> state = new CreatingState();
		case 2 -> state = new ReviewingState();
		case 1 -> state = new ActiveState();
		case 0 -> state = new SuspendedState();
		default -> throw new IllegalStateException("Trạng thái không hợp lệ");
		}
	}
	
	 public void review(Course course) {
	        state.review(course);
	    }

	 public void cancel(Course course) {
	        state.cancel(course);
	    }

	 public void restore(Course course) {
	        state.restore(course);
	    }

	 public void edit(Course course) {
	        state.edit(course);
	    }
}
