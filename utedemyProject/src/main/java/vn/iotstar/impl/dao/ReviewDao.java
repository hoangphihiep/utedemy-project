package vn.iotstar.impl.dao;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IReviewDao;
import vn.iotstar.entity.*;

public class ReviewDao implements IReviewDao{
	
	@Override
    public List<Review> getAllReviews() {
        // ✅ SỬ DỤNG JPAConfig thay vì tạo EntityManagerFactory riêng
        EntityManager em = JPAConfig.getEntityManager();
        
        try {
            String sql = "SELECT r FROM Review r";
            return em.createQuery(sql, Review.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Trả về empty list thay vì null
        } finally {
            if (em != null && em.isOpen()) {
                em.close(); // ✅ Đóng EntityManager
            }
        }
    }
}