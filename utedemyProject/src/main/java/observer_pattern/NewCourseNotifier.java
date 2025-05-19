package observer_pattern;

import vn.iotstar.entity.Course;
import vn.iotstar.entity.User;

public interface NewCourseNotifier {
	void update(User follower, Course course);
}
