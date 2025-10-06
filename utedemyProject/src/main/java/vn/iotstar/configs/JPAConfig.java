package vn.iotstar.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAConfig {
    
    // Singleton EntityManagerFactory - chỉ tạo 1 lần duy nhất
    private static EntityManagerFactory entityManagerFactory;
    
    // Khởi tạo khi class được load
    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
        } catch (Exception e) {
            System.err.println("Failed to initialize EntityManagerFactory: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }
    
    /**
     * Lấy EntityManager mới từ factory
     * QUAN TRỌNG: Phải đóng EntityManager sau khi sử dụng
     */
    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null || !entityManagerFactory.isOpen()) {
            throw new IllegalStateException("EntityManagerFactory is not initialized or has been closed");
        }
        return entityManagerFactory.createEntityManager();
    }
    
    /**
     * Đóng EntityManagerFactory khi ứng dụng shutdown
     */
    public static void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
    
    /**
     * Kiểm tra trạng thái
     */
    public static boolean isOpen() {
        return entityManagerFactory != null && entityManagerFactory.isOpen();
    }
}