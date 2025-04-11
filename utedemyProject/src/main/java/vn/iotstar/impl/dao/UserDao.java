package vn.iotstar.impl.dao;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
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
        try {
            TypedQuery<Long> query = enma.createQuery(
                "SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class);
            query.setParameter("email", email);
            Long count = query.getSingleResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            enma.close();
        }
    }

    @Override
    public boolean checkExistPhoneNumber(String phonenumber) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            TypedQuery<Long> query = enma.createQuery(
                "SELECT COUNT(u) FROM User u WHERE u.phoneNumber = :phoneNumber", Long.class);
            query.setParameter("phoneNumber", phonenumber);
            Long count = query.getSingleResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            enma.close();
        }
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            int updatedRows = em.createQuery(
                    "UPDATE User u SET u.password = :newPassword WHERE u.email = :email")
                    .setParameter("newPassword", newPassword)
                    .setParameter("email", email)
                    .executeUpdate();
            trans.commit();
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
            TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class);
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

    @Override
    public Set<Role> getRolesByUserId(int userId) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u JOIN FETCH u.roles WHERE u.id = :userId";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("userId", userId);
            User user = query.getSingleResult();
            System.out.println("domdom" + user);
            return user.getRoles();
        } catch (Exception e) {
            e.printStackTrace();
            return new HashSet<>();
        } finally {
            em.close();
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
}