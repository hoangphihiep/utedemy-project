package vn.iotstar.impl.service;

import vn.iotstar.dao.IFavoriteCourseDao;
import vn.iotstar.entity.Course;
import vn.iotstar.entity.FavoriteCourse;
import vn.iotstar.entity.User;
import vn.iotstar.impl.dao.FavoriteCourseDao;
import vn.iotstar.service.IFavoriteCourseService;

public class FavoriteCourseService implements IFavoriteCourseService {

	public IFavoriteCourseDao favoriteCourseDao = new FavoriteCourseDao();
	@Override
	public FavoriteCourse findByIdUser(int idUser) {
		return favoriteCourseDao.findByIdUser(idUser);
	}

	@Override
	public boolean addCourseToFavorite(User user, Course course) {
		return favoriteCourseDao.addCourseToFavorite(user, course);
	}

	@Override
	public boolean removeCourseFromFavorite(User user, Course course) {
		return favoriteCourseDao.removeCourseFromFavorite(user, course);
	}

	@Override
	public boolean isCourseInFavorite(User user, Course course) {
		// TODO Auto-generated method stub
		return false;
	}

}