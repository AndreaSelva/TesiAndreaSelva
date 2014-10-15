package it.uninsubria.tesiandreaselva;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;



public class AllPracticesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_practices);
		GridView gridview = (GridView) this.findViewById(R.id.griglia);
		String[] dati = new String[]{ 
				"Layout Hierarchy Best Practices","Layout Hierarchy not Best Practices"
		};
		final int[] colors = new int[] { 0xFF66FF66, 0xFFFF4C4C };
		ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.grid_cell, dati) {
			
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
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	if(position == 0){
	        		Intent intent = new Intent(AllPracticesActivity.this, BPLayoutHierarchyActivity.class);
	        		startActivity(intent);
	        	}else if(position == 1){
	        		Intent intent = new Intent(AllPracticesActivity.this, NBPLayoutHierarchyActivity.class);
	        		startActivity(intent);
	        	}
	        }
	    });
		//View v = (View) gridview.getChildAt(0);
		//v.setBackgroundColor(0x66FF66);
		//v = (View) gridview.getChildAt(1);
		//v.setBackgroundColor(Color.parseColor("#FF4C4C"));
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