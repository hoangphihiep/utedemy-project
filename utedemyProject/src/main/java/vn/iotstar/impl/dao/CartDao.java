package vn.iotstar.impl.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.ICartDao;
import vn.iotstar.entity.Cart;

public class CartDao implements ICartDao{
	
	EntityManager enma = JPAConfig.getEntityManager();

	@Override
	public Cart findByUserId(int userId) {
		
		 try {
	            return enma.createQuery("SELECT c FROM Cart c WHERE c.user.id = :userId", Cart.class)
	                     .setParameter("userId", userId)
	                     .getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        }
	}

}
