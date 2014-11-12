package info.androidhive.slidingmenu.sessions;

import info.androidhive.slidingmenu.R;
import info.androidhive.slidingmenu.ShoppingCartActivity;

import java.util.ArrayList;



import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.ContactsContract.Data;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter {
	ArrayList<String> display;
    Context context;
    int layoutResourceId;
      private static LayoutInflater inflater=null;
      
      public CustomAdapter(ShoppingCartActivity shoppingcartactivity,int layoutResourceId,ArrayList<String> result)
      {
    	 
    	  display=result;
          context=shoppingcartactivity;
          this.layoutResourceId=layoutResourceId;
           inflater = ( LayoutInflater )context.
                   getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return display.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	public class Holder
    {
        TextView tv;
        Button cancelbutton;
        Button editbutton;
    }

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder=new Holder();
		
		View rowView;        
        //rowView = inflater.inflate(R.layout.newshoppinglist_layout, null);
		 rowView = inflater.inflate(layoutResourceId, parent, false);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < display.size(); i ++) {
            sb.append(display.get(i).toString());
        }
            holder.cancelbutton=(Button) rowView.findViewById(R.id.button1); 
            holder.editbutton=(Button) rowView.findViewById(R.id.button2); 
        
           
        	holder.tv=(TextView) rowView.findViewById(R.id.textLine);
        holder.tv.setText(sb.toString());
        rowView.setTag(holder);
       
        //holder.img.setImageResource(imageId[position]); 
		return rowView;
	}

}
