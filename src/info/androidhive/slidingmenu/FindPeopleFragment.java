package info.androidhive.slidingmenu;

import info.androidhive.slidingmenu.database.Customers;
import info.androidhive.slidingmenu.database.DBHelper;
import info.androidhive.slidingmenu.database.UpcomingTours;
import info.androidhive.slidingmenu.sessions.SessionsManagement;
import info.androidhive.slidingmenu.TrekDetails;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class FindPeopleFragment extends Fragment {
	int customerid=0;
	DBHelper db;
	
	public FindPeopleFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
       View rootView = inflater.inflate(R.layout.fragment_find_people, container, false);
       final SessionsManagement sm=new SessionsManagement(getActivity());
      
		
		
	      
			 db = new DBHelper(getActivity());
			 
			 //creating tour information
			 
			//UpcomingTours ut1=new UpcomingTours(1,"South LA Power Festival","6th Sept","1:00 PM","September",2014,"Martin Luther King Park,3900 S. Western Ave,Los Angeles CA,900062 US","Community Coalition is hosting its 3rd Annual PowerFest on September 6, 2014, from 1-8pm.PowerFest is a FREE music festival that attracts thousands of families from South Los Angeles in a day of celebration, empowerment, and education. There will be live music, food trucks, live art, healthcare enrollment, and community resources.Look for the LA Commons Art Booth!!!!",200,"8:00PM");
			//UpcomingTours ut2=new UpcomingTours(2,"Frog Town River Cooldown","17th Sept","12:00 PM","September",2014,"LA River Edge,Newell Street,Los Angeles CA,900062 US","Join docent Patricia Perez for a late summer afternoon in Frogtown! We’ll begin by the riverside, telling tales of the famous LA River. Then we’ll embark on a journey through the culturally rich and diverse neighborhood of Elysian Valley. The tour will stop at the Tracy A. Stone Architecture Studio, Nomad’s Print Studio Gallery Art Compound, Steven B. Graziani Studio, as well as other artists’ lairs to explore the wide range of art found in Frogtown. We’ll also meet up with the team at Allins Surfboard Studio, where real hula dancers will show us their best moves. A visit to the seventies Community House allows tour goers to meet more local artists and check out their fascinating artwork. The final stop will top off an art-filled afternoon at the Frogtown Artwalk!Meet at the LA River edge, at the end of Newell St., at 12:00 P.M. Public transit is highly recommended. Make sure to wear comfortable walking shoes!",400,"8:00PM");
			//UpcomingTours ut3=new UpcomingTours(3,"Drums in Korea Town","27th Sept","4:00 PM","September",2014,"KoreaTown Immigrant Workers Alliance,3465 W. 8th St, Los Angeles CA,90005 US","Koreatown, one of Los Angeles’ hottest spots, is bustling with people, sights, and sounds (not to mention cars) every day. But did you ever stop to think about the artistic and historical culture of the neighborhood? Join Linette Park and learn about the socio-political anecdotes of Koreatown, the history of KIWA, then journey down the popular 8th St. Littered with locally owned shops and restaurants, 8th St. is a fitting representation of Koreatown’s unique charm. We’ll stop in Mansoo BBQ, Honey Pig, and other delicious eateries. The tour will end at the 40th Annual Korean Festival at Seoul International Park. The Festival—dedicated to embracing the diversity found in not only Koreatown, but the entirety of Los Angeles—features live music, dancing, food, and drinks. It’s the perfect way to end the 2013 Trekking LA series, and we hope you will celebrate with us!The tour will assemble at Koreatown Immigrant Workers Alliance (KIWA), 3465 W. 8th St. Los Angeles, CA 90005, at 4:00 P.M. The tour’s duration is 1-2 hours, so make sure to wear comfortable walking shoes!",300,"6:00PM");
			//UpcomingTours ut4=new UpcomingTours(4,"Indulge in Thai culture in East Hollywood","29th Oct","12:00 AM","October",2014,"Municipal Art Gallery,Barnsdall Park,Los Angeles CA,80007 US","Join Elson Trinidad to explore East Hollywood, experiencing the food, art, and music of the Thai and Armenian communites based there. We’ll start off in Little Armenia with a sampling of Armenian baked goods, then make our way over to Thai Town. Elson will bring participants to some of the most popular stops in the neighborhood as well as teach them a little something about future developments in store for Thai Town. The tour also includes stops at the Sanamlung Café, Thai bookstore, and a Thai dessert eatery. We’ll end the tour with a visit to the 21st Annual Thai Art and Culture Festival at Barnsdall Park. Peruse through a collection of amazing artwork, painting demonstrations, chow down on delicious Thai food, and course, learn the dances of Thailand with the locals. You can even get a massage or try kickboxing!The tour will assemble at the front of the Municipal Art Gallery, located in Barnsdall Park (4800 Hollywood Blvd.) Public transit is highly recommended. Wear comfortable walking shoes!",300,"6:00PM");
			
			Customers c1=new Customers("Khyati","Thakur","khyatith","1234","9311179434");
			db.createCustomers(c1);
			//inserting tours in db
			//long ut1_id = db.createTours(ut1);
			//long ut2_id=db.createTours(ut2);
			//long ut3_id=db.createTours(ut3);
			//long ut4_id=db.createTours(ut4);
			
			//DonorInformation di=new DonorInformation(1,2,"Gagandeep","Chandiok","2707 portland street","Los Angeles","CA","US","90007","gagan@usc.edu","9868179434");
			//Long di1=db.createDonorInformation(di);
			//Log.d("Tag Count", "Tag Count: " + db.getAllTours().size());
			ArrayList<String> display=new ArrayList<String>();
			String combine="";
			final ListView list=(ListView) rootView.findViewById(R.id.list1) ;
			List<UpcomingTours> allTags = db.getAllTours();
	        for (UpcomingTours tag : allTags) {
	            //Log.d("Tag Name", tag.gettourname());
	        	combine=tag.gettourname()+".\n"+tag.getdate()+",\t"+tag.gettime();
	            display.add(combine);
	            //display.add(tag.gettime());
	            //display.add(tag.getdate());
			
	        }
	      ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.textviewlayout,display);
	            list.setAdapter(adapter);
	        db.close();
	        
	       
	        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	            public void onItemClick(AdapterView<?> arg0, View arg1,
	                    int position, long arg3) {
	                // TODO Auto-generated method stub

	                       String item=(String) list.getItemAtPosition(position);
	                       

	                        Intent intent = new Intent(getActivity(), TrekDetails.class);
	                        intent.putExtra("trekevent", item);
	                        startActivity(intent);


	            }
	        });

	       Button shoppingicon =(Button) rootView.findViewById(R.id.shoppingicon);
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
				Intent i=new Intent(getActivity(),ShoppingCartActivity.class);
				i.putExtra("customerid", customerid);
				startActivity(i);
				
			}
				else
				{
					Toast.makeText(getActivity(), "Please Login to access your shopping cart!",Toast.LENGTH_SHORT).show();
				}
			}
		});
			 
	         
		       return rootView;
	}
}
			


	
