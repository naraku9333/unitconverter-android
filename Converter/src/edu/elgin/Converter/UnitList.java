package edu.elgin.Converter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 *  
 * CIS-262 - Applied Programming
 * @author Sean Vogel svogel7577@student.elgin.edu
 * @author Rob Kahren II rkahren2@gmail.com
 * 
 * UnitList
 * Unit list choice screen
 * first screen shown to user
 */
public class UnitList extends ListActivity {
	
	private static final String TAG = "Converter";//DBG
	
	//private LayoutInflater mInflater;
	private int resColor;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.d(TAG,"UnitList: onCreate()");//DBG
        resColor = 0;
        
    	//get the theme to set
    	 switch(Settings.getCustomTheme(getBaseContext())){
    	 case 0:
			 setTheme(R.style.grey);
			 resColor = R.color.converter_grey;
			 break;
		 case 1:
			 setTheme(android.R.style.Theme_Black);
			 resColor = android.R.color.black;
			 break;
		 case 2:
			 setTheme(R.style.blue);
			 resColor = R.color.converter_blue;
			 break;
		 case 3:
			 setTheme(R.style.red);
			 resColor = R.color.converter_red;
			 break;
		 case 4:
			 setTheme(R.style.plum);
			 resColor = R.color.converter_plum;
			 break;
		 case 5:
			 setTheme(android.R.style.Theme_Light);
			 resColor = android.R.color.white;
			 break;
		 }
        super.onCreate(savedInstanceState);
        
        //get list items and add to ListAdapter
        //items can be easily added
        String[] items = getResources().getStringArray(R.array.list_item_array);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, items));
        
       
        ListView list = getListView();
        //ColorDrawable draw = new ColorDrawable(this.getResources().getColor(resColor));
        list.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        list.setCacheColorHint(R.color.clear);
       // list.setBackgroundResource(R.drawable.list_selector);
        list.setTextFilterEnabled(true);
        
        //create click listener for array items
        //start activity with selected index added
        list.setOnItemClickListener(new OnItemClickListener(){
        	public void onItemClick(AdapterView<?> parent, View view, int pos, long id){	
        		 Log.d(TAG,"UnitList: onItemClick()");//DBG
        		 
        		switch(pos){
        		case 0:
        		default:
        			Intent i = new Intent(UnitList.this, ConverterActivity.class);
        			i.putExtra("unit", pos);
        			startActivity(i);
        			break;
        		}
        	}
        });
    }
    /**
	 * Create menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		MenuInflater mInflater = getMenuInflater();
		mInflater.inflate(R.menu.menu, menu);
		return true;
	}
	
	/**
	 * menu item selected
	 */
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		
		case R.id.settings:
			startActivity(new Intent(this, Settings.class));
			return true;
			
		case R.id.about_box:
			startActivity(new Intent(this, About.class));
			return true;
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		//check if this activity should be "refreshed"
		//stop and start activity if true
		if(Settings.getURefresh()){
			Settings.setURefresh(false);
			Intent intent = getIntent();
	    	finish();
	    	startActivity(intent);
		}
		super.onResume();
	}
	
	 /**
     * Make a view to hold each row.
     *
     * @see android.widget.ListAdapter#getView(int, android.view.View,
     *      android.view.ViewGroup)
     */
//    public View getView(int position, View convertView, ViewGroup parent) {
//        TextView tx;
//        // When convertView is not null, we can reuse it directly, there is no need
//        // to reinflate it. We only inflate a new View when the convertView supplied
//        // by ListView is null.
//        if (convertView == null) {
//            convertView = mInflater.inflate(R.layout.list_item, null);
//
//            // Creates a ViewHolder and store references to the two children views
//            // we want to bind data to.
//            tx = (TextView) convertView.findViewById(R.id.listItem);
//            tx.setBackgroundColor(getResources().getColor(resColor));
//            convertView.setTag(tx);
//        } else {
//            // Get the ViewHolder back to get fast access to the TextView
//            // and the ImageView.
//            tx = (TextView) convertView.getTag();
//            tx.setBackgroundColor(getResources().getColor(resColor));
//        }
//
//        return convertView;
//    }

}