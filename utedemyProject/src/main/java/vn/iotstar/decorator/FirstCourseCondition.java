package vn.iotstar.decorator;

public class FirstCourseCondition extends DiscountDecorator {

	public FirstCourseCondition(IDiscount discount) {
		super(discount);
	}

	@Override
	public String getDiscountValue() {
		return super.getDiscountValue() + ",FIRST_COURSE";
	}
}
