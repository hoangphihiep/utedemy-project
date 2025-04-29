package vn.iotstar.impl.service;

import vn.iotstar.dao.ICourseDetailDAO;
import vn.iotstar.impl.dao.CourseDetailDAO;
import vn.iotstar.entity.CourseDetail;
import vn.iotstar.service.ICourseDetailService;

public class CourseDetailService implements ICourseDetailService {

    private ICourseDetailDAO courseDetailDAO;

    public CourseDetailService() {
        this.courseDetailDAO = new CourseDetailDAO();
    }

    @Override
    public CourseDetail getCourseDetailById(int id) {
        return courseDetailDAO.findByCourseId(id);
    }
}