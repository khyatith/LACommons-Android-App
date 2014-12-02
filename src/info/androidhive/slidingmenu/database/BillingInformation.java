package info.androidhive.slidingmenu.database;

public class BillingInformation {

	int billingid;
	String billingfirstname;
	String billinglastname;
	String billingstreet;
	String billingcity;
	String billingstate;
	String billingcountry;
	String billingzip;
	String billingcontact;
	String billingemail;
	int billingcustid;
	
	public BillingInformation()
	{
		
	}
	
	public BillingInformation(int billingid,int billingcustid,String billingfirstname,String billinglastname,String billingstreet,String billingcity,String billingstate,String billingcountry,String billingzip,String billingemail,String billingcontact)
	{
		this.billingid=billingid;
		this.billingcustid=billingcustid;
		this.billingfirstname=billingfirstname;
		this.billinglastname=billinglastname;
		this.billingstreet=billingstreet;
		this.billingcity=billingcity;
		this.billingstate=billingstate;
		this.billingcountry=billingcountry;
		this.billingzip=billingzip;
		this.billingcontact=billingcontact;
		this.billingemail=billingemail;
	}
	public BillingInformation(int billingcustid,String billingfirstname,String billinglastname,String billingstreet,String billingcity,String billingstate,String billingcountry,String billingzip,String billingemail,String billingcontact)
	{
	
		this.billingcustid=billingcustid;
		this.billingfirstname=billingfirstname;
		this.billinglastname=billinglastname;
		this.billingstreet=billingstreet;
		this.billingcity=billingcity;
		this.billingstate=billingstate;
		this.billingcountry=billingcountry;
		this.billingzip=billingzip;
		this.billingcontact=billingcontact;
		this.billingemail=billingemail;
	}
	//getters
	public int getbillingid()
	{
		return this.billingid;
	}
	public int getbillingcustid()
	{
		return this.billingcustid;
	}
	public String getbillingfirstname()
	{
		return this.billingfirstname;
	}
	public String getbillinglastname()
	{
		return this.billinglastname;
	}
	public String getbillingstreet()
	{
		return this.billingstreet;
	}
	public String getbillingcity()
	{
		return this.billingcity;
	}
	public String getbillingstate()
	{
		return this.billingstate;
	}
	public String getbillingcountry()
	{
		return this.billingcountry;
	}
	public String getbillingzip()
	{
		return this.billingzip;
	}
	public String getbillingcontact()
	{
		return this.billingcontact;
	}
	public String getbillingemail()
	{
		return this.billingemail;
	}
	
	//setters
	public void setbillingid(int billingid)
	{
		this.billingid=billingid;
	}
	public void setbillingfirstname(String billingfirstname)
	{
		this.billingfirstname=billingfirstname;
	}
	public void setbillinglastname(String billinglastname)
	{
		this.billinglastname=billinglastname;
	}
	public void setbillingstreet(String billingstreet)
	{
		this.billingstreet=billingstreet;
	}
	public void setbillingcity(String billingcity)
	{
		this.billingcity=billingcity;
	}
	public void setbillingstate(String billingstate)
	{
		this.billingstate=billingstate;
	}
	public void setbillingzip(String billingzip)
	{
		this.billingzip=billingzip;
	}
	public void setbillingcountry(String billingcountry)
	{
		this.billingcountry=billingcountry;
	}
	public void setbillingemail(String billingemail)
	{
		this.billingemail=billingemail;
	}
	public void setbillingcontact(String billingcontact)
	{
		this.billingcontact=billingcontact;
	}
	public void setbillingcustid(int billingcustid)
	{
		this.billingcustid=billingcustid;
	}
	

}
