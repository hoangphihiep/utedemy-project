package vn.iotstar.decorator;

public class MinOrder5Condition extends DiscountDecorator {

	public MinOrder5Condition(IDiscount discount) {
		super(discount);
	}

	@Override
	public String getDiscountValue() {
		return super.getDiscountValue() + ",MIN_ORDER_5";
	}
}
