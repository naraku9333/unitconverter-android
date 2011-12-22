package edu.elgin.Converter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
/**
 * ThemeDialog
 * I intend to create a custom dialog for them changing
 * this is a WIP
 * @author Sean Vogel 
 * Dec 22, 2011
 */
public class ThemeDialog extends DialogPreference implements OnClickListener, android.view.View.OnClickListener{
	
	private Button grey, black, blue, red, plum, white;
	
	public ThemeDialog(Context context, AttributeSet attr) {
		super(context, attr);
		// TODO Auto-generated constructor stub
		//setContentView(R.layout.theme_dialog);
		setDialogLayoutResource(R.layout.theme_dialog);
		
//		grey = (Button)findViewById(R.id.btn_grey);
//		grey.setOnClickListener(this);
//		
//		black = (Button)findViewById(R.id.btn_black);
//		black.setOnClickListener(this);
//		
//		blue = (Button)findViewById(R.id.btn_blue);
//		blue.setOnClickListener(this);
//		red = (Button)findViewById(R.id.btn_red);
//		red.setOnClickListener(this);
//		
//		plum = (Button)findViewById(R.id.btn_plum);
//		plum.setOnClickListener(this);
//		
//		white = (Button)findViewById(R.id.btn_white);
//		white.setOnClickListener(this);
	}

	/* (non-Javadoc)
	 * @see android.preference.DialogPreference#onCreateDialogView()
	 */
	@Override
	protected View onCreateDialogView() {
		// TODO Auto-generated method stub
		View v =  super.onCreateDialogView();
		grey = (Button)v.findViewById(R.id.btn_grey);
		grey.setOnClickListener(this);
		
		black = (Button)v.findViewById(R.id.btn_black);
		black.setOnClickListener(this);
		
		blue = (Button)v.findViewById(R.id.btn_blue);
		blue.setOnClickListener(this);
		red = (Button)v.findViewById(R.id.btn_red);
		red.setOnClickListener(this);
		
		plum = (Button)v.findViewById(R.id.btn_plum);
		plum.setOnClickListener(this);
		
		white = (Button)v.findViewById(R.id.btn_white);
		white.setOnClickListener(this);
		return v;
	}

	/* (non-Javadoc)
	 * @see android.preference.DialogPreference#onDismiss(android.content.DialogInterface)
	 */
	@Override
	public void onDismiss(DialogInterface dialog) {
		// TODO Auto-generated method stub
		super.onDismiss(dialog);
	}

	

	@Override
	public void onClick(View arg) {
		// TODO Auto-generated method stub
		int theme = 0;
		switch(arg.getId()){
		case R.id.btn_grey:
			theme = R.style.grey;
			break;
		case R.id.btn_black:
			
			break;
		case R.id.btn_blue:
	
			break;
		case R.id.btn_red:
	
			break;
		case R.id.btn_plum:
	
			break;
		case R.id.btn_white:
	
			break;
		default:
			//setTheme(theme);	
		}
	}

}
