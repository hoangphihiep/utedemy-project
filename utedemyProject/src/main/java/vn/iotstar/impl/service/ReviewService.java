package vn.iotstar.impl.service;


import java.util.List;


import vn.iotstar.dao.*;
import vn.iotstar.entity.*;
import vn.iotstar.impl.dao.*;
import vn.iotstar.service.IReviewService;


public class ReviewService implements IReviewService {


	public IReviewDao reviewDao = new ReviewDao();


	@Override
	public List<Review> getAllReviews()
	{
		return reviewDao.getAllReviews();
	}
}