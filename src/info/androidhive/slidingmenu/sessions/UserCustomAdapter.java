package info.androidhive.slidingmenu.sessions;

import info.androidhive.slidingmenu.R;
import info.androidhive.slidingmenu.ShoppingCartActivity;
import info.androidhive.slidingmenu.database.DBHelper;
import info.androidhive.slidingmenu.database.ShoppingCart;
import info.androidhive.slidingmenu.database.User;

import java.util.ArrayList;



import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



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
	   row.setTag(holder);
	  } else {
	   holder = (UserHolder) row.getTag();
	  }
	  final User user = data.get(position);
	  final int sid=user.getId();
	  holder.textName.setText(user.getTname());
	  holder.textAddress.setText("Quantity\t:\t"+user.getQuantity());
	  holder.textLocation.setText("Cost\t:\t$"+user.getCost());
	  holder.btnEdit.setOnClickListener(new OnClickListener() {
	
	   @Override
	   public void onClick(View v) {
	    // TODO Auto-generated method stub
	    Log.i("Edit Button Clicked", "**********");
	    //Toast.makeText(context, "Edit button Clicked",
	      //Toast.LENGTH_LONG).show();
	    //Log.i("id clicked", ""+sid);
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
				selectedquantity=Integer.parseInt(parent.getItemAtPosition(position).toString());
				int costperticket=user.getCost();
				priceoforder.setText("Total Order Cost:\t\t$"+costperticket*selectedquantity);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				int costperticket=user.getCost();
				priceoforder.setText("Total Order Cost:\t\t$"+costperticket);
				
			}
			
		};
		quantityspin.setOnItemSelectedListener(itemselect);
		addtocart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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



	static class UserHolder {
	  TextView textName;
	  TextView textAddress;
	  TextView textLocation;
	  Button btnEdit;
	  Button btnDelete;
	 }


}
