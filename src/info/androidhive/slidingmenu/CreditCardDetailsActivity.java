package info.androidhive.slidingmenu;

import info.androidhive.slidingmenu.CheckoutActivity.SlideMenuClickListener;
import info.androidhive.slidingmenu.adapter.NavDrawerListAdapter;
import info.androidhive.slidingmenu.model.NavDrawerItem;

import java.util.ArrayList;
import java.util.Calendar;

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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CreditCardDetailsActivity extends Activity {
	
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
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.creditcardetails_layout);
		
		//populate 3  type spinners
		Spinner spinner = (Spinner) findViewById(R.id.cardtypespinner);
	    ArrayAdapter<CharSequence> cardtypeadapter = ArrayAdapter.createFromResource(this, R.array.creditcardtype, android.R.layout.simple_spinner_item);
	    cardtypeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter(cardtypeadapter);
	    
	    final Spinner monthspinner=(Spinner) findViewById(R.id.expirymonth);
	    ArrayAdapter<CharSequence> monthadapter= ArrayAdapter.createFromResource(this, R.array.ccexpirymonth,android.R.layout.simple_spinner_item);
	    monthadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    monthspinner.setAdapter(monthadapter);
	    
	    
	    
	    Spinner yearspinner=(Spinner) findViewById(R.id.expiryyear);
	    ArrayAdapter<CharSequence> yearadapter= ArrayAdapter.createFromResource(this, R.array.ccexpiryyear,android.R.layout.simple_spinner_item);
	    yearadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    yearspinner.setAdapter(yearadapter);
	    
	    yearspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				final String yearselected=parent.getItemAtPosition(position).toString();
	
				monthspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
					Long monthselected=parent.getItemIdAtPosition(position);
					Calendar c=Calendar.getInstance();
					int currentyear=c.get(Calendar.YEAR);
					int currentmonth=c.get(Calendar.MONTH);
						if(Integer.parseInt(yearselected)==currentyear)
						{
							if(monthselected+1<currentmonth)
							{
								Toast.makeText(getApplicationContext(), "Your Credit card is expired!", Toast.LENGTH_SHORT).show();
							}
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "Please select Credit card expiry month", Toast.LENGTH_SHORT).show();
						
					}
				
				});
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Please select Credit card expiry year", Toast.LENGTH_SHORT).show();
				
			}
	    	
		});
	    
	    
	    	
		
	    
	    
	    //button click pass intent to another page
		final Button paynowbutton=(Button) findViewById(R.id.paynowbutton);
        
       paynowbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//get Edittext Id
			    final EditText fname=(EditText) findViewById(R.id.firstname);
			    final EditText ccnumber=(EditText) findViewById(R.id.ccnumber);
			    final EditText cvv=(EditText) findViewById(R.id.cvvnumber);
				//get input value in edittext
				
				String firstname=fname.getText().toString();
				String ccnum=ccnumber.getText().toString();
				String cvvnumber=cvv.getText().toString().trim();
				
				
				//validations
			
				//Log.d("email",""+email.getText());
				
				if(firstname.trim().length()==0 || ccnum.trim().length()==0 || cvvnumber.trim().length()==0)
				{
					Toast.makeText(getApplicationContext(), "all required fields need to be filled", Toast.LENGTH_SHORT).show();
				}
				else if(ccnum.trim().length()!=16)
				{
					Toast.makeText(getApplicationContext(), "Credit card number should be 6 digits long!", Toast.LENGTH_SHORT).show();
				}
				else if(cvvnumber.trim().length()!=3)
				{
					Toast.makeText(getApplicationContext(), "CVV number should be 3 digits long!", Toast.LENGTH_SHORT).show();
				}
				
				
				else
				{
					final Dialog dialog=new Dialog(CreditCardDetailsActivity.this);
					dialog.setContentView(R.layout.paymentconfirmpopup);
					dialog.setTitle("Payment Confirmation");
					WindowManager.LayoutParams lp=dialog.getWindow().getAttributes();
					lp.dimAmount=0.5f;
					dialog.getWindow().setAttributes(lp);
					dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
					
					//get ids
					TextView textview1=(TextView) dialog.findViewById(R.id.textview1);
					TextView textview2=(TextView) dialog.findViewById(R.id.textview2);
					TextView textview3=(TextView) dialog.findViewById(R.id.textview3);
					TextView textview4=(TextView) dialog.findViewById(R.id.textview4);
					TextView textview5=(TextView) dialog.findViewById(R.id.textview5);
					Button okayButton=(Button) dialog.findViewById(R.id.okayButton);
 					
					textview1.setText("Get your proof of ID and collect your passes from the ticket counter on the day of the event");
					textview2.setText("For further details,call 213-633-7469");
					textview3.setText("Your payment has been received");
					textview4.setText("Confirmation receipt has been mailed to your email-Id");
					textview5.setText("Thank you !!");
					
					
					dialog.show();
					
					okayButton.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dialog.dismiss();
							
						}
					});
				
			}
			}
		});
		
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
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
		// Pages
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
		// What's hot, We  will add a counter here
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "50+"));
		

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

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
	}

	/**
	 * Slide menu item click listener
	 * */
	public class SlideMenuClickListener implements
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
