package edu.elgin.Converter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * UnitList.java
 * 
 * CIS-262 - Applied Programming
 * @author Sean Vogel svogel7577@student.elgin.edu
 * @author Rob Kahren II rkahren2@gmail.com
 */
public class UnitList extends ListActivity {
	
	private static final String TAG = "Converter";//DBG
	public static int unit = 0;
	//public static int unit;
	
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
        			unit = pos;
        			/*UnitList.this.*/startActivity(i);
        			break;
        		}
        	}
        });
    }
}