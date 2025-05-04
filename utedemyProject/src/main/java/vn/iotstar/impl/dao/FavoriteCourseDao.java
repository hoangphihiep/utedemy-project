package vn.iotstar.impl.dao;

import java.util.HashSet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IFavoriteCourseDao;
import vn.iotstar.entity.Course;
import vn.iotstar.entity.FavoriteCourse;
import vn.iotstar.entity.User;

public class FavoriteCourseDao implements IFavoriteCourseDao {

	@Override
	public FavoriteCourse findByIdUser(int idUser) {
		EntityManager em = JPAConfig.getEntityManager();
	    try {
            return em.createQuery("SELECT f FROM FavoriteCourse f WHERE f.user.id = :userId", FavoriteCourse.class)
                    .setParameter("userId", idUser)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
	}

	@Override
	public boolean addCourseToFavorite(User user, Course course) {
		EntityManager em = JPAConfig.getEntityManager();
	    try {
	        // Start transaction
	        em.getTransaction().begin();

	        // Get managed instances of the entities
	        User managedUser = em.find(User.class, user.getId());
	        Course managedCourse = em.find(Course.class, course.getId());

	        if (managedUser == null || managedCourse == null) {
	            return false; // User or course doesn't exist
	        }

	        // Find existing favorite course for user or create new one
	        FavoriteCourse favoriteCourse = findByIdUser(managedUser.getId());
	        if (favoriteCourse == null) {
	            favoriteCourse = new FavoriteCourse();
	            favoriteCourse.setUser(managedUser);
	            favoriteCourse.setCourses(new HashSet<>());
	            managedUser.setFavoriteCourse(favoriteCourse);
	        } else {
	            // Make sure the favoriteCourse is managed
	            favoriteCourse = em.merge(favoriteCourse);
	        }

	        // Add course to favorite courses if not already there
	        if (!favoriteCourse.getCourses().contains(managedCourse)) {
	            favoriteCourse.getCourses().add(managedCourse);
	        }

	        // Save or update the favorite course
	        if (favoriteCourse.getId() == 0) {
	            em.persist(favoriteCourse);
	        } else {
	            favoriteCourse = em.merge(favoriteCourse);
	        }

	        // Commit transaction
	        em.getTransaction().commit();
	        return true;
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	        return false;
	    } finally {
	        em.close();
	    }
	}

	@Override
	public boolean removeCourseFromFavorite(User user, Course course) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
            // Start transaction
            em.getTransaction().begin();

            // Find existing favorite course for user
            FavoriteCourse favoriteCourse = findByIdUser(user.getId());
            if (favoriteCourse != null && favoriteCourse.getCourses().contains(course)) {
                favoriteCourse.getCourses().remove(course);
                em.merge(favoriteCourse);
            }

            // Commit transaction
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public boolean isCourseInFavorite(User user, Course course) {
		if (user == null || course == null) {
            return false;
        }

        FavoriteCourse favoriteCourse = findByIdUser(user.getId());
        return favoriteCourse != null && favoriteCourse.getCourses().contains(course);
	}

}