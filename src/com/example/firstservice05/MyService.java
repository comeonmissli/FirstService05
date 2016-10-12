package com.example.firstservice05;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	private static final String TAG = "Myservice";
    private int count=0;
	private boolean quit=false;
	private MyBinder myBinder=new MyBinder();
    public class MyBinder extends Binder{
		public MyBinder(){
			Log.i(TAG,"MyService Onstructure invoked!");
		}
		public int getCount(){
			return count;
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		Log.i(TAG,"MyService onBind invoked!");
		return myBinder;
	}

	@Override
	public void onCreate() {
		Log.i(TAG,"MyService onCreat invoked!");
		super.onCreate();
		new Thread(){
			public void run(){
				while(!quit){
					try {
						Thread.sleep(500);
						count++;	
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}.start();
	}

	@Override
	public void onDestroy() {
		Log.i(TAG,"MyService onDestroy invoked!");
		super.onDestroy();
		quit=true;
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG,"MyService onStartCommand invoked!");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.i(TAG,"MyService onUnbind invoked!");
		return super.onUnbind(intent);
	}

	@Override
	public void onRebind(Intent intent) {
		Log.i(TAG,"MyService onRebind invoked!");
		super.onRebind(intent);
	}
}
