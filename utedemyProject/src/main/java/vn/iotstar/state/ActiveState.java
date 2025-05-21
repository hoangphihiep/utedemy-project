package vn.iotstar.state;

import vn.iotstar.entity.Course;

public class ActiveState implements CourseState {

	@Override
	public void review(Course course) {
		System.out.println("Đã hoạt động, không cần duyệt lại");

	}

	@Override
	public void cancel(Course course) {
		System.out.println("Tạm ngưng khóa học đang hoạt động");
        course.setStatus(0);

	}

	@Override
	public void restore(Course course) {
		 System.out.println("Khóa học đang hoạt động, không cần khôi phục");

	}

	@Override
	public void edit(Course course) {
		System.out.println("Không cho phép chỉnh sửa khi đang hoạt động");

	}

}
