package vn.iotstar.impl.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IOrderDao;
import vn.iotstar.entity.Orders;

public class OrderDao implements IOrderDao{
	  @Override
	   public boolean insertOrder(Orders order) {
	        EntityManager em = JPAConfig.getEntityManager();
	        EntityTransaction trans = em.getTransaction();

	        try {
	            trans.begin();

	            em.persist(order); // Lưu order và cascade lưu luôn orderItems

	            trans.commit();
	            return true; // Thành công
	        } catch (Exception e) {
	            e.printStackTrace();
	            if (trans.isActive()) trans.rollback();
	            return false; // Lỗi
	        } finally {
	            //em.close();
	        }
	    }
}
