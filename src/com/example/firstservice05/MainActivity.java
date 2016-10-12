package com.example.firstservice05;

import com.example.firstservice05.MyService.MyBinder;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	MyBinder myBinder;
	protected MyBinder service;
	protected static final String TAG = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button start=(Button) this.findViewById(R.id.start);
		Button stop=(Button) this.findViewById(R.id.stop);
		final Intent intent=new Intent();
		intent.setAction("android.intent.action.My_Service");
		start.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				startService(intent);
				
			}
		});
		stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				stopService(intent);
				
			}
		});
		
		Button bind=(Button) this.findViewById(R.id.bind);
		Button unbind=(Button) this.findViewById(R.id.unbind);
		Button getData=(Button) this.findViewById(R.id.getData);
		bind.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				bindService(intent,conn,Service.BIND_AUTO_CREATE);
				
			}
		});
		
		unbind.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				unbindService(conn);
				
			}
		});
		
		getData.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {		
				Toast.makeText(MainActivity.this, "Count="+myBinder.getCount(),100).show();
			}
		});
	}

	
	  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
  private ServiceConnection conn=new ServiceConnection(){
	  
   @Override
    public void onServiceDisconnected(ComponentName name) {
			Log.i(TAG,"MainActivity onServiceDisconnected invoked!"); 
		
		}
		
      @Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		Log.i(TAG,"MainActivity onServiceConnected invoked!");
		myBinder=(MyBinder)service;
	}


  };
  
  
}
