package it.uninsubria.tesiandreaselva;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AllPracticesActivity extends Activity {

	private TextView mMemoryTextView;
	private TextView mFreeTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_practices);
		GridView gridview = (GridView) this.findViewById(R.id.griglia);
		String[] dati = new String[] { "Layout Hierarchy Best Practices",
				"Layout Hierarchy not Best Practices", "List Layout Hierarchy Best Practices",
				"List Layout Hierarchy Not Best Practices", "Multi Thread Best Practices", "Multi Thread Not Best Practices" };
		final int[] colors = new int[] { 0xFF66FF66, 0xFFFF4C4C };
		ListAdapter adapter = new ArrayAdapter<String>(this,
				R.layout.grid_cell, dati) {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				int colorPos = position % colors.length;
				view.setBackgroundColor(colors[colorPos]);
				return view;

			}
		};

		gridview.setAdapter(adapter);
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				if (position == 0) {
					Intent intent = new Intent(AllPracticesActivity.this,
							BPLayoutHierarchyActivity.class);
					startActivity(intent);
				} else if (position == 1) {
					Intent intent = new Intent(AllPracticesActivity.this,
							NBPLayoutHierarchyActivity.class);
					startActivity(intent);
				} else if (position == 2) {
					Intent intent = new Intent(AllPracticesActivity.this,
							BPListLayoutHierarchyActivity.class);
					startActivity(intent);
				} else if (position == 3) {
					Intent intent = new Intent(AllPracticesActivity.this,
							NBPListLayoutHierarchyActivity.class);
					startActivity(intent);
				} else if (position == 4) {
					Intent intent = new Intent(AllPracticesActivity.this,
							BPMultiThreadActivity.class);
					startActivity(intent);
				} else if (position == 5) {
					Intent intent = new Intent(AllPracticesActivity.this,
							NBPMultiThreadActivity.class);
					startActivity(intent);
				}
				
			}
		});
		
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
		getMenuInflater().inflate(R.menu.all_practices, menu);
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
