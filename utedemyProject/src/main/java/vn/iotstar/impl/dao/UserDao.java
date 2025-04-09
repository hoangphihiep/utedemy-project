package vn.iotstar.impl.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.entity.User;

public class UserDao implements IUserDao {

	@Override
	public boolean checkExistEmail(String email) {
		EntityManager enma = JPAConfig.getEntityManager();
		//EntityTransaction trans = enma.getTransaction();
		try {
	        TypedQuery<Long> query = enma.createQuery(
	            "SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class);
	        query.setParameter("email", email);
	        Long count = query.getSingleResult();
	        return count > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean updatePassword(String email, String newPassword) {
	    EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();

	    try {
	        trans.begin();
	        
	        // Sử dụng JPQL để cập nhật trực tiếp mật khẩu
	        int updatedRows = em.createQuery(
	                "UPDATE User u SET u.password = :newPassword WHERE u.email = :email")
	                .setParameter("newPassword", newPassword)
	                .setParameter("email", email)
	                .executeUpdate();
	        
	        trans.commit();
	        
	        // Trả về true nếu có ít nhất 1 dòng được cập nhật
	        return updatedRows > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        if (trans.isActive()) {
	            trans.rollback();
	        }
	        return false;
	    } finally {
	        em.close();
	    }
	}
	
}
