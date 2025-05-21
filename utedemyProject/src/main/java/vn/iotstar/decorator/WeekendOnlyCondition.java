package vn.iotstar.decorator;

public class WeekendOnlyCondition extends DiscountDecorator{

	public WeekendOnlyCondition(IDiscount discount) {
		super(discount);
	}
	
	@Override
	public String getDiscountValue() {
		return super.getDiscountValue() + ",WEEKEND_ONLY";
	}
}
