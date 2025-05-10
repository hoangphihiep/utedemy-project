package vn.iotstar.impl.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import vn.iotstar.entity.Question;
import vn.iotstar.entity.Quiz;
import vn.iotstar.entity.Section;
import vn.iotstar.entity.Answer;
import vn.iotstar.entity.User;

public class CourseDao implements ICourseDao {

	@Override
	public List<Object[]> findBestSellingCourseDetails(int limit) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			String jpql = "SELECT c.courseName, t.fullname, AVG(CAST(r.rate AS double)), c.coursePrice, cd.courseImage, c.id "
					+ "FROM Course c " + "LEFT JOIN c.teacher t " + "LEFT JOIN c.review r "
					+ "LEFT JOIN c.courseDetail cd " + "LEFT JOIN c.orderItems o "
					+ "GROUP BY c.id, c.courseName, t.fullname, c.coursePrice, cd.courseImage "
					+ "ORDER BY COUNT(o.id) DESC";
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
			String jpql = "SELECT c.courseName, t.fullname, AVG(CAST(r.rate AS double)), c.coursePrice, cd.courseImage, v.percentage, c.id "
					+ "FROM Course c " + "JOIN c.teacher t " + "LEFT JOIN c.review r " + "JOIN c.courseDetail cd "
					+ "JOIN c.vouchers v " + "WHERE CURRENT_DATE BETWEEN v.dateSet AND v.dateEnd "
					+ "GROUP BY c.id, c.courseName, t.fullname, c.coursePrice, cd.courseImage, v.percentage "
					+ "ORDER BY v.percentage DESC";

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

	/*
	 * @Override public Course findByIdCourse(int id) { EntityManager em =
	 * JPAConfig.getEntityManager(); EntityTransaction trans = em.getTransaction();
	 * try { trans.begin(); Course course = em.find(Course.class, id);
	 * trans.commit(); return course; } catch (Exception e) { if (trans.isActive())
	 * { trans.rollback(); } e.printStackTrace(); return null; } finally { //
	 * em.close(); } }
	 */
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
		List<Course> courses = new ArrayList<>();
		try {
			String sql = "SELECT c FROM Course c";
			courses = em.createQuery(sql, Course.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null && em.isOpen()) {
				em.close(); 
			}
		}
		return courses;
	}

	@Override
	public boolean addCourse(String courseName, int courseTypeId) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			// Tạo đối tượng Course và gán tên khóa học
			Course course = new Course();
			course.setCourseName(courseName); // Gán tên khóa học

			// Tạo một đối tượng CourseType và gán ID (không cần tìm kiếm trong DB)
			CourseType courseType = new CourseType();
			courseType.setId(courseTypeId); // Gán ID cho CourseType

			// Gán CourseType vào Course
			course.setCourseType(courseType);

			// Lưu vào DB
			em.persist(course);
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
	public int getNextCourseId() {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			// Lấy courseId lớn nhất
			String jpql = "SELECT MAX(c.courseId) FROM Course c";
			Integer maxId = em.createQuery(jpql, Integer.class).getSingleResult();
			return (maxId != null ? maxId + 1 : 1); // Nếu chưa có thì bắt đầu từ 1
		} catch (Exception e) {
			e.printStackTrace();
			return 1; // fallback nếu có lỗi
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
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
}
