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
import vn.iotstar.entity.Teacher;
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
	public boolean checkExistPhoneNumber(String phonenumber) {
		EntityManager enma = JPAConfig.getEntityManager();
		//EntityTransaction trans = enma.getTransaction();
		try {
	        TypedQuery<Long> query = enma.createQuery(
	            "SELECT COUNT(u) FROM User u WHERE u.phoneNumber = :phoneNumber", Long.class);
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
	        // Ép Hibernate fetch roles ngay khi query
	        String jpql = "SELECT u FROM User u JOIN FETCH u.roles WHERE u.id = :userId";
	        TypedQuery<User> query = em.createQuery(jpql, User.class);
	        query.setParameter("userId", userId);
	        
	        User user = query.getSingleResult();
	        System.out.println("domdom" + user);
	        return user.getRoles(); // đã được fetch trong JOIN FETCH

	    } catch (Exception e) {
	        e.printStackTrace();
	        return new HashSet<>();
	    } finally {
	        em.close(); // Đóng sau khi dữ liệu đã được load
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

	@Override
	public void registerTeacher(int idUser, Teacher teacher) {
		EntityManager enma = JPAConfig.getEntityManager();
	    EntityTransaction trans = enma.getTransaction();

	    try {
	        trans.begin();
	        User user = enma.find(User.class, idUser);

	        if (user == null) {
	            System.out.println("\n🔹 User không tồn tại\n");
	            return;
	        }

	        System.out.println("\n🔹 User đã tồn tại, lấy thông tin...\n");

	        // Kiểm tra Role "Teacher"
	        TypedQuery<Role> roleQuery = enma.createQuery("SELECT r FROM Role r WHERE r.id = :id", Role.class);
	        roleQuery.setParameter("id", 2); // ID của role "Teacher"
	        Role teacherRole = roleQuery.getSingleResult();

	        // Thêm role "Teacher" nếu chưa có
	        boolean hasTeacherRole = false;
	        for (Role role : user.getRoles()) {
	            if (role.getId() == teacherRole.getId()) {
	                hasTeacherRole = true;
	                break;
	            }
	        }
	        
	        if (!hasTeacherRole) {
	            user.addRole(teacherRole);
	            enma.merge(user);
	            enma.flush(); // Đảm bảo thay đổi được lưu vào DB trước khi tiếp tục
	        }

	        // Kiểm tra xem User đã là Teacher hay chưa bằng JPQL
	        TypedQuery<Long> countQuery = enma.createQuery(
	            "SELECT COUNT(t) FROM Teacher t WHERE t.id = :userId", Long.class);
	        countQuery.setParameter("userId", idUser);
	        long teacherExists = countQuery.getSingleResult();

	        if (teacherExists == 0) {
	            // Chưa là Teacher => Tạo Teacher mới
	            System.out.println("\n🔹 Đăng ký trở thành giảng viên...\n");
	            
	            // Chú ý: Không tạo Teacher từ đầu, mà dùng query SQL để insert trực tiếp
	            String insertTeacherSQL = 
	                "INSERT INTO teacher (id, taxCode, identityCard, frontIdentityUrl, backIdentityUrl, " +
	                "description, socialUrl, bankAccountNumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	                
	            enma.createNativeQuery(insertTeacherSQL)
	                .setParameter(1, idUser)
	                .setParameter(2, teacher.getTaxCode())
                    .setParameter(3, teacher.getIdentityCard())
                    .setParameter(4, teacher.getFrontIdentityUrl())
                    .setParameter(5, teacher.getBackIdentityUrl())
                    .setParameter(6, teacher.getDescription())
                    .setParameter(7, teacher.getSocialUrl())
                    .setParameter(8, teacher.getBankAccountNumber())
	                .executeUpdate();
	        } else {
	            System.out.println("\n🔹 User đã là giảng viên, không cần đăng ký lại!\n");
	        }

	        trans.commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        trans.rollback();
	        throw e;
	        }
		
	}
}
