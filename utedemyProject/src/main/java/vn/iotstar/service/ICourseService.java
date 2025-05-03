package vn.iotstar.service;

import java.util.List;
import vn.iotstar.entity.Answer;
import vn.iotstar.entity.Course;
import vn.iotstar.entity.CourseDetail;
import vn.iotstar.entity.CourseType;
import vn.iotstar.entity.Lesson;
import vn.iotstar.entity.OrderItem;
import vn.iotstar.entity.Question;
import vn.iotstar.entity.Quiz;
import vn.iotstar.entity.Section;

public interface ICourseService {
	List<Object[]> getBestSellingCourses(int limit);

	List<Object[]> getTodaySaleCourses(int limit);

	public List<CourseType> listCourseType();

	public boolean saveCoure(Course course);

	public CourseType findByIDCourseType(int id);

	public Course findByIdCourse(int id);

	public int maxCourseId();

	public boolean saveCourseDetail(CourseDetail courseDetail);

	public boolean updateCourse(Course course);

	public int maxCourseDetailId();

	public CourseDetail findByIdCourseDetail(int id);

	public boolean updateCourseDetail(CourseDetail courseDetail);

	public boolean saveSection(Section section);

	public int maxSectionId();

	public Section findByIdSection(int id);

	public boolean saveLesson(Lesson lesson);

	public boolean saveQuiz(Quiz quiz);

	public int maxQuizId();

	public Quiz findByIdQuiz(int id);

	public boolean saveQuestion(Question question);

	public int maxQuestionId();

	public Question findByIdQuestion(int id);

	public boolean saveAnswer(Answer answer);

	public boolean updateSection(Section section);

	public Lesson findByIdLesson(int id);

	public boolean updateLesson(Lesson lesson);

	public boolean updateQuiz(Quiz quiz);

	public boolean deleteSection(int id) throws Exception;

	public boolean deleteLesson(int id) throws Exception;

	public boolean deleteQuiz(int id) throws Exception;

	List<Course> getAllCourses();

	public List<OrderItem> getAllOrderItems();

	public List<Lesson> getAllLessons();

	public List<Course> filterCoursesByRatingAndCourse(List<OrderItem> orderItems, double ratingThreshold);

	public double calculateAverageRating(Course course);

	public List<Course> filterCoursesByRating(double ratingThreshold);

}
