package observer_pattern;

import java.time.LocalDate;

import vn.iotstar.entity.Course;
import vn.iotstar.entity.Notification;
import vn.iotstar.entity.Teacher;
import vn.iotstar.entity.User;
import vn.iotstar.impl.service.NotificationService;
import vn.iotstar.impl.service.UserService;

public class PushSystemNotifier implements NewCourseNotifier{
	private NotificationService notificationService = new NotificationService();
	private UserService userService = new UserService();

	@Override
	public void update(User follower, Course course) {
		// TODO Auto-generated method stub
		Teacher teacher = (Teacher) userService.findById(course.getTeacher().getId());
		String message = "Tôi là " + teacher.getFullname() + ". Tôi vừa cho ra mắt một khóa học mới.\n"
						+ "Có thể sẽ phù hợp với bạn. Nếu có thời gian mời bạn ghé qua.";
		LocalDate current = LocalDate.now();
		
		Notification noti = new Notification();
		noti.setContent(message);
		noti.setSentDate(current.toString());
		noti.setSender(teacher);
		noti.setReceiver(follower);
		
		notificationService.insert(noti);
		
		System.out.println("Send push system notificaiont successfully");
	}

}
