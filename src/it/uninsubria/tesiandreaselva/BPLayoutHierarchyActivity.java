package it.uninsubria.tesiandreaselva;

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
import android.widget.ScrollView;
import android.widget.TextView;

public class BPLayoutHierarchyActivity extends Activity {

	private TextView mMemoryTextView;
	private TextView mFreeTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ScrollView scroll = new ScrollView(this);
		ScrollView.LayoutParams srlLayoutParam = new ScrollView.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		
		RelativeLayout rootLayout = new RelativeLayout(this);
		RelativeLayout.LayoutParams relLayoutParam = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		rootLayout.setPadding(25, 25, 0, 0);

		RelativeLayout.LayoutParams lpViewTop = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		lpViewTop.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		int color = Color.argb(255, getRed(), getGreen(), 0);
		TextView tv = new TextView(this);
		tv.setBackgroundColor(color);
		tv.setId(1);
		tv.setText("Best Practices Layout Hierarchy");

		rootLayout.addView(tv, lpViewTop);

		RelativeLayout.LayoutParams lpViewBelow = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		lpViewBelow.addRule(RelativeLayout.BELOW, tv.getId());

		for (int i = 2; i < 43; i++) {

			tv = new TextView(this);
			color = Color.argb(255, getRed(), getGreen(), 0);
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
		scroll.addView(rootLayout, relLayoutParam);
		setContentView(scroll, srlLayoutParam);
		
		ActionBar mActionBar = getActionBar();
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater = LayoutInflater.from(this);

		View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
		TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
		mTitleTextView.setText("BP Layout Hierarchy");
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

	private int red=0;
	private int green=255;
	private int state=1;
	
	private int getRed() {
		if(state==1){
			red=red+11;
			if(red>255){
				red=255;
				state = 2;
			}
			return red;
		}
		return red;
	}

	private int getGreen() {
		if(state==1) return green;
		else {
			green = green-11;
			if(green<0)green=0;
			return green;
		}
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
