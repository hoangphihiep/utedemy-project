package vn.iotstar.state;

import vn.iotstar.entity.Course;

public class SuspendedState implements CourseState {

	@Override
	public void review(Course course) {
		System.out.println("Không thể duyệt lại khóa học đã bị tạm ngưng");

	}

	@Override
	public void cancel(Course course) {
		System.out.println("Khóa học đã tạm ngưng rồi");

	}

	@Override
	public void restore(Course course) {
		 System.out.println("Khôi phục khóa học từ tạm ngưng sang hoạt động");
	     course.setStatus(1);

	}

	@Override
	public void edit(Course course) {
		System.out.println("Không thể chỉnh sửa khóa học đã bị tạm ngưng");

	}
}
