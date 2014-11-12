package info.androidhive.slidingmenu.database;

public class User {
	int sid;
String Tname;
int Quantity;
int Cost;



public int getId()
{
	return sid;
}
public void setId(int sid)
{
	this.sid=sid;
}
public String getTname()
{
	return Tname;
}

public void setTname(String Tname)
{
	this.Tname=Tname;
}
public int getQuantity()
{
	return Quantity;
}
public void setQuantity(int Quantity)
{
	this.Quantity=Quantity;
}
public int getCost()
{
	return Cost;
}
public void setCost(int Cost)
{
	this.Cost=Cost;
}
public User(int sid,String Tname, int Quantity,int Cost) {
	  super();
	  
	  this.sid=sid;
	  this.Tname = Tname;
	  this.Quantity = Quantity;
	  this.Cost = Cost;
	 }
}
