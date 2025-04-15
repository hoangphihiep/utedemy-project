package vn.iotstar.impl.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.ICourseDao;
import vn.iotstar.entity.Course;
import vn.iotstar.entity.CourseType;

public class CourseDao implements ICourseDao {

	@Override
	public List<CourseType> listCourseType() {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    
	    try {
	        trans.begin();
	        String jpql = "SELECT c FROM CourseType c";
	        List<CourseType> courseTypes = em.createQuery(jpql, CourseType.class).getResultList();
	        trans.commit();
	        return courseTypes;
	    } catch (Exception e) {
	        if (trans.isActive()) {
	            trans.rollback();
	        }
	        e.printStackTrace();
	        return null;
	    } finally {
	        em.close();
	    }
	}

	@Override
	public boolean saveCoure(Course course) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    
	    try {
	        trans.begin();
	        em.merge(course);
	        trans.commit();
	        return true;
	    } catch (Exception e) {
	        if (trans.isActive()) {
	            trans.rollback();
	        }
	        e.printStackTrace();
	        return false;
	    } finally {
	        em.close();
	    }
	}

	@Override
	public CourseType findByIDCourseType(int id) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    try {
	        trans.begin();
	        CourseType courseType = em.find(CourseType.class, id);
	        trans.commit();
	        return courseType;
	    } catch (Exception e) {
	        if (trans.isActive()) {
	            trans.rollback();
	        }
	        e.printStackTrace();
	        return null;
	    } finally {
	        em.close();
	    }
	}

	@Override
	public Course findByIdCourse(int id) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    try {
	        trans.begin();
	        Course course = em.find(Course.class, id);
	        trans.commit();
	        return course;
	    } catch (Exception e) {
	        if (trans.isActive()) {
	            trans.rollback();
	        }
	        e.printStackTrace();
	        return null;
	    } finally {
	        em.close();
	    }
	}

	@Override
	public int maxCourseId() {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    try {
	        trans.begin();
	        String jpql = "SELECT MAX(c.id) FROM Course c";
	        Query query = em.createQuery(jpql);
	        Integer maxId = (Integer) query.getSingleResult();
	        trans.commit();
	        return maxId != null ? maxId : 0;
	    } catch (Exception e) {
	        if (trans.isActive()) {
	            trans.rollback();
	        }
	        e.printStackTrace();
	        return 0;
	    } finally {
	        em.close();
	    }
	}
}
