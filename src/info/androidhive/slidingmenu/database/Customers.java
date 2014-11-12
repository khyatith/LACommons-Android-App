package info.androidhive.slidingmenu.database;

import java.math.BigInteger;

public class Customers {

	
	 int customerid;
	 String customerfirstname;
	 String customerlastname;
	 String customerusername;
	 String customerpassword;
	 String customercontact;
	
	 
	 public Customers()
	 {}
	 
	public Customers(String customerfirstname,String customerlastname,String customerusername,String customerpassword,String customercontact)
	{
		
		this.customerfirstname=customerfirstname;
		this.customerlastname=customerlastname;
		this.customerusername=customerusername;
		this.customerpassword=customerpassword;
		this.customercontact=customercontact;
	}
	 //getters
	 public int getcustomerid()
	 {
		 return this.customerid;
	 }
	 public String getcustomerfirstname()
	 {
		 return this.customerfirstname;
	 }
	 public String getcustomerlastname()
	 {
		 return this.customerlastname;
	 }
	 public String getcustomerusername()
	 {
		 return this.customerusername;
	 }
	 public String getcustomerpassword()
	 {
		 return this.customerpassword;
	 }
	 public String getcustomercontact()
	 {
		 return this.customercontact;
	 }

	 
	 //setters
	 public void setcustomerid(int customerid)
	 {
		 this.customerid=customerid;
	 }
	 public void setcustomerfirstname(String customerfirstname)
	 {
		 this.customerfirstname=customerfirstname;
	 }
	 public void setcustomerlastname(String customerlastname)
	 {
		 this.customerlastname=customerlastname;
	 }
	 public void setcustomerusername(String customerusername)
	 {
		 this.customerusername=customerusername;
	 }
	 public void setcustomerpassword(String customerpassword)
	 {
		 this.customerpassword=customerpassword;
	 }
	 public void setcustomercontact(String customercontact)
	 {
		 this.customercontact=customercontact;
	 }
	
	 
	 
	}


