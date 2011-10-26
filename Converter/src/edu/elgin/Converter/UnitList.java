package edu.elgin.Converter;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
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
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Log.d(TAG,"UnitList: onCreate()");//DBG
        
        //get list items and add to ListAdapter
        //items can be easily added
        String[] items = getResources().getStringArray(R.array.list_item_array);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, items));
        
       
        ListView list = getListView();
        ColorDrawable draw = new ColorDrawable(this.getResources().getColor(R.color.converter_background));
        list.setBackgroundDrawable(draw);

        list.setTextFilterEnabled(true);
        
       
        
        
        //create click listener for array items
        list.setOnItemClickListener(new OnItemClickListener(){
        	public void onItemClick(AdapterView<?> parent, View view, int pos, long id){
        		
        		 Log.d(TAG,"UnitList: onItemClick()");//DBG
        		 
        		//TODO:	call appropriate method for item click
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
}