package vn.iotstar.impl.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import vn.iotstar.dao.ICourseDetailDAO;
import vn.iotstar.entity.CourseDetail;
import org.hibernate.Hibernate;

public class CourseDetailDAO implements ICourseDetailDAO {

    private EntityManagerFactory emf;

    public CourseDetailDAO() {
        emf = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
    }

    @Override
    public CourseDetail findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            CourseDetail courseDetail = em.find(CourseDetail.class, id);
            if (courseDetail != null && courseDetail.getCourse() != null) {
                // Tải trước collection review
                Hibernate.initialize(courseDetail.getCourse().getReview());
            }
            return courseDetail;
        } finally {
            em.close();
        }
    }
}