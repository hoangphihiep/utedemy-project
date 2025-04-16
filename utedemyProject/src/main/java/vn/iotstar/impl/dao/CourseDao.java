package vn.iotstar.impl.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.ICourseDao;
import vn.iotstar.entity.Course;

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

}