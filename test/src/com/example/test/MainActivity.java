package com.example.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements android.view.View.OnClickListener {
	private TextView tv;
	private EditText et;
	private Button startactivity;
	private static final int UPGRADE_UI = 1;
	protected static final String TAG = null;
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case UPGRADE_UI :
				Toast.makeText(MainActivity.this, "这是由mainActivity打印", Toast.LENGTH_SHORT).show();
				break;
			}
			super.handleMessage(msg);
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startactivity = (Button) findViewById(R.id.startactivity);
		startactivity.setOnClickListener(this);
//		Looper.prepare();
		Looper mLooper = Looper.myLooper();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(mLooper);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
		new Thread(){
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Looper.prepare();
				Looper looper = Looper.myLooper();
				Handler handler = new Handler(looper);
				Message msg = handler.obtainMessage();
				System.out.println("<<<<<<<<<<<<<<<<<<<<<<");
				System.out.println(looper);
				System.out.println("<<<<<<<<<<<<<<<<<<<<<<");
				msg.what = UPGRADE_UI;
				handler.sendMessage(msg);
			};
		}.start();
	}

	public void click(View view){
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("我是对话框");
		builder.setMessage("我是内容");
		builder.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		builder.show();
		
	
	}
	
	
	@Override
	protected void onPause() {
		System.out.println("onpause");
		super.onPause();
	}

	@Override
	protected void onResume() {
		System.out.println("onresume");
		super.onResume();
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(MainActivity.this,SecondActivity.class);
		startActivity(intent);
	}
	
	public void test(View v){
		switch(v.getId()){
		case R.drawable.ic_launcher:
			break;
		}
	}
}
