package vn.iotstar.impl.dao;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.ICourseDao;
import vn.iotstar.entity.Course;
import vn.iotstar.entity.CourseDetail;
import vn.iotstar.entity.CourseType;
import vn.iotstar.entity.Lesson;
import vn.iotstar.entity.OrderItem;
import vn.iotstar.entity.Question;
import vn.iotstar.entity.Quiz;
import vn.iotstar.entity.Review;
import vn.iotstar.entity.Section;
import vn.iotstar.entity.Answer;
import vn.iotstar.entity.User;

public class CourseDao implements ICourseDao {

	private EntityManager entityManager;

	@Override
	public List<Object[]> findBestSellingCourseDetails(int limit) {
	    EntityManager em = JPAConfig.getEntityManager();
	    try {
	        String jpql = "SELECT c.courseName, t.fullname, AVG(CAST(r.rate AS double)), c.coursePrice, cd.courseImage, c.id " +
	                "FROM Course c " +
	                "LEFT JOIN c.teacher t " +
	                "LEFT JOIN c.review r " +
	                "LEFT JOIN c.courseDetail cd " +
	                "LEFT JOIN c.orderItems o " +
	                "GROUP BY c.id, c.courseName, t.fullname, c.coursePrice, cd.courseImage " +
	                "ORDER BY COUNT(o.id) DESC";
	        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
	        query.setMaxResults(limit);
	        return query.getResultList();
	    } finally {
	        em.close();
	    }
	}


