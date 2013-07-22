package com.example.test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

public class SecondActivity extends Activity {
	private static final int UPGRADE_UI = 1;
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch(msg.what){
			case UPGRADE_UI :
				Toast.makeText(SecondActivity.this, "这是由secondActivity打印", Toast.LENGTH_SHORT).show();
				break;
			}
			super.handleMessage(msg);
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
//		handler = new Handler();
		Message msg = handler.obtainMessage();
		msg.what = UPGRADE_UI;
		handler.sendMessage(msg);
		new Thread(){
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Looper.prepare();
				Handler handler = new Handler();
				Message msg = handler.obtainMessage();
				msg.what = UPGRADE_UI;
				handler.sendMessage(msg);
			};
		}.start();
	}
}
