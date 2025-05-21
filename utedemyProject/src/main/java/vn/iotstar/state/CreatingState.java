package vn.iotstar.state;

import vn.iotstar.entity.Course;

public class CreatingState implements CourseState {

	@Override
	public void review(Course course) {
		System.out.println("Chuyển từ 'Đang tạo' sang 'Đang kiểm duyệt'");
		course.setStatus(2);
	}

	@Override
	public void cancel(Course course) {
		System.out.println("Không thể hủy khi đang tạo");

	}

	@Override
	public void restore(Course course) {
		System.out.println("Không thể khôi phục khi đang tạo");

	}

	@Override
	public void edit(Course course) {
		System.out.println("đang cho phép chỉnh sửa nội dung khóa học");

	}

}
