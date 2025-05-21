package vn.iotstar.decorator;

public class LoyalCustomerCondition extends DiscountDecorator{

    public LoyalCustomerCondition(IDiscount discount) {
        super(discount);
    }
	@Override
	public String getDiscountValue() {
		return super.getDiscountValue() + ",LOYAL_CUSTOMER";
	}
}
