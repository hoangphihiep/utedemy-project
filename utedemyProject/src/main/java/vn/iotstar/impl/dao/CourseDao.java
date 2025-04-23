package vn.iotstar.impl.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.ICourseDao;
import vn.iotstar.entity.Course;
import vn.iotstar.entity.CourseDetail;
import vn.iotstar.entity.CourseType;

public class CourseDao implements ICourseDao {

	private EntityManager entityManager;

	@Override
	public List<Object[]> findBestSellingCourseDetails(int limit) {
	    EntityManager em = JPAConfig.getEntityManager();
	    try {
	    	String jpql = "SELECT c.courseName, t.fullname, AVG(CAST(r.rate AS double)), c.coursePrice, cd.courseImage " +
	                "FROM OrderItem o " +
	                "JOIN o.course c " +
	                "JOIN c.teacher t " +
	                "LEFT JOIN c.review r " +
	                "JOIN c.courseDetail cd " +
	                "GROUP BY c.id, c.courseName, t.fullname, c.coursePrice, cd.courseImage " +
	                "ORDER BY COUNT(o.id) DESC";

	        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
	        query.setMaxResults(limit);
	        return query.getResultList();
	    } finally {
	        em.close();
	    }
	}

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
	public List<Object[]> findTodaySaleCourses(int limit) {
	    EntityManager em = JPAConfig.getEntityManager();
	    try {
	    	String jpql = "SELECT c.courseName, t.fullname, AVG(CAST(r.rate AS double)), c.coursePrice, cd.courseImage, v.percentage " +
	    			"FROM Course c " +
	    			"JOIN c.teacher t " +
	    			"LEFT JOIN c.review r " +
	    			"JOIN c.courseDetail cd " +
	    			"JOIN c.vouchers v " +
	    			"WHERE CURRENT_DATE BETWEEN v.dateSet AND v.dateEnd " +
	    			"GROUP BY c.id, c.courseName, t.fullname, c.coursePrice, cd.courseImage, v.percentage " +
	    			"ORDER BY v.percentage DESC";

	        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
	        query.setMaxResults(limit);
	        return query.getResultList();
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

	@Override
	public boolean saveCourseDetail(CourseDetail courseDetail) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    
	    try {
	        trans.begin();
	        em.merge(courseDetail);
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
	public boolean updateCourse(Course course) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			// gọi phuong thức để insert, update, delete
			enma.merge(course);
			trans.commit();
			return true;
		} catch (Exception e) {
			if (trans.isActive()) {
	            trans.rollback();
	        }
	        e.printStackTrace();
			return false;
		} finally {
			enma.close();
		}
	}

	@Override
	public int maxCourseDetailId() {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    try {
	        trans.begin();
	        String jpql = "SELECT MAX(c.id) FROM CourseDetail c";
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

	@Override
	public CourseDetail findByIdCourseDetail(int id) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    try {
	        trans.begin();
	        CourseDetail courseDetail = em.find(CourseDetail.class, id);
	        trans.commit();
	        return courseDetail;
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
}

