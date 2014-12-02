package info.androidhive.slidingmenu;

import info.androidhive.slidingmenu.sessions.SessionsManagement;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class CommunityFragment extends Fragment {
	Button loginbutton;
    Button signupbutton;
	public CommunityFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_community, container, false);
        loginbutton=(Button) rootView.findViewById(R.id.loginbutton);
        signupbutton=(Button) rootView.findViewById(R.id.Singupbutton);
        
        
        final SessionsManagement sm=new SessionsManagement(getActivity());
        if(sm.isLoggedIn())
        {
        	loginbutton.setText("LogOut");
        	signupbutton.setClickable(false);
        	loginbutton.setOnClickListener(new View.OnClickListener()
        	{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					sm.logoutUser();
					loginbutton.setText("LogIn");
					
					Toast.makeText(getActivity().getApplicationContext(), "You have been Logged Out!", Toast.LENGTH_LONG).show();  
					
				}
        		
        	});
        	
        }
        else
        {
        	loginbutton.setText("LogIn");
        	signupbutton.setClickable(true);
        	
        	loginbutton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(getActivity(),LoginActivity.class);
					startActivityForResult(intent, 1);
					
				}
			});
        	signupbutton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i=new Intent(getActivity(),SignUpActivity.class);
					startActivity(i);
					
				}
			});
        }
        return rootView;
    }

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		  super.onActivityResult(requestCode, resultCode, data);
		  
		      if (resultCode == Activity.RESULT_OK) {
		        // TODO Extract the data returned from the child Activity.
		    	  loginbutton.setText("LogOut");
		    	  signupbutton.setClickable(false);
		    	  Toast.makeText(getActivity().getApplicationContext(), "Welcome to LACommons!", Toast.LENGTH_LONG).show(); 
		    	  
		    	  
		      }
		     
		  }
	}

