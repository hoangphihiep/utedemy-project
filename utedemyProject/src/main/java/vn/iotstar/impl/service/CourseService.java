package vn.iotstar.impl.service;

import java.util.List;

import vn.iotstar.dao.ICourseDao;
import vn.iotstar.entity.Course;
import vn.iotstar.entity.CourseDetail;
import vn.iotstar.entity.CourseType;
import vn.iotstar.impl.dao.CourseDao;
import vn.iotstar.service.ICourseService;

public class CourseService implements ICourseService {

	public ICourseDao courseDao = new CourseDao();
	@Override
	public List<CourseType> listCourseType() {
		return courseDao.listCourseType();
	}
	@Override
	public boolean saveCoure(Course course) {
		return courseDao.saveCoure(course);
	}
	@Override
	public CourseType findByIDCourseType(int id) {
		return courseDao.findByIDCourseType(id);
	}
	@Override
	public Course findByIdCourse(int id) {
		return courseDao.findByIdCourse(id);
	}
	@Override
	public int maxCourseId() {
		return courseDao.maxCourseId();
	}
	@Override
	public boolean saveCourseDetail(CourseDetail courseDetail) {
		return courseDao.saveCourseDetail(courseDetail);
	}
	@Override
	public boolean updateCourse(Course course) {
		return courseDao.updateCourse(course);
	}
	@Override
	public int maxCourseDetailId() {
		return courseDao.maxCourseDetailId();
	}
	@Override
	public CourseDetail findByIdCourseDetail(int id) {
		return courseDao.findByIdCourseDetail(id);
	}

}
