package vn.iotstar.decorator;


public abstract class DiscountDecorator implements IDiscount{

	protected IDiscount wrappedDiscount;
    
    public DiscountDecorator(IDiscount discount) {
        this.wrappedDiscount = discount;
    }  
  
    public IDiscount getDiscount() {
        return wrappedDiscount;
    }

    public void setDiscount(IDiscount discount) {
    	this.wrappedDiscount = discount;
    }
    
    @Override
    public String getDiscountValue() {
        return wrappedDiscount.getDiscountValue();
    }
}
