package vn.iotstar.service;

import java.util.List;

import vn.iotstar.entity.CourseDetail;
import vn.iotstar.entity.CourseProgress;
import vn.iotstar.entity.CourseType;

public interface ICourseDetailService {
    CourseDetail getCourseDetailById(int id); 
    public List<CourseProgress> getAllCourseProgress();
    public CourseType getCourseTypeById(int id);
    public List<CourseType> getAllCourseTypes();
}