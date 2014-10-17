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
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NBPLayoutHierarchyActivity extends Activity {

	private TextView mMemoryTextView;
	private TextView mFreeTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout rootLayout = new LinearLayout(this);
		rootLayout.setOrientation(LinearLayout.VERTICAL);
		LayoutParams linLayoutParam = new LayoutParams(
				LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		rootLayout.setPadding(25, 25, 0, 0);
		Random rnd = new Random();
		int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
		rootLayout.setBackgroundColor(color);

		LayoutParams lpView = new LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		TextView tv = new TextView(this);
		tv.setText("Not Best Practices Layout Hierarchy");

		rootLayout.addView(tv, lpView);
		LinearLayout fatherLayout = rootLayout;

		for (int i = 0; i < 25; i++) {

			LinearLayout childLayout = new LinearLayout(this);
			childLayout.setOrientation(LinearLayout.VERTICAL); 
			color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
			childLayout.setBackgroundColor(color);
			fatherLayout.addView(childLayout, linLayoutParam);
			tv = new TextView(this);
			tv.setText("Not Best Practices Layout Hierarchy");
			childLayout.addView(tv, lpView);
			fatherLayout = childLayout;

		}
		
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
		
		
		setContentView(rootLayout, linLayoutParam);
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
		getMenuInflater().inflate(R.menu.nbplayout_hierarchy, menu);
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
