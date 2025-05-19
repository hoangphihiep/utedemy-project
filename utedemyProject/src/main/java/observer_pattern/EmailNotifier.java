package observer_pattern;

import vn.iotstar.entity.Course;
import vn.iotstar.entity.Teacher;
import vn.iotstar.entity.User;
import vn.iotstar.impl.service.EmailService;
import vn.iotstar.impl.service.UserService;

public class EmailNotifier implements NewCourseNotifier{
	private EmailService emailService = new EmailService();
	private UserService userService = new UserService();
	
	public EmailNotifier() {}

	@Override
	public void update(User follower, Course course) {
		// we need implement notification to user email with mail sender
		// send email right here
		System.out.println("Email while send: " + follower.getFullname());
		
		Teacher teacher = (Teacher) userService.findById(course.getTeacher().getId());
		
		String message = "<html><body>"
				+ "Xin chào " + teacher.getFullname() + ". Chúng tôi có khóa học mới đây !\n"
		        + "<h2>🎉 Khóa học mới vừa được phát hành!</h2>"
		        + "<p>Khóa học: <b>" + course.getCourseName() + "</b></p>"
		        + "<a href='https://your-site.com/courses/123' "
		        + "style='display:inline-block;padding:10px 15px;background:#28a745;color:#fff;text-decoration:none;border-radius:5px;'>"
		        + "Nhấn vào đây để xem khóa học"
		        + "</a>"
		        + "</body></html>";
		emailService.sendNewCourseNotification(follower.getEmail(), message);
	}

}
