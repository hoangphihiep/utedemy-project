package vn.iotstar.observer_pattern;

import java.util.ArrayList;
import java.util.List;

import vn.iotstar.entity.Course;
import vn.iotstar.entity.User;

public class NewCourseNotificationManager {
	private List<NewCourseNotifier> observers = new ArrayList<>();

	public void addObserver(NewCourseNotifier observer) {
		System.out.println("You just add a observer");
		observers.add(observer);
	}

	public void removeObserver(NewCourseNotifier observer) {
		observers.remove(observer);
	}

	public void notifyAllObservers(User follower, Course course) {
		for (NewCourseNotifier obs : observers) {
			// send notification
			System.out.println("You just enter into notify all observer section. Test successfully");
			obs.update(follower, course);
		}
	}

}
