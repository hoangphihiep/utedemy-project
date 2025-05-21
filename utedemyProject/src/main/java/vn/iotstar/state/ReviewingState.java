package vn.iotstar.state;

import vn.iotstar.entity.Course;

public class ReviewingState implements CourseState {

	@Override
	public void review(Course course) {
		System.out.println("Chuyển từ 'kiểm duyệt' sang 'Hoạt động'");
		course.setStatus(1);
	}

	@Override
	public void cancel(Course course) {
		 System.out.println("Hủy khóa học từ trạng thái kiểm duyệt → Tạm ngưng");
	      course.setStatus(0);

	}

	@Override
	public void restore(Course course) {
		System.out.println("Không thể khôi phục khi đang kiểm duyệt");

	}

	@Override
	public void edit(Course course) {
		System.out.println("Không thể chỉnh sửa nội dung trong quá trình kiểm duyệt");

	}

}
