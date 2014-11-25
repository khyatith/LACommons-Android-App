package info.androidhive.slidingmenu;

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
import info.androidhive.slidingmenu.database.User;
import info.androidhive.slidingmenu.model.NavDrawerItem;
import info.androidhive.slidingmenu.sessions.SessionsManagement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class ShoppingCartActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	final Context context=this;
	
	
	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
	ArrayList<User> userArray = new ArrayList<User>();
	

	 protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shoppingcart_layout);
		
		
		final DBHelper db= new DBHelper(getApplicationContext());
		ArrayList<String> display=new ArrayList<String>();
		Bundle extras = getIntent().getExtras();
		int cid=extras.getInt("customerid");
		String combine="";
		List<ShoppingCart> allTags= db.getCartDetails(cid);
		final ListView list=(ListView) findViewById(R.id.shoplist) ;
		final TextView totalamounttextview=(TextView) findViewById(R.id.totalamounttextview);
		
		
	
		int totalcost=0;
		for(ShoppingCart tag : allTags) {
	    
		int cost=tag.getcost();
	    int trekid=tag.gettrekid();
	    int quantity=tag.getquantity();
	    
	    int shoppingcartid=tag.getshoppingcartid();
	    totalcost=totalcost+cost;
	    //totalcost=totalcost+cost;
	    List<UpcomingTours> everytour=db.getTreksForCart(trekid);
	    
	   
	    
	    for(UpcomingTours tag1 : everytour)
	    {
	    	String trekname=tag1.gettourname();
	    	 //Log.d("id",""+shoppingcartid);
	    	 combine="\n\n"+trekname+"\nQuantity:\t"+quantity+"\nCost:\t$"+cost+"\n\n";
	            userArray.add(new User(shoppingcartid,trekname,quantity,cost));
	            
	  	   
	    	
	    }
	   
		}
		 totalamounttextview.setText("Total Amount:\t$"+totalcost);
		 
		
		
		UserCustomAdapter userAdapter;
		userAdapter = new UserCustomAdapter(ShoppingCartActivity.this, R.layout.row,userArray);
			  
			  list.setItemsCanFocus(false);
			  list.setAdapter(userAdapter);
			 
			  TextView footertext=(TextView) findViewById(R.id.footertext);
			  footertext.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						final SessionsManagement sm=new SessionsManagement(context);
						HashMap<String, String> custid=sm.getUserDetails();
						String cusername=custid.get(SessionsManagement.KEY_NAME);
						String cpassword=custid.get(SessionsManagement.KEY_PASSWORD);
						int cid=0;
						List<Customers> allCust=db.getLoggedInCustomerId(cusername,cpassword);
						for (Customers tag : allCust) {
					    cid=tag.getcustomerid();
					    Log.d("customer id:",""+cid);
						}
						Intent intent=new Intent(ShoppingCartActivity.this,CheckoutActivity.class);
						intent.putExtra("customerid",cid );
						startActivity(intent);
						
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
	
	public class UserCustomAdapter extends ArrayAdapter<User> {
		Context context;
		 int layoutResourceId;
		 ArrayList<User> data = new ArrayList<User>();
		 DBHelper dbhelper;
		
		 public UserCustomAdapter(Context context, int layoutResourceId,
		   ArrayList<User> data) {
		  super(context, layoutResourceId, data);
		  this.layoutResourceId = layoutResourceId;
		  this.context = context;
		  this.data = data;
		  dbhelper=new DBHelper(context);
		 }

		 @Override
		 public View getView(final int position, View convertView, ViewGroup parent) {
		  View row = convertView;
		  UserHolder holder = null;

		  if (row == null) {
		   LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		   row = inflater.inflate(layoutResourceId, parent, false);
		   holder = new UserHolder();
		   holder.textName = (TextView) row.findViewById(R.id.textView1);
		   holder.textAddress = (TextView) row.findViewById(R.id.textView2);
		   holder.textLocation = (TextView) row.findViewById(R.id.textView3);
		   holder.btnEdit = (Button) row.findViewById(R.id.button1);
		   holder.btnDelete = (Button) row.findViewById(R.id.button2);
		   holder.btnViewDetails=(Button) row.findViewById(R.id.button3);
		   
		   row.setTag(holder);
		  } else {
		   holder = (UserHolder) row.getTag();
		  }
		  final User user = data.get(position);
		  final int sid=user.getId();
		  //Log.d("sid",""+sid);
		  holder.textName.setText(user.getTname());
		  holder.textAddress.setText("Quantity\t:\t"+user.getQuantity());
		  holder.textLocation.setText("Cost\t:\t$"+user.getCost());
		  
		 holder.btnViewDetails.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//Log.d("selected trek name:",""+user.getTname());
				final Dialog dialog=new Dialog(context);
				dialog.setContentView(R.layout.trekdetailspopup);
				dialog.setTitle("Trek Details");
				WindowManager.LayoutParams lp=dialog.getWindow().getAttributes();
				lp.dimAmount=0.5f;
				dialog.getWindow().setAttributes(lp);
				dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
				List<UpcomingTours> allTags=dbhelper.getTourDetails(user.getTname());
				for(UpcomingTours tags : allTags)
				{
				TextView trekname=(TextView) dialog.findViewById(R.id.trekname);
				TextView trekdate=(TextView) dialog.findViewById(R.id.trekdate);
				TextView trektime=(TextView) dialog.findViewById(R.id.trektime);
				TextView trekvenue=(TextView) dialog.findViewById(R.id.trekvenue);
			
				trekname.setText(tags.gettourname());
				trekdate.setText("On\t"+tags.getdate()+","+tags.getyear());
				trektime.setText("From\t"+tags.gettime()+"\tTo\t"+tags.getendtime());
				String[] venue=tags.getvenue().split("\\,");
				trekvenue.setText("Venue\n"+venue[0]+",\n"+venue[1]+",\n"+venue[2]+",\n"+venue[3]);
				
				}
				dialog.show();
				Button okbutton=(Button) dialog.findViewById(R.id.OKButton);
				okbutton.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.dismiss();
						
					}
				});
			}
		});
		  holder.btnEdit.setOnClickListener(new OnClickListener() {
		
		   @Override
		   public void onClick(View v) {
		    // TODO Auto-generated method stub
		   // Log.i("Edit Button Clicked", "**********");
		    //Toast.makeText(context, "Edit button Clicked",
		      //Toast.LENGTH_LONG).show();
		   // Log.i("id clicked", ""+sid);
		    final Dialog dialog=new Dialog(context);
		    dialog.setContentView(R.layout.quantitypopup_layout);
		    dialog.setTitle("Edit Ticket");
		    WindowManager.LayoutParams lp=dialog.getWindow().getAttributes();
		    lp.dimAmount=0.5f;
		    dialog.getWindow().setAttributes(lp);
		    dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
			dialog.show();
			Button addtocart=(Button) dialog.findViewById(R.id.addtocartbutton);
			final Spinner quantityspin=(Spinner) dialog.findViewById(R.id.quantityspinner);
			final TextView priceoforder=(TextView) dialog.findViewById(R.id.priceoforder);
			
			OnItemSelectedListener itemselect=new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					int selectedquantity=1;
					
					int fc=0;
					selectedquantity=Integer.parseInt(parent.getItemAtPosition(position).toString());
					//selectedquantity=user.getQuantity();
					int costperticket=user.getCost();
					/*dbhelper.getshoppingItemCost(sid);
					for(ShoppingCart tag : allTags) {
					    
						costperticket=tag.getcost();
					   selectedquantity=tag.getquantity();
					}*/
					fc=selectedquantity*costperticket;
					priceoforder.setText("Total Order Cost:\t\t$"+fc);
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub
					
					
					
				}
				
			};
			quantityspin.setOnItemSelectedListener(itemselect);
			addtocart.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int quan=Integer.parseInt(quantityspin.getSelectedItem().toString());
					
					int finalcost=Integer.parseInt(priceoforder.getText().toString().substring(20));
				
					
					final SessionsManagement sm=new SessionsManagement(context);
					HashMap<String, String> custid=sm.getUserDetails();
					String cusername=custid.get(SessionsManagement.KEY_NAME);
					String cpassword=custid.get(SessionsManagement.KEY_PASSWORD);
					int cid=0;
					int trekid=0;
					List<Customers> allTags=dbhelper.getLoggedInCustomerId(cusername,cpassword);
					for (Customers tag : allTags) {
				    cid=tag.getcustomerid();
				    Log.d("customer id:",""+cid);
					}
					int updatedrow=dbhelper.updateShoppingCart(cid,quan,finalcost);
					Log.d("updated row", ""+updatedrow);
					if(updatedrow>1)
					{
						/*holder.textAddress.setText("Quantity\t:\t"+quan);
						textLocation.setText("Cost\t:\t$"+finalcost);*/
						
						user.setCost(finalcost);
						user.setQuantity(quan);
						UserCustomAdapter.this.notifyDataSetChanged(); 
						
					}
					dialog.dismiss();
				}
			});
		   }
		  });
		  holder.btnDelete.setOnClickListener(new OnClickListener() {

		   @Override
		   public void onClick(View v) {
		    // TODO Auto-generated method stub
			   
		    //Log.i("Delete Button Clicked", "**********");
		    //Toast.makeText(context, "Delete button Clicked",
		     // Toast.LENGTH_LONG).show();
		    int rowsdeleted=dbhelper.RemoveItemFromCart(sid);
		    
		    	Log.d("item removed",""+rowsdeleted);
		    	data.remove(position);
		    	updateTotalCost(user.getCost());
		    	UserCustomAdapter.this.notifyDataSetChanged(); 
		    	
		    
		   }
		  });
		  return row;

		 }

		

		protected void updateTotalCost(int cost) {
			// TODO Auto-generated method stub
			Log.d("cost deleted",""+cost);
			
		}



		 class UserHolder {
		   Button btnViewDetails;
		TextView textName;
		  TextView textAddress;
		  TextView textLocation;
		  Button btnEdit;
		  Button btnDelete;
		 }


	}

}

