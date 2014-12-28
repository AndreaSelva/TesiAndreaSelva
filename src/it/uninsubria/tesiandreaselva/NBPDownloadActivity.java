package it.uninsubria.tesiandreaselva;

import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class NBPDownloadActivity extends Activity {

	private TextView mMemoryTextView;
	private TextView mFreeTextView;
	private TextView timer;
	private TextView status;
	private int count = 0;
	private int realCount = 5;
	private List<Map<String, String>> DB;
	private ProgressBar bar;
	private boolean state = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nbpdownload);

		timer = (TextView) findViewById(R.id.NBPcounter);
		status = (TextView) findViewById(R.id.NBPstatus);
		bar = (ProgressBar) findViewById(R.id.NBPprogressBar);

		Thread t = new Thread() {

			@Override
			public void run() {
				try {
					while (!isInterrupted()) {
						Thread.sleep(1000);
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								updateCounter(timer);
							}
						});
					}
				} catch (InterruptedException e) {
				}
			}
		};
		t.start();
		android.os.Debug.startMethodTracing("NBPDownload");

		ActionBar mActionBar = getActionBar();
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);

		LayoutInflater mInflater = LayoutInflater.from(this);
		View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
		TextView mTitleTextView = (TextView) mCustomView
				.findViewById(R.id.title_text);
		mTitleTextView.setText("All Practices");
		mMemoryTextView = (TextView) mCustomView.findViewById(R.id.memory_text);

		mMemoryTextView.setText("Memory Usage: "
				+ (Runtime.getRuntime().totalMemory() - Runtime.getRuntime()
						.freeMemory()) + " KB");
		mFreeTextView = (TextView) mCustomView.findViewById(R.id.free_text);
		mFreeTextView.setText("Memory Free: "
				+ Runtime.getRuntime().freeMemory() + " KB");

		ImageButton imageButton = (ImageButton) mCustomView
				.findViewById(R.id.imageButton);
		imageButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				mMemoryTextView.setText("Memory Usage: "
						+ (Runtime.getRuntime().totalMemory() - Runtime
								.getRuntime().freeMemory()) + " KB");
				mFreeTextView.setText("Memory Free: "
						+ Runtime.getRuntime().freeMemory() + " KB");
			}
		});

		mActionBar.setCustomView(mCustomView);
		mActionBar.setDisplayShowCustomEnabled(true);
		status.setText("Preparazione download");
		ViewServer.get(this).addWindow(this);

	}

	private void updateCounter(TextView timer) {
		if (count == 5)
			download();
		if (realCount > 0) {
			timer.setText("" + realCount);
			realCount--;
		}
		count++;
		if (count == 5) {
			status.setText("Download in corso...");
			new ProgressTask().execute();
		}
		if (count == 10)
			android.os.Debug.stopMethodTracing();
	}

	private class ProgressTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			bar.setVisibility(View.VISIBLE);
			timer.setVisibility(View.GONE);
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			while (state) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			bar.setVisibility(View.GONE);
		}
	}

	private void download() {
		DB = MyNote.getData();
		DB = MyNote.getData();
		DB = MyNote.getData();
		status.setText("Download completato");
		state = false;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mMemoryTextView.setText("Memory Usage: "
				+ (Runtime.getRuntime().totalMemory() - Runtime.getRuntime()
						.freeMemory()) + " KB");
		mFreeTextView.setText("Memory Free: "
				+ Runtime.getRuntime().freeMemory() + " KB");
		ViewServer.get(this).removeWindow(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		mMemoryTextView.setText("Memory Usage: "
				+ (Runtime.getRuntime().totalMemory() - Runtime.getRuntime()
						.freeMemory()) + " KB");
		mFreeTextView.setText("Memory Free: "
				+ Runtime.getRuntime().freeMemory() + " KB");
		ViewServer.get(this).setFocusedWindow(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nbpdownload, menu);
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
