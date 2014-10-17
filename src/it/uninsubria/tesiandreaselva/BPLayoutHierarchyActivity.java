package it.uninsubria.tesiandreaselva;

import java.util.Random;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BPLayoutHierarchyActivity extends Activity {

	private TextView mMemoryTextView;
	private TextView mFreeTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		RelativeLayout rootLayout = new RelativeLayout(this);
		RelativeLayout.LayoutParams relLayoutParam = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		rootLayout.setPadding(25, 25, 0, 0);

		RelativeLayout.LayoutParams lpViewTop = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		lpViewTop.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		Random rnd = new Random();
		int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
		TextView tv = new TextView(this);
		tv.setBackgroundColor(color);
		tv.setId(1);
		tv.setText("Best Practices Layout Hierarchy");

		rootLayout.addView(tv, lpViewTop);

		RelativeLayout.LayoutParams lpViewBelow = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		lpViewBelow.addRule(RelativeLayout.BELOW, tv.getId());

		for (int i = 2; i < 100; i++) {

			tv = new TextView(this);
			color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
			tv.setBackgroundColor(color);
			tv.setId(i);
			tv.setText("Best Practices Layout Hierarchy");
			rootLayout.addView(tv, lpViewBelow);
			lpViewBelow = new RelativeLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			lpViewBelow.addRule(RelativeLayout.BELOW, tv.getId());

		}
		rootLayout.setBackgroundColor(0xFF66FF66);
		setContentView(rootLayout, relLayoutParam);
		
		ActionBar mActionBar = getActionBar();
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater = LayoutInflater.from(this);

		View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
		TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
		mTitleTextView.setText("All Practices");
		mMemoryTextView = (TextView) mCustomView.findViewById(R.id.memory_text);
		
		mMemoryTextView.setText("Memory Usage: "+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())+" KB");
		mFreeTextView = (TextView) mCustomView.findViewById(R.id.free_text);
		mFreeTextView.setText("Memory Free: "+Runtime.getRuntime().freeMemory()+" KB");

		ImageButton imageButton = (ImageButton)mCustomView.findViewById(R.id.imageButton);
		imageButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				mMemoryTextView.setText("Memory Usage: "+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())+" KB");
				mFreeTextView.setText("Memory Free: "+Runtime.getRuntime().freeMemory()+" KB");
			}
		});

		mActionBar.setCustomView(mCustomView);
		mActionBar.setDisplayShowCustomEnabled(true);
		
		
		ViewServer.get(this).addWindow(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mMemoryTextView.setText("Memory Usage: "+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())+" KB");
		mFreeTextView.setText("Memory Free: "+Runtime.getRuntime().freeMemory()+" KB");
		ViewServer.get(this).removeWindow(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		mMemoryTextView.setText("Memory Usage: "+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())+" KB");
		mFreeTextView.setText("Memory Free: "+Runtime.getRuntime().freeMemory()+" KB");
		ViewServer.get(this).setFocusedWindow(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bplayout_hierarchy, menu);
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
