package info.androidhive.slidingmenu.database;

public class DonorInformation {
	
	int donorid;
	String donorfirstname;
	String donorlastname;
	String donorstreet;
	String donorcity;
	String donorstate;
	String donorcountry;
	String donorzip;
	String donorcontact;
	String donoremail;
	int donorcustid;
	
	public DonorInformation()
	{
		
	}
	
	public DonorInformation(int donorid,int donorcustid,String donorfirstname,String donorlastname,String donorstreet,String donorcity,String donorstate,String donorcountry,String donorzip,String donoremail,String donorcontact)
	{
		this.donorid=donorid;
		this.donorcustid=donorcustid;
		this.donorfirstname=donorfirstname;
		this.donorlastname=donorlastname;
		this.donorstreet=donorstreet;
		this.donorcity=donorcity;
		this.donorstate=donorstate;
		this.donorcountry=donorcountry;
		this.donorzip=donorzip;
		this.donorcontact=donorcontact;
		this.donoremail=donoremail;
	}
	public DonorInformation(int donorcustid,String donorfirstname,String donorlastname,String donorstreet,String donorcity,String donorstate,String donorcountry,String donorzip,String donoremail,String donorcontact)
	{
		
		this.donorcustid=donorcustid;
		this.donorfirstname=donorfirstname;
		this.donorlastname=donorlastname;
		this.donorstreet=donorstreet;
		this.donorcity=donorcity;
		this.donorstate=donorstate;
		this.donorcountry=donorcountry;
		this.donorzip=donorzip;
		this.donorcontact=donorcontact;
		this.donoremail=donoremail;
	}
	//getters
	public int getdonorid()
	{
		return this.donorid;
	}
	public int getdonorcustid()
	{
		return this.donorcustid;
	}
	public String getdonorfirstname()
	{
		return this.donorfirstname;
	}
	public String getdonorlastname()
	{
		return this.donorlastname;
	}
	public String getdonorstreet()
	{
		return this.donorstreet;
	}
	public String getdonorcity()
	{
		return this.donorcity;
	}
	public String getdonorstate()
	{
		return this.donorstate;
	}
	public String getdonorcountry()
	{
		return this.donorcountry;
	}
	public String getdonorzip()
	{
		return this.donorzip;
	}
	public String getdonorcontact()
	{
		return this.donorcontact;
	}
	public String getdonoremail()
	{
		return this.donoremail;
	}
	
	//setters
	public void setdonorid(int donorid)
	{
		this.donorid=donorid;
	}
	public void setdonorfirstname(String donorfirstname)
	{
		this.donorfirstname=donorfirstname;
	}
	public void setdonorlastname(String donorlastname)
	{
		this.donorlastname=donorlastname;
	}
	public void setdonorstreet(String donorstreet)
	{
		this.donorstreet=donorstreet;
	}
	public void setdonorcity(String donorcity)
	{
		this.donorcity=donorcity;
	}
	public void setdonorstate(String donorstate)
	{
		this.donorstate=donorstate;
	}
	public void setdonorzip(String donorzip)
	{
		this.donorzip=donorzip;
	}
	public void setdonorcountry(String donorcountry)
	{
		this.donorcountry=donorcountry;
	}
	public void setdonoremail(String donoremail)
	{
		this.donoremail=donoremail;
	}
	public void setdonorcontact(String donorcontact)
	{
		this.donorcontact=donorcontact;
	}
	public void setdonorcustid(int donorcustid)
	{
		this.donorcustid=donorcustid;
	}
	

}
