package vn.iotstar.impl.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.entity.Role;
import vn.iotstar.entity.User;

public class UserDao implements IUserDao {

	@Override
	public void insert(User user) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			em.persist(user);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (trans.isActive()) {
				trans.rollback();
			}
		} finally {
			em.close();
		}
	}

	@Override
	public boolean checkExistEmail(String email) {
		EntityManager enma = JPAConfig.getEntityManager();
		// EntityTransaction trans = enma.getTransaction();
		try {
			TypedQuery<Long> query = enma.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class);
			query.setParameter("email", email);
			Long count = query.getSingleResult();
			return count > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean checkExistPhoneNumber(String phonenumber) {
		EntityManager enma = JPAConfig.getEntityManager();
		// EntityTransaction trans = enma.getTransaction();
		try {
			TypedQuery<Long> query = enma.createQuery("SELECT COUNT(u) FROM User u WHERE u.phoneNumber = :phoneNumber",
					Long.class);
			query.setParameter("phoneNumber", phonenumber);
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
			int updatedRows = em.createQuery("UPDATE User u SET u.password = :newPassword WHERE u.email = :email")
					.setParameter("newPassword", newPassword).setParameter("email", email).executeUpdate();

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

	@Override
	public User findByEmailAndPassword(String email, String password) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<User> query = em
					.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class);
			query.setParameter("email", email);
			query.setParameter("password", password);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public User findByPhoneAndPassword(String phone, String password) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<User> query = em.createQuery(
					"SELECT u FROM User u WHERE u.phoneNumber = :phone AND u.password = :password", User.class);
			query.setParameter("phone", phone);
			query.setParameter("password", password);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public User findById(int id) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			return em.find(User.class, id);
		} finally {
			em.close();
		}
	}

	public Set<Role> getRolesByUserId(int userId) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			// Use the exact SQL query that worked in your database
			String sql = "SELECT r.id, r.name FROM role r " + "JOIN user_roles ur ON r.id = ur.role_id "
					+ "WHERE ur.user_id = :userId";

			Query query = em.createNativeQuery(sql);
			query.setParameter("userId", userId);

			List<Object[]> results = query.getResultList();
			System.out.println("Raw query returned " + results.size() + " results");

			// Print each raw result
			for (int i = 0; i < results.size(); i++) {
				Object[] row = results.get(i);
				System.out.println("Result " + i + ": ID=" + row[0] + ", Name=" + row[1]);
			}

			Set<Role> roles = new HashSet<>();
			for (Object[] result : results) {
				Role role = new Role();
				role.setId(((Number) result[0]).intValue());
				role.setName((String) result[1]);
				roles.add(role);
				System.out.println("Added role to set: ID=" + role.getId() + ", Name=" + role.getName());
			}

			System.out.println("Final roles set size: " + roles.size());
			return roles;
		} catch (Exception e) {
			System.out.println("Exception occurred in getRolesByUserId: " + e.getMessage());
			e.printStackTrace();
			return new HashSet<>();
		}
	}

	@Override
	public void update(User user) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			em.merge(user);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (trans.isActive()) {
				trans.rollback();
			}
		} finally {
			em.close();
		}
	}

	@Override
	public boolean updateUserInformation(User user) {
		try {
			update(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");

	@Override
	public List<User> getAllUsers() {
		EntityManager em = emf.createEntityManager();
		List<User> users = null;
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
			users = query.getResultList();
		} finally {
			em.close();
		}
		return users;
	}

	public List<User> getUsersByRole(int roleId) {
		EntityManager em = emf.createEntityManager();
		List<User> teachers = null;
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u JOIN u.roles r WHERE r.id = :roleId",
					User.class);
			query.setParameter("roleId", roleId);
			teachers = query.getResultList();
		} finally {
			em.close();
		}
		return teachers;
	}
}
