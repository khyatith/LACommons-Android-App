package info.androidhive.slidingmenu.database;

public class ShoppingCart {
	
	int shoppingcartid;
	int trekid;
	int custid;
	int quantity;
	int cost;
	
	public ShoppingCart()
	{}
	
	public ShoppingCart(int trekid,int custid,int quantity,int cost)
	{
		this.trekid=trekid;
		this.custid=custid;
		this.quantity=quantity;
		this.cost=cost;
		
	}
	
	//getters
	
	public int getshoppingcartid()
	 {
		 return this.shoppingcartid;
	 }
	public int gettrekid()
	 {
		 return this.trekid;
	 }
	public int getcustid()
	 {
		 return this.custid;
	 }
	public int getquantity()
	 {
		 return this.quantity;
	 }
	public int getcost()
	 {
		 return this.cost;
	 }
	
	//setters
	
	public void setshoppingcartid(int shoppingcartid)
	 {
		 this.shoppingcartid=shoppingcartid;
	 }
	public void settrekid(int trekid)
	 {
		 this.trekid=trekid;
	 }
	public void setcustid(int custid)
	 {
		 this.custid=custid;
	 }
	public void setquantity(int quantity)
	 {
		 this.quantity=quantity;
	 }
	public void setcost(int cost)
	 {
		 this.cost=cost;
	 }
}
