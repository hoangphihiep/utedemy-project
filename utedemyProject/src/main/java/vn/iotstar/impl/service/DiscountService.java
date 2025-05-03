package vn.iotstar.impl.service;

import java.util.List;

import vn.iotstar.dao.IDiscountDao;
import vn.iotstar.entity.Discount;
import vn.iotstar.impl.dao.DiscountDao;
import vn.iotstar.service.IDiscountService;

public class DiscountService implements IDiscountService {
	
	public IDiscountDao discountDao = new DiscountDao();
	@Override
	public List<Discount> findByIdUser(int id) {
		return discountDao.findByIdUser(id);
	}
	@Override
	public boolean insert(Discount discount) {
		return discountDao.insert(discount);
	}
	@Override
	public Discount findById(int id) {
		return discountDao.findById(id);
	}
	@Override
	public boolean update(Discount discount) {
		return discountDao.update(discount);
	}

}
