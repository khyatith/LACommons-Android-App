package info.androidhive.slidingmenu.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;



public class DBHelper extends SQLiteOpenHelper {

	//private static final String TAG=DBHelper.class.getSimpleName();
    public static final String DB_NAME = "lacommons.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_UPCOMINGTOURS="UpcomingTours"; //table name
    public static final String TABLE_CUSTOMERS="Customers"; // table name
    public static final String TABLE_SHOPPINGCART="ShoppingCart";
    public static final String TABLE_DONORINFORMATION="DonorInformation";
    public static final String TABLE_BILLINGINFORMATION="BillingInformation";
    
    //column names in UpcomingTours
    
    public static final String TOUR_ID="TourId";
    public static final String TOUR_NAME="TourName";
    public static final String TOUR_DATE="TourDate";
    public static final String TOUR_MONTH="TourMonth";
    public static final String TOUR_YEAR="TourYear";
    public static final String TOUR_TIME="TourTime";
    public static final String TOUR_COST="TourCost";
    public static final String TOUR_VENUE="TourVenue";
    public static final String TOUR_DESC="TourDesc";
    public static final String TOUR_ENDTIME="EndTime";
    
    //column names in customer table
    public static final String CUSTOMER_ID="CustomerId";
    public static final String CUSTOMER_FIRSTNAME="CustomerFirstName";
    public static final String CUSTOMER_LASTNAME="CustomerLastName";
    public static final String CUSTOMER_USERNAME="CustomerUserName";
    public static final String CUSTOMER_PASSWORD="CustomerPassword";
    public static final String CUSTOMER_CONTACT="CustomerContact";
    
    
    
    //column names in shopping cart table
    public static final String SHOPPINGCART_ID="ShoppingCartId";
   public static final String TOTAL_COST="TotalCost";
   public static final String TOTAL_QUANTITY="TotalQuantity";
   public static final String C_ID="CustId";
   public static final String TREK_ID="TrekId";
   
   //column names in Donor info table
   public static final String DONOR_ID="DonorId";
   public static final String DONOR_FIRSTNAME="DonorFirstName";
   public static final String DONOR_LASTNAME="DonorLastName";
   public static final String DONOR_EMAIL="DonorEmail";
   public static final String DONOR_STREET="DonorStreet";
   public static final String DONOR_CITY="DonorCity";
   public static final String DONOR_STATE="DonorState";
   public static final String DONOR_ZIP="DonorZip";
   public static final String DONOR_COUNTRY="DonorCountry";
   public static final String DONOR_CONTACT="DonorContact";
   public static final String DONOR_CUSTOMERID="DonorCustId";
   
   //column names in Billing info table
   public static final String BILLING_ID="BillingId";
   public static final String BILLING_FIRSTNAME="BillingFirstName";
   public static final String BILLING_LASTNAME="BillingLastName";
   public static final String BILLING_EMAIL="BillingEmail";
   public static final String BILLING_STREET="BillingStreet";
   public static final String BILLING_CITY="BillingCity";
   public static final String BILLING_STATE="BillingState";
   public static final String BILLING_ZIP="BillingZip";
   public static final String BILLING_COUNTRY="BillingCountry";
   public static final String BILLING_CONTACT="BillingContact";
   public static final String BILLING_CUSTOMERID="BillingCustId";
   
   
   
   
// create table upcomingtours
    
    private static final String CREATE_TABLE_UPCOMINGTOURS = "CREATE TABLE IF NOT EXISTS " + TABLE_UPCOMINGTOURS + "(" + TOUR_ID + " INTEGER PRIMARY KEY," + TOUR_NAME + " TEXT," + TOUR_DATE + " TEXT," + TOUR_TIME + " TEXT," + TOUR_MONTH + " TEXT," + TOUR_YEAR + " INTEGER," + TOUR_VENUE + " TEXT," + TOUR_DESC + " TEXT," + TOUR_COST + " INTEGER," + TOUR_ENDTIME + " TEXT" + ")";
	//private static final String LOG = null;
 
	//create table customers
	private static final String CREATE_TABLE_CUSTOMERS= "CREATE TABLE IF NOT EXISTS " + TABLE_CUSTOMERS + "(" + CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + CUSTOMER_FIRSTNAME + " TEXT," + CUSTOMER_LASTNAME + " TEXT," + CUSTOMER_USERNAME + " TEXT," + CUSTOMER_PASSWORD + " TEXT," + CUSTOMER_CONTACT + " TEXT" + ")";

