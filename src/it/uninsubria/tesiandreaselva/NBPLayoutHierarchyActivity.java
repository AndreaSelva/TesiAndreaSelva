package it.uninsubria.tesiandreaselva;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NBPLayoutHierarchyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout rootLayout = new LinearLayout(this);
		rootLayout.setOrientation(LinearLayout.VERTICAL);
		LayoutParams linLayoutParam = new LayoutParams(
				LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		rootLayout.setPadding(25, 25, 0, 0);

		LayoutParams lpView = new LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		TextView tv = new TextView(this);
		tv.setText("Not Best Practices Layout Hierarchy");

		rootLayout.addView(tv, lpView);
		LinearLayout fatherLayout = rootLayout;

		for (int i = 0; i < 43; i++) {

			LinearLayout childLayout = new LinearLayout(this);
			childLayout.setOrientation(LinearLayout.VERTICAL);
			fatherLayout.addView(childLayout, linLayoutParam);
			tv = new TextView(this);
			tv.setText("Not Best Practices Layout Hierarchy");
			childLayout.addView(tv, lpView);
			fatherLayout = childLayout;

		}
		
		rootLayout.setBackgroundColor(0xFFFF4C4C);
		setContentView(rootLayout, linLayoutParam);
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
