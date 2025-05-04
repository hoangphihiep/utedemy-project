package vn.iotstar.service;

import vn.iotstar.entity.Course;
import vn.iotstar.entity.User;
import vn.iotstar.entity.FavoriteCourse;

public interface IFavoriteCourseService {
	
	public FavoriteCourse findByIdUser(int idUser);
	
	boolean addCourseToFavorite(User user, Course course);

	boolean removeCourseFromFavorite(User user, Course course);

	boolean isCourseInFavorite(User user, Course course);

}
