package vn.iotstar.dao;

import vn.iotstar.entity.Course;
import vn.iotstar.entity.FavoriteCourse;
import vn.iotstar.entity.User;

public interface IFavoriteCourseDao {
	
	public FavoriteCourse findByIdUser(int idUser);

	boolean addCourseToFavorite(User user, Course course);

	boolean removeCourseFromFavorite(User user, Course course);

	boolean isCourseInFavorite(User user, Course course);

}
