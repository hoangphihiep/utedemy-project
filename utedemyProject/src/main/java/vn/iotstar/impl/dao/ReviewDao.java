package vn.iotstar.impl.dao;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import vn.iotstar.dao.IReviewDao;
import vn.iotstar.entity.*;

public class ReviewDao implements IReviewDao{
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");

	@Override
	public List<Review> getAllReviews() {
	    EntityManager em = emf.createEntityManager();
	    List<Review> reviews = new ArrayList<>();
	    try {
	        String sql = "SELECT o FROM Review o";
	        reviews = em.createQuery(sql, Review.class).getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (em != null && em.isOpen()) {
	            em.close(); // Ensuring session is closed after use
	        }
	    }
	    return reviews;
	}
}