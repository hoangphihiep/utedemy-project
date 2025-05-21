package vn.iotstar.decorator;

import vn.iotstar.entity.Discount;

public class PercentageDiscount implements IDiscount{
	
	private Discount discount;
    
    public PercentageDiscount(Discount discount) {
        this.discount = discount;
    }

	@Override
	public String getDiscountValue() {
		return discount.getDecreasedFee() + " %";
	}
}
