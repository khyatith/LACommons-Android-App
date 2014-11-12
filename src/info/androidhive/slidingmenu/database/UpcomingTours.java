package info.androidhive.slidingmenu.database;

public class UpcomingTours {
 int id;
 String tourname;
 String date;
 String month;
 int year;
 String time;
 int cost;
 String venue;
 String tourdescription;
 String endtime;
 
 public UpcomingTours()
 {}
 
 public UpcomingTours(int id,String tourname,String date,String time,String month,int year,String venue,String tourdescription,int cost,String endtime)
 {
	 this.id=id;
	 this.tourname=tourname;
	 this.date=date;
	 this.month=month;
	 this.year=year;
	 this.time=time;
	 this.cost=cost;
	 this.venue=venue;
	 this.tourdescription=tourdescription;
	 this.endtime=endtime;
 }
 public UpcomingTours(int id,String tourname,String date,String time)
 {
	 this.id=id;
	 this.tourname=tourname;
	 this.date=date;
	 this.time=time;
 
 }
 //getters
 public int getid()
 {
	 return this.id;
 }
 public String getendtime()
 {
	 return this.endtime;
 }
 public String gettourname()
 {
	 return this.tourname;
 }
 public String getdate()
 {
	 return this.date;
 }
 public String getmonth()
 {
	 return this.month;
 }
 public int getyear()
 {
	 return this.year;
 }
 public String gettime()
 {
	 return this.time;
 }
 public int getcost()
 {
	 return this.cost;
 }
 public String getvenue()
 {
	 return this.venue;
 }
 public String getdesc()
 {
	 return this.tourdescription;
 }
 
 //setters
 public void setid(int id)
 {
	 this.id=id;
 }
 public void setendtime(String endtime)
 {
	 this.endtime=endtime;
 }
 public void settourname(String tourname)
 {
	 this.tourname=tourname;
 }
 public void setdate(String date)
 {
	 this.date=date;
 }
 public void setmonth(String month)
 {
	 this.month=month;
 }
 public void setyear(int year)
 {
	 this.year=year;
 }
 public void settime(String time)
 {
	 this.time=time;
 }
 public void setcost(int cost)
 {
	 this.cost=cost;
 }
 public void setvenue(String venue)
 {
	 this.venue=venue;
 }
 public void setdesc(String tourdescription)
 {
	 this.tourdescription=tourdescription;
 }
 
 
}
