package vn.iotstar.impl.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IDiscountDao;
import vn.iotstar.entity.Discount;

public class DiscountDao implements IDiscountDao {

	@Override
	public List<Discount> findByIdUser(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
	    try {
	        String jpql = "SELECT DISTINCT d FROM Discount d JOIN FETCH d.appliedCourses c WHERE c.teacher.id = :teacherId";
	        TypedQuery<Discount> query = enma.createQuery(jpql, Discount.class);
	        query.setParameter("teacherId", id);
	        return query.getResultList();
	    } finally {
	        enma.close();
	    }
	}

	@Override
	public boolean insert(Discount discount) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    
	    try {
	        trans.begin();
	        em.merge(discount);
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
	public Discount findById(int id) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    try {
	        trans.begin();
	        Discount discount = em.find(Discount.class, id);
	        trans.commit();
	        return discount;
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
	public boolean update(Discount discount) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction transaction = em.getTransaction();
	    
	    try {
	        transaction.begin();
	        em.merge(discount);
	        transaction.commit();
	        return true;
	    } catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
			return false;
	    } finally {
	        em.close();
	    }
	}

}
