package vn.iotstar.strategy;

import java.util.List;

import vn.iotstar.entity.Course;

public class SearchContext {
	private SearchStrategy strategy =(k,courses)->courses;

	

	public void setStrategy(SearchStrategy strategy) {
		this.strategy = strategy;
	}
	public List<Course> executeSearch(String keyword,List<Course> allCourses){
		if(strategy==null) throw new IllegalStateException("Strategy not set");
		return strategy.search(keyword, allCourses);
		
	}
}