	//create table shopping cart
	//private static final String CREATE_TABLE_SHOPPINGCART= "CREATE TABLE IF NOT EXISTS " + TABLE_SHOPPINGCART + "(" + SHOPPINGCART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + TOTAL_COST + " INTEGER," + TOTAL_QUANTITY + " INTEGER," + " FOREIGN KEY ("+ C_ID +") REFERENCES "+ TABLE_CUSTOMERS +" ("+ CUSTOMER_ID +")," + " FOREIGN KEY ("+ TREK_ID +") REFERENCES "+ TABLE_UPCOMINGTOURS +" ("+ TOUR_ID +"));";
	
	private static final String CREATE_TABLE_SHOPPINGCART = "CREATE TABLE IF NOT EXISTS " + TABLE_SHOPPINGCART + "(" + SHOPPINGCART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + TOTAL_COST + " INTEGER," + TOTAL_QUANTITY + " INTEGER," + C_ID + " INTEGER," + TREK_ID + " INTEGER" + ")";
	
	//create table donor information
	private static final String CREATE_TABLE_DONORINFORMATION = "CREATE TABLE IF NOT EXISTS " + TABLE_DONORINFORMATION + "(" + DONOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DONOR_FIRSTNAME + " TEXT," + DONOR_LASTNAME + " TEXT," + DONOR_EMAIL + " TEXT," + DONOR_STREET + " TEXT," + DONOR_CITY + " TEXT," + DONOR_STATE + " TEXT," + DONOR_ZIP + " TEXT," + DONOR_COUNTRY + " TEXT," + DONOR_CONTACT + " TEXT," + DONOR_CUSTOMERID + " INTEGER" + ")";
	
