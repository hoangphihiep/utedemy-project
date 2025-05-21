package vn.iotstar.decorator;


import vn.iotstar.entity.Discount;

public class FixedAmountDiscount implements IDiscount{
    
    Discount discount;

    public FixedAmountDiscount(Discount discount) {
        this.discount = discount;
    }
    
	@Override
	public String getDiscountValue() {
		return discount.getDecreasedFee() + " VNĐ";
	}
}