	@Override
	public List<CourseType> listCourseType() {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    
	    try {
	        trans.begin();
	        String jpql = "SELECT c FROM CourseType c";
	        List<CourseType> courseTypes = em.createQuery(jpql, CourseType.class).getResultList();
	        trans.commit();
	        return courseTypes;
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
	public List<Object[]> findTodaySaleCourses(int limit) {
	    EntityManager em = JPAConfig.getEntityManager();
	    try {
	    	String jpql = "SELECT c.courseName, t.fullname, AVG(CAST(r.rate AS double)), c.coursePrice, cd.courseImage, v.percentage, c.id " +
	    			"FROM Course c " +
	    			"JOIN c.teacher t " +
	    			"LEFT JOIN c.review r " +
	    			"JOIN c.courseDetail cd " +
	    			"JOIN c.vouchers v " +
	    			"WHERE CURRENT_DATE BETWEEN v.dateSet AND v.dateEnd " +
	    			"GROUP BY c.id, c.courseName, t.fullname, c.coursePrice, cd.courseImage, v.percentage " +
	    			"ORDER BY v.percentage DESC";

	        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
	        query.setMaxResults(limit);
	        return query.getResultList();
	    } finally {
	        em.close();
	    }
	}

    @Override
	public boolean saveCoure(Course course) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    
	    try {
	        trans.begin();
	        em.merge(course);
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
	public CourseType findByIDCourseType(int id) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    try {
	        trans.begin();
	        CourseType courseType = em.find(CourseType.class, id);
	        trans.commit();
	        return courseType;
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

	/*@Override
	public Course findByIdCourse(int id) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    try {
	        trans.begin();
	        Course course = em.find(Course.class, id);
	        trans.commit();
	        return course;
	    } catch (Exception e) {
	        if (trans.isActive()) {
	            trans.rollback();
	        }
	        e.printStackTrace();
	        return null;
	    } finally {
	       // em.close();
	    }
	}*/
	@Override
	public Course findByIdCourse(int id) {
	    System.out.println("Get EntityManager");
	    EntityManager em = JPAConfig.getEntityManager();
	    System.out.println("Got EntityManager");
	    try {
	        System.out.println("Before find");
	        Course course = em.find(Course.class, id);
	        System.out.println("After find");
	        return course;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        em.close();
	    }
	}



	@Override
	public int maxCourseId() {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    try {
	        trans.begin();
	        String jpql = "SELECT MAX(c.id) FROM Course c";
	        Query query = em.createQuery(jpql);
	        Integer maxId = (Integer) query.getSingleResult();
	        trans.commit();
	        return maxId != null ? maxId : 0;
	    } catch (Exception e) {
	        if (trans.isActive()) {
	            trans.rollback();
	        }
	        e.printStackTrace();
	        return 0;
	    } finally {
	        em.close();
	    }
	}

	@Override
	public boolean saveCourseDetail(CourseDetail courseDetail) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    
	    try {
	        trans.begin();
	        em.merge(courseDetail);
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
	public boolean updateCourse(Course course) {
		EntityManager enma = JPAConfig.getEntityManager();
	    EntityTransaction trans = enma.getTransaction();
	    try {
	        trans.begin();
	        
	        // Lấy các collection hiện tại để tránh xung đột
	        Course existingCourse = enma.find(Course.class, course.getId());
	        if (existingCourse != null) {
	            // Giữ nguyên các collection để tránh sửa đổi đồng thời
	            course.setSections(existingCourse.getSections());
	            course.setReview(existingCourse.getReview());
	            course.setFavoriteCourse(existingCourse.getFavoriteCourse());
	            course.setCart(existingCourse.getCart());
	            course.setVouchers(existingCourse.getVouchers());
	            course.setDiscounts(existingCourse.getDiscounts());
	        }
	        
	        // Sau đó merge
	        enma.merge(course);
	        trans.commit();
	        return true;
	    } catch (Exception e) {
	        if (trans.isActive()) {
	            trans.rollback();
	        }
	        e.printStackTrace();
	        return false;
	    } finally {
	        enma.close();
	    }
	}

	@Override
	public int maxCourseDetailId() {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    try {
	        trans.begin();
	        String jpql = "SELECT MAX(c.id) FROM CourseDetail c";
	        Query query = em.createQuery(jpql);
	        Integer maxId = (Integer) query.getSingleResult();
	        trans.commit();
	        return maxId != null ? maxId : 0;
	    } catch (Exception e) {
	        if (trans.isActive()) {
	            trans.rollback();
	        }
	        e.printStackTrace();
	        return 0;
	    } finally {
	        em.close();
	    }
	}

	@Override
	public CourseDetail findByIdCourseDetail(int id) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    try {
	        trans.begin();
	        CourseDetail courseDetail = em.find(CourseDetail.class, id);
	        trans.commit();
	        return courseDetail;
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
	public boolean updateCourseDetail(CourseDetail courseDetail) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			// gọi phuong thức để insert, update, delete
			enma.merge(courseDetail);
			trans.commit();
			return true;
		} catch (Exception e) {
			if (trans.isActive()) {
	            trans.rollback();
	        }
	        e.printStackTrace();
			return false;
		} finally {
			enma.close();
		}
	}
	@Override
	public boolean saveLesson(Lesson lesson) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    
	    try {
	        trans.begin();
	        em.persist(lesson);
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
	public Section findByIdSection(int id) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    try {
	        trans.begin();
	        Section section = em.find(Section.class, id);
	        trans.commit();
	        return section;
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
	public boolean saveQuiz(Quiz quiz) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    
	    try {
	        trans.begin();
	        em.persist(quiz);
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
	public int maxQuizId() {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    try {
	        trans.begin();
	        String jpql = "SELECT MAX(q.id) FROM Quiz q";
	        Query query = em.createQuery(jpql);
	        Integer maxId = (Integer) query.getSingleResult();
	        trans.commit();
	        return maxId != null ? maxId : 0;
	    } catch (Exception e) {
	        if (trans.isActive()) {
	            trans.rollback();
	        }
	        e.printStackTrace();
	        return 0;
	    } finally {
	        em.close();
	    }
	}
	
	@Override
	public Quiz findByIdQuiz(int id) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    try {
	        trans.begin();
	        Quiz quiz = em.find(Quiz.class, id);
	        trans.commit();
	        return quiz;
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
	public boolean saveQuestion(Question question) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();

	    try {
	        trans.begin();
	        em.persist(question);
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
	public int maxQuestionId() {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    try {
	        trans.begin();
	        String jpql = "SELECT MAX(q.id) FROM Question q";
	        Query query = em.createQuery(jpql);
	        Integer maxId = (Integer) query.getSingleResult();
	        trans.commit();
	        return maxId != null ? maxId : 0;
	    } catch (Exception e) {
	        if (trans.isActive()) {
	            trans.rollback();
	        }
	        e.printStackTrace();
	        return 0;
	    } finally {
	        em.close();
	    }
	}
	
	@Override
	public Question findByIdQuestion(int id) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    try {
	        trans.begin();
	        Question question = em.find(Question.class, id);
	        trans.commit();
	        return question;
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
	public boolean saveAnswer(Answer answer) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    
	    try {
	        trans.begin();
	        em.persist(answer);
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
	public boolean updateSection(Section section) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			// gọi phuong thức để insert, update, delete
			enma.merge(section);
			trans.commit();
			return true;
		} catch (Exception e) {
			if (trans.isActive()) {
	            trans.rollback();
	        }
	        e.printStackTrace();
			return false;
		} finally {
			enma.close();
		}
	}
	
	@Override
	public Lesson findByIdLesson(int id) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    try {
	        trans.begin();
	        Lesson lesson = em.find(Lesson.class, id);
	        trans.commit();
	        return lesson;
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
	public boolean updateLesson(Lesson lesson) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			// gọi phuong thức để insert, update, delete
			enma.merge(lesson);
			trans.commit();
			return true;
		} catch (Exception e) {
			if (trans.isActive()) {
	            trans.rollback();
	        }
	        e.printStackTrace();
			return false;
		} finally {
			enma.close();
		}
	}
	
	@Override
	public boolean updateQuiz(Quiz quiz) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    try {
	        tx.begin();

	        Quiz existing = em.find(Quiz.class, quiz.getId());
	        if (existing != null) {
	            existing.setTitle(quiz.getTitle());
	            existing.setDescription(quiz.getDescription());
	            existing.setDuration(quiz.getDuration());
	            existing.getQuestions().clear();
	            for (Question q : quiz.getQuestions()) {
	                q.setQuiz(existing);
	                
	                for (Answer a : q.getAnswers()) {
	                    a.setQuestion(q);
	                }
	                
	                existing.getQuestions().add(q);
	            }

	            em.merge(existing);
	        }
	        
	        tx.commit();
	        return true;
	    } catch (Exception e) {
	        tx.rollback();
	        throw new RuntimeException(e);
	        
	    } finally {
	        em.close();
	    }
	}
	
	@Override
	public boolean deleteSection(int id) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
	    EntityTransaction trans = enma.getTransaction();
	    try {
	        trans.begin();
	        Section section = enma.find(Section.class, id);
	        if (section != null) {
	            enma.remove(section);
	            trans.commit();
	            return true;
	        } else {
	            trans.rollback();
	            return false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        if (trans.isActive()) {
	            trans.rollback();
	        }
	        throw e; // hoặc return false;
	    } finally {
	        enma.close();
	    }
	}
	
	@Override
	public boolean deleteLesson(int id) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
	    EntityTransaction trans = enma.getTransaction();
	    try {
	        trans.begin();
	        Lesson lesson = enma.find(Lesson.class, id);
	        if (lesson != null) {
	            enma.remove(lesson);
	            trans.commit();
	            return true;
	        } else {
	            trans.rollback();
	            return false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        if (trans.isActive()) {
	            trans.rollback();
	        }
	        throw e;
	    } finally {
	        enma.close();
	    }
	}
	
	@Override
	public boolean deleteQuiz(int id) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
	    EntityTransaction trans = enma.getTransaction();
	    try {
	        trans.begin();
	        Quiz quiz = enma.find(Quiz.class, id);
	        if (quiz != null) {
	            enma.remove(quiz);
	            trans.commit();
	            return true;
	        } else {
	            trans.rollback();
	            return false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        if (trans.isActive()) {
	            trans.rollback();
	        }
	        throw e;
	    } finally {
	        enma.close();
	    }
	}
	
	@Override
	public int maxSectionId() {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    try {
	        trans.begin();
	        String jpql = "SELECT MAX(s.id) FROM Section s";
	        Query query = em.createQuery(jpql);
	        Integer maxId = (Integer) query.getSingleResult();
	        trans.commit();
	        return maxId != null ? maxId : 0;
	    } catch (Exception e) {
	        if (trans.isActive()) {
	            trans.rollback();
	        }
	        e.printStackTrace();
	        return 0;
	    } finally {
	        em.close();
	    }
	}
	
	@Override
	public boolean saveSection(Section section) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();
	    
	    try {
	        trans.begin();
	        em.persist(section);
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
	public List<Course> findByIdTeacher(User user) {
		if (user == null) {
	        return Collections.emptyList();
	    }

	    EntityManager enma = JPAConfig.getEntityManager();
	    String jpql = "SELECT c FROM Course c WHERE c.teacher.id = :teacherId";
	    TypedQuery<Course> query = enma.createQuery(jpql, Course.class);
	    query.setParameter("teacherId", user.getId());
	    return query.getResultList();
	}
	@Override
	public List<Course> findAllCourse() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Course> query = enma.createNamedQuery("Course.findAll", Course.class);
		return query.getResultList();
	}
	
	@Override
	public List<Course> getAllCourses() {
		EntityManager em = JPAConfig.getEntityManager();
		List<Course> Courses = new ArrayList<>();
		try {
			String sql = "SELECT c FROM Course c";
			Courses = em.createQuery(sql, Course.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null && em.isOpen()) {
				em.close(); // Ensuring session is closed after use
			}
		}
		return Courses;
	}
	
	@Override
	public List<Lesson> getAllLessons() {
		EntityManager em = JPAConfig.getEntityManager();
		List<Lesson> lessons = new ArrayList<>();
		try {
			// JPQL query to fetch all lessons
			String sql = "SELECT l FROM Lesson l";
			lessons = em.createQuery(sql, Lesson.class).getResultList();
		} catch (Exception e) {
			// Handle exceptions (consider logging instead of printing stack trace in
			// production)
			e.printStackTrace();
		} finally {
			// Ensure the EntityManager is closed after use
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		return lessons;
	}
	public List<Course> filterCoursesByRatingAndCourse(List<OrderItem> orderItems, double ratingThreshold) {
		EntityManager em = JPAConfig.getEntityManager();
		// Tạo truy vấn JPQL để lấy khóa học với rating >= ratingThreshold
		String query = "SELECT c FROM Course c " + "JOIN c.reviews r " + // JOIN với bảng Review để truy xuất điểm đánh
																			// giá
				"JOIN c.orderItems oi " + // JOIN với bảng OrderItem để kiểm tra khóa học có trong order
				"WHERE r.rating >= :ratingThreshold " + // Điều kiện lọc theo rating
				"AND oi.course.id = c.id"; // Điều kiện kiểm tra khóa học trong OrderItem

		TypedQuery<Course> typedQuery = em.createQuery(query, Course.class);
		typedQuery.setParameter("ratingThreshold", ratingThreshold);

		// Trả về danh sách các khóa học thỏa mãn điều kiện
		return typedQuery.getResultList();
	}

	// Phương thức tính rating trung bình của khóa học
	public double calculateAverageRating(Course course) {
		// Truyền vào course để lấy danh sách review
		Set<Review> reviews = course.getReview(); // Sử dụng getter của Review từ lớp Course
		if (reviews.isEmpty()) {
			return 0;
		}
		double totalRating = 0;
		for (Review review : reviews) {
			totalRating += review.getRate();
		}
		return totalRating / reviews.size();
	}
	// Phương thức lọc các khóa học theo rating
		public List<Course> filterCoursesByRating(double ratingThreshold) {
			EntityManager em = JPAConfig.getEntityManager();
			String query = "SELECT c FROM Course c JOIN c.review r WHERE r.rating >= :ratingThreshold";
			TypedQuery<Course> typedQuery = em.createQuery(query, Course.class);
			typedQuery.setParameter("ratingThreshold", ratingThreshold);

			return typedQuery.getResultList();
		}
		public List<Course> getCoursesByUserId(int userId) {
			EntityManager em =JPAConfig.getEntityManager();
			List<Course> courseResults = new ArrayList<>();
			try {
				String queryStr = "SELECT oi.course FROM OrderItem oi WHERE oi.order.user.id = :userId";
				TypedQuery<Course> query = em.createQuery(queryStr, Course.class);
				query.setParameter("userId", userId);
				courseResults = query.getResultList();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (em != null && em.isOpen())
					em.close();
			}
			return courseResults;
		}
		@Override
		public Course getCourseById(int Id) {
			EntityManager em = JPAConfig.getEntityManager();
			Course course = null;
			try {
				course = em.find(Course.class, Id); // Lấy 1 đối tượng theo khóa chính
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (em != null && em.isOpen()) {
					em.close();
				}
			}
			return course;
		}
		@Override
		public int getMaxCourseId() {
			EntityManager em = JPAConfig.getEntityManager();
			try {
				// Truy vấn lấy ID lớn nhất từ bảng Course
				String jpql = "SELECT MAX(c.id) FROM Course c";
				Integer maxId = (Integer) em.createQuery(jpql).getSingleResult();

				// Trả về maxId nếu có, nếu không trả về 0 (hoặc giá trị mặc định khác)
				return maxId != null ? maxId : 0;
			} catch (Exception e) {
				e.printStackTrace();
				return 0; // Trả về 0 nếu có lỗi xảy ra
			} finally {
				// Đảm bảo EntityManager được đóng
				if (em != null && em.isOpen()) {
					em.close();
				}
			}
		}
		@Override
		public void addCourse(Course course) {
			EntityManager em = JPAConfig.getEntityManager();
			EntityTransaction trans = em.getTransaction();

			try {
				if (course == null) {
					throw new IllegalArgumentException("Course cannot be null");
				}

				trans.begin();

				// Lấy CourseType từ DB thay vì chỉ set ID
				int courseTypeId = course.getCourseType().getId();
				CourseType courseType = em.find(CourseType.class, courseTypeId);
				if (courseType == null) {
					throw new IllegalArgumentException("CourseType not found with id: " + courseTypeId);
				}

				// Gắn courseType đã lấy vào course
				course.setCourseType(courseType);

				// Lưu course vào DB
				em.persist(course);

				// Commit transaction
				trans.commit();
			} catch (Exception e) {
				// Nếu transaction vẫn còn active, rollback
				if (trans.isActive()) {
					trans.rollback();
				}
				e.printStackTrace();
			} finally {
				if (em != null && em.isOpen()) {
					em.close(); // Đảm bảo đóng EntityManager
				}
			}
		}

		
		@Override
		public void addCourseType(CourseType courseType) {
		    EntityManager em = JPAConfig.getEntityManager();
		    EntityTransaction trans = em.getTransaction();

		    try {
		        if (courseType == null) {
		            throw new IllegalArgumentException("CourseType cannot be null");
		        }

		        trans.begin();

		        // Lưu courseType vào DB
		        em.persist(courseType);

		        // Commit transaction
		        trans.commit();
		    } catch (Exception e) {
		        if (trans.isActive()) {
		            trans.rollback();
		        }
		        e.printStackTrace();
		    } finally {
		        if (em != null && em.isOpen()) {
		            em.close(); // Đảm bảo đóng EntityManager
		        }
		    }
		}
		
		@Override
		public List<CourseType> getAllCourseTypes() {
		    EntityManager em = JPAConfig.getEntityManager();
		    List<CourseType> courseTypes = new ArrayList<>();
		    
		    try {
		        String sql = "SELECT ct FROM CourseType ct";
		        courseTypes = em.createQuery(sql, CourseType.class).getResultList();
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        if (em != null && em.isOpen()) {
		            em.close(); // Đảm bảo đóng EntityManager
		        }
		    }

		    return courseTypes;
		}
		
		@Override
		public boolean updateCourseType(CourseType courseType) {
		    EntityManager enma = JPAConfig.getEntityManager();
		    EntityTransaction trans = enma.getTransaction();

		    try {
		        trans.begin();

		        // Kiểm tra xem có tồn tại không
		        CourseType existingType = enma.find(CourseType.class, courseType.getId());
		        if (existingType != null) {
		            existingType.setCourseTypeName(courseType.getCourseTypeName());
		            enma.merge(existingType);
		        } else {
		            return false; // Không tìm thấy
		        }

		        trans.commit();
		        return true;
		    } catch (Exception e) {
		        if (trans.isActive()) {
		            trans.rollback();
		        }
		        e.printStackTrace();
		        return false;
		    } finally {
		        enma.close();
		    }
		}
		
		@Override
		public CourseType getCourseTypeById(int id) {
		    EntityManager em = JPAConfig.getEntityManager();
		    CourseType courseType = null;
		    try {
		        courseType = em.find(CourseType.class, id); // Tìm theo khóa chính
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        if (em != null && em.isOpen()) {
		            em.close();
		        }
		    }
		    return courseType;
		}
}

