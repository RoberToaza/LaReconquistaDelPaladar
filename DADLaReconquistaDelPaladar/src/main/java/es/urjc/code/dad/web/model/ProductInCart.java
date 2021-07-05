package es.urjc.code.dad.web.model;


@SuppressWarnings("serial")
public class ProductInCart extends Product{
	
	private int amount;
	
	public ProductInCart(String n, double p, int s, String  c, int amount){
		super(n,p,s,c);
		this.amount = amount;
	}
	
	public ProductInCart(Product p, int amount) {
		super(p.getName(),p.getPrice(),p.getStock(),p.getCountry());
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void addAmount() {
		this.amount++;
	}
	
	public long getTotal() {
		return (long) (amount * this.getPrice());
	}

}
