package vn.iotstar.impl.service;

import java.util.List;

import vn.iotstar.dao.ICourseDetailDAO;
import vn.iotstar.entity.CourseProgress;
import vn.iotstar.impl.dao.CourseDetailDAO;
import vn.iotstar.entity.CourseDetail;
import vn.iotstar.service.ICourseDetailService;
import vn.iotstar.entity.CourseType;

public class CourseDetailService implements ICourseDetailService {

    private ICourseDetailDAO courseDetailDAO;

    public CourseDetailService() {
        this.courseDetailDAO = new CourseDetailDAO();
    }

    @Override
    public CourseDetail getCourseDetailById(int id) {
        return courseDetailDAO.findByCourseId(id);
    }
    @Override
    public List<CourseProgress> getAllCourseProgress()
    {
    	return courseDetailDAO.getAllCourseProgress();
    }
    @Override
    public CourseType getCourseTypeById(int id)
    {
    	return courseDetailDAO.getCourseTypeById(id);
    }

    @Override
    public List<CourseType> getAllCourseTypes()
    {
    	return courseDetailDAO.getAllCourseTypes();
    }
}