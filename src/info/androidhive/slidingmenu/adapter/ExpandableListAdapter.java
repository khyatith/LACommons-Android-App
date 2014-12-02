package info.androidhive.slidingmenu.adapter;

import info.androidhive.slidingmenu.R;
import info.androidhive.slidingmenu.database.BillingInformation;
import info.androidhive.slidingmenu.database.Customers;
import info.androidhive.slidingmenu.database.DBHelper;
import info.androidhive.slidingmenu.database.DonorInformation;

import info.androidhive.slidingmenu.sessions.SessionsManagement;


import java.util.HashMap;
import java.util.List;

import android.content.Context;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ExpandableListAdapter extends BaseExpandableListAdapter implements TextWatcher {

	
	private Context context;
	
	LayoutInflater layoutInflater;
	DBHelper db;
	int customerid=0;
	int donorid=0;
	int billingid=0;
	
	 public ExpandableListAdapter(Context context, LayoutInflater layoutInflater) {
	        this.context = context;
	        this.layoutInflater = layoutInflater;
	        db=new DBHelper(context);
	    }
	
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		//return this.listDataHeader.size();
		return 3;
		
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		//return this.listDataChild.get(this.listDataHeader.get(groupPosition))
          //      .size();
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		//return this.listDataHeader.get(groupPosition);
		return null;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		//return this.listDataChild.get(this.listDataHeader.get(groupPosition)).get(childPosition);
		return null;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
		
	}
	
	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
			
		 View v = View.inflate(context, R.layout.list_group, null);
		 TextView lblListHeader = (TextView) v.findViewById(R.id.lblListHeader);
		 
		 if(groupPosition == 0) {
			 lblListHeader .setText("Personal Information");
			 lblListHeader .setTextSize(15f);
	        }
		 if(groupPosition == 1) {
			 lblListHeader .setText("Donor Information");
			 lblListHeader .setTextSize(15f);
	        }
		 if(groupPosition == 2) {
			 lblListHeader .setText("Billing Information");
			 lblListHeader .setTextSize(15f);
	        }
		 v.invalidate();
		 return v;
	      
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//final String childText = (String) getChild(groupPosition, childPosition);
		 View v=null;
		 
        if (groupPosition==0)
        {
        	v = View.inflate(context, R.layout.list_item, null);
           
        	
        final EditText txtfirstname = (EditText) v.findViewById(R.id.firstnametext);
        final EditText txtlastname = (EditText) v.findViewById(R.id.lastnametext);
        //final EditText txtemail = (EditText) v.findViewById(R.id.emailtext);
        final EditText txtcontact = (EditText) v.findViewById(R.id.contacttext);
        
        //txtemail.setKeyListener(null);
        // To do - get entries form database based on the customer id from shared preferences
        final SessionsManagement sm=new SessionsManagement(context);
        if(sm.isLoggedIn())
        {
        	
        	HashMap<String, String> custid=sm.getUserDetails();
			String loggedinusername=custid.get(SessionsManagement.KEY_NAME);
			String  loggedinpassword=custid.get(SessionsManagement.KEY_PASSWORD);
			Log.d("loggedinusername",loggedinusername);
			Log.d("loggedinpassword",loggedinpassword);
        	List<Customers> customer=db.getLoggedInCustomerId(loggedinusername, loggedinpassword);
        	for(Customers tags : customer)
        	{
        		txtfirstname.setText(tags.getcustomerfirstname());
        		txtlastname.setText(tags.getcustomerlastname());
        		//txtemail.setText(loggedinusername);
        		txtcontact.setText(tags.getcustomercontact());
        		customerid=tags.getcustomerid();
        	}
        }
        	else
        	{
        		Toast.makeText(context, "You cannot edit this information unless you are logged in!", Toast.LENGTH_SHORT).show();
        		txtfirstname.setKeyListener(null);
        		txtlastname.setKeyListener(null);
        		txtcontact.setKeyListener(null);
        		
        	}
        
        
        if( txtfirstname != null && txtlastname!=null && txtcontact!=null )
        {
	        txtfirstname.addTextChangedListener(this);
	        
	      	txtlastname.addTextChangedListener(this);
        
	      	//txtemail.addTextChangedListener(this);
       
	      	txtcontact.addTextChangedListener(this);
	      	
	      	
        }
        
        Button txtsave=(Button) v.findViewById(R.id.savetext);
        
        txtsave.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 String fname=txtfirstname.getText().toString();
			 String lname=txtlastname.getText().toString();
			// String mail=txtemail.getText().toString();
			 String phone=txtcontact.getText().toString();
			
			 if(fname!=null || lname!=null  || phone!=null)
			 {
				 int updatedrow=db.updateCustomerRecord(customerid,fname, lname,phone);
				 if(updatedrow==1)
				 {
					 
				 ExpandableListAdapter.this.notifyDataSetChanged();
				 Toast.makeText(context, "Your records have been updated!", Toast.LENGTH_SHORT).show();
				 }
				 
			 }
			
			//Toast.makeText(context, "Your Credentials have been saved", Toast.LENGTH_SHORT).show();
			
		        }
		 
			
		}
    	  
      );
        
        //txtListChild.setText(childText);
        }
        if(groupPosition==1)
        {
        	v = View.inflate(context, R.layout.donor_item, null);
        	
        	final EditText firstname=(EditText) v.findViewById(R.id.firstname);
    		final EditText lastname=(EditText) v.findViewById(R.id.lastname);
    		final EditText email=(EditText) v.findViewById(R.id.email);
    		final EditText streetaddr=(EditText) v.findViewById(R.id.streetaddr);
    		final EditText city=(EditText) v.findViewById(R.id.city);
    		final EditText State=(EditText) v.findViewById(R.id.State);
    		final EditText zip=(EditText) v.findViewById(R.id.zip);
    		final EditText country=(EditText) v.findViewById(R.id.country);
    		final EditText contact=(EditText) v.findViewById(R.id.contact);
    		Button savebutton=(Button) v.findViewById(R.id.savetext1);
    		//CheckBox checkBox1=(CheckBox) v.findViewById(R.id.checkBox1);
    		
        	final SessionsManagement sm=new SessionsManagement(context);
        	
            if(sm.isLoggedIn())
            {
            	
            	HashMap<String, String> custid=sm.getUserDetails();
    			String loggedinusername=custid.get(SessionsManagement.KEY_NAME);
    			String  loggedinpassword=custid.get(SessionsManagement.KEY_PASSWORD);
    			Log.d("loggedinusername",loggedinusername);
    			Log.d("loggedinpassword",loggedinpassword);
            	List<Customers> customer=db.getLoggedInCustomerId(loggedinusername, loggedinpassword);
            	for(Customers tags : customer)
            	{
            	
            		customerid=tags.getcustomerid();
            	}
            }
            	else
            	{
            		Toast.makeText(context, "You cannot edit this information unless you are logged in!", Toast.LENGTH_SHORT).show();
            		firstname.setKeyListener(null);
            		lastname.setKeyListener(null);
            		email.setKeyListener(null);
            		streetaddr.setKeyListener(null);
            		city.setKeyListener(null);
            		State.setKeyListener(null);
            		zip.setKeyListener(null);
            		country.setKeyListener(null);
            		contact.setKeyListener(null);
            		
            	}
            if(customerid!=0)
            {
            	List<DonorInformation> donor=db.getDonorInformation(customerid);
            	
            	if(donor==null)
            	{
            		//Toast.makeText(context, "please provide donor information", Toast.LENGTH_SHORT).show();
            		
            		savebutton.setOnClickListener(new OnClickListener()
           		 {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							//Log.d("donor id",""+donorid);
							String donorfname=firstname.getText().toString();
							String donorlname=lastname.getText().toString();
							String donoremail=email.getText().toString();
							String donorstreet=streetaddr.getText().toString();
							String donorcity=city.getText().toString();
							String donorstate=State.getText().toString();
							String donorzip=zip.getText().toString();
							String donorcountry=country.getText().toString();
							String donorcontact=contact.getText().toString();
							
						
							
							if(donorfname!=null || donorlname!=null || donoremail!=null || donorstreet!=null || donorcity!=null || donorstate!=null || donorzip!=null || donorcountry!=null || donorcontact!=null)
							{
								DonorInformation donorinfo=new DonorInformation(customerid,donorfname,donorlname,donorstreet,donorcity,donorstate,donorzip,donorcountry,donoremail,donorcontact);
								Long createddonorid=db.createDonorInformation(donorinfo);
								
								if(createddonorid!=-1)
								 {
									 
								 ExpandableListAdapter.this.notifyDataSetChanged();
								 Toast.makeText(context, "Your records have been Inserted!", Toast.LENGTH_SHORT).show();
								 }
							}
							
						}
           			 
           		 });
            	}
            	else
            	{
            		
            		
            		for(DonorInformation tags:donor)
            		{
            			firstname.setText(tags.getdonorfirstname());
            			lastname.setText(tags.getdonorlastname());
            			email.setText(tags.getdonoremail());
            			streetaddr.setText(tags.getdonorstreet());
            			city.setText(tags.getdonorcity());
            			State.setText(tags.getdonorstate());
            			zip.setText(tags.getdonorzip());
            			country.setText(tags.getdonorcountry());
            			contact.setText(tags.getdonorcontact());
            			donorid=tags.getdonorid();
            			
            			
            		}
            	
            		 savebutton.setOnClickListener(new OnClickListener()
            		 {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Log.d("donor id",""+donorid);
							String donorfname=firstname.getText().toString();
							String donorlname=lastname.getText().toString();
							String donoremail=email.getText().toString();
							String donorstreet=streetaddr.getText().toString();
							String donorcity=city.getText().toString();
							String donorstate=State.getText().toString();
							String donorzip=zip.getText().toString();
							String donorcountry=country.getText().toString();
							String donorcontact=contact.getText().toString();
							
							if(donorfname!=null || donorlname!=null || donoremail!=null || donorstreet!=null || donorcity!=null || donorstate!=null || donorzip!=null || donorcountry!=null || donorcontact!=null)
							{
								int updateddonor=db.updateDonorRecord(customerid, donorfname, donorlname, donorstreet, donorcity, donorstate, donorzip, donorcountry, donoremail, donorcontact);
								Log.d("updateddonor",""+updateddonor); 
								if(updateddonor>=1)
								 {
									 
								 ExpandableListAdapter.this.notifyDataSetChanged();
								 Toast.makeText(context, "Your records have been updated!", Toast.LENGTH_SHORT).show();
								 }
							}
							
						}
            			 
            		 });
            	}
            }
        }
        
        if(groupPosition==2)
        {
        	v = View.inflate(context, R.layout.donor_item, null);
        	
        	final EditText firstname=(EditText) v.findViewById(R.id.firstname);
    		final EditText lastname=(EditText) v.findViewById(R.id.lastname);
    		final EditText email=(EditText) v.findViewById(R.id.email);
    		final EditText streetaddr=(EditText) v.findViewById(R.id.streetaddr);
    		final EditText city=(EditText) v.findViewById(R.id.city);
    		final EditText State=(EditText) v.findViewById(R.id.State);
    		final EditText zip=(EditText) v.findViewById(R.id.zip);
    		final EditText country=(EditText) v.findViewById(R.id.country);
    		final EditText contact=(EditText) v.findViewById(R.id.contact);
    		Button savebutton=(Button) v.findViewById(R.id.savetext1);
    		//CheckBox checkBox1=(CheckBox) v.findViewById(R.id.checkBox1);
    		
        	final SessionsManagement sm=new SessionsManagement(context);
        	
            if(sm.isLoggedIn())
            {
            	
            	HashMap<String, String> custid=sm.getUserDetails();
    			String loggedinusername=custid.get(SessionsManagement.KEY_NAME);
    			String  loggedinpassword=custid.get(SessionsManagement.KEY_PASSWORD);
    			//Log.d("loggedinusername",loggedinusername);
    			//Log.d("loggedinpassword",loggedinpassword);
            	List<Customers> customer=db.getLoggedInCustomerId(loggedinusername, loggedinpassword);
            	for(Customers tags : customer)
            	{
            	
            		customerid=tags.getcustomerid();
            	}
            }
            	else
            	{
            		Toast.makeText(context, "You cannot edit this information unless you are logged in!", Toast.LENGTH_SHORT).show();
            		firstname.setKeyListener(null);
            		lastname.setKeyListener(null);
            		email.setKeyListener(null);
            		streetaddr.setKeyListener(null);
            		city.setKeyListener(null);
            		State.setKeyListener(null);
            		zip.setKeyListener(null);
            		country.setKeyListener(null);
            		contact.setKeyListener(null);
            		
            	}
            if(customerid!=0)
            {
            	List<BillingInformation> billing=db.getBillingInformation(customerid);
            	
            	if(billing==null)
            	{
            		//Toast.makeText(context, "please provide donor information", Toast.LENGTH_SHORT).show();
            		
            		savebutton.setOnClickListener(new OnClickListener()
           		 {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							//Log.d("donor id",""+donorid);
							String billingfname=firstname.getText().toString();
							String billinglname=lastname.getText().toString();
							String billingemail=email.getText().toString();
							String billingstreet=streetaddr.getText().toString();
							String billingcity=city.getText().toString();
							String billingstate=State.getText().toString();
							String billingzip=zip.getText().toString();
							String billingcountry=country.getText().toString();
							String billingcontact=contact.getText().toString();
							
						
							
							if(billingfname!=null || billinglname!=null || billingemail!=null || billingstreet!=null || billingcity!=null || billingstate!=null || billingzip!=null || billingcountry!=null || billingcontact!=null)
							{
								BillingInformation billinginfo=new BillingInformation(customerid,billingfname,billinglname,billingstreet,billingcity,billingstate,billingzip,billingcountry,billingemail,billingcontact);
								Long createdbillingid=db.createBillingInformation(billinginfo);
								
								if(createdbillingid!=-1)
								 {
									 
								 ExpandableListAdapter.this.notifyDataSetChanged();
								 Toast.makeText(context, "Your records have been Inserted!", Toast.LENGTH_SHORT).show();
								 }
							}
							
						}
           			 
           		 });
            	}
            	else
            	{
            		
            		
            		for(BillingInformation tags:billing)
            		{
            			firstname.setText(tags.getbillingfirstname());
            			lastname.setText(tags.getbillinglastname());
            			email.setText(tags.getbillingemail());
            			streetaddr.setText(tags.getbillingstreet());
            			city.setText(tags.getbillingcity());
            			State.setText(tags.getbillingstate());
            			zip.setText(tags.getbillingzip());
            			country.setText(tags.getbillingcountry());
            			contact.setText(tags.getbillingcontact());
            			billingid=tags.getbillingid();
            			
            			
            		}
            	
            		 savebutton.setOnClickListener(new OnClickListener()
            		 {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Log.d("billing id",""+billingid);
							String billingfname=firstname.getText().toString();
							String billinglname=lastname.getText().toString();
							String billingemail=email.getText().toString();
							String billingstreet=streetaddr.getText().toString();
							String billingcity=city.getText().toString();
							String billingstate=State.getText().toString();
							String billingzip=zip.getText().toString();
							String billingcountry=country.getText().toString();
							String billingcontact=contact.getText().toString();
							
							if(billingfname!=null || billinglname!=null || billingemail!=null || billingstreet!=null || billingcity!=null || billingstate!=null || billingzip!=null || billingcountry!=null || billingcontact!=null)
							{
								int updatedbilling=db.updateBillingRecord(customerid, billingfname, billinglname, billingstreet, billingcity, billingstate, billingzip, billingcountry, billingemail, billingcontact);
								Log.d("updatedbilling",""+updatedbilling); 
								if(updatedbilling>=1)
								 {
									 
								 ExpandableListAdapter.this.notifyDataSetChanged();
								 Toast.makeText(context, "Your records have been updated!", Toast.LENGTH_SHORT).show();
								 }
							}
							
						}
            			 
            		 });
            	}
            }
        }
        
        return v;
        
		
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		String first_name=s.toString();
		String last_name=s.toString();
		String emailid=s.toString();
		String contactnumber=s.toString();
		
		Log.d("firstname",first_name);
		Log.d("contactnumber",contactnumber);
	
		if(first_name!=null && last_name!=null && emailid!=null && contactnumber!=null)
		{
			//To do - enter the entries in database
			
			
		}
		
	}

}
