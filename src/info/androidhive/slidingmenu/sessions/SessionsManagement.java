package info.androidhive.slidingmenu.sessions;

import info.androidhive.slidingmenu.LoginActivity;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionsManagement {
	
	SharedPreferences pref;
	
	Editor editor;
	
	Context _context;
	int PRIVATE_MODE=0;
	
	//Shared preference file name
	private static final String PREF_NAME = "AndroidHivePref";
	
	//all shared preference keys
	private static final String IS_LOGIN = "IsLoggedIn";
	
	// User name (make variable public to access from outside)
    public static final String KEY_NAME = "username";
    public static final String KEY_PASSWORD="password";
   
    
 // Constructor
    public SessionsManagement(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.commit();
      
    }
    
    public void createLoginSession(String username,String password){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
         
        // Storing name in pref
        editor.putString(KEY_NAME, username);
         
        //Storing password in pref
        editor.putString(KEY_PASSWORD, password);
        
        //Storing customerid in pref
        
     // commit changes
        editor.commit();
    }
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
    	
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
      
         
       // return user
        return user;
    } 
    
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             
            // Staring Login Activity
            _context.startActivity(i);
        }
    }
      
        
        public void logoutUser()
        {
            // Clearing all data from Shared Preferences
            editor.clear();
            editor.commit();
             
            // After logout redirect user to Loing Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             
            // Staring Login Activity
            _context.startActivity(i);
        }
         
    }
   
	
	


