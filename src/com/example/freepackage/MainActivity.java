package com.example.freepackage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	private final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(TAG, "Environment.getExternalStorageDirectory()=" + Environment.getExternalStorageDirectory());
		Log.d(TAG, "getCacheDir().getAbsolutePath()=" + getCacheDir().getAbsolutePath());
		showDownLoadDialog();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void showDownLoadDialog() {
		new AlertDialog.Builder(this).setTitle("确认").setMessage("是否下载？").setPositiveButton("是", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.d(TAG, "onClick 1 = " + which);
				doDownLoadWork();
			}
		}).setNegativeButton("否", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.d(TAG, "onClick 2 = " + which);
			}
		}).show();
	}

	public void showUnzipDialog() {
		new AlertDialog.Builder(this).setTitle("确认").setMessage("是否解压？").setPositiveButton("是", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.d(TAG, "onClick 1 = " + which);
				doZipExtractorWork();
			}
		}).setNegativeButton("否", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.d(TAG, "onClick 2 = " + which);
			}
		}).show();
	}

	public void doZipExtractorWork() {
		ZipExtractorTask task = new ZipExtractorTask("/storage/emulated/legacy/test.zip",
				"/storage/emulated/legacy/", this, true);
		task.execute();
		Toast.makeText(this, "Free successful.", Toast.LENGTH_LONG).show();
	}

	private void doDownLoadWork() {
		DownLoaderTask task = new DownLoaderTask("http://xgftea.hongweipeng.com/Public/test.zip", "/storage/emulated/legacy/",
				this);
		task.execute();
	}

}
