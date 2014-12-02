package info.androidhive.slidingmenu;

import java.util.HashMap;
import java.util.List;
import info.androidhive.slidingmenu.adapter.ExpandableListAdapter;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;


public class HomeFragment extends Fragment {
	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
	
    public HomeFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		
        
	    View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        // get the listview
	    expListView = (ExpandableListView) rootView.findViewById(R.id.lvExp);
	    expListView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
	   listAdapter = new ExpandableListAdapter(getActivity(),inflater);
	   expListView.setAdapter( listAdapter);
	   
	   
        
	   return rootView;
    }
	
}
