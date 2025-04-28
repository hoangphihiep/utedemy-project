package vn.iotstar.impl.service;

import vn.iotstar.dao.ICourseDetailDAO;
import vn.iotstar.impl.dao.CourseDetailDAO;
import vn.iotstar.entity.CourseDetail;
import vn.iotstar.service.ICourseDetailService;
import org.hibernate.Hibernate;

public class CourseDetailService implements ICourseDetailService {

    private ICourseDetailDAO courseDetailDAO;

    public CourseDetailService() {
        this.courseDetailDAO = new CourseDetailDAO();
    }

    @Override
    public CourseDetail getCourseDetailById(int id) {
        CourseDetail courseDetail = courseDetailDAO.findById(id);
        if (courseDetail != null && courseDetail.getCourse() != null) {
            // Tải trước collection review
            Hibernate.initialize(courseDetail.getCourse().getReview());
        }
        return courseDetail;
    }
}