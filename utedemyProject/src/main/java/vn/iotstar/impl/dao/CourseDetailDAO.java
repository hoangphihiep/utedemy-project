package vn.iotstar.impl.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import vn.iotstar.dao.ICourseDetailDAO;
import vn.iotstar.entity.Course;
import vn.iotstar.entity.CourseDetail;
import vn.iotstar.entity.CourseProgress;
import vn.iotstar.entity.Section;

import java.util.ArrayList;
import java.util.List;

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
                
                // Tải trước collection sections
                Hibernate.initialize(courseDetail.getCourse().getSections());
                
                // Tải trước các collection lessons và quizs trong từng section
                for (Section section : courseDetail.getCourse().getSections()) {
                    Hibernate.initialize(section.getLessons());
                    Hibernate.initialize(section.getQuizs());
                }
            }
            return courseDetail;
        } finally {
            em.close();
        }
    }
    @Override
    public CourseDetail findByCourseId(int courseId) {
        EntityManager em = emf.createEntityManager();
        try {
            Course course = em.find(Course.class, courseId);
            if (course != null) {
                return course.getCourseDetail();
            }
            return null;
        } finally {
         //   em.close();
        }
    }

    @Override
	public List<CourseProgress> getAllCourseProgress() {
		EntityManager em = emf.createEntityManager();
		List<CourseProgress> c = new ArrayList<>();
		try {
			String sql = "SELECT c FROM CourseProgress c";
			c = em.createQuery(sql, CourseProgress.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null && em.isOpen()) {
				em.close(); // Ensuring session is closed after use
			}
		}
		return c;
	}


}