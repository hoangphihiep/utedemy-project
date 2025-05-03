package vn.iotstar.service;

import java.util.List;

import vn.iotstar.entity.CourseDetail;
import vn.iotstar.entity.CourseProgress;

public interface ICourseDetailService {
    CourseDetail getCourseDetailById(int id); 
    public List<CourseProgress> getAllCourseProgress();
}