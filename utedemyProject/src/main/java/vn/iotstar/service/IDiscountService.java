package vn.iotstar.service;

import java.util.List;

import vn.iotstar.entity.Discount;

public interface IDiscountService {
	public List<Discount> findByIdUser (int id);
	
	public boolean insert (Discount discount);
	
	public Discount findById (int id);
	
	public boolean update (Discount discount);
}
