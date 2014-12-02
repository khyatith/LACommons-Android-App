package info.androidhive.slidingmenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import info.androidhive.slidingmenu.CommunityFragment;
import info.androidhive.slidingmenu.FindPeopleFragment;
import info.androidhive.slidingmenu.HomeFragment;
import info.androidhive.slidingmenu.PagesFragment;
import info.androidhive.slidingmenu.PhotosFragment;
import info.androidhive.slidingmenu.R;
import info.androidhive.slidingmenu.WhatsHotFragment;
import info.androidhive.slidingmenu.adapter.NavDrawerListAdapter;
import info.androidhive.slidingmenu.database.Customers;
import info.androidhive.slidingmenu.database.DBHelper;
import info.androidhive.slidingmenu.database.ShoppingCart;
import info.androidhive.slidingmenu.database.UpcomingTours;
import info.androidhive.slidingmenu.model.NavDrawerItem;
import info.androidhive.slidingmenu.sessions.SessionsManagement;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;

import android.support.v4.widget.DrawerLayout;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import android.widget.Spinner;
import android.widget.TextView;

public class TrekDetails extends Activity{
	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
	int customerid=0;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trekdetails_layout);
		final Context context = this;
		Intent intent=getIntent();
		String infooftrek=intent.getStringExtra("trekevent");
		final String[] nameoftrek=infooftrek.split("\\.");
		TextView tv=(TextView) findViewById(R.id.upcomingtours);
		tv.setText(nameoftrek[0]);
		final SessionsManagement sm=new SessionsManagement(getApplicationContext());
		Button shoppingicon =(Button) findViewById(R.id.shoppingicon);
		
		final DBHelper db;
	     
		 db = new DBHelper(getApplicationContext());
		 List<UpcomingTours> allTags = db.getTourDetails(nameoftrek[0]);
		 final TextView cost=(TextView) findViewById(R.id.cost);
	        for (UpcomingTours tag : allTags) {
	        	
	        	String[] splitday=tag.getdate().split("\\s+");
	        	
	        	
	        	//date
	        	TextView daymonthyear=(TextView) findViewById(R.id.daymonthyear);
	        	daymonthyear.setText(tag.getmonth()+"\t"+splitday[0]+"\t,\t"+tag.getyear());
	        	
	        	//timings
	        	TextView timings=(TextView) findViewById(R.id.timings);
	        	timings.setText(tag.gettime()+"\t-\t"+tag.getendtime());
	        	
	        	//venue
	        	TextView venue=(TextView) findViewById(R.id.venue);
	        	String[] venuesplit=tag.getvenue().split("\\,");
	        	venue.setText(venuesplit[0]+",\n"+venuesplit[1]+",\n"+venuesplit[2]+",\n"+venuesplit[3]);
	        	
	        	//cost
	        	//TextView cost=(TextView) findViewById(R.id.cost);
	        	cost.setText("Cost\t:\t\t$"+tag.getcost());
	        }
	        shoppingicon.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(sm.isLoggedIn())
						
					{
						HashMap<String, String> custid=sm.getUserDetails();
						String loggedinusername=custid.get(SessionsManagement.KEY_NAME);
						String  loggedinpassword=custid.get(SessionsManagement.KEY_PASSWORD);
						
			        	List<Customers> customer=db.getLoggedInCustomerId(loggedinusername, loggedinpassword);
			        	for(Customers tags : customer)
			        	{
			        	
			        		customerid=tags.getcustomerid();
			        	}
					Intent i=new Intent(TrekDetails.this,ShoppingCartActivity.class);
					i.putExtra("customerid", customerid);
					startActivity(i);
					
				}
					else
					{
						Toast.makeText(context, "Please Login to access your shopping cart!",Toast.LENGTH_SHORT).show();
					}
				
					
				}
			});
	        Button knowmorebutton=(Button) findViewById(R.id.knowmorebutton);
	        final Button bookticketsbutton=(Button) findViewById(R.id.bookticketsbutton);
	        
	        knowmorebutton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(TrekDetails.this,TrekDescription.class);
					intent.putExtra("nameoftrek", nameoftrek[0]);
					startActivity(intent);
					
				}
			});
		bookticketsbutton.setOnClickListener(new View.OnClickListener() {
			 
			
			
			@Override
			public void onClick(View v) {
				if(sm.isLoggedIn())
				{
					//sm.logoutUser();
				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.quantitypopup_layout);
				dialog.setTitle("Book Tickets");
				WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();  
				lp.dimAmount=0.5f;  
				dialog.getWindow().setAttributes(lp);  
				dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
				dialog.show();
				//getWindow().setBackgroundDrawable(new ColorDrawable(0x7000000));
				Button addtocart=(Button) dialog.findViewById(R.id.addtocartbutton);
				final Spinner quantityspin=(Spinner) dialog.findViewById(R.id.quantityspinner);
				final TextView priceoforder=(TextView) dialog.findViewById(R.id.priceoforder);
				
				
				OnItemSelectedListener itemSelect=new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						int selectedquantity=1;
						//selectedquantity=Integer.parseInt(quantityspin.getSelectedItem().toString());
						selectedquantity=Integer.parseInt(parent.getItemAtPosition(position).toString());
						
						//Log.d("quantityselected",""+selectedquantity);
						String costperticket=(String) cost.getText();
						//String[] cpt=costperticket.split("\\$",4);	
						//Log.d("cost of tickets",costperticket.substring(9));
						int costofticket=0;
						costofticket=Integer.parseInt(costperticket.substring(9));
						
						int totalcost=costofticket*selectedquantity;
						//Log.d("total order price:",""+totalcost);
					 priceoforder.setText("Total Order Cost:\t\t$"+totalcost);
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub
						String costperticket=(String) cost.getText();
						//TextView priceoforder=(TextView) parent.findViewById(R.id.priceoforder);
						priceoforder.setText("Total Order Cost:\t\t$"+costperticket);
					}
					
					
				};
				//Log.d("quantityselected",""+selectedquantity);
				quantityspin.setOnItemSelectedListener(itemSelect);
				
				
			
				addtocart.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						int totalcost=0;
						// TODO Auto-generated method stub
						//dialog.dismiss();
						//add the selected item to shopping cart table and pass intent to shoppingcart activity
						int quan=Integer.parseInt(quantityspin.getSelectedItem().toString());
						//Log.d("final spinner",""+quan);
						int finalcost=Integer.parseInt(priceoforder.getText().toString().substring(20));
						//Log.d("final cost",finalcost);
						totalcost=totalcost+finalcost;
						
						HashMap<String, String> custid=sm.getUserDetails();
						String cusername=custid.get(SessionsManagement.KEY_NAME);
						String cpassword=custid.get(SessionsManagement.KEY_PASSWORD);
						int cid=0;
						int trekid=0;
						List<Customers> allTags=db.getLoggedInCustomerId(cusername,cpassword);
						for (Customers tag : allTags) {
					    cid=tag.getcustomerid();
					    Log.d("customer id:",""+cid);
						}
						
						List<UpcomingTours> allUT = db.getTourDetails(nameoftrek[0]);
						for(UpcomingTours tag1 : allUT)
						{
						trekid=tag1.getid();
						}
						
						ShoppingCart shoppingcart=new ShoppingCart(trekid,cid,quan,finalcost);
						Long shoppingcartid=db.createShoppingCart(shoppingcart);
						
						Log.d("shoppingcartid:",""+shoppingcartid);
					Intent intent=new Intent(TrekDetails.this,ShoppingCartActivity.class);
					intent.putExtra("customerid",cid);
					/*intent.putExtra("final cost", finalcost);
					intent.putExtra("nameofevent", nameoftrek[0]);*/
					//intent.putExtra(name, value)
					startActivity(intent);
						
						
						
					}
				});
				
				}
		
				else
				{
					sm.checkLogin();
				}
		
			}	
				
		});
	
				// TODO Auto-generated method stub
				/*LayoutInflater layoutInflater 
			     = (LayoutInflater)getBaseContext()
			      .getSystemService(LAYOUT_INFLATER_SERVICE);  
			    View popupView = layoutInflater.inflate(R.layout.quantitypopup_layout, null);  
			   
			             final PopupWindow popupWindow = new PopupWindow(
			               popupView, 
			               LayoutParams.WRAP_CONTENT,  
			                     LayoutParams.WRAP_CONTENT);  
			             popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
			             
			          
			            
			             Button btnDismiss = (Button)popupView.findViewById(R.id.addtocartbutton);
			            
			             btnDismiss.setOnClickListener(new Button.OnClickListener(){

			     @Override
			     public void onClick(View v) {
			      // TODO Auto-generated method stub
			      popupWindow.dismiss();
			      
			     }});
			               
			             popupWindow.showAsDropDown(bookticketsbutton, 50, -30);*/
			         
			   
			    
	
	
		
		mTitle = mDrawerTitle = getTitle();

		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		// Home
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		// Find People
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		// Photos
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
		// Communities, Will add a counter here
		/*navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
		// Pages
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
		// What's hot, We  will add a counter here
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "50+"));*/
		

		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
		) {
			public void onDrawerClosed(View view) {
				//getActionBar().setTitle(mTitle);
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		/*if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
		*/
	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new HomeFragment();
			break;
		case 1:
			fragment = new FindPeopleFragment();
			break;
		case 2:
			fragment = new PhotosFragment();
			break;
		case 3:
			fragment = new CommunityFragment();
			break;
		case 4:
			fragment = new PagesFragment();
			break;
		case 5:
			fragment = new WhatsHotFragment();
			break;

		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			/*mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);*/
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

}




