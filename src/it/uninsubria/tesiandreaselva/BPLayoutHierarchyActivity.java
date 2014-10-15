package it.uninsubria.tesiandreaselva;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BPLayoutHierarchyActivity extends Activity {

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
		TextView tv = new TextView(this);
		tv.setId(1);
		tv.setText("Best Practices Layout Hierarchy");

		rootLayout.addView(tv, lpViewTop);

		RelativeLayout.LayoutParams lpViewBelow = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		lpViewBelow.addRule(RelativeLayout.BELOW, tv.getId());

		for (int i = 2; i < 45; i++) {

			tv = new TextView(this);
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
		ViewServer.get(this).addWindow(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		ViewServer.get(this).removeWindow(this);
	}

	@Override
	public void onResume() {
		super.onResume();
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
