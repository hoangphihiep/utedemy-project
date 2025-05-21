package vn.iotstar.decorator;



public class NewUserCondition extends DiscountDecorator {
    
	public NewUserCondition(IDiscount discount) {
        super(discount);
    }

	@Override
	public String getDiscountValue() {
		return super.getDiscountValue() + ",NEW_USER";
	}
    
    
}