	//create table billing information
	private static final String CREATE_TABLE_BILLINGINFORMATION = "CREATE TABLE IF NOT EXISTS " + TABLE_BILLINGINFORMATION + "(" + BILLING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + BILLING_FIRSTNAME + " TEXT," + BILLING_LASTNAME + " TEXT," + BILLING_EMAIL + " TEXT," + BILLING_STREET + " TEXT," + BILLING_CITY + " TEXT," + BILLING_STATE + " TEXT," + BILLING_ZIP + " TEXT," + BILLING_COUNTRY + " TEXT," + BILLING_CONTACT + " TEXT," + BILLING_CUSTOMERID + " INTEGER" + ")";
	
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		db.execSQL(CREATE_TABLE_UPCOMINGTOURS);
		db.execSQL(CREATE_TABLE_CUSTOMERS);
		db.execSQL(CREATE_TABLE_SHOPPINGCART);
		db.execSQL(CREATE_TABLE_DONORINFORMATION);
		db.execSQL(CREATE_TABLE_BILLINGINFORMATION);
		
	}
	

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("PRAGMA foreign_keys = ON;");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_UPCOMINGTOURS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPINGCART);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DONORINFORMATION);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BILLINGINFORMATION);
		onCreate(db);
		
	}
	public void onOpen(SQLiteDatabase db) {
	   

	    // Enable foreign key constraints
		db.execSQL("PRAGMA foreign_keys = ON;");
	}
	
	//creating tours
	public long createTours(UpcomingTours upcomingtours) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(TOUR_ID, upcomingtours.id);
	    values.put(TOUR_NAME, upcomingtours.tourname);
	    values.put(TOUR_DATE, upcomingtours.date);
	    values.put(TOUR_TIME, upcomingtours.time);
	    values.put(TOUR_MONTH, upcomingtours.month);
	    values.put(TOUR_YEAR, upcomingtours.year);
	    values.put(TOUR_VENUE, upcomingtours.venue);
	    values.put(TOUR_DESC, upcomingtours.tourdescription);
	    values.put(TOUR_COST, upcomingtours.cost);
	    values.put(TOUR_ENDTIME, upcomingtours.endtime);
	 
	    // insert row
	    long tour_id = db.insert(TABLE_UPCOMINGTOURS, null, values);
	 
	   
	 
	    return tour_id;
	}
	
	public List<UpcomingTours> getAllTours() {
        List<UpcomingTours> tours = new ArrayList<UpcomingTours>();
        String selectQuery = "SELECT  * FROM " + TABLE_UPCOMINGTOURS;
 
        //Log.e(LOG, selectQuery);
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                UpcomingTours td = new UpcomingTours();
                td.setid(c.getInt((c.getColumnIndex(TOUR_ID))));
                td.settourname((c.getString(c.getColumnIndex(TOUR_NAME))));
                td.setdate(c.getString(c.getColumnIndex(TOUR_DATE)));
                td.settime(c.getString(c.getColumnIndex(TOUR_TIME)));
                td.setmonth(c.getString(c.getColumnIndex(TOUR_MONTH)));
                td.setyear(c.getInt(c.getColumnIndex(TOUR_YEAR)));
                td.setvenue(c.getString(c.getColumnIndex(TOUR_VENUE)));
                td.setdesc(c.getString(c.getColumnIndex(TOUR_DESC)));
                td.setcost(c.getInt(c.getColumnIndex(TOUR_COST)));
                td.setendtime(c.getString(c.getColumnIndex(TOUR_ENDTIME)));
                // adding to todo list
                tours.add(td);
            } while (c.moveToNext());
        }
 
        return tours;
    }

	public List<UpcomingTours> getTourDetails(String name)
	{
		List<UpcomingTours> tours = new ArrayList<UpcomingTours>();
		SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM UpcomingTours WHERE TourName = '"+name+"'", null);
        
        if (c.moveToFirst()) {
            do {
                UpcomingTours td = new UpcomingTours();
                td.setid(c.getInt((c.getColumnIndex(TOUR_ID))));
                td.settourname((c.getString(c.getColumnIndex(TOUR_NAME))));
                td.setdate(c.getString(c.getColumnIndex(TOUR_DATE)));
                td.settime(c.getString(c.getColumnIndex(TOUR_TIME)));
                td.setmonth(c.getString(c.getColumnIndex(TOUR_MONTH)));
                td.setyear(c.getInt((c.getColumnIndex(TOUR_YEAR))));
                td.setdesc(c.getString((c.getColumnIndex(TOUR_DESC))));
                td.setvenue(c.getString(c.getColumnIndex(TOUR_VENUE)));
                td.setcost(c.getInt(c.getColumnIndex(TOUR_COST)));
                td.setendtime(c.getString(c.getColumnIndex(TOUR_ENDTIME)));
                
                // adding to todo list
                tours.add(td);
            } while (c.moveToNext());
        }
 
        return tours;
		
	}
	
	public long createCustomers(Customers customers) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    //values.put(CUSTOMER_ID, customers.customerid);
	    values.put(CUSTOMER_FIRSTNAME, customers.customerfirstname);
	    values.put(CUSTOMER_LASTNAME, customers.customerlastname);
	    values.put(CUSTOMER_USERNAME, customers.customerusername);
	    values.put(CUSTOMER_PASSWORD, customers.customerpassword);
	    values.put(CUSTOMER_CONTACT, customers.customercontact);
	    
	 
	    // insert row
	    long customer_id = db.insert(TABLE_CUSTOMERS, null, values);
	 
	   
	 
	    return customer_id;
	}
	public boolean findCustomer(String username, String password)
	{
		SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Customers WHERE CustomerUserName = '"+username+"' AND CustomerPassword = '"+password+"'", null);
		int count=c.getCount();
		
        if(count==0)
		{
			return false;
		}
		return true;
	}
	public List<Customers> getAllCustomers() {
        List<Customers> cust = new ArrayList<Customers>();
        String selectQuery = "SELECT  * FROM " + TABLE_CUSTOMERS;
 
        //Log.e(LOG, selectQuery);
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Customers td = new Customers();
                td.setcustomerid(c.getInt((c.getColumnIndex(CUSTOMER_ID))));
                td.setcustomerusername((c.getString(c.getColumnIndex(CUSTOMER_USERNAME))));
                //td.setcustomerlastname(c.getString(c.getColumnIndex(CUSTOMER_LASTNAME)));
               
                // adding to todo list
                cust.add(td);
            } while (c.moveToNext());
        }
 
        return cust;
    }
	public List<Customers> getLoggedInCustomerId(String username,String password)
	{
		List<Customers> customers = new ArrayList<Customers>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM Customers WHERE CustomerUserName = '"+username+"' AND CustomerPassword = '"+password+"'", null);
		if (c.moveToFirst()) {
            do {
                Customers td = new Customers();
                td.setcustomerid(c.getInt((c.getColumnIndex(CUSTOMER_ID))));
                td.setcustomerfirstname((c.getString(c.getColumnIndex(CUSTOMER_FIRSTNAME))));
               td.setcustomerlastname(c.getString(c.getColumnIndex(CUSTOMER_LASTNAME)));
               td.setcustomercontact(c.getString(c.getColumnIndex(CUSTOMER_CONTACT)));
               
                
                // adding to todo list
                customers.add(td);
            } while (c.moveToNext());
        }
 
        return customers;
		
	}
	public long createShoppingCart(ShoppingCart shoppingcart) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    //values.put(CUSTOMER_ID, customers.customerid);
	    values.put(TOTAL_COST, shoppingcart.getcost());
	    values.put(C_ID, shoppingcart.getcustid());
	    values.put(TREK_ID, shoppingcart.gettrekid());
	    values.put(TOTAL_QUANTITY, shoppingcart.getquantity());
	   //values.put(FINAL_TOTALCOST,shoppingcart.getfinaltotalcost() );
	    
	 
	    // insert row
	    long shopping_id = db.insert(TABLE_SHOPPINGCART, null, values);
	 
	   
	 
	    return shopping_id;
	}
	public List<ShoppingCart> getCartDetails(int cid)
	{
		List<ShoppingCart> sc = new ArrayList<ShoppingCart>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM ShoppingCart WHERE CustId = '"+cid+"'", null);
		if (c.moveToFirst()) {
            do {
                ShoppingCart td = new ShoppingCart();
                td.setshoppingcartid(c.getInt((c.getColumnIndex(SHOPPINGCART_ID))));
                td.settrekid((c.getInt(c.getColumnIndex(TREK_ID))));
               td.setquantity(c.getInt(c.getColumnIndex(TOTAL_QUANTITY)));
                td.setcost(c.getInt(c.getColumnIndex(TOTAL_COST)));
                //td.setfinaltotalcost(c.getInt(c.getColumnIndex(FINAL_TOTALCOST)));
                // adding to todo list
                sc.add(td);
            } while (c.moveToNext());
        }
 
        return sc;
		
	}
	public List<UpcomingTours> getTreksForCart(int trekid) {
        List<UpcomingTours> tours = new ArrayList<UpcomingTours>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c= db.rawQuery("SELECT  * FROM UpcomingTours WHERE TourId = '"+trekid+"'",null);
 
        //Log.e(LOG, selectQuery);
 
       // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                UpcomingTours td = new UpcomingTours();
                td.setid(c.getInt((c.getColumnIndex(TOUR_ID))));
                td.settourname((c.getString(c.getColumnIndex(TOUR_NAME))));
                td.setdate(c.getString(c.getColumnIndex(TOUR_DATE)));
                td.settime(c.getString(c.getColumnIndex(TOUR_TIME)));
                td.setmonth(c.getString(c.getColumnIndex(TOUR_MONTH)));
                td.setyear(c.getInt(c.getColumnIndex(TOUR_YEAR)));
                td.setvenue(c.getString(c.getColumnIndex(TOUR_VENUE)));
                td.setdesc(c.getString(c.getColumnIndex(TOUR_DESC)));
                td.setcost(c.getInt(c.getColumnIndex(TOUR_COST)));
                td.setendtime(c.getString(c.getColumnIndex(TOUR_ENDTIME)));
                // adding to todo list
                tours.add(td);
            } while (c.moveToNext());
        }
 
        return tours;
    }
	public int RemoveItemFromCart(int sid)
	{
		//List<ShoppingCart> sc = new ArrayList<ShoppingCart>();
		SQLiteDatabase db = this.getReadableDatabase();
		int rowsdeleted=db.delete(TABLE_SHOPPINGCART, SHOPPINGCART_ID+"="+sid, null);
		
 return rowsdeleted;
		
	}
	public int updateShoppingCart(int cid,int quan,int finalcost)
	{
		ContentValues cv = new ContentValues();
	    cv.put(TOTAL_QUANTITY, quan);
	    cv.put(TOTAL_COST,finalcost);
	    
	                 /* use COLUMN NAMES here */                     
	    String where = "CustId = ?";
	    SQLiteDatabase db = this.getReadableDatabase();
	                 /* bind VALUES here */
	    String[] whereArgs ={String.valueOf(cid)};
	    return db.update(TABLE_SHOPPINGCART, cv, where, whereArgs);
	    
	}
	public List<ShoppingCart> getshoppingItemCost(int sid)
	{
		List<ShoppingCart> sc=new ArrayList<ShoppingCart>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT TotalCost,TotalQuantity FROM ShoppingCart WHERE ShoppingCartId = '"+sid+"'", null);
	
		
		
		if (c.moveToFirst()) {
            do {
                ShoppingCart td = new ShoppingCart();
              
               td.setquantity(c.getInt(c.getColumnIndex(TOTAL_QUANTITY)));
                td.setcost(c.getInt(c.getColumnIndex(TOTAL_COST)));
               
                sc.add(td);
            } while (c.moveToNext());
        }
 
             return sc;   
          
        }
	public int updateCustomerRecord(int cid,String cfirstname, String clastname,String ccontact )
	{
		ContentValues cv = new ContentValues();
	    cv.put(CUSTOMER_FIRSTNAME, cfirstname);
	    cv.put(CUSTOMER_LASTNAME,clastname);
	    cv.put(CUSTOMER_CONTACT, ccontact);
	   // cv.put(CUSTOMER_USERNAME, cusername);
	    
	    String where = "CustomerId = ?";
	    SQLiteDatabase db = this.getReadableDatabase();
	              
	    String[] whereArgs ={String.valueOf(cid)};
	    return db.update(TABLE_CUSTOMERS, cv, where, whereArgs);
	    
	}
	
	public long createDonorInformation(DonorInformation di) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    //values.put(CUSTOMER_ID, customers.customerid);
	   
	    values.put(DONOR_FIRSTNAME, di.getdonorfirstname());
	    values.put(DONOR_LASTNAME, di.getdonorlastname());
	    values.put(DONOR_STREET, di.getdonorstreet());
	    values.put(DONOR_CITY, di.getdonorcity());
	    values.put(DONOR_STATE, di.getdonorstate());
	    values.put(DONOR_ZIP, di.getdonorzip());
	    values.put(DONOR_COUNTRY, di.getdonorcountry());
	    values.put(DONOR_EMAIL, di.getdonoremail());
	    values.put(DONOR_CONTACT, di.getdonorcontact());
	    values.put(DONOR_CUSTOMERID, di.getdonorcustid());
	   
	   // insert row
	    long donor_id = db.insert(TABLE_DONORINFORMATION, null, values);
	    return donor_id;
	}
	
	public List<DonorInformation> getDonorInformation(int custid)
	{
		List<DonorInformation> tours = new ArrayList<DonorInformation>();
		SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM DonorInformation WHERE DonorCustId = '"+custid+"'", null);
        
        if (c.moveToFirst()) {
            do {
                DonorInformation td = new DonorInformation();
                td.setdonorid(c.getInt(c.getColumnIndex(DONOR_ID)));
                td.setdonorfirstname(c.getString(c.getColumnIndex(DONOR_FIRSTNAME)));
                td.setdonorlastname(c.getString(c.getColumnIndex(DONOR_LASTNAME)));
                td.setdonorstreet(c.getString(c.getColumnIndex(DONOR_STREET)));
                td.setdonorcity(c.getString(c.getColumnIndex(DONOR_CITY)));
                td.setdonorstate(c.getString((c.getColumnIndex(DONOR_STATE))));
                td.setdonorzip(c.getString((c.getColumnIndex(DONOR_ZIP))));
                td.setdonorcountry(c.getString(c.getColumnIndex(DONOR_COUNTRY)));
                td.setdonoremail(c.getString(c.getColumnIndex(DONOR_EMAIL)));
                td.setdonorcontact(c.getString(c.getColumnIndex(DONOR_CONTACT)));
                
                // adding to todo list
                tours.add(td);
        
            } while (c.moveToNext());
        }
        else
        {
        	tours=null;
        }
        
        
 
        return tours;
		
	}
	public int updateDonorRecord(int custid, String firstname,String lastname,String street, String city,String state, String zip, String country, String email,String contact  )
	{
		ContentValues cv = new ContentValues();
		
	    cv.put(DONOR_FIRSTNAME,firstname);
	    cv.put(DONOR_LASTNAME,lastname);
	    cv.put(DONOR_STREET, street);
	    cv.put(DONOR_CITY, city);
	    cv.put(DONOR_STATE, state);
	    cv.put(DONOR_ZIP, zip);
	    cv.put(DONOR_COUNTRY, country);
	    cv.put(DONOR_EMAIL, email);
	    cv.put(DONOR_CONTACT, contact);
	    
	    String where = "DonorCustId = ?";
	    SQLiteDatabase db = this.getReadableDatabase();
	              
	    String[] whereArgs ={String.valueOf(custid)};
	    return db.update(TABLE_DONORINFORMATION, cv, where, whereArgs);
	    
	}
	
	public long createBillingInformation(BillingInformation bi) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    //values.put(CUSTOMER_ID, customers.customerid);
	   
	    values.put(BILLING_FIRSTNAME, bi.getbillingfirstname());
	    values.put(BILLING_LASTNAME, bi.getbillinglastname());
	    values.put(BILLING_STREET, bi.getbillingstreet());
	    values.put(BILLING_CITY, bi.getbillingcity());
	    values.put(BILLING_STATE, bi.getbillingstate());
	    values.put(BILLING_ZIP, bi.getbillingzip());
	    values.put(BILLING_COUNTRY, bi.getbillingcountry());
	    values.put(BILLING_EMAIL, bi.getbillingemail());
	    values.put(BILLING_CONTACT, bi.getbillingcontact());
	    values.put(BILLING_CUSTOMERID, bi.getbillingcustid());
	   
	   // insert row
	    long billing_id = db.insert(TABLE_BILLINGINFORMATION, null, values);
	    return billing_id;
	}
	
	public List<BillingInformation> getBillingInformation(int custid)
	{
		List<BillingInformation> tours = new ArrayList<BillingInformation>();
		SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM BillingInformation WHERE BillingCustId = '"+custid+"'", null);
        
        if (c.moveToFirst()) {
            do {
                BillingInformation td = new BillingInformation();
                td.setbillingid(c.getInt(c.getColumnIndex(BILLING_ID)));
                td.setbillingfirstname(c.getString(c.getColumnIndex(BILLING_FIRSTNAME)));
                td.setbillinglastname(c.getString(c.getColumnIndex(BILLING_LASTNAME)));
                td.setbillingstreet(c.getString(c.getColumnIndex(BILLING_STREET)));
                td.setbillingcity(c.getString(c.getColumnIndex(BILLING_CITY)));
                td.setbillingstate(c.getString((c.getColumnIndex(BILLING_STATE))));
                td.setbillingzip(c.getString((c.getColumnIndex(BILLING_ZIP))));
                td.setbillingcountry(c.getString(c.getColumnIndex(BILLING_COUNTRY)));
                td.setbillingemail(c.getString(c.getColumnIndex(BILLING_EMAIL)));
                td.setbillingcontact(c.getString(c.getColumnIndex(BILLING_CONTACT)));
                
                // adding to todo list
                tours.add(td);
        
            } while (c.moveToNext());
        }
        else
        {
        	tours=null;
        }
        
        
 
        return tours;
		
	}
	
	public int updateBillingRecord(int custid, String firstname,String lastname,String street, String city,String state, String zip, String country, String email,String contact  )
	{
		ContentValues cv = new ContentValues();
		
	    cv.put(BILLING_FIRSTNAME,firstname);
	    cv.put(BILLING_LASTNAME,lastname);
	    cv.put(BILLING_STREET, street);
	    cv.put(BILLING_CITY, city);
	    cv.put(BILLING_STATE, state);
	    cv.put(BILLING_ZIP, zip);
	    cv.put(BILLING_COUNTRY, country);
	    cv.put(BILLING_EMAIL, email);
	    cv.put(BILLING_CONTACT, contact);
	    
	    String where = "BillingCustId = ?";
	    SQLiteDatabase db = this.getReadableDatabase();
	              
	    String[] whereArgs ={String.valueOf(custid)};
	    return db.update(TABLE_BILLINGINFORMATION, cv, where, whereArgs);
	    
	}
	}
	

