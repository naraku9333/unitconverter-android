package edu.elgin.Converter;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ThemeDialog extends Dialog implements OnClickListener{
	
	private Button grey, black, blue, red, plum, white;
	
	public ThemeDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		setContentView(R.layout.theme_dialog);
		grey = (Button)findViewById(R.id.btn_grey);
		grey.setOnClickListener(this);
		
		black = (Button)findViewById(R.id.btn_black);
		black.setOnClickListener(this);
		
		blue = (Button)findViewById(R.id.btn_blue);
		blue.setOnClickListener(this);
		red = (Button)findViewById(R.id.btn_red);
		red.setOnClickListener(this);
		
		plum = (Button)findViewById(R.id.btn_plum);
		plum.setOnClickListener(this);
		
		white = (Button)findViewById(R.id.btn_white);
		white.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg) {
		// TODO Auto-generated method stub
		switch(arg.getId()){
		case R.id.btn_grey:
			
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
		}
	}

}
