package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.Discount;

public interface IDiscountDao {
	public List<Discount> findByIdUser (int id);
	
	public boolean insert (Discount discount);
	
	public Discount findById (int id);
	
	public boolean update (Discount discount);
}
