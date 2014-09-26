package com.notiphi.android;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.tenthseptnotiphi.R;
import com.notikum.notifypassive.NotiphiSession;
import com.notikum.notifypassive.utils.NotiphiEventReceiver;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.provider.Settings.Secure;

public class MainActivity extends ActionBarActivity {
	
	TextView tv1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv1 = (TextView)findViewById(R.id.textview1);
		
		JSONObject json = new JSONObject();
		try {
			json.put("key1", "value1");
			json.put("key2", "value2");
			Log.i("[json successfull]", json.toString());
		} catch (JSONException e1) {
			e1.printStackTrace();
			Log.i("[json error]", e1.toString());
		}
		
		new NotiphiEventReceiver(json, getApplicationContext());
		
		try {
			NotiphiSession.init(MainActivity.this, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	protected void onResume() {
	     super.onResume();
	     //Handling client_payload  
	     String cPayload = "There is no clientPayload";
	     Log.i("On Resume","Called........");
	   
	     if(getIntent().getStringExtra("client_data")!=null){
	    	cPayload =  getIntent().getStringExtra("client_data");
	    	Log.i("[client payload value....]", cPayload);
	     }
	     else{
	    	 Log.i("[checking payload data extras]",cPayload);
	     }
	     tv1.setText(cPayload);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
