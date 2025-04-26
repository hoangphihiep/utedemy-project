package vn.iotstar.impl.service;

import java.util.List;

import vn.iotstar.dao.ICourseDao;
import vn.iotstar.entity.Answer;
import vn.iotstar.entity.Course;
import vn.iotstar.entity.CourseDetail;
import vn.iotstar.entity.CourseType;
import vn.iotstar.entity.Lesson;
import vn.iotstar.entity.Question;
import vn.iotstar.entity.Quiz;
import vn.iotstar.entity.Section;
import vn.iotstar.impl.dao.CourseDao;
import vn.iotstar.service.ICourseService;

public class CourseService implements ICourseService {

	public ICourseDao courseDao = new CourseDao();
	@Override
	public List<CourseType> listCourseType() {
		return courseDao.listCourseType();
	}
	@Override
	public boolean saveCoure(Course course) {
		return courseDao.saveCoure(course);
	}
	@Override
	public CourseType findByIDCourseType(int id) {
		return courseDao.findByIDCourseType(id);
	}
	@Override
	public Course findByIdCourse(int id) {
		return courseDao.findByIdCourse(id);
	}
	@Override
	public int maxCourseId() {
		return courseDao.maxCourseId();
	}
	@Override
	public boolean saveCourseDetail(CourseDetail courseDetail) {
		return courseDao.saveCourseDetail(courseDetail);
	}
	@Override
	public boolean updateCourse(Course course) {
		return courseDao.updateCourse(course);
	}
	@Override
	public int maxCourseDetailId() {
		return courseDao.maxCourseDetailId();
	}
	@Override
	public CourseDetail findByIdCourseDetail(int id) {
		return courseDao.findByIdCourseDetail(id);
	}
	@Override
	public boolean updateCourseDetail(CourseDetail courseDetail) {
		return courseDao.updateCourseDetail(courseDetail);
	}
	@Override
	public boolean saveSection(Section section) {
		return courseDao.saveSection(section);
	}
	@Override
	public int maxSectionId() {
		return courseDao.maxSectionId();
	}
	@Override
	public Section findByIdSection(int id) {
		return courseDao.findByIdSection(id);
	}
	@Override
	public boolean saveLesson(Lesson lesson) {
		return courseDao.saveLesson(lesson);
	}
	@Override
	public boolean saveQuiz(Quiz quiz) {
		return courseDao.saveQuiz(quiz);
	}
	@Override
	public int maxQuizId() {
		return courseDao.maxQuizId();
	}
	@Override
	public Quiz findByIdQuiz(int id) {
		return courseDao.findByIdQuiz(id);
	}
	@Override
	public boolean saveQuestion(Question question) {
		return courseDao.saveQuestion(question);
	}
	@Override
	public int maxQuestionId() {
		return courseDao.maxQuestionId();
	}
	@Override
	public Question findByIdQuestion(int id) {
		return courseDao.findByIdQuestion(id);
	}
	@Override
	public boolean saveAnswer(Answer answer) {
		return courseDao.saveAnswer(answer);
	}
	@Override
	public boolean updateSection(Section section) {
		return courseDao.updateSection(section);
	}
	@Override
	public Lesson findByIdLesson(int id) {
		return courseDao.findByIdLesson(id);
	}
	@Override
	public boolean updateLesson(Lesson lesson) {
		return courseDao.updateLesson(lesson);
	}
	@Override
	public boolean updateQuiz(Quiz quiz) {
		return courseDao.updateQuiz(quiz);
	}
	@Override
	public boolean deleteSection(int id) throws Exception {
		return courseDao.deleteSection(id);
	}
	@Override
	public boolean deleteLesson(int id) throws Exception {
		return courseDao.deleteLesson(id);
	}
	@Override
	public boolean deleteQuiz(int id) throws Exception {
		return courseDao.deleteQuiz(id);
	}
}
