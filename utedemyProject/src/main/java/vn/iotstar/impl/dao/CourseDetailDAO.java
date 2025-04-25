package vn.iotstar.impl.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import vn.iotstar.dao.ICourseDetailDAO;
import vn.iotstar.entity.CourseDetail;

public class CourseDetailDAO implements ICourseDetailDAO {

    private EntityManagerFactory emf;

    public CourseDetailDAO() {
        emf = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
    }

    @Override
    public CourseDetail findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(CourseDetail.class, id);
        } finally {
            em.close();
        }
    }
}